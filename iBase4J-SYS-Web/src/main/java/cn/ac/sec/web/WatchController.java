package cn.ac.sec.web;

import cn.ac.sec.entity.BizUser;
import cn.ac.sec.entity.BizWatch;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.BaseController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/biz/watch")
@Api
public class WatchController extends AbstractController<IBizProvider> {
    @Override
    public String getService() {
        return "watchService";
    }

    @DeleteMapping("/{id}")
    @ApiOperation (value="删除手表")
    public Object deleteWatch(@PathVariable Long id,ModelMap modelMap){
        Parameter parameter=new Parameter (getService (),"deleteWatch",id);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @PostMapping
    @ApiOperation (value = "添加手表")
    public Object createWatch(@RequestBody BizWatch watch, ModelMap modelMap){
        Parameter parameter;
        if(watch.getId ()==null) {
            watch.setAddUser (WebUtil.getCurrentUser ());
            parameter=new Parameter (getService (),"addWatch",watch);
        }
        else parameter=new Parameter (getService (),"updateWatch",watch);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @RequestMapping("detail/user/{userId}")
    @ApiOperation (value= "获取手表详情")
    public Object getWatchValue(@PathVariable Long userId,ModelMap modelMap){
        Parameter parameter=new Parameter (getService (),"getWatchByUser",userId);
        BizWatch watch= (BizWatch) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, watch);
    }


    @ApiOperation (value = "")
    @PutMapping(value = "/list",produces = "application/json",consumes = "application/json")
    public Object getUserList(ModelMap modelMap,@RequestBody PageTransport transport){
        Parameter parameter=new Parameter (getService (),"getWatchListWithUser",transport);
        Page<Map<String,Object>> userPage=(Page<Map<String,Object>>) provider.execute (parameter).getResultPage ();
        return setSuccessModelMap (modelMap,userPage);
    }
}
