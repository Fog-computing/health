package cn.ac.sec.web;


import cn.ac.sec.entity.BizRemind;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app/remind")
public class AppRemindController extends AppBaseController<IBizProvider> {
    @Override
    public String getService() {
        return "remindService";
    }

    @PostMapping()
    @ApiOperation("添加提醒")
    public Object addRemind(@RequestBody BizRemind remind, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "createRemind", remind);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @DeleteMapping
    @ApiOperation("删除提醒")
    public Object deleteRemind(@RequestParam Long id, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "deleteRemindById", id);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @PutMapping
    @ApiOperation("修改提醒")
    public Object updateRemind(@RequestBody BizRemind remind, ModelMap modelMap) {
        remind.setRemindUpdateTime (new Date ());
        Parameter parameter = new Parameter (getService (), "updateById", remind);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @GetMapping("/list")
    @ApiOperation("获取提醒列表")
    public Object getRemind(@RequestParam Long userId, ModelMap modelMap,
                            @RequestParam Long parentId) {
        Parameter parameter = new Parameter (getService (), "getRemindListByUser", userId, parentId);
        List<BizRemind> remindList = (List<BizRemind>) provider.execute (parameter).getResult ();
        return setSuccessModelMap ("remindList", modelMap, remindList);
    }
}
