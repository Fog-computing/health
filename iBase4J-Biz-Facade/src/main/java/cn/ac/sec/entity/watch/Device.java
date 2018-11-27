package cn.ac.sec.entity.watch;

import java.io.Serializable;

public class Device implements Serializable {

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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Device [deviceId=" + deviceId + "]";
    }


}
