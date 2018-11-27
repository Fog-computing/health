package cn.ac.sec.web;

import org.ibase4j.core.util.InstanceUtil;
import org.ibase4j.provider.ISysProvider;
import org.ibase4j.web.AppBaseController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.Map;

@RequestMapping("/app/dic")
@RestController
public class DicController  extends AppBaseController<ISysProvider>{


    @Override
    public String getService() {
        return "sysDicService";
    }


    @RequestMapping("/HRDic")
    public Object getHRUnusualDic(ModelMap modelMap){
        Map<String,Object> param= InstanceUtil.newHashMap ();
        param.put("orderBy", "type_,sort_no");
        param.put ("type","HEARTRATE");
        return super.queryList(modelMap, param);
    }
}
