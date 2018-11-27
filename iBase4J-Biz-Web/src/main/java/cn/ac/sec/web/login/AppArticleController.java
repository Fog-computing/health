package cn.ac.sec.web.login;


import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.ISysProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/article")
public class AppArticleController extends AppBaseController<ISysProvider> {

    @ApiOperation(value = "")
    @GetMapping(value = "/list")
    public Object getAlarmList(ModelMap modelMap, @RequestParam String type) {
        Parameter parameter = new Parameter (getService (), "getArticleList", type);
        List<Map<String, Object>> list = (List<Map<String, Object>>) provider.execute (parameter).getResultList ();
        System.out.println (list);
        modelMap.addAttribute ("listKey", "articleList");
        return setSuccessModelMap (modelMap, list);
    }

    @ApiOperation(value = "")
    @GetMapping(value = "/detail/{id}")
    public Object getAlarmDetail(ModelMap modelMap, @PathVariable Long id) {
        Parameter parameter = new Parameter (getService (), "getArticleDetail", id);
        Map<String, Object> detail = (Map<String, Object>) provider.execute (parameter).getResult ();
        System.out.println (detail);
        return setSuccessModelMap (modelMap, detail);
    }


    @Override
    public String getService() {
        return "sysArticleService";
    }
}
