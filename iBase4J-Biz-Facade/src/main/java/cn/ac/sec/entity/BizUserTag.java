package cn.ac.sec.entity;

import java.io.Serializable;

public class BizUserTag implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_user_tag.id_
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_user_tag.tag_name
     *
     * @mbggenerated
     */
    private String tagName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_user_tag.tag_detail
     *
     * @mbggenerated
     */
    private String tagDetail;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_tag
     *
     * @mbggenerated
     */
    public BizUserTag(Integer id, String tagName, String tagDetail) {
        this.id = id;
        this.tagName = tagName;
        this.tagDetail = tagDetail;
    }

    public BizUserTag(Integer id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_user_tag
     *
     * @mbggenerated
     */
    public BizUserTag() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_user_tag.id_
     *
     * @return the value of biz_user_tag.id_
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_user_tag.id_
     *
     * @param id the value for biz_user_tag.id_
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_user_tag.tag_name
     *
     * @return the value of biz_user_tag.tag_name
     *
     * @mbggenerated
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_user_tag.tag_name
     *
     * @param tagName the value for biz_user_tag.tag_name
     *
     * @mbggenerated
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_user_tag.tag_detail
     *
     * @return the value of biz_user_tag.tag_detail
     *
     * @mbggenerated
     */
    public String getTagDetail() {
        return tagDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_user_tag.tag_detail
     *
     * @param tagDetail the value for biz_user_tag.tag_detail
     *
     * @mbggenerated
     */
    public void setTagDetail(String tagDetail) {
        this.tagDetail = tagDetail == null ? null : tagDetail.trim();
    }
}