package org.ibase4j.core.util;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author ShenHuaJie
 * @version 2016年5月27日 下午4:23:06
 */
public class DubboUtil {
    private static final ConcurrentMap<String, ReferenceBean<?>> referenceConfigs = new ConcurrentHashMap<String, ReferenceBean<?>> ();

    private DubboUtil() {
    }

    /**
     * 获取Dubbo服务
     */
    public static Object refer(ApplicationContext applicationContext, String interfaceName) {
        String key = "/" + interfaceName + ":";
        ReferenceBean<?> referenceConfig = referenceConfigs.get (key);
        if (referenceConfig == null) {
            referenceConfig = new ReferenceBean<Object> ();
            referenceConfig.setInterface (interfaceName);
            if (applicationContext != null) {
                referenceConfig.setApplicationContext (applicationContext);
                try {
                    referenceConfig.afterPropertiesSet ();
                } catch (Exception e) {
                    throw new IllegalStateException (e.getMessage (), e);
                }
            }
            referenceConfigs.putIfAbsent (key, referenceConfig);
            referenceConfig = referenceConfigs.get (key);
        }
        return referenceConfig.get ();
    }
}
