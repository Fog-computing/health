package cn.ac.sec.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sys/articleType")
@RestController
@Api
public class SysArticleTypeController extends AbstractController<IBizProvider>{

    @RequestMapping("/list")
    @ApiOperation ("获取文章类型列表")
    public Object getArticleTypeList(ModelMap modelMap){

        return setSuccessModelMap (modelMap);
    }

    @Override
    public String getService() {
        return "articleTypeService";
    }
}
