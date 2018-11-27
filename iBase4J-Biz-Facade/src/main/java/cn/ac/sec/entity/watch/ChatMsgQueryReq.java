package cn.ac.sec.entity.watch;


public class ChatMsgQueryReq extends Device {

    /**
     * 好友类型
     * 1-APP 2-手表  3-群好友
     */
    public int friendType;

    /**
     * 好友的Id
     */
    public int friendId;

    /**
     * 查询的开始的时间段
     */
    public long startTime;

    /**
     * 查询的结束的时间段
     */
    public long endTime;

    /**
     * @return the friendType
     */
    public int getFriendType() {
        return friendType;
    }

    /**
     * @param friendType the friendType to set
     */
    public void setFriendType(int friendType) {
        this.friendType = friendType;
    }

    /**
     * @return the friendId
     */
    public int getFriendId() {
        return friendId;
    }

    /**
     * @param friendId the friendId to set
     */
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


}
