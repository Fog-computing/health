package cn.ac.sec.entity;

import java.io.Serializable;
import java.util.Date;

public class BizFallDevice implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.id_
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.imei_
     *
     * @mbggenerated
     */
    private String imei;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.user_
     *
     * @mbggenerated
     */
    private Long user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.last_online_time
     *
     * @mbggenerated
     */
    private Date lastOnlineTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.phone_num
     *
     * @mbggenerated
     */
    private String phoneNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.add_user
     *
     * @mbggenerated
     */
    private Long addUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.update_status
     *
     * @mbggenerated
     */
    private Byte updateStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.battery_
     *
     * @mbggenerated
     */
    private Byte battery;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_fall_device.bind_phone_num
     *
     * @mbggenerated
     */
    private String bindPhoneNum;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    public BizFallDevice(Long id, String imei, Long user, Date lastOnlineTime, String phoneNum, Long addUser, Byte updateStatus, Byte battery, String bindPhoneNum) {
        this.id = id;
        this.imei = imei;
        this.user = user;
        this.lastOnlineTime = lastOnlineTime;
        this.phoneNum = phoneNum;
        this.addUser = addUser;
        this.updateStatus = updateStatus;
        this.battery = battery;
        this.bindPhoneNum = bindPhoneNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_fall_device
     *
     * @mbggenerated
     */
    public BizFallDevice() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.id_
     *
     * @return the value of biz_fall_device.id_
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.id_
     *
     * @param id the value for biz_fall_device.id_
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.imei_
     *
     * @return the value of biz_fall_device.imei_
     *
     * @mbggenerated
     */
    public String getImei() {
        return imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.imei_
     *
     * @param imei the value for biz_fall_device.imei_
     *
     * @mbggenerated
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.user_
     *
     * @return the value of biz_fall_device.user_
     *
     * @mbggenerated
     */
    public Long getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.user_
     *
     * @param user the value for biz_fall_device.user_
     *
     * @mbggenerated
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.last_online_time
     *
     * @return the value of biz_fall_device.last_online_time
     *
     * @mbggenerated
     */
    public Date getLastOnlineTime() {
        return lastOnlineTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.last_online_time
     *
     * @param lastOnlineTime the value for biz_fall_device.last_online_time
     *
     * @mbggenerated
     */
    public void setLastOnlineTime(Date lastOnlineTime) {
        this.lastOnlineTime = lastOnlineTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.phone_num
     *
     * @return the value of biz_fall_device.phone_num
     *
     * @mbggenerated
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.phone_num
     *
     * @param phoneNum the value for biz_fall_device.phone_num
     *
     * @mbggenerated
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.add_user
     *
     * @return the value of biz_fall_device.add_user
     *
     * @mbggenerated
     */
    public Long getAddUser() {
        return addUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.add_user
     *
     * @param addUser the value for biz_fall_device.add_user
     *
     * @mbggenerated
     */
    public void setAddUser(Long addUser) {
        this.addUser = addUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.update_status
     *
     * @return the value of biz_fall_device.update_status
     *
     * @mbggenerated
     */
    public Byte getUpdateStatus() {
        return updateStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.update_status
     *
     * @param updateStatus the value for biz_fall_device.update_status
     *
     * @mbggenerated
     */
    public void setUpdateStatus(Byte updateStatus) {
        this.updateStatus = updateStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.battery_
     *
     * @return the value of biz_fall_device.battery_
     *
     * @mbggenerated
     */
    public Byte getBattery() {
        return battery;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.battery_
     *
     * @param battery the value for biz_fall_device.battery_
     *
     * @mbggenerated
     */
    public void setBattery(Byte battery) {
        this.battery = battery;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_fall_device.bind_phone_num
     *
     * @return the value of biz_fall_device.bind_phone_num
     *
     * @mbggenerated
     */
    public String getBindPhoneNum() {
        return bindPhoneNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_fall_device.bind_phone_num
     *
     * @param bindPhoneNum the value for biz_fall_device.bind_phone_num
     *
     * @mbggenerated
     */
    public void setBindPhoneNum(String bindPhoneNum) {
        this.bindPhoneNum = bindPhoneNum == null ? null : bindPhoneNum.trim();
    }
}