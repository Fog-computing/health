package cn.ac.sec.entity.watch;

import java.io.Serializable;

public class BatteryLow implements Serializable {

    public String deviceId;

    /**
     * 警报时间
     */
    private Long warnDate;

    /**
     * @return the warnDate
     */
    public Long getWarnDate() {
        return warnDate;
    }

    /**
     * @param warnDate the warnDate to set
     */
    public void setWarnDate(Long warnDate) {
        this.warnDate = warnDate;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


}
