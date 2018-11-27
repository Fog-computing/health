package cn.ac.sec.web;


import cn.ac.sec.entity.BizPhoneCallWithBLOBs;
import cn.ac.sec.entity.transport.PageTransport;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/biz/phoneCall")
public class PhoneCallController extends AbstractController<IBizProvider> {

    @Override
    public String getService() {
        return "userCallService";
    }

    @PutMapping("/list")
    @ApiOperation("获取用户拨打电话列表")
    public Object getPhoneCallList(ModelMap modelMap, @RequestBody PageTransport transport) {
        Parameter parameters = new Parameter (getService (), "getPhoneCallPage", transport);
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, page);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户拨打电话内容")
    public Object addPhoneCall(ModelMap modelMap, @RequestBody BizPhoneCallWithBLOBs phoneCall) {
        Parameter parameters = new Parameter (getService (), "addPhoneCall", phoneCall);
        phoneCall.setOperator (getCurrUser ());
        provider.execute (parameters);
        return setSuccessModelMap (modelMap);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("删除用户拨打电话记录")
    public Object deletePhoneCall(ModelMap modelMap, @PathVariable Long id) {
        Parameter parameter = new Parameter (getService (), "deletePhoneCall", id);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }

    @PutMapping("/detail/{id}")
    @ApiOperation("获取用户电话详情")
    public Object getPhoneDetail(ModelMap modelMap, @PathVariable Long id) {
        Parameter parameters = new Parameter (getService (), "getPhoneCallDetail", id);
        BizPhoneCallWithBLOBs phoneCall = (BizPhoneCallWithBLOBs) provider.execute (parameters).getResult ();
        return setSuccessModelMap (modelMap, phoneCall);
    }
}
