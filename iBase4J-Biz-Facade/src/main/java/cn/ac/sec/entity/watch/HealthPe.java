package cn.ac.sec.entity.watch;


public class HealthPe extends HealthData {

    /**
     * 计步数(步)
     */
    private Integer stepCount;

    /**
     * 距离(米)
     */
    private double distance;

    /**
     * 消耗热量(卡)
     */
    private double calories;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 开始纬度
     */
    private Double startLat;

    /**
     * 开始经度
     */
    private Double startLng;

    /**
     * 结束纬度
     */
    private Double endLat;

    /**
     * 结束经度
     */
    private Double endLng;

    /**
     * @param stepCount 计步数(步)
     * @param distance
     * @param calories
     * @param startTime
     * @param endTime
     * @param startLat
     * @param startLng
     * @param endLat
     * @param endLng
     */
    public HealthPe(Integer stepCount, double distance,
                    double calories, Long startTime, Long endTime, Double startLat,
                    Double startLng, Double endLat, Double endLng) {
        super ();
        this.stepCount = stepCount;
        this.distance = distance;
        this.calories = calories;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;

        type = HEALTH_TYPE_PE;
    }


    public HealthPe() {
        super ();
        type = HEALTH_TYPE_PE;
    }


    /**
     * @return the stepCount
     */
    public Integer getStepCount() {
        return stepCount;
    }

    /**
     * @param stepCount the stepCount to set
     */
    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * @param calories the calories to set
     */
    public void setCalories(double calories) {
        this.calories = calories;
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
     * @return the startLat
     */
    public Double getStartLat() {
        return startLat;
    }

    /**
     * @param startLat the startLat to set
     */
    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }

    /**
     * @return the startLng
     */
    public Double getStartLng() {
        return startLng;
    }

    /**
     * @param startLng the startLng to set
     */
    public void setStartLng(Double startLng) {
        this.startLng = startLng;
    }

    /**
     * @return the endLat
     */
    public Double getEndLat() {
        return endLat;
    }

    /**
     * @param endLat the endLat to set
     */
    public void setEndLat(Double endLat) {
        this.endLat = endLat;
    }

    /**
     * @return the endLng
     */
    public Double getEndLng() {
        return endLng;
    }

    /**
     * @param endLng the endLng to set
     */
    public void setEndLng(Double endLng) {
        this.endLng = endLng;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HealthPe [stepCount=" + stepCount + ", distance=" + distance
                + ", calories=" + calories + ", startTime=" + startTime
                + ", endTime=" + endTime + ", startLat=" + startLat
                + ", startLng=" + startLng + ", endLat=" + endLat + ", endLng="
                + endLng + ", deviceId=" + deviceId + "]";
    }

}
