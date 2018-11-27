package cn.ac.sec.entity.watch;


public class NewChatMsgReq extends Device {
    public ChatMsg chatMsg;

    /**
     * @return the chatMsg
     */
    public ChatMsg getChatMsg() {
        return chatMsg;
    }

    /**
     * @param chatMsg the chatMsg to set
     */
    public void setChatMsg(ChatMsg chatMsg) {
        this.chatMsg = chatMsg;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NewChatMsgReq [chatMsg=" + chatMsg + ", deviceId=" + deviceId
                + "]";
    }


}
