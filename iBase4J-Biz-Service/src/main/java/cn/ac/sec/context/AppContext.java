package cn.ac.sec.context;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class AppContext implements ApplicationContextAware {
    public static final String TCP_SERVER = "tcpServer";
    private static final Logger LOG = LogManager
            .getLogger(ApplicationContext.class);
    // The spring application context.
    private static ApplicationContext applicationContext=null;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException
    {
        AppContext.applicationContext = applicationContext;
        LOG.info("------SpringContextUtil setApplicationContext-------");
    }

    // 根据beanName获取bean
    public static Object getBean(String beanName)
    {
        if (null == beanName)
        {
            return null;
        }
        return applicationContext.getBean(beanName);
    }
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean (clazz);
    }
    public static <T> T getBeanByName(Class<T> clazz) throws BeansException {
        try {
            char[] cs=clazz.getSimpleName().toCharArray();
            cs[0] += 32;//首字母大写到小写
            return (T) applicationContext.getBean(String.valueOf(cs));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
