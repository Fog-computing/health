package cn.ac.sec.entity.watch;


import java.io.Serializable;

public class Friend implements Serializable {

    public static final int FRIEND_TYPE_APP = 1;
    public static final int FRIEND_TYPE_WATCH = 2;
    public static final int FRIEND_TYPE_GROUP = 3;

    /**
     * 好友类型
     * 1-APP 2-手表  3-群好友
     */
    public int type;

    /**
     * 好友索引1、2、3、4
     */
    public int index;

    /**
     * 好友uid/gid
     */
    public int fid;

    /**
     * 好友号码：手机号或者家庭圈号（IMEI的后11位）
     */
    public String phone;

    /**
     * 好友的名称
     */
    public String name;

    /**
     * 好友的头像Url
     */
    public String headUrl;

    /**
     * 好友的在线离线状态
     * 1-在线， 0-离线
     */
    public int status;

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the fid
     */
    public int getFid() {
        return fid;
    }

    /**
     * @param fid the fid to set
     */
    public void setFid(int fid) {
        this.fid = fid;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the headUrl
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * @param headUrl the headUrl to set
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
