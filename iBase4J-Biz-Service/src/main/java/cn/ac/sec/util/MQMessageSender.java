package cn.ac.sec.util;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class MQMessageSender {
    private static final String USER = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL = "failover://tcp://220.195.3.162:61616";
    private static final String SUBJECT = "alarm";
    private static final String POSTION = "position";
    private static Destination destination = null;
    private static Connection conn = null;
    private static Session session = null;
    private static MessageProducer producer = null;

    static {
        try {
            initialize ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    private static void initialize() throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory (USER, PASSWORD, URL);
        conn = connectionFactory.createConnection ();
// 事务性会话，自动确认消息
        session = conn.createSession (false, Session.AUTO_ACKNOWLEDGE);
// 消息的目的地（Queue/Topic）
        destination = session.createTopic (SUBJECT);
// destination = session.createTopic(SUBJECT);
// 消息的提供者（生产者）
        producer = session.createProducer (destination);
// 不持久化消息
        producer.setDeliveryMode (DeliveryMode.NON_PERSISTENT);
        conn.start ();
    }

    public static void sendAlarmMessage(String msgType, Integer messageCount) throws Exception {
// 发送文本消息
        if ("text".equals (msgType)) {
            String textMsg = "" + messageCount;
            TextMessage msg = session.createTextMessage ();
// TextMessage msg = session.createTextMessage(textMsg);
            msg.setText (textMsg);
            producer.send (msg);
        }
// 发送Map消息
        if ("map".equals (msgType)) {
            MapMessage msg = session.createMapMessage ();
            msg.setBoolean ("boolean", true);
            msg.setShort ("short", (short) 0);
            msg.setLong ("long", 123456);
            msg.setString ("MapMessage", "ActiveMQ Map Message!");
            producer.send (msg);
        }
// 发送流消息
        if ("stream".equals (msgType)) {
            String streamValue = "ActiveMQ stream Message!";
            StreamMessage msg = session.createStreamMessage ();
            msg.writeString (streamValue);
            msg.writeBoolean (false);
            msg.writeLong (1234567890);
            producer.send (msg);
        }
// 发送字节消息
        if ("bytes".equals (msgType)) {
            String byteValue = "字节消息";
            BytesMessage msg = session.createBytesMessage ();
            msg.writeBytes (byteValue.getBytes ());
            producer.send (msg);
        }
    }

    public static void main(String args[]) {
        try {
            sendAlarmMessage ("text", 1);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

}
