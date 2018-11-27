package cn.ac.sec.web;


import cn.ac.sec.entity.BizDevice;
import cn.ac.sec.entity.BizDeviceType;
import cn.ac.sec.entity.messure.Base;
import cn.ac.sec.entity.transport.PageTransport;
import cn.ac.sec.util.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.util.DateUtil;
import org.ibase4j.core.util.WebUtil;
import org.ibase4j.model.ChartData;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/biz/device")
public class DeviceController extends AbstractController<IBizProvider> {
    @Override
    public String getService() {
        return "deviceService";
    }

    /**
     * 设备类型REST 接口
     */

    @RequestMapping("type/list")
    @ApiOperation(value = "查询设备类型列表")
    public Object getDeviceTypeList(ModelMap modelMap) {
        Parameter parameters = new Parameter (getService (), "queryDeviceTypeList");
        List<BizDeviceType> typesList = (List<BizDeviceType>) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, typesList);
    }

    @GetMapping("type/{id}")
    @ApiOperation(value = "获取单个设备类型详情")
    public String getDeviceType(@PathVariable Byte id) {
        Parameter parameter = new Parameter (getService (), "queryDeviceTypeById", id);
        BizDeviceType type = (BizDeviceType) provider.execute (parameter).getResult ();
        return JSONUtil.toJSon (type);
    }

    @PutMapping(value = "type", consumes = "application/json")
    @ApiOperation(value = "添加设备类型", produces = "int", consumes = "application", responseReference = "1 插入成功,0插入失败")
    @ResponseBody
    public String addDeviceType(@RequestBody BizDeviceType type) {
        Parameter parameter = new Parameter (getService (), "insertDeviceType", type);
        provider.execute (parameter);
        return "1";
    }

    @PostMapping(value = "type", consumes = "application/json")
    @ApiOperation(value = "更新设备类型信息", consumes = "application/json")
    @ResponseBody
    public String updateDeviceType(@RequestBody BizDeviceType type) {
        Parameter parameter = new Parameter (getService (), "updateDeviceType", type);
        provider.execute (parameter);
        return "1";
    }

    @DeleteMapping(value = "type")
    @ApiOperation(value = "删除设备")
    @ResponseBody
    public String deleteDeviceType(Byte id) {
        Parameter parameter = new Parameter (getService (), "deleteDeviceType", id);
        provider.execute (parameter);
        return "1";
    }

    /**
     * 设备类型REST 接口结束
     */

    @PutMapping("/list")
    @ApiOperation(value = "获取用户设备列表")
    public Object getDeviceByUser(ModelMap modelMap, @RequestBody PageTransport data) {
        Parameter parameters = new Parameter (getService (), "getDeviceList", data);
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, page);
    }

    @PutMapping("/list/user/{userId}")
    @ApiOperation(value = "获取用户设备")
    public Object getDeviceByType(@PathVariable Long userId, ModelMap modelMap) {
        Parameter parameters = new Parameter (getService (), "getDeviceListByUser", userId);
        List<Map<String, Object>> page = (List<Map<String, Object>>) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, page);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加设备")
    public Object addDevice(@RequestBody BizDevice device, ModelMap modelMap) {
        Parameter parameters = new Parameter (getService (), "insertDevice", device);
        device.setAddUser (WebUtil.getCurrentUser ());
        device.setAddTime (new Date ());
        provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap);
    }

    @PutMapping("detail/{id}")
    @ApiOperation(value = "")
    public Object getDeviceById(ModelMap modelMap, @PathVariable Long id) {
        Parameter parameter = new Parameter (getService (), "getDeviceById", id);
        BizDevice device = (BizDevice) provider.execute (parameter).getResult ();
        System.out.println (device);
        return setSuccessModelMap (modelMap, device);
    }

