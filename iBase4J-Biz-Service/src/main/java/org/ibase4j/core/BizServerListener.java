package org.ibase4j.core;

import javax.servlet.ServletContextEvent;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ibase4j.core.listener.ServerListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class BizServerListener extends ServerListener{
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public void contextInitialized(ServletContextEvent contextEvent) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
//        context.getBean(SysCacheService.class).flush();
//        context.getBean(SysUserService.class).init();
//        SysDicService sysDicService = context.getBean(SysDicService.class);
//        sysDicService.getAllDic();
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        super.contextInitialized(contextEvent);
    }
}
