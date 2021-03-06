package cn.ac.sec.dao;

import cn.ac.sec.entity.BizUserRelationPhoneNum;
import cn.ac.sec.entity.BizUserRelationPhoneNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BizUserRelationPhoneNumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    int countByExample(BizUserRelationPhoneNumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    int deleteByExample(BizUserRelationPhoneNumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    @Delete({
        "delete from biz_user_relation_phone_num",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    @Insert({
        "insert into biz_user_relation_phone_num (id_, user_, ",
        "phone_num, name_, ",
        "relation_, type_)",
        "values (#{id,jdbcType=BIGINT}, #{user,jdbcType=BIGINT}, ",
        "#{phoneNum,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{relation,jdbcType=VARCHAR}, #{type,jdbcType=TINYINT})"
    })
    int insert(BizUserRelationPhoneNum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    int insertSelective(BizUserRelationPhoneNum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    List<BizUserRelationPhoneNum> selectByExample(BizUserRelationPhoneNumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id_, user_, phone_num, name_, relation_, type_",
        "from biz_user_relation_phone_num",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    BizUserRelationPhoneNum selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BizUserRelationPhoneNum record, @Param("example") BizUserRelationPhoneNumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BizUserRelationPhoneNum record, @Param("example") BizUserRelationPhoneNumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BizUserRelationPhoneNum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_relation_phone_num
     *
     * @mbggenerated
     */
    @Update({
        "update biz_user_relation_phone_num",
        "set user_ = #{user,jdbcType=BIGINT},",
          "phone_num = #{phoneNum,jdbcType=VARCHAR},",
          "name_ = #{name,jdbcType=VARCHAR},",
          "relation_ = #{relation,jdbcType=VARCHAR},",
          "type_ = #{type,jdbcType=TINYINT}",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(BizUserRelationPhoneNum record);
}