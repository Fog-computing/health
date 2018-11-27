package cn.ac.sec.protocol.util;

import cn.ac.sec.protocol.message.Message;
import cn.ac.sec.protocol.message.OutMessage;

public class MessageFilter{

    //消息回调中用于剔除手表响应数据
    public static boolean watchResponseCheck(Message message){
        if(message.getType ()==OutMessage.HRDETECT)return true;
        if(message.getType ()==OutMessage.BIND)return true;
        if(message.getType ()==OutMessage.HRRATE)return true;
        if(message.getType ()==OutMessage.INFOSET)return true;
        if(message.getType ()==OutMessage.POSITIONDETECT)return true;
        if(message.getType ()==OutMessage.POSITIONSCHEDULE)return true;
        if(message.getType ()==OutMessage.QUICKNUM)return true;
        if(message.getType ()==OutMessage.SILENCE)return true;
        if(message.getType ()==OutMessage.STEPSET)return true;
        if(message.getType ()==OutMessage.SOSNUMDETAIL)return true;
        if(message.getType ()==OutMessage.SOSNUM)return true;
        if(message.getType ()==OutMessage.SLEEPSET) return true;
        if(message.getType ()==OutMessage.SITSET) return true;
        if(message.getType ()==OutMessage.REMIND) return true;
        if(message.getType ()==OutMessage.DE_REMIND) return true;
        return false;

    }
}

