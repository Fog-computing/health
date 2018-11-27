package org.ibase4j.mapper;

import org.apache.ibatis.annotations.Param;
import org.ibase4j.core.base.BaseMapper;
import org.ibase4j.model.SysArticle;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author ShenHuaJie
 * @since 2017-03-12
 */
public interface SysArticleMapper extends BaseMapper<SysArticle> {

    List<Map<String, Object>> getArticleList(@Param("type") String type);

    Map<String, Object> getArticleDetail(@Param("id") Long id);

    int updateImg(SysArticle article);
}