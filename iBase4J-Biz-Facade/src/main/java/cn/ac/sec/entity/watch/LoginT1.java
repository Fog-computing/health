package cn.ac.sec.entity.watch;

public class LoginT1 extends Device {
    public static final int DEV_TYPE_T9 = 1;
    public static final int DEV_TYPE_T9s = 2;

    /**
     * 设备类型: T9 1, T9s 2;
     */
    public int devType;

    /**
     * @return the devType
     */
    public int getDevType() {
        return devType;
    }

    /**
     * @param devType the devType to set
     */
    public void setDevType(int devType) {
        this.devType = devType;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LoginT1 [devType=" + devType + ", deviceId=" + deviceId + "]";
    }


}
