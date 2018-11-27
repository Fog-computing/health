package cn.ac.sec.entity.watch;


public class NewChatMsgResp extends BaseResp {

    public long saveTime;

    public int msgId;

    /**
     * @return the saveTime
     */
    public long getSaveTime() {
        return saveTime;
    }

    /**
     * @param saveTime the saveTime to set
     */
    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
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


}
