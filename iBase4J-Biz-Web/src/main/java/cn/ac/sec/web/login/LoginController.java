package cn.ac.sec.web.login;

import cn.ac.sec.entity.BizUser;
import cn.ac.sec.web.util.MessageUtil;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.util.CacheUtil;
import org.ibase4j.core.util.PropertiesUtil;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Pattern;

@RequestMapping("/app/user")
@RestController
public class LoginController extends AppBaseController<IBizProvider> {

    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private  static final Map<Long,String> login_map=new ConcurrentHashMap<>();

    @PostMapping("/message")
    @ApiOperation(value = "发送短信绑定短信")
    public Object sendBindMessage(@RequestParam String phoneNum, ModelMap modelMap) {
        if (CacheUtil.getCache ().exists ("MSG_TIME" + phoneNum) &&
                CacheUtil.getCache ().get ("MSG_TIME" + phoneNum) != null) {
            throw new BusinessException ("发送过于频繁，请稍后请求");
        }
        Integer shortMessage = (int) ((Math.random () * 9 + 1) * 100000);
        CacheUtil.getCache ().setnx ("MSG_TIME" + phoneNum, null);
        CacheUtil.getCache ().expire ("MSG_TIME" + phoneNum, 120);
        System.out.println (shortMessage);
        CacheUtil.getCache ().set (phoneNum, shortMessage, 60 * 60 * 15);
        MessageUtil.sendMessage (phoneNum, shortMessage.toString ());
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Object userRegister(@RequestParam String phoneNum,
                               @RequestParam Integer messageContent,
                               @RequestParam String password,
                               @RequestParam String account, ModelMap modelMap) {
        BizUser user = new BizUser ();
        user.setUserType ((byte) 2);
        user.setPhoneNum (phoneNum);
        user.setAccount (account);
        user.setPassword (SecurityUtil.encryptPassword (password));
        Integer cacheData = (Integer) CacheUtil.getCache ().get (phoneNum);
        if (cacheData == null) throw new BusinessException ("验证码错误");
        if (!cacheData.equals (messageContent)) throw new BusinessException ("验证码错误");
        Parameter parameter = new Parameter (getService (), "checkPhoneExist", user);
        if ((boolean) provider.execute (parameter).getResult ()) throw new BusinessException ("手机已注册过账号");
        parameter = new Parameter (getService (), "addUser", user);
        BizUser result = (BizUser) provider.execute (parameter).getResult ();
        Map<String, Object> resultMap = new HashMap<> ();
        resultMap.put ("user", result.getId ());
        String token = UUID.randomUUID ().toString ();
        CacheUtil.getCache ().set (token, result.getId (), 60 * 60 * 24 * 5);
        resultMap.put ("TOKEN", token);
        resultMap.put ("type", 2);
        login_map.put(result.getId(),token);
        return setSuccessModelMap (modelMap, resultMap);
    }

    @PostMapping(value = "/login", produces = "application/json")
    @ApiOperation(value = "app登录", consumes = "application/x-www-form-urlencoded")
    public Object login(@RequestParam String password,
                        @RequestParam String account
            , ModelMap modelMap) {
        BizUser user = new BizUser ();
        user.setPassword (password);
        if (Pattern.matches (REGEX_MOBILE, account)) user.setPhoneNum (account);
        else user.setAccount (account);
        Parameter parameter = new Parameter (getService (), "appLogin", user);
        BizUser result = (BizUser) provider.execute (parameter).getResult ();
        Map<String, Object> resultMap = new HashMap<> ();
        resultMap.put ("userId", result.getId ());
        resultMap.put ("userType", result.getUserType ());
        String token = UUID.randomUUID ().toString ().replace("-","");
        System.out.println(token);
        CacheUtil.getCache ().set (token, result.getId(), 60 * 60 * 24 * 5);
        resultMap.put ("TOKEN", token);
        BizUser tempUser=new BizUser();
        tempUser.setToken(result.getToken());
        tempUser.setAccount(result.getAccount());
        user.setId (user.getId ());
        /**
         * 处理多终端登录问题
         */
        if(result.getToken() != null && ! result.getToken().equals("")){
            if(CacheUtil.getCache().exists(result.getToken())){
                CacheUtil.getCache().del(result.getToken());
                Thread pushThread = new Thread(() -> {
                    Parameter threadParameter = new Parameter (getService (), "pushMessage", tempUser);
                    provider.execute (threadParameter);
                });
                pushThread.run();
            }
        }
        result.setToken(token);
        parameter = new Parameter(getService(),"updateUserByPrimaryKey",result);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap, resultMap);
    }


