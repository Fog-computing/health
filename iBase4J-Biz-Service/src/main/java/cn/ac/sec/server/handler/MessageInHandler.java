//package cn.ac.sec.server.handler;
//
//import cn.ac.sec.context.AppContext;
//import cn.ac.sec.entity.BizFallDevice;
//import cn.ac.sec.entity.BizMessageAlarm;
//import cn.ac.sec.entity.BizWatch;
//import cn.ac.sec.protocol.message.FallMessage;
//import cn.ac.sec.protocol.message.InMessage;
//import cn.ac.sec.protocol.message.Message;
//import cn.ac.sec.protocol.message.OutMessage;
//import cn.ac.sec.protocol.util.MessageCaster;
//import cn.ac.sec.protocol.util.MessageFilter;
//import cn.ac.sec.server.NettyTCPServer;
//import cn.ac.sec.sevice.FallDeviceService;
//import cn.ac.sec.sevice.WatchMessageService;
//import cn.ac.sec.sevice.WatchService;
//import cn.ac.sec.util.GPSUtil;
//import cn.ac.sec.util.Point;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.util.ReferenceCountUtil;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author 沈硕
// * 手表上报信息回调
// */
//@Component
//public class MessageInHandler extends ChannelInboundHandlerAdapter {
//
//
//    private WatchMessageService watchMessageService;
//    private WatchService watchService;
//    private FallDeviceService fallDeviceService;
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ByteBuf in = (ByteBuf) msg;
//        watchMessageService = AppContext.getBean (WatchMessageService.class);
//        watchService = AppContext.getBean (WatchService.class);
//        fallDeviceService = AppContext.getBean (FallDeviceService.class);
//        StringBuilder resultBuffer = new StringBuilder ();
//        try {
//            while (in.isReadable ()) { // (1)
//                resultBuffer.append ((char) in.readByte ());
//            }
//            String checkDevice = resultBuffer.substring (0, 2);
//            if (checkDevice.startsWith ("+")) {
//                if(checkDevice.equals ("+S")){
//                    FallMessage message = MessageCaster.getFallMessageFromString (resultBuffer);
//                    if (!NettyTCPServer.getClient_map ().containsKey (Long.parseLong (message.getId ()))) {
//                        NettyTCPServer.getClient_map ().put (Long.parseLong (message.getId ()), ctx.channel ());
//                    }
//                    if(!NettyTCPServer.getUser_map ().containsKey (Long.parseLong (message.getId ()))){
//                        NettyTCPServer.getUser_map ().put (Long.parseLong (message.getId ()),
//                                fallDeviceService.getDeviceByIMEI (message.getId ()).getUser ());
//                    }
//                    BizFallDevice fallDevice = new BizFallDevice ();
//                    fallDevice.setImei (message.getId ());
//                    fallDevice.setLastOnlineTime (new Date ());
//                    fallDevice.setBattery ((byte)message.getBat ());
//                    fallDeviceService.updateLatestLogin (fallDevice);
//                    if (!"00".equals (message.getWarning ())) {
//                        BizMessageAlarm messageAlarm = new BizMessageAlarm ();
//                        if (!message.getGpsLatitude ().equals (new Double (0))) {
//                            System.out.println ("GPS" + message.getGpsLongitude ());
//                            Point point = GPSUtil.getBaiduPosition (new Point (message.getGpsLongitude (), message.getGpsLatitude ()));
//                            messageAlarm.setLatitude (point.getLatitude ());
//                            messageAlarm.setLongitude (point.getLongitude ());
//                        } else {
//                            System.out.println ("LBS" + message.getLbsLongitude ());
//                            Point point = GPSUtil.getBaiduPosition (new Point (message.getLbsLongitude (), message.getLbsLatitude ()));
//                            messageAlarm.setLatitude (point.getLatitude ());
//                            messageAlarm.setLongitude (point.getLongitude ());
//                        }
//                        if (message.getWarning ().equals ("01")) {
//                            messageAlarm.setTime (new Date ());
//                            messageAlarm.setType ((byte) 15);
//                        } else if (message.getWarning ().equals ("02")) {
//                            messageAlarm.setTime (new Date ());
//                            messageAlarm.setType ((byte) 16);
//                        }
//                        messageAlarm.setUser (NettyTCPServer.getUser_map ().get (Long.parseLong (message.getId ())));
//                        fallDeviceService.addFallAlarm (messageAlarm);
//                    } else {
//                        fallDeviceService.setBindPhoneNum (message.getId ());
//                    }
//                }
//                else {
//                    System.out.println (resultBuffer.toString ());
//                }
//            } else if (checkDevice.startsWith ("[")) {
//                List<Message> inMessages = MessageCaster.getMessageFromString (resultBuffer);
//                for (Message inMessage : inMessages) {
//                    BizWatch watch = watchService.getWatchByImei (inMessage.getIMEI ());
//                    Message outMessage = new OutMessage (inMessage);
//                    //将客户端连接注册到Map中
//                    if (inMessage.getIMEI () == null) {
//                        return;
//                    }
//                    if (!NettyTCPServer.getClient_map ().containsKey (inMessage.getIMEI ())) {
//                        NettyTCPServer.getClient_map ().put (inMessage.getIMEI (), ctx.channel ());
//                        NettyTCPServer.getIdentifier ().put (inMessage.getIdentifier (), inMessage.getIMEI ());
//                    }
//                    if (!NettyTCPServer.getUser_map ().containsKey (inMessage.getIMEI ())) {
//                        NettyTCPServer.getUser_map ().put (inMessage.getIMEI (), watchMessageService.getUserByMessage (inMessage));
//                    }
//                    //向客户端返回回应信息
//                    boolean checkFlag = false;
//                    System.out.println (inMessage.getType () + "this is type");
//                    switch (inMessage.getType ()) {
//                        case InMessage.BP:
//                            watchMessageService.saveWatchData (inMessage);
//                            break;
//                        case InMessage.BS:
//                            watchMessageService.saveWatchData (inMessage);
//                            break;
//                        case InMessage.BPWITHTIME:
//                            watchMessageService.saveWatchData (inMessage);
//                            break;
//                        case InMessage.BSWITHTIME:
//                            watchMessageService.saveWatchData (inMessage);
//                            break;
//                        case InMessage.HR:
//                            watchMessageService.saveWatchData (inMessage);
//                            break;
//                        case InMessage.GPS:
//                            watchMessageService.savePosition (inMessage);
//                            break;
////                        case InMessage.HEARTBEAT:
////                            watchMessageService.updateWatchLastLoginTime (inMessage);
////                            checkFlag = true;
////                            break;
////                        case InMessage.LOGIN:
////                            watchMessageService.updateWatchLastLoginTime (inMessage);
////                            checkFlag = true;
////                            break;
//                        case InMessage.LOWPOWER:
//                            watchMessageService.saveAlarmWatch (inMessage);
//                            break;
//                        case InMessage.MULTIBS:
//                            Map<String, Object> objectMap = GPSUtil.getPosition (inMessage.getParams ().get (1));
//                            objectMap = (Map<String, Object>) objectMap.get ("datas");
//                            String resultString = (String) objectMap.get ("address");
//                            if (resultString.equals ("未知位置")) break;
//                            else watchMessageService.savePositionWithBS (inMessage, objectMap);
//                            break;
//                        case InMessage.SITELONG:
//                            watchMessageService.saveAlarmWatch (inMessage);
//                            break;
//                        case InMessage.SLEEPINFO:
//                            watchMessageService.saveWatchData (inMessage);
//                            break;
//                        case InMessage.SLEPPINFOMORE:
//                            watchMessageService.saveWatchData (inMessage);
//                            break;
//                        case InMessage.SOS:
//                            watchMessageService.saveAlarmWatch (inMessage);
//                            break;
//                        case InMessage.STEPS:
//                            watchMessageService.saveStep (inMessage);
//                            break;
//                        case InMessage.HISTORY:
//                            watchMessageService.historyMessage (inMessage);
//                            break;
//                        case InMessage.MULTIPOSITION:
//                            System.out.println ("this is a muiltipostion message");
//                            watchMessageService.multiPosition (inMessage);
//                            break;
//                        default:
//                            break;
//                    }
//                    String outMessageString = MessageCaster.getStringFromMessage (outMessage);
//                    if (!MessageFilter.watchResponseCheck (inMessage))
//                        ctx.write (outMessageString);
//                    else {
//                        watch.setSyncStatus ((byte) 1);
//                        watchService.updateWatch (watch);
//                    }
//                    if (checkFlag) {
//                        watchService.connectCheck (inMessage);
//                    }
//                }
//            }
//            else return;
////            System.out.println (resultBuffer.toString ());
//
//        } finally {
//            ReferenceCountUtil.release (msg); // (2)
//        }
//
////        super.channelRead (ctx, msg);
//    }
//
//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println ("channelRegistered");
//        super.channelRegistered (ctx);
//    }
//
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        // 当出现异常就关闭连接
//        cause.printStackTrace ();
//        ctx.close ();
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) {
//        ctx.flush ();
//    }
//
//    public MessageInHandler() {
//        super ();
//    }
//
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        System.out.println ("channel unregistered");
//        super.channelUnregistered (ctx);
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println ("channel is active");
//        super.channelActive (ctx);
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println ("channel is inactive");
//        for (Map.Entry entry : NettyTCPServer.getClient_map ().entrySet ()) {
//            if (entry.getValue () == ctx.channel ()) {
//                NettyTCPServer.getClient_map ().remove (entry);
//            }
//        }
//        for (Map.Entry entry : NettyTCPServer.getClient_map ().entrySet ()) {
//            if (entry.getValue () == ctx.channel ()) {
//                NettyTCPServer.getUser_map ().remove (entry);
//            }
//        }
//        super.channelInactive (ctx);
//    }
//
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        super.userEventTriggered (ctx, evt);
//    }
//
//    @Override
//    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
//        super.channelWritabilityChanged (ctx);
//    }
//}
