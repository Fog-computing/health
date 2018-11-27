package cn.ac.sec.dao;

import cn.ac.sec.entity.BizFallDevice;
import cn.ac.sec.entity.BizFallDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BizFallDeviceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    int countByExample(BizFallDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    int deleteByExample(BizFallDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    @Delete({
        "delete from biz_fall_device",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    @Insert({
        "insert into biz_fall_device (id_, imei_, ",
        "user_, last_online_time, ",
        "phone_num, add_user, ",
        "update_status, battery_, ",
        "bind_phone_num)",
        "values (#{id,jdbcType=BIGINT}, #{imei,jdbcType=VARCHAR}, ",
        "#{user,jdbcType=BIGINT}, #{lastOnlineTime,jdbcType=TIMESTAMP}, ",
        "#{phoneNum,jdbcType=VARCHAR}, #{addUser,jdbcType=BIGINT}, ",
        "#{updateStatus,jdbcType=TINYINT}, #{battery,jdbcType=TINYINT}, ",
        "#{bindPhoneNum,jdbcType=VARCHAR})"
    })
    int insert(BizFallDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    int insertSelective(BizFallDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    List<BizFallDevice> selectByExample(BizFallDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id_, imei_, user_, last_online_time, phone_num, add_user, update_status, battery_, ",
        "bind_phone_num",
        "from biz_fall_device",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    BizFallDevice selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BizFallDevice record, @Param("example") BizFallDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BizFallDevice record, @Param("example") BizFallDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BizFallDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    @Update({
        "update biz_fall_device",
        "set imei_ = #{imei,jdbcType=VARCHAR},",
          "user_ = #{user,jdbcType=BIGINT},",
          "last_online_time = #{lastOnlineTime,jdbcType=TIMESTAMP},",
          "phone_num = #{phoneNum,jdbcType=VARCHAR},",
          "add_user = #{addUser,jdbcType=BIGINT},",
          "update_status = #{updateStatus,jdbcType=TINYINT},",
          "battery_ = #{battery,jdbcType=TINYINT},",
          "bind_phone_num = #{bindPhoneNum,jdbcType=VARCHAR}",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(BizFallDevice record);
}