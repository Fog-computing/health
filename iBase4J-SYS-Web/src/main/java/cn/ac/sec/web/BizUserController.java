package cn.ac.sec.web;

import cn.ac.sec.entity.*;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.collections.map.HashedMap;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.DateUtil;
import org.ibase4j.core.util.SecurityUtil;
import org.ibase4j.core.util.UploadUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.ChartData;
import org.ibase4j.provider.IBizProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(value ="业务用户管理接口")
@RestController()
@RequestMapping("biz/user")
public class BizUserController extends AbstractController<IBizProvider> {

    @Override
    public String getService() {
        return "bizUserService";
    }


    @ApiOperation(value = "获取用户健康档案")
    @PutMapping (value = "/file/{userId}",produces = "application/json",consumes = "application/json")
    public Object getUserFile(ModelMap modelMap, @PathVariable Long userId, @RequestBody Map<String, Date> dateMap) {
        System.out.println (dateMap);
        Parameter parameter = new Parameter (getService (), "userFile", userId, dateMap);
        Map<String,Object> userFile=(Map<String,Object>) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,userFile);
    }


    @ApiOperation(value = "获取用户列表")
    @PutMapping (value = "/list",produces = "application/json",consumes = "application/json")
    public Object getUserList(ModelMap modelMap,@RequestBody PageTransport transport){
        Parameter parameter = new Parameter (getService (), "getUserListAll", transport);
        Page<BizUser> userPage=(Page<BizUser>) provider.execute (parameter).getResultPage ();
        return setSuccessModelMap (modelMap,userPage);
    }

    @ApiOperation(value = "获取带标签用户列表")
    @PutMapping (value = "/list/tags",produces = "application/json",consumes = "application/json")
    public Object getUserWithTagsList(ModelMap modelMap,@RequestBody PageTransport transport){
        Parameter parameter=new Parameter (getService (),"userWithTags",transport);
        Page<Map<String,Object>> userPage=(Page<Map<String, Object>>) provider.execute (parameter).getResultPage ();
        return setSuccessModelMap (modelMap,userPage);
    }

    @ApiOperation(value = "通过是否有专业处理获取用户")
    @PutMapping(value = "/profession/list", produces = "application/json", consumes = "application/json")
    public Object getProfessionUserWithTagsList(ModelMap modelMap, @RequestBody PageTransport transport) {
        Parameter parameter = new Parameter (getService (), "professionUserWithTags", transport);
        Page<Map<String, Object>> userPage = (Page<Map<String, Object>>) provider.execute (parameter).getResultPage ();
        return setSuccessModelMap (modelMap, userPage);
    }

    @ApiOperation(value = "修改用户租期")
    @PostMapping(value = "/updateSub", consumes = "application/json")
    public Object updateUserSubscription(ModelMap modelMap, @RequestBody BizUserSubscription subscription) {
        Parameter parameter;
        parameter = new Parameter (getService (), "updateUserSubscription");
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation (value= "后台修改、编辑老人用户")
    @PostMapping(value = "",consumes = "application/json")
    public Object updateUser(ModelMap modelMap,@RequestBody BizUser user){
        Parameter parameter;
        if(user.getId ()!=null) {
            if (!"".equals (user.getPassword ())) user.setPassword (SecurityUtil.encryptPassword (user.getPassword ()));
            else user.setPassword (null);
            user.setUpdatedBy (WebUtil.getCurrentUser ());
            user.setUpdatedDatetime (new Date());
            parameter  = new Parameter (getService (),"updateUserByPrimaryKey",user);
        }
        else {
            user.setPassword (SecurityUtil.encryptPassword (user.getPassword ()));
            user.setCreatedBy (WebUtil.getCurrentUser ());
            user.setCreatedDatetime (new Date());
            user.setUserType ((byte)1);
            parameter = new Parameter (getService (),"addUser",user);
        }
        user=(BizUser) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,user);
    }

    @ApiOperation(value = "获取用户详情")
    @PutMapping(value="/detail/{userId}")
    public Object getUserById(ModelMap modelMap,@PathVariable Long userId){
        Parameter parameter =new Parameter (getService (),"getUserById",userId);
        BizUser user=(BizUser) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,user);
    }

    @ApiOperation (value = "后台删除用户")
    @RequestMapping(value="/delete/{userId}")
    public Object deleteUser(ModelMap modelMap, @PathVariable Long userId){
        Parameter  parameter  = new Parameter (getService (),"deleteUser",userId);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation (value="更新用户信息")
    @PostMapping
    public Object editUser(ModelMap modelMap,@RequestBody BizUser user){
        Parameter parameter= new Parameter (getService (),"updateUser",user);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation (value="获取用户联系人信息")
    @RequestMapping("/relation_num/{userId}")
    public Object getQuickNum(ModelMap modelMap,@PathVariable Long userId){
        Parameter parameter =new Parameter (getService (),"getQuickNums",userId);
        List<BizUserRelationPhoneNum> list= (List<BizUserRelationPhoneNum>)  provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,list);
    }


    @ApiOperation (value="添加用户联系人信息")
    @RequestMapping("/relation_num/add")
    public Object putSosNum(ModelMap modelMap,@RequestBody BizUserRelationPhoneNum phoneNum){
        Parameter parameter =new Parameter (getService (),"addSosNum",phoneNum);
        provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation (value="获得用户标签列表")
    @RequestMapping("/userTag/{userId}")
    public Object getUserTag(ModelMap modelMap,@PathVariable Long userId){
        Parameter parameter = new Parameter (getService (),"getUserTags",userId);
        List<BizUserTag> userTags= (List<BizUserTag>) provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap,userTags);
    }

    @ApiOperation (value="添加用户标签")
    @RequestMapping("/userTag/add")
    public Object addUserTag(ModelMap modelMap, @RequestBody BizRelationTagUser tagUser){
        Parameter parameter = new Parameter (getService (),"addUserTags",tagUser);
        List<BizUserTag> tags=(List<BizUserTag>)provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap,tags);
    }

    @ApiOperation (value = "删除用户标签")
    @DeleteMapping("/userTag/delete/{id}")
    public Object deleteUserTag(ModelMap modelMap,@PathVariable Long id){
        Parameter parameter = new Parameter (getService (),"deleteUserRelation",id);
        List<BizUserTag> tags=(List<BizUserTag>)provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap,tags);
    }

    @ApiOperation (value="获取用户跌倒设备")
        @PutMapping("/fallDevice/{userId}")
    public Object getFallDownDevice(ModelMap modelMap,@PathVariable Long userId){
        Parameter parameter = new Parameter ("fallDeviceService","getFallDeviceByUser",userId);
        BizFallDevice fallDevice=(BizFallDevice)provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,fallDevice);
    }

    @ApiOperation (value = "添加或修改用户跌倒设备")
    @PostMapping("/fallDevice")
    public Object updateFallDownDevice(ModelMap modelMap,@RequestBody BizFallDevice fallDevice){
        Parameter parameter;
        if (fallDevice.getId () == null) parameter = new Parameter ("fallDeviceService", "addFallDevice", fallDevice);
        else parameter = new Parameter ("fallDeviceService","updateUserFallDevice",fallDevice);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation (value="获取用户标签列表")
    @PutMapping("/tags/total/{userId}")
    public Object getShowTags(ModelMap modelMap,@PathVariable Long userId){
        Parameter parameter = new Parameter ("userTagService","tagMap",userId);
        Map<String, List<BizUserTag>> tagsMap = (Map<String, List<BizUserTag>>) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, tagsMap);
    }


    @ApiOperation(value = "删除用户标签")
    @DeleteMapping("/tags/detail")
    public Object deleteUserTag(ModelMap modelMap, @RequestBody BizRelationTagUser relationTagUser) {
        Parameter parameter = new Parameter ("userTagService",
                "deleteUserTagRelationByUserId", relationTagUser);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation(value = "添加用户标签")
    @PostMapping("/tags/detail")
    public Object addUserTagRelation(ModelMap modelMap, @RequestBody BizRelationTagUser relationTagUser) {
        Parameter parameter = new Parameter ("userTagService",
                "addRelationTag", relationTagUser);
        relationTagUser.setAddUser (getCurrUser ());
        relationTagUser.setAddTime (new Date ());
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping(value = "/update/pic/{userId}")
    public Object updateAvatar(@PathVariable Long userId ,
                               HttpServletRequest request, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImageData(request);
        if (fileNames.size() > 0) {
            BizUser param = new BizUser ();
            param.setId(userId);
            for (String fileName : fileNames) {
                String filePath = "/upload/" + fileName;
//				System.out.println (request.getRequestURI ());
//				System.out.println (request.getRequestURL ().toString ());
//				System.out.println (UploadUtil.getUploadDir (request));
//				String avatar = UploadUtil.remove2DFS("sysUser", "U" + param.getId(), filePath).getRemotePath();
                param.setPicture (filePath);
            }
            Parameter parameter  = new Parameter (getService (),"updateUserByPrimaryKey",param);
            provider.execute (parameter);
            return setSuccessModelMap (modelMap,param);

        } else {
            setModelMap(modelMap, HttpCode.BAD_REQUEST);
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

    @ApiOperation (value="获得注册用户折线图数据")
    @RequestMapping(value="/statistics",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object>  account(String type,String time){
        if(type.equals("month")){
            return findRegisterCountByMonth(time);
        }else if (type.equals("week")){
            return findRegisterCountByWeek(time);
        }else if(type.equals("day")){
            return findRegisterCountByDay(time);
        }else {
            return findRegisterCountCurrenYear();
        }
    }

    private Map<String, Object> findRegisterCountByMonth(String time){
        Parameter parameter = new Parameter (getService (), "getRegisterCountByMonth", time);
        List<ChartData> datas=(List<ChartData>) provider.execute (parameter).getResult();
        List<List<?>> series = new ArrayList<List<?>>();
        List<List<?>> seriesData = new ArrayList<List<?>>();
        for (int i = 0; i < 12; i++) {
            int y = 0;
            for (ChartData data : datas) {
                if (data.getX() == (i+1)) {
                    y = data.getY();
                    break;
                }
            }
            series.add(Arrays.asList(i + 1, "" + (i+1)));
            seriesData.add(Arrays.asList(i + 1, y));
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("series", series);
        result.put("seriesData", seriesData);

        return result;
    }

    private Map<String, Object> findRegisterCountByWeek(String time){
        Parameter parameter = new Parameter (getService (), "getRegisterCountByWeek", time);
        List<ChartData> datas=(List<ChartData>) provider.execute (parameter).getResult();
        List<List<?>> series = new ArrayList<List<?>>();
        List<List<?>> seriesData = new ArrayList<List<?>>();
        for (int i = 0; i < 53; i++) {
            int y = 0;
            for (ChartData data : datas) {
                if (data.getX() == (i+1)) {
                    y = data.getY();
                    break;
                }
            }
            series.add(Arrays.asList(i + 1, "" + (i+1)));
            seriesData.add(Arrays.asList(i + 1, y));
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("series", series);
        result.put("seriesData", seriesData);

        return result;
    }

    private Map<String, Object> findRegisterCountByDay(String time){
        String[] str=time.split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        List<Integer> list =new ArrayList<Integer>();
        list.add(year);
        list.add(month);
        Parameter parameter = new Parameter (getService (), "getRegisterCountByDay", list);
        List<ChartData> datas=(List<ChartData>) provider.execute (parameter).getResult();
        List<List<?>> series = new ArrayList<List<?>>();
        List<List<?>> seriesData = new ArrayList<List<?>>();
        for (int i = 0; i < DateUtil.getDaysByYearMonth(year, month); i++) {
            int y = 0;
            for (ChartData data : datas) {
                if (data.getX() == (i + 1)) {
                    y = data.getY();
                    break;
                }
            }
            series.add(Arrays.asList(i + 1, "" + (i + 1)));
            seriesData.add(Arrays.asList(i + 1, y));
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("series", series);
        result.put("seriesData", seriesData);
        return result;
    }

    private  Map<String, Object> findRegisterCountCurrenYear(){

        return null;
    }

    @ApiOperation (value="获得老人用户性别分布数据")
    @RequestMapping(value="/genderDistributed",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>>  gender(){
        List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>() ;
        Parameter parameter = new Parameter (getService (), "getMaleAmount", 0);
        Long maleAmount = provider.execute (parameter).getResultLong();
        Parameter param = new Parameter (getService (), "getMaleAmount", 1);
        Long femaleAmount = provider.execute (param).getResultLong();
        Map<String,Object> male = new HashMap<String,Object>();
        male.put("name","男");
        male.put("value",maleAmount);
        Map<String,Object> female = new HashMap<String,Object>();
        female.put("name","女");
        female.put("value",femaleAmount);
        list.add(male);
        list.add(female);
        return list;
    }

    @ApiOperation (value="获得老人用户年龄分布数据")
    @RequestMapping(value="/ageDistributed",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>>  age(){
        List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>() ;
        for (int i=50;i<100;i=i+10){
            List<Integer> parm = new ArrayList<Integer>();
            parm.add(i); parm.add(i+9);
            Parameter parameter = new Parameter (getService (), "getAgeAmount",parm);
            Long ageAmount = provider.execute (parameter).getResultLong();
            Map<String,Object> age = new HashMap<String,Object>();
            age.put("name",i+"~"+(i+9)+"岁");
            age.put("value",ageAmount);
            list.add(age);
        }
            Map<String,Object> otherAge = new HashMap<String,Object>();
            Parameter parameter = new Parameter (getService (), "getOtherAgeAmount");
            Long otherAgeAmount = provider.execute (parameter).getResultLong();
            otherAge.put("name","其他");
            otherAge.put("value",otherAgeAmount);
            list.add(otherAge);
        return list;
    }

    @ApiOperation (value="获得老人用户标签分布数据")
    @RequestMapping(value="/tagDistributed",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Map<String,Object>>  tag(){
        List<Map<String,Object>>  list = new ArrayList<Map<String,Object>>() ;
        Parameter parameter =new Parameter ("userTagService","getTagList");
        List<BizUserTag> tagList =(List<BizUserTag>)provider.execute (parameter).getResultList ();
        for(BizUserTag bizUserTag : tagList){
            Map<String,Object> tag = new HashMap<String,Object>();
            Parameter param = new Parameter (getService (), "getTagAmount" ,bizUserTag.getId());
            Long tagAmount = provider.execute (param).getResultLong();
            tag.put("name",bizUserTag.getTagName());
            tag.put("value",tagAmount);
            list.add(tag);
        }
        return list;
    }
    
}
