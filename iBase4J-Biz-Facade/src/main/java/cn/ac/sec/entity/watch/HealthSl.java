package cn.ac.sec.entity.watch;


public class HealthSl extends HealthData {

    /**
     * 入睡时间
     */
    private Long startTime;

    /**
     * 起床时间
     */
    private Long endTime;

    /**
     * 睡眠数据
     */
    private String sleepData;

    /**
     * 深度睡眠百分比
     */
    private Integer deep;

    /**
     * 安静睡眠百分比
     */
    private Integer quiet;

    /**
     * 最小心率值
     */
    private Integer minHr;

    /**
     * 最大心率值
     */
    private Integer maxHr;

    /**
     * 平均心率值
     */
    private Integer aveHr;

    /**
     * 睡眠时长（单位分）
     */
    private Integer duration;

    /**
     * 测量时间
     */
    private Long measureDate;


    public HealthSl(Long startTime, Long endTime, String sleepData,
                    Integer deep, Integer quiet, Integer minHr, Integer maxHr,
                    Integer aveHr, Integer duration, Long measureDate) {
        super ();
        this.startTime = startTime;
        this.endTime = endTime;
        this.sleepData = sleepData;
        this.deep = deep;
        this.quiet = quiet;
        this.minHr = minHr;
        this.maxHr = maxHr;
        this.aveHr = aveHr;
        this.duration = duration;
        this.measureDate = measureDate;

        type = HEALTH_TYPE_SL;
    }


    public HealthSl() {
        super ();
        type = HEALTH_TYPE_SL;
    }


    /**
     * @return the startTime
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the sleepData
     */
    public String getSleepData() {
        return sleepData;
    }

    /**
     * @param sleepData the sleepData to set
     */
    public void setSleepData(String sleepData) {
        this.sleepData = sleepData;
    }

    /**
     * @return the deep
     */
    public Integer getDeep() {
        return deep;
    }

    /**
     * @param deep the deep to set
     */
    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    /**
     * @return the quiet
     */
    public Integer getQuiet() {
        return quiet;
    }

    /**
     * @param quiet the quiet to set
     */
    public void setQuiet(Integer quiet) {
        this.quiet = quiet;
    }

    /**
     * @return the minHr
     */
    public Integer getMinHr() {
        return minHr;
    }

    /**
     * @param minHr the minHr to set
     */
    public void setMinHr(Integer minHr) {
        this.minHr = minHr;
    }

    /**
     * @return the maxHr
     */
    public Integer getMaxHr() {
        return maxHr;
    }

    /**
     * @param maxHr the maxHr to set
     */
    public void setMaxHr(Integer maxHr) {
        this.maxHr = maxHr;
    }

    /**
     * @return the aveHr
     */
    public Integer getAveHr() {
        return aveHr;
    }

    /**
     * @param aveHr the aveHr to set
     */
    public void setAveHr(Integer aveHr) {
        this.aveHr = aveHr;
    }

    /**
     * @return the duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
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
        return "HealthSl [startTime=" + startTime + ", endTime=" + endTime
                + ", sleepData=" + sleepData + ", deep=" + deep + ", quiet="
                + quiet + ", minHr=" + minHr + ", maxHr=" + maxHr + ", aveHr="
                + aveHr + ", duration=" + duration + ", measureDate="
                + measureDate + ", deviceId=" + deviceId + "]";
    }

}
