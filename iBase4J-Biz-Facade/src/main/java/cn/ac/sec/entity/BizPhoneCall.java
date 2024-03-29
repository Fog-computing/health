package cn.ac.sec.entity;

import java.io.Serializable;
import java.util.Date;

public class BizPhoneCall implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_phone_call.id_
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_phone_call.consumer_
     *
     * @mbggenerated
     */
    private Long consumer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_phone_call.operator_
     *
     * @mbggenerated
     */
    private Long operator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_phone_call.call_time
     *
     * @mbggenerated
     */
    private Date callTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_phone_call.save_time
     *
     * @mbggenerated
     */
    private Date saveTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public BizPhoneCall(Long id, Long consumer, Long operator, Date callTime, Date saveTime) {
        this.id = id;
        this.consumer = consumer;
        this.operator = operator;
        this.callTime = callTime;
        this.saveTime = saveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_phone_call
     *
     * @mbggenerated
     */
    public BizPhoneCall() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_phone_call.id_
     *
     * @return the value of biz_phone_call.id_
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_phone_call.id_
     *
     * @param id the value for biz_phone_call.id_
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_phone_call.consumer_
     *
     * @return the value of biz_phone_call.consumer_
     *
     * @mbggenerated
     */
    public Long getConsumer() {
        return consumer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_phone_call.consumer_
     *
     * @param consumer the value for biz_phone_call.consumer_
     *
     * @mbggenerated
     */
    public void setConsumer(Long consumer) {
        this.consumer = consumer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_phone_call.operator_
     *
     * @return the value of biz_phone_call.operator_
     *
     * @mbggenerated
     */
    public Long getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_phone_call.operator_
     *
     * @param operator the value for biz_phone_call.operator_
     *
     * @mbggenerated
     */
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_phone_call.call_time
     *
     * @return the value of biz_phone_call.call_time
     *
     * @mbggenerated
     */
    public Date getCallTime() {
        return callTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_phone_call.call_time
     *
     * @param callTime the value for biz_phone_call.call_time
     *
     * @mbggenerated
     */
    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_phone_call.save_time
     *
     * @return the value of biz_phone_call.save_time
     *
     * @mbggenerated
     */
    public Date getSaveTime() {
        return saveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_phone_call.save_time
     *
     * @param saveTime the value for biz_phone_call.save_time
     *
     * @mbggenerated
     */
    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }
}