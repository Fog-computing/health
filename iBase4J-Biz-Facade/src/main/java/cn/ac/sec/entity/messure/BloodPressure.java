package cn.ac.sec.entity.messure;


//血压类
public class BloodPressure extends HeartRateBase {
    private int lowPressure;
    private int highPressure;
    public final static int LOW_L=60;
    public final static int LOW_H=90;
    public final static int HIGH_L=90;
    public final static int HIGH_H=140;
    public int getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(int lowPressure) {
        this.lowPressure = lowPressure;
    }

    public int getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(int highPressure) {
        this.highPressure = highPressure;
    }
}
