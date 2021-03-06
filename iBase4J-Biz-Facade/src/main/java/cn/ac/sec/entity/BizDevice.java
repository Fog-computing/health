package cn.ac.sec.entity;

import java.io.Serializable;
import java.util.Date;

public class BizDevice implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_device.id_
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_device.device_code
     *
     * @mbggenerated
     */
    private String deviceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_device.device_type
     *
     * @mbggenerated
     */
    private String deviceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_device.add_user
     *
     * @mbggenerated
     */
    private Long addUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_device.add_time
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_device.user_
     *
     * @mbggenerated
     */
    private Long user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_device.IMEI_
     *
     * @mbggenerated
     */
    private Long imei;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_device
     *
     * @mbggenerated
     */
    public BizDevice(Long id, String deviceCode, String deviceType, Long addUser, Date addTime, Long user, Long imei) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.deviceType = deviceType;
        this.addUser = addUser;
        this.addTime = addTime;
        this.user = user;
        this.imei = imei;
    }
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_device
     *
     * @mbggenerated
     */
    public BizDevice() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_device.id_
     *
     * @return the value of biz_device.id_
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_device.id_
     *
     * @param id the value for biz_device.id_
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_device.device_code
     *
     * @return the value of biz_device.device_code
     *
     * @mbggenerated
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_device.device_code
     *
     * @param deviceCode the value for biz_device.device_code
     *
     * @mbggenerated
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_device.device_type
     *
     * @return the value of biz_device.device_type
     *
     * @mbggenerated
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_device.device_type
     *
     * @param deviceType the value for biz_device.device_type
     *
     * @mbggenerated
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_device.add_user
     *
     * @return the value of biz_device.add_user
     *
     * @mbggenerated
     */
    public Long getAddUser() {
        return addUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_device.add_user
     *
     * @param addUser the value for biz_device.add_user
     *
     * @mbggenerated
     */
    public void setAddUser(Long addUser) {
        this.addUser = addUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_device.add_time
     *
     * @return the value of biz_device.add_time
     *
     * @mbggenerated
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_device.add_time
     *
     * @param addTime the value for biz_device.add_time
     *
     * @mbggenerated
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_device.user_
     *
     * @return the value of biz_device.user_
     *
     * @mbggenerated
     */
    public Long getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_device.user_
     *
     * @param user the value for biz_device.user_
     *
     * @mbggenerated
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_device.IMEI_
     *
     * @return the value of biz_device.IMEI_
     *
     * @mbggenerated
     */
    public Long getImei() {
        return imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_device.IMEI_
     *
     * @param imei the value for biz_device.IMEI_
     *
     * @mbggenerated
     */
    public void setImei(Long imei) {
        this.imei = imei;
    }
}