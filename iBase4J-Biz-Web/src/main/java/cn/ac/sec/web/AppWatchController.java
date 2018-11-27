package cn.ac.sec.web;

import cn.ac.sec.entity.BizLogWatch;
import cn.ac.sec.entity.BizMessagePosition;
import cn.ac.sec.entity.BizWatch;
import cn.ac.sec.entity.messure.Base;
import cn.ac.sec.entity.messure.Sleep;
import cn.ac.sec.protocol.message.OutMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/watch")
@Api(value = "手表配置接口", description = "手表配置接口")
public class AppWatchController extends AppBaseController<IBizProvider> {
    private static final String serviceForWatch = "watchService";

    @Override
    public String getService() {
        return "watchSettingService";
    }

    @GetMapping("/position")
    @ApiOperation("获取最近的一次位置信息")
    public Object getLastPosition(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getLastPosition", userId);
        BizMessagePosition position = (BizMessagePosition) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, position);
    }

    @GetMapping("/position/list")
    @ApiOperation("获取位置信息列表")
    public Object getPositionList(@RequestParam Long userId, @RequestParam Long startTime,
                                  @RequestParam Long endTime, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getPositionList", userId, new Date (startTime), new Date (endTime));
        List<BizMessagePosition> position = (List<BizMessagePosition>) provider.execute (parameters).getResultList ();
        return setSuccessModelMap ("positionList", modelMap, position);
    }

    @GetMapping("/step")
    @ApiOperation("获取今日记步数据")
    public Object getStepsToday(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getSteps", userId);
        Integer pedo = (Integer) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, pedo);
    }

    @GetMapping("/step/List")
    @ApiOperation("获取数据列表")
    public Object getStepsList(@RequestParam Long userId, @RequestParam Long startTime,
                               @RequestParam Long endTime, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getStepList", userId, new Date (startTime), new Date (endTime));
        List<Map<String, Object>> stepList = (List<Map<String, Object>>) provider.execute (parameters).getResult ();
        return setSuccessModelMap ("stepList", modelMap, stepList);
    }

    @GetMapping("/operate")
    @ApiOperation("获取操作记录列表")
    public Object getOperationListToday(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getOperationsByUser", userId);
        List<Map<String, Object>> optionList = (List<Map<String, Object>>) provider.execute (parameters).getResult ();
        return setSuccessModelMap ("operationList", modelMap, optionList);
    }

    @GetMapping("/operate/count")
    @ApiOperation("获取操作记录数")
    public Object getOperationCount(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getOperateCount", userId);
        Integer optionList = (Integer) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, optionList);
    }

    @GetMapping("/sleep")
    @ApiOperation("获取睡眠数据")
    public Object getSleepInfoYesterday(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getLatestSleep", userId);
        Sleep sleep = (Sleep) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, sleep);
    }

    @GetMapping("/sleep/list")
    @ApiOperation("获取睡眠数据列表")
    public Object getSleepInfoList(@RequestParam Long userId, @RequestParam Long startTime,
                                   @RequestParam Long endTime,
                                   ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getSleepInfo", userId, new Date (startTime), new Date (endTime));
        List<Sleep> sleep = (List<Sleep>) provider.execute (parameters).getResultList ();
        return setSuccessModelMap ("sleepList", modelMap, sleep);
    }

    @GetMapping("/getWatch")
    @ApiOperation("获取手表信息")
    public Object getWatchInfo(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameters = new Parameter (serviceForWatch, "getWatchByUser", userId);
        BizWatch watch = (BizWatch) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, watch);
    }
