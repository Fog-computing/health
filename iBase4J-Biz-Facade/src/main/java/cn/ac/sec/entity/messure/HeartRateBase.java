package cn.ac.sec.entity.messure;


public class HeartRateBase extends Base{
    private int heartRate;
    public static int HIGH = 100;
    public static int LOW = 60;
    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
}
