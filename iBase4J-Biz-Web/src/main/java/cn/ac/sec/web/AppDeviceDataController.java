package cn.ac.sec.web;

import cn.ac.sec.entity.messure.*;
import cn.ac.sec.entity.transport.DataTransport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app/device/data")
@Api(value="设备消息接收接口")
public class AppDeviceDataController extends AppBaseController<IBizProvider> {
    @Override
    public String getService() {
        return "deviceDataService";
    }


    @ApiOperation(value="血糖信息上传")
    @PostMapping("/BS")
    public Object addDeviceBSData(@RequestBody DataTransport<BloodSugar> transport, ModelMap modelMap){
        System.out.println (transport.getData ());
        Parameter parameter =new Parameter (getService (),"insertDataFromTransport",transport);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }


    @ApiOperation (value = "心率信息上传")
    @PostMapping("/HR")
    public Object addDeviceHRData(@RequestBody DataTransport<HeartRate> transport,ModelMap modelMap){
        System.out.println (transport.getData ());
        List<HeartRate> heartRates = transport.getData ();
        for (HeartRate heartRate : heartRates) {
            if (heartRate.getTimeLen () == null) heartRate.setTimeLen (0);
        }
        Parameter parameter =new Parameter (getService (),"insertDataFromTransport",transport);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @ApiOperation (value = "尿液分析信息上传")
    @PostMapping("/urinalysis")
    public Object addDeviceURIAData(@RequestBody DataTransport<Urinalysis> transport,ModelMap modelMap){
        System.out.println (transport.getData ());
        Parameter parameter =new Parameter (getService (),"insertDataFromTransport",transport);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);

    }

    @ApiOperation (value = "血氧信息上传")
    @PostMapping("/BO")
    public Object addDeviceBOAData(@RequestBody DataTransport<BloodOxygen> transport,ModelMap modelMap){
        System.out.println (transport.getData ());
        Parameter parameter =new Parameter (getService (),"insertDataFromTransport",transport);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);

    }

    @ApiOperation (value = "血压信息上传")
    @PostMapping("/BP")
    public Object addDeviceBPAData(@RequestBody DataTransport<BloodPressure> transport,ModelMap modelMap){
        System.out.println (transport.getData ());
        Parameter parameter =new Parameter (getService (),"insertDataFromTransport",transport);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);

    }

    @ApiOperation (value = "血压信息上传")
    @PostMapping("/BodyFat")
    public Object addBodyFatData(@RequestBody DataTransport<BodyFat> transport,ModelMap modelMap){
        System.out.println (transport.getData ());
        Parameter parameter =new Parameter (getService (),"insertDataFromTransport",transport);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);

    }

    @ApiOperation(value = "获取单条详细信息")
    @PostMapping("/detail")
    public Object getDetailData(@RequestParam Long id, @RequestParam String type, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "getDataById", id, type);
        Base base = (Base) provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap, base);
    }

    @GetMapping("/detail")
    @ApiOperation("根据测量时间获取设备")
    public Object getDate(ModelMap modelMap, @RequestParam Long userId, @RequestParam Long time, @RequestParam String typeCode) {
        Parameter parameter = new Parameter ("deviceDataService", "getDataByTime",
                userId, new Date (time), typeCode);
        Base base = (Base) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, base);
    }


    @ApiOperation (value = "获取信息列表" )
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public Object getDeviceData(@RequestParam Long startTime,@RequestParam Long endTime,
                                @RequestParam Long userId,@RequestParam String type,
                                ModelMap modelMap){
        Parameter parameter = new Parameter (getService (),"getDataByUser",userId,new Date(startTime),new Date(endTime),type);
        List<Base> baseList = (List<Base>) provider.execute (parameter).getResultList ();
        if (baseList != null) return setSuccessModelMap ("dataList", modelMap, baseList);
        else throw new BusinessException ("没有数据");
    }

    @ApiOperation (value = "获取用户最近的一条测量信息" )
    @GetMapping("/user/latest")
    public Object getLatestData(@RequestParam Long userId,@RequestParam String type,
                                ModelMap modelMap){
        Parameter parameter = new Parameter (getService (),"getLatestDataByUser",userId,type);
        Base base=(Base) provider.execute (parameter).getResult ();
        if (base != null) return setSuccessModelMap (modelMap, base);
        else throw new BusinessException ("没有数据");
    }
}

