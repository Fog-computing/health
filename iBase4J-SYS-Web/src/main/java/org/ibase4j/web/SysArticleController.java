package org.ibase4j.web;

import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.support.HttpCode;
import org.ibase4j.core.util.UploadUtil;
import org.ibase4j.model.SysArticle;
import org.ibase4j.provider.ISysProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章  前端控制器
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Controller
@RequestMapping("/article")
public class SysArticleController extends AbstractController<ISysProvider> {
	public String getService() {
		return "sysArticleService";
	}

	@ApiOperation(value = "查询文章")
	@RequiresPermissions("cms.article.read")
	@PutMapping(value = "/read/list")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "文章详情")
	@RequiresPermissions("cms.article.read")
	@PutMapping(value = "/read/detail")
	public Object get(ModelMap modelMap, @RequestBody SysArticle param) {
		return super.get(modelMap, param);
	}

	@PostMapping
	@ApiOperation(value = "修改文章")
	@RequiresPermissions("cms.article.update")
	public Object update(ModelMap modelMap, @RequestBody SysArticle param) {
		if (param.getEnable() == null) {
			param.setEnable(0);
		}
		if (param.getIsTop() == null) {
			param.setIsTop(0);
		}
		return super.update(modelMap, param);
	}

    @ApiOperation("获取顶栏文章")
    @PutMapping("/list/top")
    public Object getTopArticles(ModelMap modelMap) {
        String type = new String ("3");
        Parameter parameter = new Parameter (getService (), "getArticleList", type);
        List<Map<String, Object>> list = (List<Map<String, Object>>) provider.execute (parameter).getResultList ();
        System.out.println (list);
        return setSuccessModelMap (modelMap, list);
    }


    @ApiOperation(value = "添加图片")
    @PostMapping(value = "/update/img/")
    public Object updateAvatar(HttpServletRequest request, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImageData (request);
        return getObject (modelMap, fileNames);
    }


    @ApiOperation(value = "添加图片")
    @PostMapping(value = "/update/rollImg/")
    public Object updateRollImg(HttpServletRequest request, ModelMap modelMap) {
        List<String> fileNames = UploadUtil.uploadImage (request, false, false);
        return getObject (modelMap, fileNames);
    }

    private Object getObject(ModelMap modelMap, List<String> fileNames) {
        if (fileNames.size () > 0) {
            SysArticle param = new SysArticle ();
            param.setId (getCurrUser ());
            for (int i = 0; i < fileNames.size (); i++) {
                String filePath = "/upload/" + fileNames.get (i);
                param.setOutUrl (filePath);
            }
            modelMap.put ("data", param);
            return setSuccessModelMap (modelMap, param);
        } else {
            setModelMap (modelMap, HttpCode.BAD_REQUEST);
            modelMap.put ("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }

	@DeleteMapping
	@ApiOperation(value = "删除文章")
	@RequiresPermissions("cms.article.delete")
	public Object delete(ModelMap modelMap, @RequestBody SysArticle param) {
		return super.delete(modelMap, param);
	}
}