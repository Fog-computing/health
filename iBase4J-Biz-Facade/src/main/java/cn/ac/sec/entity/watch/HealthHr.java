package cn.ac.sec.entity.watch;


public class HealthHr extends HealthData {

    /**
     * 心率值
     */
    private Integer heartRate;

    /**
     * 测量时间
     */
    private Long measureDate;


    /**
     * 健康数据心率
     *
     * @param heartRate   心率值
     * @param measureDate 测量时间
     */
    public HealthHr(Integer heartRate, Long measureDate) {
        super ();
        this.heartRate = heartRate;
        this.measureDate = measureDate;
        type = HEALTH_TYPE_HR;
    }


    public HealthHr() {
        super ();
        type = HEALTH_TYPE_HR;
    }


    /**
     * @return the heartRate
     */
    public Integer getHeartRate() {
        return heartRate;
    }

    /**
     * @param heartRate the heartRate to set
     */
    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
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
        return "HealthHr [heartRate=" + heartRate + ", measureDate="
                + measureDate + ", deviceId=" + deviceId + "]";
    }


}
