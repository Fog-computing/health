package cn.ac.sec.web;


import cn.ac.sec.entity.BizUserRelationPhoneNum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value ="业务用户管理接口")
@RestController()
@RequestMapping("biz/relationPhoneNum")
public class UserRelationPhoneNumController  extends AbstractController<IBizProvider>{


    @Override
    public String getService() {
        return "userRelationPhoneNumService";
    }


    @ApiOperation ("获取用户联系人列表")
    @RequestMapping("/list/{userId}")
    public Object getRelationNumList(@PathVariable Long userId, ModelMap modelMap){
        Parameter parameters=new Parameter (getService (),"getPhoneNumListByUserId",userId);
        List<BizUserRelationPhoneNum> phoneNumList=(List<BizUserRelationPhoneNum>) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap,phoneNumList);
    }

    @ApiOperation ("获取用户联系人详细信息")
    @RequestMapping("/detail/{id}")
    public Object getRelationDetailById(@PathVariable Long id, ModelMap modelMap){
        Parameter parameters=new Parameter (getService (),"getPhoneNumById",id);
        BizUserRelationPhoneNum phoneNum=(BizUserRelationPhoneNum) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap,phoneNum);
    }


    @ApiOperation ("添加或修改用户联系人信息")
    @PostMapping("/add")
    public Object addOrUpdateUserRelation(@RequestBody BizUserRelationPhoneNum phoneNum, ModelMap modelMap){
        Parameter parameters;
        if(phoneNum.getId ()==null) parameters=new Parameter (getService (),"addUserPhoneNum",phoneNum);
        else parameters=new Parameter (getService (),"updateUserPhoneNum",phoneNum);

        Integer result=(Integer) provider.execute (parameters).getResult ();
        if(result==0)  return setSuccessModelMap (modelMap);
        if (result == 1) throw new BusinessException ("已经添加了过多的紧急联系人");
        if (result == 2) throw new BusinessException ("已经添加了过多的快速拨号号码");
        return null;
    }

    @ApiOperation ("删除用户联系人")
    @DeleteMapping("/{id}")
    public Object addOrUpdateUserRelation(@PathVariable Long id,ModelMap modelMap){
        Parameter parameters =new Parameter (getService (),"deleteUserPhoneNum",id);
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);
    }

}
