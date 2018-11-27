package cn.ac.sec.dao;

import cn.ac.sec.entity.BizRemind;
import cn.ac.sec.entity.BizRemindExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BizRemindMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    int countByExample(BizRemindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    int deleteByExample(BizRemindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    @Delete({
        "delete from biz_remind",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    @Insert({
        "insert into biz_remind (id_, user_, ",
        "remind_content, remind_type, ",
        "remind_time, remind_update_time, ",
            "remind_add_user, delete_status)",
        "values (#{id,jdbcType=BIGINT}, #{user,jdbcType=BIGINT}, ",
        "#{remindContent,jdbcType=VARCHAR}, #{remindType,jdbcType=TINYINT}, ",
        "#{remindTime,jdbcType=TIMESTAMP}, #{remindUpdateTime,jdbcType=TIMESTAMP}, ",
            "#{remindAddUser,jdbcType=BIGINT}, #{deleteStatus,jdbcType=BIT})"
    })
    int insert(BizRemind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    int insertSelective(BizRemind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    List<BizRemind> selectByExample(BizRemindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id_, user_, remind_content, remind_type, remind_time, remind_update_time, remind_add_user, ",
            "delete_status",
        "from biz_remind",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    BizRemind selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BizRemind record, @Param("example") BizRemindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BizRemind record, @Param("example") BizRemindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BizRemind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    @Update({
        "update biz_remind",
        "set user_ = #{user,jdbcType=BIGINT},",
          "remind_content = #{remindContent,jdbcType=VARCHAR},",
          "remind_type = #{remindType,jdbcType=TINYINT},",
          "remind_time = #{remindTime,jdbcType=TIMESTAMP},",
          "remind_update_time = #{remindUpdateTime,jdbcType=TIMESTAMP},",
          "remind_add_user = #{remindAddUser,jdbcType=BIGINT},",
            "delete_status = #{deleteStatus,jdbcType=BIT}",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(BizRemind record);

    @Select({
            "SELECT  * FROM biz_remind WHERE (TIMESTAMPDIFF(SECOND,current_time,TIME(remind_time))<60 AND TIMESTAMPDIFF(SECOND,current_time,TIME(remind_time))>=0 AND delete_status=FALSE AND remind_type>2);"
    })
    @ResultMap("BaseResultMap")
    List<BizRemind> getReminds();
}