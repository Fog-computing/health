package cn.ac.sec.web;

import cn.ac.sec.entity.BizProfession;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api
@RequestMapping("/biz/profession")
@RestController
public class ProfessionController extends AbstractController<IBizProvider> {
    @Override
    public String getService() {
        return "professionService";
    }

    @ApiOperation("checkProfession")
    @GetMapping("unprocessed/{userId}")
    public Object unprocessedProcession(@PathVariable Long userId, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "getUnprocessedProfession", userId);
        BizProfession profession = (BizProfession) provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap, profession);
    }

    @ApiOperation("processionProfession")
    @PostMapping("")
    public Object processProfession(@RequestBody BizProfession profession, ModelMap modelMap) {
        Parameter parameter = new Parameter (getService (), "updateProfession", profession);
        profession.setProcessDate (new Date ());
        profession.setProcessUser (getCurrUser ());
        provider.execute (parameter).getResult ();
        return setSuccessModelMap (modelMap);
    }
}
