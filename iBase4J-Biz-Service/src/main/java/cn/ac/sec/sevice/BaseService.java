package cn.ac.sec.sevice;

import cn.ac.sec.entity.transport.PageTransport;
import cn.ac.sec.entity.transport.Pager;
import com.baomidou.mybatisplus.plugins.Page;
import org.ibase4j.core.util.DataUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

public abstract class BaseService<T> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


    public  Page<T>  getPage(PageTransport params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "id_";
        if (DataUtil.isNotEmpty(params.getPageNum ())) {
            current = Integer.valueOf(params.getPageNum ());
        }
        if (DataUtil.isNotEmpty(params.getPageIndex ())) {
            current = Integer.valueOf(params.getPageIndex ());
        }
        if (DataUtil.isNotEmpty(params.getPageSize ())) {
            size = Integer.valueOf(params.getPageSize ());
        }
        Page<T> page = new Page<>(current, size, orderBy);
        page.setAsc(false);
        return page;
    }
}
