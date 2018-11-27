package cn.ac.sec.web;

import cn.ac.sec.entity.BizUser;
import cn.ac.sec.entity.BizUserRelationPhoneNum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.UploadUtil;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "app用户接口")
@RestController()
@RequestMapping("app/user")
public class UserController extends AppBaseController<IBizProvider> {
    @Override
    public String getService() {
        return "bizUserService";
    }

    @GetMapping
    @ApiOperation(value = "获取用户信息")
    public Object getUserDetail(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "getUserDetail", userId);
        BizUser user = (BizUser) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, user);
    }

    @PostMapping
    @ApiOperation(value = "修改用户信息")
    public Object updateUserDetail(@RequestBody BizUser user, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "updateUserByPrimaryKey", user);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @GetMapping("/relations")
    @ApiOperation(value = "获取关联用户信息")
    public Object getUserParent(@RequestParam Long userId, ModelMap modelMap) {
        modelMap.addAttribute ("listKey", "userList");
        Parameter parameter = new Parameter (getService (), "getUserByChild", userId);
        List<BizUser> userList = (List<BizUser>) provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap, userList);
    }

    @GetMapping("/phoneNums")
    @ApiOperation(value = "获取用户联系人")
    public Object getUserSOSPhoneNumList(@RequestParam Long userId, ModelMap modelMap) {
        modelMap.addAttribute ("listKey", "phoneNumList");
        Parameter parameter = new Parameter (getService (), "getUserPhoneNum", userId);
        List<BizUserRelationPhoneNum> phoneNumList = (List<BizUserRelationPhoneNum>) provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap, phoneNumList);
    }

    @GetMapping("/firstPhoneNum")
    @ApiOperation(value = "")
    public Object getUserSOSPhoneNum(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "getUserSOSPhoneNum", userId);
        String phoneNum = (String) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, phoneNum);
    }

    @PostMapping(value = "/updateAvatar", consumes = "multipart/form-data")
    @ApiOperation("上传头像")
    public Object updateAvatar(@RequestParam Long userId,
                               @RequestParam MultipartFile file,
                               @RequestHeader String TOKEN,
                               HttpServletRequest request, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImage (request, false, true);
        System.out.println (file);
        String filePath = null;
        if (fileNames.size () > 0) {
            BizUser param = new BizUser ();
            param.setId (userId);
            for (String fileName : fileNames) {
                filePath = "/app/upload/" + fileName;
                param.setAvator (filePath);
            }
            Parameter parameter = new Parameter (getService (), "updateUserByPrimaryKey", param);
            provider.execute (parameter);
            return setSuccessModelMap (modelMap, filePath);
        } else {
            setModelMap (modelMap, HttpCode.BAD_REQUEST);
            modelMap.put ("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }


}