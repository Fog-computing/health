/*
 * lbs signal 信号强度, 取值范围：0到-113dbm
 * 各地图API坐标系统比较与转换;
 * WGS84坐标系：即地球坐标系，国际上通用的坐标系。设备一般包含GPS芯片或者北斗芯片获取的经纬度为WGS84地理坐标系,
 * 谷歌地图采用的是WGS84地理坐标系（中国范围除外）;
 * GCJ02坐标系：即火星坐标系，是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系。
 * 谷歌中国地图和搜搜中国地图采用的是GCJ02地理坐标系; BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系;
 * 搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。
 */

package cn.ac.sec.entity.watch;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * lbs signal 信号强度, 取值范围：0到-113dbm
 * 各地图API坐标系统比较与转换;
 * WGS84坐标系：即地球坐标系，国际上通用的坐标系。设备一般包含GPS芯片或者北斗芯片获取的经纬度为WGS84地理坐标系,
 * 谷歌地图采用的是WGS84地理坐标系（中国范围除外）;
 * GCJ02坐标系：即火星坐标系，是由中国国家测绘局制订的地理信息系统的坐标系统。由WGS84坐标系经加密后的坐标系。
 * 谷歌中国地图和搜搜中国地图采用的是GCJ02地理坐标系; BD09坐标系：即百度坐标系，GCJ02坐标系经加密后的坐标系;
 * 搜狗坐标系、图吧坐标系等，估计也是在GCJ02基础上加密而成的。
 */

public class Location implements Serializable {


    private String deviceId;

    /*
     * T86和T94才会有值
     * 电量
     */
    private Integer voltage;

    private String address;

    public static final int TYPE_GPS = 0;
    public static final int TYPE_LBS = 1;
    public static final int TYPE_GPS_LBS = 2;
    public static final int TYPE_WIFI = 3;
    public static final int TYPE_GPS_WIFI = 4;
    public static final int TYPE_LBS_WIFI = 5;
    public static final int TYPE_GPS_LBS_WIFI = 6;

    /*
     * WGS84
     */
    private Double lng;

    /*
     * WGS84
     */
    private Double lat;

    /*
     * 移动用户所属国家代码，默认值为460
     */
    private String mcc;

    /*
     * 移动网号, 中国移动：0；中国联通：1
     */
    private String mnc;

    /*
     * 周边基站信息(lac,cellid,arfcn,rxLev)
     * 693d|6c22|79|35
     * lacX：基站X的小区编号，16进制
     * cellidX：基站X的基站编号，16进制
     * arfcnX：基站X的信号频点，10进制
     * rxlevX：基站X的信号强度值，10进制
     */
    private List<String> nearbts;

    /*
     * wifi列表mac信息
     * mac地址,信号强度,wifi名称
     */
    private List<String> macs;

    /*
     * 高度
     */
    private Double height;

    /*
     * 速度
     */
    private Double speed;

    /*
     * 方向，角度
     */
    private Float direction;

    /*
     * gps 卫星个数
     */
    private Integer gpsStar;

    private Integer type;//0 gps; 1 lbs;  2 gps&lbs;

    private Date measureDate;

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * @return the lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @return the mcc
     */
    public String getMcc() {
        return mcc;
    }

    /**
     * @return the mnc
     */
    public String getMnc() {
        return mnc;
    }

    /**
     * @return the nearbts
     */
    public List<String> getNearbts() {
        return nearbts;
    }

    /**
     * @return the height
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @return the speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @return the direction
     */
    public Float getDirection() {
        return direction;
    }

    /**
     * @return the gpsStar
     */
    public Integer getGpsStar() {
        return gpsStar;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @return the measureDate
     */
    public Date getMeasureDate() {
        return measureDate;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * @param mcc the mcc to set
     */
    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    /**
     * @param mnc the mnc to set
     */
    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    /**
     * @param nearbts the nearbts to set
     */
    public void setNearbts(List<String> nearbts) {
        this.nearbts = nearbts;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Float direction) {
        this.direction = direction;
    }

    /**
     * @param gpsStar the gpsStar to set
     */
    public void setGpsStar(Integer gpsStar) {
        this.gpsStar = gpsStar;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @param measureDate the measureDate to set
     */
    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }


    /**
     * @return the voltage
     */
    public Integer getVoltage() {
        return voltage;
    }

    /**
     * @param voltage the voltage to set
     */
    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }


    /**
     * @return the macs
     */
    public List<String> getMacs() {
        return macs;
    }

    /**
     * @param macs the macs to set
     */
    public void setMacs(List<String> macs) {
        this.macs = macs;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Location [deviceId=" + deviceId + ", voltage=" + voltage
                + ", address=" + address + ", lng=" + lng + ", lat=" + lat
                + ", mcc=" + mcc + ", mnc=" + mnc + ", nearbts=" + nearbts
                + ", macs=" + macs + ", height=" + height + ", speed=" + speed
                + ", direction=" + direction + ", gpsStar=" + gpsStar
                + ", type=" + type + ", measureDate=" + measureDate + "]";
    }

}
