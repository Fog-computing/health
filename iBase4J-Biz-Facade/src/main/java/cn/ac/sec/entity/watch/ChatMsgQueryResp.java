package cn.ac.sec.entity.watch;

import java.util.List;

public class ChatMsgQueryResp extends BaseResp {

    public List<ChatMsg> chatMsgList;

    /**
     * @return the chatMsgList
     */
    public List<ChatMsg> getChatMsgList() {
        return chatMsgList;
    }

    /**
     * @param chatMsgList the chatMsgList to set
     */
    public void setChatMsgList(List<ChatMsg> chatMsgList) {
        this.chatMsgList = chatMsgList;
    }


}
