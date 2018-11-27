package cn.ac.sec.entity.messure;


public class BloodOxygen extends HeartRateBase {
    private float Oxygen;
    public static final float OXYGEN_L = 90F;
    public float getOxygen() {
        return Oxygen;
    }

    public void setOxygen(float oxygen) {
        Oxygen = oxygen;
    }
}
