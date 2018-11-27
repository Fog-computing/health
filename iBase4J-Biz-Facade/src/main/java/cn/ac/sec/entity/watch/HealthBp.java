package cn.ac.sec.entity.watch;


public class HealthBp extends HealthData {

    /**
     * 收缩压
     */
    private Integer systolic;

    /**
     * 舒张压
     */
    private Integer diastolic;

    /**
     * 心率/脉搏
     */
    private Integer pulse;

    /**
     * 脉压差
     */
    private Integer pulsePressure;

    /**
     * 平均动脉差
     */
    private Integer aveArterialDiff;

    /**
     * 测量时间
     */
    private Long measureDate;


    /**
     * 健康数据血压
     *
     * @param systolic        收缩压
     * @param diastolic       舒张压
     * @param pulse           脉搏
     * @param pulsePressure   脉压差
     * @param aveArterialDiff 平均动脉差
     * @param measureDate     测量时间
     */
    public HealthBp(Integer systolic, Integer diastolic, Integer pulse,
                    Integer pulsePressure, Integer aveArterialDiff, Long measureDate) {
        super ();
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.pulsePressure = pulsePressure;
        this.aveArterialDiff = aveArterialDiff;
        this.measureDate = measureDate;

        type = HEALTH_TYPE_BP;
    }


    public HealthBp() {
        super ();
        type = HEALTH_TYPE_BP;
    }


    /**
     * @return the systolic
     */
    public Integer getSystolic() {
        return systolic;
    }

    /**
     * @param systolic the systolic to set
     */
    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    /**
     * @return the diastolic
     */
    public Integer getDiastolic() {
        return diastolic;
    }

    /**
     * @param diastolic the diastolic to set
     */
    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    /**
     * @return the pulse
     */
    public Integer getPulse() {
        return pulse;
    }

    /**
     * @param pulse the pulse to set
     */
    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    /**
     * @return the pulsePressure
     */
    public Integer getPulsePressure() {
        return pulsePressure;
    }

    /**
     * @param pulsePressure the pulsePressure to set
     */
    public void setPulsePressure(Integer pulsePressure) {
        this.pulsePressure = pulsePressure;
    }

    /**
     * @return the aveArterialDiff
     */
    public Integer getAveArterialDiff() {
        return aveArterialDiff;
    }

    /**
     * @param aveArterialDiff the aveArterialDiff to set
     */
    public void setAveArterialDiff(Integer aveArterialDiff) {
        this.aveArterialDiff = aveArterialDiff;
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
        return "HealthBp [systolic=" + systolic + ", diastolic=" + diastolic
                + ", pulse=" + pulse + ", pulsePressure=" + pulsePressure
                + ", aveArterialDiff=" + aveArterialDiff + ", measureDate="
                + measureDate + ", deviceId=" + deviceId + "]";
    }

}

