package cn.ac.sec.protocol.message;

import java.util.Date;

public class FallMessage {
    private String id;
    private String warning;
    private int bat;
    private int temp;
    private Date time;
    private Double gpsLongitude;
    private Double gpsLatitude;
    private Double lbsLongitude;
    private Double lbsLatitude;
    private int medicine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public int getBat() {
        return bat;
    }

    public void setBat(int bat) {
        this.bat = bat;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(Double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public Double getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(Double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public Double getLbsLongitude() {
        return lbsLongitude;
    }

    public void setLbsLongitude(Double lbsLongitude) {
        this.lbsLongitude = lbsLongitude;
    }

    public Double getLbsLatitude() {
        return lbsLatitude;
    }

    public void setLbsLatitude(Double lbsLatitude) {
        this.lbsLatitude = lbsLatitude;
    }

    public int getMedicine() {
        return medicine;
    }

    public void setMedicine(int medicine) {
        this.medicine = medicine;
    }
}
