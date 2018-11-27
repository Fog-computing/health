package cn.ac.sec.entity;

public class BizMessagePositionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_position.user_
     *
     * @mbggenerated
     */
    private Long user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column biz_message_position.timestamp_
     *
     * @mbggenerated
     */
    private Integer timestamp;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_position
     *
     * @mbggenerated
     */
    public BizMessagePositionKey(Long user, Integer timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table biz_message_position
     *
     * @mbggenerated
     */
    public BizMessagePositionKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_position.user_
     *
     * @return the value of biz_message_position.user_
     *
     * @mbggenerated
     */
    public Long getUser() {
        return user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_position.user_
     *
     * @param user the value for biz_message_position.user_
     *
     * @mbggenerated
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column biz_message_position.timestamp_
     *
     * @return the value of biz_message_position.timestamp_
     *
     * @mbggenerated
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column biz_message_position.timestamp_
     *
     * @param timestamp the value for biz_message_position.timestamp_
     *
     * @mbggenerated
     */
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}