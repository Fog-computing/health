package cn.ac.sec.web;

import cn.ac.sec.entity.BizMessageAlarm;
import cn.ac.sec.entity.BizMessageAlarmType;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/biz/alarm")
public class AlarmController extends AbstractController<IBizProvider> {
    @Override
    public String getService() {
        return "alarmService";
    }


    @ApiOperation(value = "")
    @PutMapping(value = "/list", produces = "application/json", consumes = "application/json")
    public Object getAlarmList(ModelMap modelMap, @RequestBody PageTransport transport) {
        System.out.println (transport);
        Parameter parameter = new Parameter (getService (), "getAlarmList", transport);
        Page<Map<String, Object>> userPage = (Page<Map<String, Object>>) provider.execute (parameter).getResultPage ();
        return setSuccessModelMap (modelMap, userPage);
    }


    @ApiOperation(value = " 获取详情")
    @PutMapping(value = "/detail/{id}")
    public Object getAlarmDetail(ModelMap modelMap, @PathVariable Long id) {
        Parameter parameter = new Parameter (getService (), "getAlarmDetail", id);
        Map<String, Object> alarm = (Map<String, Object>) provider.execute (parameter).getResult ();
        BizMessageAlarm alarmData = (BizMessageAlarm) alarm.get ("alarm");
        if (alarmData.getStatus ().equals ((byte) 0))
            setProcessingAlarm ((BizMessageAlarm) alarm.get ("alarm"));
        return setSuccessModelMap (modelMap, alarm);
    }

    @ApiOperation(value = "获取报警类型别表")
    @PutMapping(value = "/type")
    public Object getAlarmDetail(ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "getAlarmTypes");
        List<BizMessageAlarmType> alarmTypes = (List<BizMessageAlarmType>) provider.execute (parameter).getResult ();
        Page<BizMessageAlarmType> page = new Page<> ();
        page.setRecords (alarmTypes);
        return setSuccessModelMap (modelMap, page);
    }

    @ApiOperation(value = "获取未处理报警数")
    @GetMapping(value = "/count")
    public Object getAlarmCount(ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "getAlarmCount");
        Integer count = (Integer) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, count);
    }


    @ApiOperation(value = "获取历史记录详情")
    @PutMapping(value = "/history/detail//{id}")
    public Object getHistoryAlarmDetail(ModelMap modelMap, @PathVariable Long id) {
        Parameter parameter = new Parameter (getService (), "getAlarmDetail", id);
        Map<String, Object> alarm = (Map<String, Object>) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, alarm);
    }

    @ApiOperation (value = "查看是否改用户有未处理的信息")
    @PutMapping(value = "/checkNotProcess")
    public Object getNotProcess(ModelMap modelMap){
        Parameter parameter=new Parameter (getService (),"checkNotProcess",getCurrUser ());
        Long result=(Long)provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap,result);
    }

    @ApiOperation(value = "查看历史报警记录")
    @PutMapping(value = "/history/{startTime}/{endTime}/{status}")
    public Object getAlarmHistory(ModelMap modelMap, @RequestBody PageTransport transport,
                                  @PathVariable Long startTime,
                                  @PathVariable Long endTime,
                                  @PathVariable Byte status) {
        System.out.println (transport);
        Parameter parameter = new Parameter (getService (), "getAlarmHistoryList",
                transport, new Date (startTime), new Date (endTime), status);
        Page<Map<String, Object>> alarmHistoryList = (Page<Map<String, Object>>) provider.execute (parameter).getResultPage ();
        return setSuccessModelMap (modelMap, alarmHistoryList);
    }

    @ApiOperation(value = "" +
            "" +
            "获取详情")
    @PutMapping(value = "/latest")
    public Object getAlarmLatest(ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "getLatestAlarm");
        Map<String, Object> alarm = (Map<String, Object>) provider.execute (parameter).getResult ();
        setProcessingAlarm ((BizMessageAlarm) alarm.get ("alarm"));
        return setSuccessModelMap (modelMap, alarm);
    }

    @ApiOperation(value = "处理报警")
    @PostMapping(value = "/process")
    public Object processAlarm(ModelMap modelMap, @RequestBody BizMessageAlarm alarm) {
        //2代表直接处理
        if(alarm.getStatus ()!=null)alarm.setStatus ((byte)3);
        //3代表转发
        else alarm.setStatus ((byte)2);
        System.out.println (alarm.getStatus ());
        alarm.setProcessUser (getCurrUser ());
        alarm.setProcessDate (new Date ());
        Parameter parameter = new Parameter (getService (), "processAlarm", alarm);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    private BizMessageAlarm setProcessingAlarm(BizMessageAlarm alarm){
        alarm.setProcessUser (getCurrUser ());
        alarm.setStatus ((byte)1);
        Parameter parameter =  new Parameter (getService (),"updateProcessing",alarm);
        provider.execute (parameter);
        return alarm;
    }

}