//    @PostMapping("/setSilence.api")
//    @ApiOperation (value = "设置静音时间")
//    @ResponseBody
//    public void setSilence(Long userId,String span){
//        Map<String,Object> params=new HashMap<> ();
//        params.put ("userId",userId);
//        params.put ("type", OutMessage.SILENCE);
//        params.put ("span",span);
//        Parameter parameters=new Parameter (getService (),"watchOperate",params);
//        provider.execute (parameters);
//    }

    @GetMapping("/getHR")
    @ApiOperation(value = "获取心率信息")
    public Object getHR(@RequestParam Long userId, ModelMap modelMap) {
        Map<String, Object> params = new HashMap<> ();
        params.put ("userId", userId);
        params.put ("type", OutMessage.HRDETECT);
        Parameter parameters = new Parameter (getService (), "watchOperate", params);
        provider.execute (parameters);
        Parameter parameter = new Parameter ("deviceDataService", "getLatestDataByUser", userId, "watch_hr");
        Base base = (Base) provider.execute (parameter).getResult ();
        if (base != null) return setSuccessModelMap (modelMap, base);
        else throw new BusinessException ("没有数据");
    }

    @GetMapping("/getGPS")
    @ApiOperation(value = "获取位置信息")
    public Object getGPS(@RequestParam Long userId, ModelMap modelMap) {
        Map<String, Object> params = new HashMap<> ();
        params.put ("userId", userId);
        params.put ("type", OutMessage.POSITIONDETECT);
        Parameter parameters = new Parameter (getService (), "watchOperate", params);
        Integer resultCode = (Integer) provider.execute (parameters).getResult ();
        System.out.println (resultCode);
        parameters = new Parameter (serviceForWatch, "getLastPosition", userId);
        BizMessagePosition position = (BizMessagePosition) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, position);
    }

    @PostMapping("/setGPSSchedule")
    @ApiOperation(value = "设置GPS上报周期")
    public Object setGPSSchedule(@RequestParam Long userId,
                                 @RequestParam Long operator,
                                 @RequestParam Integer span, ModelMap modelMap) {
        BizWatch watch = new BizWatch ();
        BizLogWatch logWatch = new BizLogWatch ();
        logWatch.setOperation ("29");
        logWatch.setUserOperate (operator);
        logWatch.setUserId (userId);
        logWatch.setOperateDate (new Date ());
        watch.setPositionSpan (span);
        watch.setUser (userId);
        watch.setSyncStatus ((byte) 2);
        Parameter parameters = new Parameter (serviceForWatch, "updateWatchByUser", watch, logWatch);
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/setSit")
    @ApiOperation(value = "设置久坐提醒开关")
    public Object setSitLongAlarm(@RequestParam Long userId, @RequestParam Long operator, @RequestParam Boolean status, ModelMap modelMap) {
        BizWatch watch = new BizWatch ();
        BizLogWatch logWatch = new BizLogWatch ();
        logWatch.setOperation ("87");
        logWatch.setUserId (userId);
        logWatch.setUserOperate (operator);
        logWatch.setOperateDate (new Date ());
        watch.setSitLock (status);
        watch.setUser (userId);
        watch.setSyncStatus ((byte) 2);
        Parameter parameters = new Parameter (serviceForWatch, "updateWatchByUser", watch, logWatch);
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/setHRAlarm")
    @ApiOperation(value = "设置心率报警开关")
    public Object setHRAlarm(@RequestParam Long userId,
                             @RequestParam Long operator,
                             @RequestParam Boolean status, ModelMap modelMap) {
        BizWatch watch = new BizWatch ();
        watch.setHrAlarmLock (status);
        watch.setUser (userId);
        watch.setSyncStatus ((byte) 2);
        BizLogWatch logWatch = new BizLogWatch ();
        logWatch.setOperation ("29");
        logWatch.setUserId (userId);
        logWatch.setUserOperate (operator);
        logWatch.setOperateDate (new Date ());
        Parameter parameters = new Parameter (serviceForWatch, "updateWatchByUser", watch, logWatch);
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/setFenceStatus")
    @ApiOperation(value = "设置电子围栏开关")
    public Object setFenceStatus(@RequestParam Long userId,
                                 @RequestParam Long operator,
                                 @RequestParam Boolean status, ModelMap modelMap) {
        BizWatch watch = new BizWatch ();
        watch.setFence (status);
        watch.setUser (userId);
        watch.setSyncStatus ((byte) 2);
        BizLogWatch logWatch = new BizLogWatch ();
        logWatch.setOperation ("90");
        logWatch.setUserId (userId);
        logWatch.setUserOperate (operator);
        logWatch.setOperateDate (new Date ());
        Parameter parameters = new Parameter (serviceForWatch, "updateWatchByUser", watch, logWatch);
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);
    }
    //0 关 1 开
    @PostMapping("/setSleepStatus")
    @ApiOperation (value ="设置睡眠时间")
    public Object setSleepStatus(@RequestParam Long userId,
                             @RequestParam Long operator,
                             @RequestParam String startTime,
                             @RequestParam String endTime,
                             @RequestParam Boolean status,
                             ModelMap modelMap) {
        BizWatch watch = new BizWatch ();
        BizLogWatch logWatch = new BizLogWatch ();
        logWatch.setOperation ("75");
        logWatch.setUserId (userId);
        logWatch.setUserOperate (operator);
        logWatch.setOperateDate (new Date ());
        watch.setUser (userId);
        watch.setSleepStartTime (startTime);
        watch.setSleepEndTime (endTime);
        watch.setSleepStatus (status);
        Parameter parameters = new Parameter (serviceForWatch, "updateWatchByUser", watch,logWatch);
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);
    }

    @PostMapping("/setFence")
    @ApiOperation(value = "设置电子围栏")
    public Object setFence(@RequestParam Long userId,
                           @RequestParam Double longitude,
                           @RequestParam Double latitude,
                           @RequestParam Integer radius,
                           @RequestParam Long operator,
                           ModelMap modelMap) {
        BizWatch watch = new BizWatch ();
        watch.setLatitude (latitude);
        watch.setLongitude (longitude);
        BizLogWatch logWatch = new BizLogWatch ();
        logWatch.setOperation ("91");
        logWatch.setUserId (userId);
        logWatch.setUserOperate (operator);
        logWatch.setOperateDate (new Date ());
        watch.setUser (userId);
        watch.setRadius (radius);
        watch.setSyncStatus ((byte) 2);
        Parameter parameters = new Parameter (serviceForWatch, "updateWatchByUser", watch, logWatch);
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);

    }


}

