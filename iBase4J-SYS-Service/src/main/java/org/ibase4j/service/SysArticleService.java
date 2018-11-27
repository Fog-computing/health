package org.ibase4j.service;

import org.ibase4j.core.base.BaseService;
import org.ibase4j.mapper.SysArticleMapper;
import org.ibase4j.model.SysArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章  服务实现类
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
@Service
@CacheConfig(cacheNames = "sysArticle")
public class SysArticleService extends BaseService<SysArticle> {
    @Autowired
    private SysArticleMapper sysArticleMapper;

    public List<Map<String, Object>> getArticleList(String type) {
        return sysArticleMapper.getArticleList (type);
    }

    public Map<String, Object> getArticleDetail(Long id) {
        return sysArticleMapper.getArticleDetail (id);
    }

    public int updateImg(SysArticle article) {
        return sysArticleMapper.updateImg (article);
    }


}