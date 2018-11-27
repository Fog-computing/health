package cn.ac.sec.entity.watch;


import java.io.Serializable;

public class ChatMsg implements Serializable {

    public static final int MSG_TYPE_TEXT = 1;
    public static final int MSG_TYPE_PIC = 2;
    public static final int MSG_TYPE_AUDIO = 3;
    public static final int MSG_TYPE_URL = 4;
    public static final int MSG_TYPE_OTHER = 5;

    public static final int DIRECTION_SEND = 1;
    public static final int DIRECTION_RECEIVE = 2;

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
     * 消息方向 1—发送   2—接收
     */
    public int direction;

    /**
     * 消息类型
     * 1-文字消息; 2-图片消息; 3-语音消息; 4-url; 5-其他类型文件消息
     */
    public int msgType;

    /**
     * 消息Id
     */
    public int msgId;

    /**
     * 发送时间utc时间到秒
     */
    public int sendTime;

    /**
     * 消息内容
     */
    public String msgContent;


    /**
     * 语音文件和图片文件的附加信息
     */
    public FileExtra fileExtra;


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
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the msgType
     */
    public int getMsgType() {
        return msgType;
    }

    /**
     * @param msgType the msgType to set
     */
    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the msgId
     */
    public int getMsgId() {
        return msgId;
    }

    /**
     * @param msgId the msgId to set
     */
    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    /**
     * @return the sendTime
     */
    public int getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime the sendTime to set
     */
    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * @return the fileExtra
     */
    public FileExtra getFileExtra() {
        return fileExtra;
    }

    /**
     * @param fileExtra the fileExtra to set
     */
    public void setFileExtra(FileExtra fileExtra) {
        this.fileExtra = fileExtra;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChatMsg [friendType=" + friendType + ", friendId=" + friendId
                + ", direction=" + direction + ", msgType=" + msgType
                + ", msgId=" + msgId + ", sendTime=" + sendTime
                + ", msgContent=" + msgContent + ", fileExtra=" + fileExtra
                + "]";
    }


}
