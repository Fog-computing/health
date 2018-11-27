package cn.ac.sec.entity;

import java.io.Serializable;
import java.util.Date;

public class BizDeviceData extends BizDeviceDataKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_data_9.datetime_update
     *
     * @mbggenerated
     */
    private Date datetimeUpdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_data_9.user_id
     *
     * @mbggenerated
     */

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_data_9.data_
     *
     * @mbggenerated
     */
    private String data;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_data_9
     *
     * @mbggenerated
     */

    private String tableName;

    public BizDeviceData(Date datetimeMeasure, Date datetimeUpdate, Long userId, String data) {
        super(userId, datetimeMeasure);
        this.datetimeUpdate = datetimeUpdate;
        this.data = data;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_data_9
     *
     * @mbggenerated
     */
    public BizDeviceData() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_data_9.datetime_update
     *
     * @return the value of biz_data_9.datetime_update
     *
     * @mbggenerated
     */
    public Date getDatetimeUpdate() {
        return datetimeUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_data_9.datetime_update
     *
     * @param datetimeUpdate the value for biz_data_9.datetime_update
     *
     * @mbggenerated
     */
    public void setDatetimeUpdate(Date datetimeUpdate) {
        this.datetimeUpdate = datetimeUpdate;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_data_9.data_
     *
     * @return the value of biz_data_9.data_
     *
     * @mbggenerated
     */
    public String getData() {
        return data;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_data_9.data_
     *
     * @param data the value for biz_data_9.data_
     *
     * @mbggenerated
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}