package cn.ac.sec.entity.transport;

public class DeviceTransport {
    private int id;
    private int deviceName;
    private String deviceUsername;
    private String userPhoneNum;
    private Long user_id;
    private Long imei;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(int deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceUsername() {
        return deviceUsername;
    }

    public void setDeviceUsername(String deviceUsername) {
        this.deviceUsername = deviceUsername;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }
}
