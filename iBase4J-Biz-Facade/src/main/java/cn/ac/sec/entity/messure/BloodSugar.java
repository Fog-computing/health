package cn.ac.sec.entity.messure;

public class BloodSugar extends Base{
    private float bloodSugarValue;
    public static final float BS_L=3.9F;
    public static final float BS_H=6.1F;
    public static final float DIABETES=11.1F;

    public float getBloodSugarValue() {
        return bloodSugarValue;
    }

    public void setBloodSugarValue(float bloodSugarValue) {
        this.bloodSugarValue = bloodSugarValue;
    }

}
