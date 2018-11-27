package cn.ac.sec.entity.watch;

import java.io.Serializable;

public class CallRecord implements Serializable {

    /**
     * 时间
     */
    public Long callTime;

    /**
     * 时长(s)
     */
    public String phone;

    /**
     *
     */
    public int dulation;

    /**
     * 0-呼出，1-呼入
     */
    public int type;

    /**
     * @return the callTime
     */
    public Long getCallTime() {
        return callTime;
    }

    /**
     * @param callTime the callTime to set
     */
    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the dulation
     */
    public int getDulation() {
        return dulation;
    }

    /**
     * @param dulation the dulation to set
     */
    public void setDulation(int dulation) {
        this.dulation = dulation;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CallRecord [callTime=" + callTime + ", phone=" + phone
                + ", dulation=" + dulation + ", type=" + type + "]";
    }


}