    @PostMapping(value = "/logout", produces = "application/json")
    @ApiOperation("用户登出")
    public Object logout(@RequestParam Long userId,HttpServletRequest request,
                          ModelMap modelMap) {
        String token = request.getHeader ("TOKEN");
        CacheUtil.getCache ().del (token);
        Parameter parameter = new Parameter(getService(),"getUserById",userId);
        BizUser result = (BizUser) provider.execute (parameter).getResult ();
        if(token.equals(result.getToken())){
            result.setToken("");
            parameter = new Parameter(getService(),"updateUserByPrimaryKey",result);
            provider.execute (parameter);
        }
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/bind")
    @ApiOperation(value = "用户绑定")
    public Object userRegister(@RequestParam String phoneNum, @RequestParam Integer messageContent, @RequestParam Long userId, ModelMap modelMap) {
        Integer cacheData = (Integer) CacheUtil.getCache ().get (phoneNum);
        if (cacheData == null) throw new BusinessException ("验证码错误");
        if (!cacheData.equals (messageContent)) throw new BusinessException ("验证码错误");
        Parameter parameter = new Parameter (getService (), "bindUser", userId, phoneNum);
        BizUser user = (BizUser) provider.execute (parameter).getResult ();
        if (user.getUserType ().equals ((byte) 1)) {
            user.setUserType ((byte) 3);
            parameter = new Parameter (getService (), "updateUser", user);
            provider.execute (parameter);
        }
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/changePassword")
    @ApiOperation(value = "忘记用户密码")
    public Object changePassword(@RequestParam String phoneNum,
                                 @RequestParam Integer messageContent,
                                 @RequestParam String password,
                                 ModelMap modelMap) {
        Integer cacheData = (Integer) CacheUtil.getCache ().get (phoneNum);
        BizUser user = new BizUser ();
        user.setPhoneNum (phoneNum);
        user.setPassword (SecurityUtil.encryptPassword (password));
        if (cacheData == null) throw new BusinessException ("验证码错误");
        if (!cacheData.equals (messageContent)) throw new BusinessException ("验证码错误");
        Parameter parameter = new Parameter (getService (), "updateUserByPhoneNum", user);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改密码")
    public Object updatePassword(ModelMap modelMap,
                                 @RequestParam Long userId,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword) {
        Parameter parameter = new Parameter (getService (), "getUserDetail", userId);
        BizUser user = (BizUser) provider.execute (parameter).getResult ();
        if (user.getPassword ().equals (SecurityUtil.encryptPassword (oldPassword))) {
            user.setPassword (SecurityUtil.encryptPassword (newPassword));
        } else throw new BusinessException ("您输入的旧密码错误，请重新输入");
        parameter = new Parameter (getService (), "updateUserByPrimaryKey", user);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @GetMapping("/servicePhoneNum")
    @ApiOperation("服务号码")
    public Object getServicePhoneNum(ModelMap modelMap) {
        String phoneNum = PropertiesUtil.getString ("phoneNum.service");
        System.out.println (phoneNum);
        return setSuccessModelMap (modelMap, phoneNum);
    }

    @Override
    public String getService() {
        return "bizUserService";
    }
}

