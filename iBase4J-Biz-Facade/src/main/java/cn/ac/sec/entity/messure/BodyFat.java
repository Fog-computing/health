package cn.ac.sec.entity.messure;

public class BodyFat extends Base{
    private float weight;
    private float BMI;
    private float bodyFat;
    public static final float MALEHIGH = 18;
    public static final float FEMALEHIGH = 25;
    public float getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(float bodyFat) {
        this.bodyFat = bodyFat;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBMI() {
        return BMI;
    }

    public void setBMI(float BMI) {
        this.BMI = BMI;
    }
}
