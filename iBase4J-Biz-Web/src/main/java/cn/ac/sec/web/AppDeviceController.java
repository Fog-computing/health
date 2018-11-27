package cn.ac.sec.web;

import cn.ac.sec.entity.BizDevice;
import cn.ac.sec.entity.BizDeviceType;
import cn.ac.sec.util.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/app/device")
@Api(value = "设备管理配置接口", description = "设备管理配置接口")
public class AppDeviceController extends AppBaseController<IBizProvider> {
    @Override
    public String getService() {
        return "deviceService";
    }
    /**
     * 设备类型REST 接口
     *
     */

    @GetMapping("type/list")
    @ApiOperation(value = "查询设备类型列表")
    @ResponseBody
    public String getDeviceTypeList(){
        Parameter parameters=new Parameter (getService (),"queryDeviceTypeList");
        List<BizDeviceType> typesList=(List<BizDeviceType>)provider.execute (parameters).getResult ();
        return JSONUtil.toJSon (typesList);
    }

    @GetMapping("type/{id}")
    @ApiOperation (value = "获取单个设备类型详情")
    public String getDeviceType(@PathVariable Byte id){
        Parameter parameter =new Parameter (getService (),"queryDeviceTypeById",id);
        BizDeviceType type=(BizDeviceType)provider.execute (parameter).getResult ();
        return JSONUtil.toJSon (type);
    }

    @PutMapping(value = "type",consumes = "application/json")
    @ApiOperation (value="添加设备类型",produces = "int",consumes = "application",responseReference = "1 插入成功,0插入失败")
    @ResponseBody
    public String addDeviceType(@RequestBody BizDeviceType type){
        Parameter parameter =new Parameter (getService(),"insertDeviceType",type);
        provider.execute (parameter);
        return "1";
    }

    @PostMapping(value = "type",consumes = "application/json")
    @ApiOperation (value = "更新设备类型信息",consumes = "application/json")
    @ResponseBody
    public String updateDeviceType(@RequestBody BizDeviceType type){
        Parameter parameter =new Parameter (getService(),"updateDeviceType",type);
        provider.execute (parameter);
        return "1";
    }

    @DeleteMapping(value="type")
    @ApiOperation (value = "删除设备")
    @ResponseBody
    public String deleteDeviceType(Byte id){
        Parameter parameter =new Parameter (getService (),"deleteDeviceType",id);
        provider.execute (parameter);
        return "1";
    }
    /**
     * 设备类型REST 接口结束
     */


    @GetMapping(value="/list/user",produces = "application/json;charset=utf-8")
    @ApiOperation (value="获取用户设备")
    public Object getDeviceByUser(@RequestParam Long userId, ModelMap modelMap) {
        Parameter parameters=new Parameter (getService (),"getDeviceListByUser",userId);
        List<Map<String,Object>> typesList=(List<Map<String,Object>>)provider.execute (parameters).getResult ();
        return setSuccessModelMap ("deviceList", modelMap, typesList);
    }

    @GetMapping("/list/type/{type}")
    @ApiOperation (value="获取用户设备")
    @ResponseBody
    public String getDeviceByType(@PathVariable String type){
        Parameter parameters=new Parameter (getService (),"getDeviceListByType",type);
        List<BizDevice> typesList=(List<BizDevice>)provider.execute (parameters).getResult ();
        return JSONUtil.toJSon (typesList);
    }

    @PutMapping("")
    @ApiOperation (value ="添加设备")
    @ResponseBody
    public String addDevice(@RequestBody BizDevice device){
        Parameter parameters=new Parameter (getService (),"insertDevice",device);
        provider.execute (parameters).getResult ();
        return JSONUtil.toJSon (1);
    }

    @PostMapping("")
    @ApiOperation (value="更新设备信息")
    @ResponseBody
    public String updateDevice(@RequestBody BizDevice device) {
        Parameter parameters = new Parameter (getService (), "updateDevice", device);
        provider.execute (parameters).getResult ();
        return JSONUtil.toJSon (1);
    }


    @DeleteMapping("")
    @ApiOperation (value = "删除设备")
    @ResponseBody
    public String deleteDevice(@RequestBody BizDevice device){
        Parameter parameters = new Parameter (getService (), "deleteDevice", device);
        provider.execute (parameters).getResult ();
        return JSONUtil.toJSon (1);
    }


}
