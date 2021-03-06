package cn.ac.sec.dao;

import cn.ac.sec.entity.BizRelationTagUser;
import cn.ac.sec.entity.BizRelationTagUserExample;
import java.util.List;

import cn.ac.sec.entity.BizUserTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BizRelationTagUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    int countByExample(BizRelationTagUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    int deleteByExample(BizRelationTagUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    @Delete({
        "delete from biz_relation_tag_user",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    @Insert({
        "insert into biz_relation_tag_user (id_, tag_, ",
        "user_, add_user, add_time)",
        "values (#{id,jdbcType=BIGINT}, #{tag,jdbcType=INTEGER}, ",
        "#{user,jdbcType=BIGINT}, #{addUser,jdbcType=BIGINT}, #{addTime,jdbcType=TIMESTAMP})"
    })
    int insert(BizRelationTagUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    int insertSelective(BizRelationTagUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    List<BizRelationTagUser> selectByExample(BizRelationTagUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id_, tag_, user_, add_user, add_time",
        "from biz_relation_tag_user",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    BizRelationTagUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BizRelationTagUser record, @Param("example") BizRelationTagUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BizRelationTagUser record, @Param("example") BizRelationTagUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BizRelationTagUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_relation_tag_user
     *
     * @mbggenerated
     */
    @Update({
        "update biz_relation_tag_user",
        "set tag_ = #{tag,jdbcType=INTEGER},",
          "user_ = #{user,jdbcType=BIGINT},",
          "add_user = #{addUser,jdbcType=BIGINT},",
          "add_time = #{addTime,jdbcType=TIMESTAMP}",
        "where id_ = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(BizRelationTagUser record);

    int addRelationTagsList(List<BizRelationTagUser> record);

    List<BizUserTag> getTagWithoutUser(Long userId);
}