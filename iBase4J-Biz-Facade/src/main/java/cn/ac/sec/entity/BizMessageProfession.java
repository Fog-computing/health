package cn.ac.sec.entity;

import java.io.Serializable;
import java.util.Date;

public class BizMessageProfession implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_profession.id_
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_profession.alarm_id
     *
     * @mbggenerated
     */
    private Long alarmId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_profession.process_user
     *
     * @mbggenerated
     */
    private Long processUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_profession.process_content
     *
     * @mbggenerated
     */
    private String processContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_profession.process_date
     *
     * @mbggenerated
     */
    private Date processDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_profession.status_
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public BizMessageProfession(Long id, Long alarmId, Long processUser, String processContent, Date processDate, Byte status) {
        this.id = id;
        this.alarmId = alarmId;
        this.processUser = processUser;
        this.processContent = processContent;
        this.processDate = processDate;
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_profession
     *
     * @mbggenerated
     */
    public BizMessageProfession() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_profession.id_
     *
     * @return the value of biz_message_profession.id_
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_profession.id_
     *
     * @param id the value for biz_message_profession.id_
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_profession.alarm_id
     *
     * @return the value of biz_message_profession.alarm_id
     *
     * @mbggenerated
     */
    public Long getAlarmId() {
        return alarmId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_profession.alarm_id
     *
     * @param alarmId the value for biz_message_profession.alarm_id
     *
     * @mbggenerated
     */
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_profession.process_user
     *
     * @return the value of biz_message_profession.process_user
     *
     * @mbggenerated
     */
    public Long getProcessUser() {
        return processUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_profession.process_user
     *
     * @param processUser the value for biz_message_profession.process_user
     *
     * @mbggenerated
     */
    public void setProcessUser(Long processUser) {
        this.processUser = processUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_profession.process_content
     *
     * @return the value of biz_message_profession.process_content
     *
     * @mbggenerated
     */
    public String getProcessContent() {
        return processContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_profession.process_content
     *
     * @param processContent the value for biz_message_profession.process_content
     *
     * @mbggenerated
     */
    public void setProcessContent(String processContent) {
        this.processContent = processContent == null ? null : processContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_profession.process_date
     *
     * @return the value of biz_message_profession.process_date
     *
     * @mbggenerated
     */
    public Date getProcessDate() {
        return processDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_profession.process_date
     *
     * @param processDate the value for biz_message_profession.process_date
     *
     * @mbggenerated
     */
    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_profession.status_
     *
     * @return the value of biz_message_profession.status_
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_profession.status_
     *
     * @param status the value for biz_message_profession.status_
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}