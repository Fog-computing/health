package cn.ac.sec.web;

import cn.ac.sec.entity.BizMessageAlarm;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/alarm")
public class AppAlarmController extends AppBaseController<IBizProvider> {

    @ApiOperation(value = "")
    @GetMapping(value = "/list")
    public Object getAlarmList(ModelMap modelMap, @RequestParam Long userId) {
        Parameter parameter = new Parameter (getService (), "getAlarmListByUser", userId);
        List<Map<String, Object>> list = (List<Map<String, Object>>) provider.execute (parameter).getResultList ();
        System.out.println ();
        modelMap.addAttribute ("listKey", "alarmList");
        return setSuccessModelMap (modelMap, list);
    }


    @ApiOperation(value = "")
    @GetMapping(value = "/detail")
    public Object getAlarmDetail(ModelMap modelMap, @RequestParam Long id) {
        Parameter parameter = new Parameter (getService (), "getAlarmDetailWithUserById", id);
        Map<String, Object> detail = (Map<String, Object>) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, detail);
    }

    @ApiOperation(value = "上传报警信息")
    @PostMapping(value = "")
    public Object addAlarm(ModelMap modelMap, @RequestBody BizMessageAlarm alarm) {
        if (alarm.getType ().equals ((byte) 3)) alarm.setType ((byte) 5);
        else alarm.setType ((byte) 14);
        Parameter parameter = new Parameter (getService (), "addAlarm", alarm);
        provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap);
    }

    @Override
    public String getService() {
        return "alarmService";
    }
}
