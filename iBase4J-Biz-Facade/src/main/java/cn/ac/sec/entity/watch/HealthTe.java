package cn.ac.sec.entity.watch;


public class HealthTe extends HealthData {

    /**
     * 体温值
     */
    private Float temperature;

    /**
     * 测量时间
     */
    private Long measureDate;


    /**
     * 健康数据体温
     *
     * @param temperature 体温值
     * @param measureDate 测量时间
     */
    public HealthTe(Float temperature, Long measureDate) {
        super ();
        this.temperature = temperature;
        this.measureDate = measureDate;
        type = HEALTH_TYPE_TE;
    }


    public HealthTe() {
        super ();
        type = HEALTH_TYPE_TE;
    }


    /**
     * @return the temperature
     */
    public Float getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(Float temperature) {
        this.temperature = temperature;
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
        return "HealthTe [temperature=" + temperature + ", measureDate="
                + measureDate + ", deviceId=" + deviceId + "]";
    }

}