//    @PostMapping("")
//    @ApiOperation (value="更新设备信息")
//    public Object updateDevice(@RequestBody BizDevice device,) {
//        Parameter parameters = new Parameter (getService (), "updateDevice", device);
//        provider.execute (parameters).getResult ();
//        return JSONUtil.toJSon (1);
//    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除设备")
    public Object deleteDevice(@PathVariable Long id, ModelMap modelMap) {
        Parameter parameters = new Parameter (getService (), "deleteDeviceById", id);
        provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap);
    }

    @GetMapping("/data/{userId}")
    @ApiOperation(value = "获取获取测量数据")
    public Object getData(@PathVariable Long userId, @RequestParam Long start,
                          @RequestParam Long end, @RequestParam String typeCode, ModelMap modelMap) {
        Parameter parameter;
        if (typeCode.equals ("PM10"))
            parameter = new Parameter ("deviceDataService", "getHeartRate", userId,
                    new Date (start), new Date (end));
        else parameter = new Parameter ("deviceDataService", "getDataByUser",
                userId, new Date (start), new Date (end), typeCode);
        List<Base> list = (List<Base>) provider.execute (parameter).getResultList ();
        return setSuccessModelMap (modelMap, list);
    }

    @GetMapping("/data/detail/time/{userId}")
    @ApiOperation("根据测量时间获取设备")
    public Object getDate(ModelMap modelMap, @PathVariable Long userId, @RequestParam Long time, @RequestParam String typeCode) {
        Parameter parameter = new Parameter ("deviceDataService", "getDataByTime",
                userId, new Date (time), typeCode);
        Base base = (Base) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, base);
    }


    @GetMapping("/data/detail/{id}")
    @ApiOperation(value = "获单条设备信息")
    public Object getSingleData(@PathVariable Long id, @RequestParam String typeCode, ModelMap modelMap) {
        Parameter parameter = new Parameter ("deviceDataService", "getDataById",
                id, typeCode);
        Base baseData = (Base) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, baseData);
    }

    @RequestMapping(value="/queryType")
    @ApiOperation(value = "查询所有设备类型")
    @ResponseBody
    public List<Map<String,String>> queryDeviceType(){
        List<Map<String,String>> msgList=new ArrayList<Map<String,String>>();
        try{
            Parameter parameters = new Parameter (getService (), "queryDeviceTypeList");
            List<BizDeviceType> typesList = (List<BizDeviceType>) provider.execute (parameters).getResult ();
            if(typesList.size()>0){
                for(BizDeviceType bizDeviceType : typesList){
                    Map<String,String> msgMap=new HashMap<String, String>();
                    msgMap.put("name", bizDeviceType.getName());
                    msgMap.put("type", bizDeviceType.getTypeCode());
                    msgList.add(msgMap);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msgList;

    }

    @RequestMapping(value="/addStatistics")
    @ApiOperation(value = "获取新增设备的折线图的数据")
    @ResponseBody
    public Map<String, Object>  account(String type,String time,String typeCode){
        if(type.equals("month")){
            return findRegisterCountByMonth(time,typeCode);
        }else if (type.equals("week")){
            return findRegisterCountByWeek(time,typeCode);
        }else if(type.equals("day")){
            return findRegisterCountByDay(time,typeCode);
        }else {
            return findRegisterCountCurrenYear();
        }
    }

    private Map<String, Object> findRegisterCountByMonth(String time,String typeCode){
        List<String> list =new ArrayList<String>();
        list.add(time);
        list.add(typeCode);
        Parameter parameter = new Parameter (getService (), "getRegisterCountByMonth", list);
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

    private Map<String, Object> findRegisterCountByWeek(String time,String typeCode){
        List<String> list = new ArrayList<>();
        list.add(time);
        list.add(typeCode);
        Parameter parameter = new Parameter (getService (), "getRegisterCountByWeek", list);
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

    private Map<String, Object> findRegisterCountByDay(String time,String typeCode){
        String[] str=time.split("-");
        List<String> list =new ArrayList<>();
        list.add(str[0]);
        list.add(str[1]);
        list.add(typeCode);
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
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



    @RequestMapping(value="/useStatistics")
    @ApiOperation(value = "获取使用设备的折线图的数据")
    @ResponseBody
    public Map<String, Object>  useDevice(String type,String time,String typeCode){
        if(type.equals("month")){
            return findUseDeviceCountByMonth(time,typeCode);
        }else if (type.equals("week")){
            return findUseDeviceCountByWeek(time,typeCode);
        }else if(type.equals("day")){
            return findUseDeviceCountByDay(time,typeCode);
        }else {
            return findUseDeviceCountCurrenYear();
        }
    }

    private Map<String, Object> findUseDeviceCountByMonth(String time,String typeCode){
       /* Parameter parameter = new Parameter (getService (), "getAllUseDeviceCountByMonth", time);
        List<Integer> total=(List<Integer>)provider.execute (parameter).getResult();*/
        List<String> list =new ArrayList<String>();
        list.add(time);
        list.add(typeCode);
        Parameter param= new Parameter (getService (), "getUseDeviceCountByMonth", list);
        List<ChartData> datas=(List<ChartData>) provider.execute (param).getResult();
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

    private Map<String, Object> findUseDeviceCountByWeek(String time,String typeCode){
       /* Parameter parameter = new Parameter (getService (), "getAllUseDeviceCountByWeek", time);
        List<Integer> total=(List<Integer>)provider.execute (parameter).getResult();*/
        List<String> list = new ArrayList<>();
        list.add(time);
        list.add(typeCode);
        Parameter param = new Parameter (getService (), "getUseDeviceCountByWeek", list);
        List<ChartData> datas=(List<ChartData>) provider.execute (param).getResult();
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

    private Map<String, Object> findUseDeviceCountByDay(String time,String typeCode){
        String[] str=time.split("-");
        List<String> list =new ArrayList<>();
        list.add(str[0]);
        list.add(str[1]);
        list.add(typeCode);
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        /*Parameter parameter = new Parameter (getService (), "getAllUseDeviceCountByDay", list);
        List<Integer> total=(List<Integer>) provider.execute (parameter).getResult();*/
        Parameter param = new Parameter (getService (), "getUseDeviceCountByDay", list);
        List<ChartData> datas=(List<ChartData>) provider.execute (param).getResult();
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

    private  Map<String, Object> findUseDeviceCountCurrenYear(){
        return null;
    }
}
