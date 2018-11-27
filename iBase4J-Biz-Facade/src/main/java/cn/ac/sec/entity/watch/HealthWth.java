package cn.ac.sec.entity.watch;


public class HealthWth extends HealthData {

    /**
     * 体重值 65.5KG
     */
    private Float weight;

    /**
     * 体脂百分比  88.8f
     */
    private Float bodyFat;

    /**
     * 测量时间
     */
    private Long measureDate;

    /**
     * @param weight      体重 65.5KG
     * @param bodyFat     体脂百分比 88.8f
     * @param measureDate
     */
    public HealthWth(Float weight, Float bodyFat, Long measureDate) {
        super ();
        this.weight = weight;
        this.bodyFat = bodyFat;
        this.measureDate = measureDate;
        type = HEALTH_TYPE_WTH;
    }

    /**
     * @return the weight
     */
    public Float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    /**
     * @return the bodyFat
     */
    public Float getBodyFat() {
        return bodyFat;
    }

    /**
     * @param bodyFat the bodyFat to set
     */
    public void setBodyFat(Float bodyFat) {
        this.bodyFat = bodyFat;
    }

    /**
     * @return the measureDate
     */
    public Long getMeasureDate() {
        return measureDate;
    }

    /**
     * @param measureDate the measureDate to set
     */
    public void setMeasureDate(Long measureDate) {
        this.measureDate = measureDate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HealthWth [weight=" + weight + ", bodyFat=" + bodyFat
                + ", measureDate=" + measureDate + ", deviceId=" + deviceId
                + "]";
    }

}
