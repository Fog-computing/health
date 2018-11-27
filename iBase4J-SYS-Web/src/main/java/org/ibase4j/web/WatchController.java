package org.ibase4j.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.ac.sec.entity.BizUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.ibase4j.provider.IBizProvider;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.ibase4j.core.base.AbstractController;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.core.exception.IllegalParameterException;
import org.ibase4j.model.SysRoleMenu;
import org.ibase4j.model.SysUserMenu;
import org.ibase4j.model.SysUserRole;
import org.ibase4j.provider.ISysProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权限管理
 *
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:05
 */
@Api(value = "手表配置", description = "手表配置")
public class WatchController extends AbstractController<IBizProvider> {

    public String getService() {
        return "watchSettingService";
    }


    @ApiOperation(value = "清理缓存")
//    @RequiresPermissions("sys.cache.update")
    @RequestMapping(value = "/watch/getGPS", method = RequestMethod.POST)
    public Object getGPS(ModelMap modelMap, @RequestParam String userId) {
        BizUser user = new BizUser ();
        user.setId (Long.parseLong (userId));
        Parameter parameter = new Parameter(getService(),"getGPS", "userId", userId);
        provider.execute (parameter);
        return setSuccessModelMap(modelMap);
    }
}
