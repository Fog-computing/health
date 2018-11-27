package cn.ac.sec.web;


import cn.ac.sec.entity.BizRelationTagUser;
import cn.ac.sec.entity.BizUserTag;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("biz/userTag")
@RestController
public class UserTagController extends AbstractController<IBizProvider> {


    @Override
    public String getService() {
        return "userTagService";
    }

    @PutMapping("/detail/{id}")
    @ApiOperation ("获取详情")
    public Object getTagDetail(@PathVariable Integer id,ModelMap modelMap){
        Parameter parameter = new Parameter (getService (),"getUserTagById",id);
        BizUserTag userTag = (BizUserTag) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,userTag);
    }

    @GetMapping("/list")
    @ApiOperation ("获取标签列表")
    public Object getTagList(ModelMap modelMap){
        Parameter parameter =new Parameter (getService (),"getTagList");
        List<BizUserTag> tagList =(List<BizUserTag>)provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap,tagList);
    }

    @PutMapping("/page")
    @ApiOperation ("获取标签分页列表")
    public Object getTagPage(ModelMap modelMap, @RequestBody PageTransport pageTransport){
        Parameter parameter = new Parameter (getService (),"getTagPage",pageTransport);
        Page<BizUserTag> page=(Page<BizUserTag>)provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,page);
    }

    @PostMapping("/")
    @ApiOperation ("添加用户标签")
    public Object addUserTag(ModelMap modelMap,@RequestBody BizUserTag tag){
        Parameter parameter;
        if(tag.getId ()==null) parameter = new Parameter (getService (),"addTag",tag);
        else parameter = new Parameter (getService () ,"updateTag",tag);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }


    @GetMapping("/user/list/{userId}")
    @ApiOperation ("获取用户标签列表")
    public Object getUserTagPage(ModelMap modelMap, @PathVariable Long userId){
        Parameter parameter = new Parameter (getService (),"getTagListWithUser",userId);
        List<BizUserTag> tags= (List<BizUserTag>)provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,tags);
    }

    @DeleteMapping("/user/{relationId}")
    @ApiOperation ("删除用户标签")
    public Object deleteUserTag(ModelMap modelMap,@PathVariable Long relationId){
        Parameter parameter = new Parameter (getService (),"deleteUserTagRelationById",relationId);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @PutMapping("/user/{userId}/{tagId}")
    @ApiOperation ("添加用户标签")
    public Object addUserTagRelation(ModelMap modelMap,@PathVariable Long userId,@PathVariable Integer tagId){
        BizRelationTagUser relationTagUser = new BizRelationTagUser ();
        relationTagUser.setAddTime (new Date ());
        relationTagUser.setTag (tagId);
        relationTagUser.setAddUser (WebUtil.getCurrentUser ());
        relationTagUser.setUser (userId);
        Parameter parameter = new Parameter (getService (),"addRelationTag",relationTagUser);
        relationTagUser =(BizRelationTagUser) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,relationTagUser);
    }


}
