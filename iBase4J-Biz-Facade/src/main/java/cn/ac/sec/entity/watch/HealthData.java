package cn.ac.sec.entity.watch;

import java.io.Serializable;

public abstract class HealthData implements Serializable {
    public static final int HEALTH_TYPE_BP = 1;
    public static final int HEALTH_TYPE_HR = 2;
    public static final int HEALTH_TYPE_BS = 3;
    public static final int HEALTH_TYPE_PE = 4;
    public static final int HEALTH_TYPE_SL = 5;
    public static final int HEALTH_TYPE_TE = 6;
    public static final int HEALTH_TYPE_WTH = 7;
    public static final int HEALTH_TYPE_SPO2 = 8;

    public int type;

    public String deviceId;

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
