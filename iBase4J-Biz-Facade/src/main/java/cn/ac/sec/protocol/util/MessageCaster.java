package cn.ac.sec.protocol.util;

import cn.ac.sec.protocol.message.FallMessage;
import cn.ac.sec.protocol.message.InMessage;
import cn.ac.sec.protocol.message.Message;
import cn.ac.sec.protocol.message.OutMessage;
import cn.ac.sec.util.CommonUtil;
import org.ibase4j.core.exception.LoginException;
import org.jetbrains.annotations.NotNull;
import org.redisson.api.listener.MessageListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessageCaster {
    private static final String PATTERN="yyyy-MM-dd HH:mm:ss";
    private static final String FALLPATTERN="HH:mm:ss";
    public static List<Message>  getMessageFromString(StringBuilder oriMessage){
        List<Message> list = new ArrayList<> ();
        SimpleDateFormat sdf= new SimpleDateFormat (PATTERN);
        String messageListString=oriMessage.toString ();
        String messageList[] = messageListString.split ("\r\n");
        List<String> messages=Arrays.asList (messageList);
        for (String messageString :messages){
            System.out.println (messageString);
            Message message = new InMessage ();
            messageString =messageString.substring (1,messageString.length ()-1);
            String messageArray[]=messageString.split (",");
            if(messageArray.length<6)break;
            List<String> paramList= Arrays.asList (messageArray);
            message.setVersion (paramList.get (0));
            message.setIdentifier (paramList.get (1));
            try {
                message.setMessageTime (sdf.parse (paramList.get (4)));
            } catch (ParseException e) {
                e.printStackTrace ();
            }
            if(paramList.get (6)!=null&&!paramList.get (6).equals (""))
                message.setIMEI (Long.parseLong (paramList.get (6)));
            message.setType (Integer.parseInt (paramList.get (8).substring (1)));
            if(paramList.size ()==9) list.add (message);
            else {
                message.setParams (paramList.subList (9,paramList.size ()));
                list.add (message);
            }
        }
        //低电报警，可能会产生IMEI读取不到的问题
        return list;
    }

    @NotNull
    public static String getStringFromMessage (Message message){
        StringBuilder buffer=new StringBuilder ();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat (PATTERN);
        //协议头
        buffer.append ('[');
        //版本号
        buffer.append (message.getVersion ()).append (',');
        //唯一标识符
        buffer.append (message.getIdentifier ()).append (',');
        //加密方式，校验值
        buffer.append ("1,abcd,");
        //消息时间
        buffer.append (simpleDateFormat.format (message.getMessageTime ())).append (',');
        //IMEI值
        buffer.append (message.getIMEI ()).append (',');
        //报文类型
        buffer.append ('S').append (message.getType ());
        //下行命令无参数
        if(message.getParams ()==null||message.getParams ().size ()==0)
            return buffer.append (']').toString ();
        //下行命令添加参数
        else
            buffer.append (',');
        if(message.getType ()==OutMessage.SOSNUMDETAIL){
            for (int i=0;i<message.getParams ().size ();i++){
                if(i%2==0)buffer.append (message.getParams ().get (i)).append (',');
                else {
                    buffer.append (message.getParams ().get (i));
                    if(i!=message.getParams ().size ()-1)buffer.append (';');
                }
            }
            buffer.append (']');
            return buffer.toString ();
        }
        for(String param : message.getParams ()){
            buffer.append (param);
            //如果不是最后一个参数，用 , 隔开
            if(!(message.getParams ().lastIndexOf (param)==message.getParams ().size ()-1)) buffer.append (',');
        }
        buffer.append (']');
        return buffer.toString ();
    }

    public static FallMessage getFallMessageFromString(StringBuilder builder){
        String messageString=builder.toString ();
        System.out.println (messageString);
        messageString=messageString.substring (0,messageString.length ()-3);
        FallMessage message =new FallMessage ();
        List<String> messageList=Arrays.asList ( messageString.split (","));
        message.setId (messageList.get (0).split (":")[1]);
        message.setWarning (messageList.get (1));
        message.setBat (Integer.parseInt (messageList.get (2)));
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat (FALLPATTERN);
        try {
            Date date =simpleDateFormat.parse (messageList.get (4));
            date = CommonUtil.nowFallDownTime (date);
            message.setTime (date);
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        message.setGpsLatitude (Double.parseDouble (messageList.get (5).substring (1)));
        message.setGpsLongitude (Double.parseDouble (messageList.get (6).substring (1)));
        message.setLbsLatitude (Double.parseDouble (messageList.get (8).substring (1)));
        message.setLbsLongitude (Double.parseDouble (messageList.get (9).substring (1)));
        if(messageList.size ()>10) message.setMedicine (Integer.parseInt (messageList.get (10)));
        return message;
    }

    public static void multiMessageBuilder(){

    }
}
