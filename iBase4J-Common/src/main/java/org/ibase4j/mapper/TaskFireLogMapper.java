package org.ibase4j.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.ibase4j.model.TaskFireLog;

import java.util.List;
import java.util.Map;

public interface TaskFireLogMapper extends BaseMapper<TaskFireLog> {
    List<Long> selectIdByMap(RowBounds rowBounds, @Param("cm") Map<String, Object> params);
}
