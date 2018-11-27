package cn.ac.sec.dao;

import cn.ac.sec.entity.BizMessageAlarm;
import cn.ac.sec.entity.BizMessageAlarmExample;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BizMessageAlarmMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    int countByExample(BizMessageAlarmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    int deleteByExample(BizMessageAlarmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    @Delete({
        "delete from biz_message_alarm",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    @Insert({
        "insert into biz_message_alarm (id_, user_, ",
        "type_, time_, status_, ",
        "process_user, process_content, ",
        "process_date, longitude_, ",
        "latitude_)",
        "values (#{id,jdbcType=BIGINT}, #{user,jdbcType=BIGINT}, ",
        "#{type,jdbcType=TINYINT}, #{time,jdbcType=TIMESTAMP}, #{status,jdbcType=TINYINT}, ",
        "#{processUser,jdbcType=BIGINT}, #{processContent,jdbcType=VARCHAR}, ",
        "#{processDate,jdbcType=TIMESTAMP}, #{longitude,jdbcType=DOUBLE}, ",
        "#{latitude,jdbcType=DOUBLE})"
    })
    int insert(BizMessageAlarm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    int insertSelective(BizMessageAlarm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    List<BizMessageAlarm> selectByExample(BizMessageAlarmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id_, user_, type_, time_, status_, process_user, process_content, process_date, ",
        "longitude_, latitude_",
        "from biz_message_alarm",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    BizMessageAlarm selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BizMessageAlarm record, @Param("example") BizMessageAlarmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BizMessageAlarm record, @Param("example") BizMessageAlarmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BizMessageAlarm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_alarm
     *
     * @mbggenerated
     */
    @Update({
        "update biz_message_alarm",
        "set user_ = #{user,jdbcType=BIGINT},",
          "type_ = #{type,jdbcType=TINYINT},",
          "time_ = #{time,jdbcType=TIMESTAMP},",
          "status_ = #{status,jdbcType=TINYINT},",
          "process_user = #{processUser,jdbcType=BIGINT},",
          "process_content = #{processContent,jdbcType=VARCHAR},",
          "process_date = #{processDate,jdbcType=TIMESTAMP},",
          "longitude_ = #{longitude,jdbcType=DOUBLE},",
          "latitude_ = #{latitude,jdbcType=DOUBLE}",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(BizMessageAlarm record);
    List<Map<String,Object>> listWithType();

    List<Map<String, Object>> listWithTypeByUser(@Param("userId") Long userId);

    Map<String, Object> detailWithProcessUser(@Param("id") Long id);


    List<Map<String, Object>> filterHistoryList(@Param("keyword") String keyword,
                                                @Param("start") Date start,
                                                @Param("end") Date end,
                                                @Param("status") Byte status);
}