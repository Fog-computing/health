package cn.ac.sec.entity.watch;


public class HealthSpo2 extends HealthData {

    /**
     * 血氧值
     * 比如90.5
     */
    private Float spo2;

    /**
     * 血流灌注指数
     */
    private Float pi;

    /**
     * 心率值
     */
    private Integer plus;

    /**
     * 测量时间
     */
    private Long measureDate;

    /**
     * @param spo2        血氧百分比比如90.5
     * @param pi          血流灌注指数
     * @param plus        心率值
     * @param measureDate
     */
    public HealthSpo2(Float spo2, Float pi, Integer plus, Long measureDate) {
        super ();
        this.spo2 = spo2;
        this.pi = pi;
        this.plus = plus;
        this.measureDate = measureDate;
        type = HEALTH_TYPE_SPO2;
    }


    /**
     * @return the spo2
     */
    public Float getSpo2() {
        return spo2;
    }

    /**
     * @param spo2 the spo2 to set
     */
    public void setSpo2(Float spo2) {
        this.spo2 = spo2;
    }

    /**
     * @return the pi
     */
    public Float getPi() {
        return pi;
    }

    /**
     * @param pi the pi to set
     */
    public void setPi(Float pi) {
        this.pi = pi;
    }

    /**
     * @return the plus
     */
    public Integer getPlus() {
        return plus;
    }

    /**
     * @param plus the plus to set
     */
    public void setPlus(Integer plus) {
        this.plus = plus;
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
        return "HealthSpo2 [spo2=" + spo2 + ", pi=" + pi + ", plus=" + plus
                + ", measureDate=" + measureDate + ", deviceId=" + deviceId
                + "]";
    }


}
