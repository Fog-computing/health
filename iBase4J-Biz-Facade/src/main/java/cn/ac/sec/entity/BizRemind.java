package cn.ac.sec.entity;

import java.io.Serializable;
import java.util.Date;

public class BizRemind implements Serializable {
    public static final byte WatchOnce = 1;
    public static final byte WatchSchedule=2;
    public static final byte MobileOnce=3;
    public static final byte MobileSchedule=4;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.id_
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.user_
     *
     * @mbggenerated
     */
    private Long user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.remind_content
     *
     * @mbggenerated
     */
    private String remindContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.remind_type
     *
     * @mbggenerated
     */
    private Byte remindType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.remind_time
     *
     * @mbggenerated
     */
    private Date remindTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.remind_update_time
     *
     * @mbggenerated
     */
    private Date remindUpdateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.remind_add_user
     *
     * @mbggenerated
     */
    private Long remindAddUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_remind.delete_status
     *
     * @mbggenerated
     */
    private Boolean deleteStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    public BizRemind(Long id, Long user, String remindContent, Byte remindType, Date remindTime, Date remindUpdateTime, Long remindAddUser, Boolean deleteStatus) {
        this.id = id;
        this.user = user;
        this.remindContent = remindContent;
        this.remindType = remindType;
        this.remindTime = remindTime;
        this.remindUpdateTime = remindUpdateTime;
        this.remindAddUser = remindAddUser;
        this.deleteStatus = deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_remind
     *
     * @mbggenerated
     */
    public BizRemind() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.id_
     *
     * @return the value of biz_remind.id_
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.id_
     *
     * @param id the value for biz_remind.id_
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.user_
     *
     * @return the value of biz_remind.user_
     *
     * @mbggenerated
     */
    public Long getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.user_
     *
     * @param user the value for biz_remind.user_
     *
     * @mbggenerated
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.remind_content
     *
     * @return the value of biz_remind.remind_content
     *
     * @mbggenerated
     */
    public String getRemindContent() {
        return remindContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.remind_content
     *
     * @param remindContent the value for biz_remind.remind_content
     *
     * @mbggenerated
     */
    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent == null ? null : remindContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.remind_type
     *
     * @return the value of biz_remind.remind_type
     *
     * @mbggenerated
     */
    public Byte getRemindType() {
        return remindType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.remind_type
     *
     * @param remindType the value for biz_remind.remind_type
     *
     * @mbggenerated
     */
    public void setRemindType(Byte remindType) {
        this.remindType = remindType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.remind_time
     *
     * @return the value of biz_remind.remind_time
     *
     * @mbggenerated
     */
    public Date getRemindTime() {
        return remindTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.remind_time
     *
     * @param remindTime the value for biz_remind.remind_time
     *
     * @mbggenerated
     */
    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.remind_update_time
     *
     * @return the value of biz_remind.remind_update_time
     *
     * @mbggenerated
     */
    public Date getRemindUpdateTime() {
        return remindUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.remind_update_time
     *
     * @param remindUpdateTime the value for biz_remind.remind_update_time
     *
     * @mbggenerated
     */
    public void setRemindUpdateTime(Date remindUpdateTime) {
        this.remindUpdateTime = remindUpdateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.remind_add_user
     *
     * @return the value of biz_remind.remind_add_user
     *
     * @mbggenerated
     */
    public Long getRemindAddUser() {
        return remindAddUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.remind_add_user
     *
     * @param remindAddUser the value for biz_remind.remind_add_user
     *
     * @mbggenerated
     */
    public void setRemindAddUser(Long remindAddUser) {
        this.remindAddUser = remindAddUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_remind.delete_status
     *
     * @return the value of biz_remind.delete_status
     *
     * @mbggenerated
     */
    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_remind.delete_status
     *
     * @param deleteStatus the value for biz_remind.delete_status
     *
     * @mbggenerated
     */
    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}