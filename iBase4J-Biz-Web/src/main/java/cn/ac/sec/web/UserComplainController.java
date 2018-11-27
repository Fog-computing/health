package cn.ac.sec.web;

import cn.ac.sec.entity.BizUserComplain;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/userComplain")
public class UserComplainController extends AppBaseController<IBizProvider> {

    @Override
    public String getService() {
        return "userComplainService";
    }

    @PostMapping
    public Object addUserComplain(@RequestBody BizUserComplain complain, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "addUserComplain", complain);
        provider.execute (parameter);
        return setSuccessModelMap (modelMap);
    }
}
