package cn.ac.sec.entity.watch;


public class HealthBs extends HealthData {
    /**
     * 血糖类型：0-未确认; 1-餐前; 2-餐后;
     */
    public static final int BS_TYPE_NOT_CONFIRMED = 0;
    public static final int BS_TYPE_BEFORE_MEAL = 1;
    public static final int BS_TYPE_AFTER_MEAL = 2;

    /**
     * 血糖值
     */
    private Float bloodSugar;


    /**
     * 血糖类型
     */
    private int bsType;

    /**
     * 测量时间
     */
    private Long measureDate;


    /**
     * 健康数据血糖
     *
     * @param bloodSugar  血糖值
     * @param bsType      血糖类型：0-未确认; 1-餐前; 2-餐后;
     * @param measureDate 测量时间
     */
    public HealthBs(Float bloodSugar, int bsType, Long measureDate) {
        super ();
        this.bloodSugar = bloodSugar;
        this.bsType = bsType;
        this.measureDate = measureDate;

        type = HEALTH_TYPE_BS;
    }


    public HealthBs() {
        super ();
        type = HEALTH_TYPE_BS;
    }


    /**
     * @return the bloodSugar
     */
    public Float getBloodSugar() {
        return bloodSugar;
    }

    /**
     * @param bloodSugar the bloodSugar to set
     */
    public void setBloodSugar(Float bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    /**
     * @return the bsType
     */
    public int getBsType() {
        return bsType;
    }

    /**
     * @param bsType the bsType to set
     */
    public void setBsType(int bsType) {
        this.bsType = bsType;
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
        return "HealthBs [bloodSugar=" + bloodSugar + ", bsType=" + bsType
                + ", measureDate=" + measureDate + ", deviceId=" + deviceId
                + "]";
    }

}
