package cn.ac.sec.web.login;

import cn.ac.sec.entity.watch.*;
import cn.ac.sec.util.Point;
import org.ibase4j.core.base.Parameter;
import org.ibase4j.provider.IBizProvider;
import org.ibase4j.web.AppBaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/app/t9/device")
public class NewDataController extends AppBaseController<IBizProvider> {
    private static final Logger logger = LoggerFactory.getLogger (NewDataController.class);
    private static final String serviceForWatch = "watchService";


    @RequestMapping(value = "/T1", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t1(@RequestBody LoginT1 loginT1) {
        Parameter parameter = new Parameter (getService (), "updateWatchLastLoginTime", loginT1);
        provider.execute (parameter).getResult ();
        logger.info (loginT1.toString ());
        try {
            Thread.sleep (2000);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        Parameter watchCheckParameter = new Parameter ("watchService", "connectCheck", Long.parseLong (loginT1.deviceId));
        provider.execute (watchCheckParameter);
        return new BaseResp ();
    }

    @RequestMapping(value = "/U10", method = RequestMethod.POST)
    public @ResponseBody
    U10 u10(@RequestBody Device device) {
        logger.info (device.toString ());
        Parameter watchCheckParameter = new Parameter ("watchService", "watchPoint", device.getDeviceId ());
        Point point = (Point) provider.execute (watchCheckParameter).getResult ();
        U10 u10 = new U10 ();
        u10.setLat (point.getLatitude ());
        u10.setLng (point.getLongitude ());
        return u10;
    }

    @RequestMapping(value = "/U13", method = RequestMethod.POST)
        FriendsResp u13(@RequestBody Device device) {
        logger.info (device.toString ());
        FriendsResp friendsResp = new FriendsResp ();
        List<Friend> friendsList = new ArrayList<Friend> ();
        Friend friend = new Friend ();
        friend.type = Friend.FRIEND_TYPE_APP;
        friend.index = 1;
        friend.fid = 24586;
        friend.name = "tomhe";
        friend.phone = "13632283688";
        friend.headUrl = "http://123.56.148.205/upload/user/13632283688/head/986f506339390bd6_1477106208579.jpeg";
        friend.status = 1;
        friendsList.add (friend);

        friendsResp.setFriendsList (friendsList);

        return friendsResp;
    }

    @RequestMapping(value = "/U23", method = RequestMethod.POST)
    public @ResponseBody
    ChatMsgQueryResp u23(@RequestBody ChatMsgQueryReq chatMsgQueryReq) {
        logger.info (chatMsgQueryReq.toString ());

        List<ChatMsg> chatMsgList = new ArrayList<ChatMsg> ();

        Calendar cal = Calendar.getInstance ();
        cal.add (Calendar.MONTH, -2);

        for (int i = 2; i < 5; i++) {
            ChatMsg chatMsg = new ChatMsg ();
            chatMsg.direction = ChatMsg.DIRECTION_RECEIVE;
            chatMsg.friendType = Friend.FRIEND_TYPE_APP;
            chatMsg.friendId = 24586;
            chatMsg.msgContent = "hello world! " + i;
            chatMsg.msgId = i;
            chatMsg.msgType = ChatMsg.MSG_TYPE_TEXT;
            cal.add (Calendar.MINUTE, 5);
            chatMsg.sendTime = (int) (cal.getTimeInMillis () / 1000);
            chatMsgList.add (chatMsg);
        }

        cal.add (Calendar.MINUTE, 5);
        ChatMsg chatMsgTmp = new ChatMsg ();
        chatMsgTmp.direction = ChatMsg.DIRECTION_RECEIVE;
        chatMsgTmp.friendType = Friend.FRIEND_TYPE_APP;
        chatMsgTmp.friendId = 24586;
        chatMsgTmp.msgContent = "http://47.93.115.119/file/voice/12058.amr";
        chatMsgTmp.msgId = 17;
        chatMsgTmp.msgType = ChatMsg.MSG_TYPE_AUDIO;
        chatMsgTmp.sendTime = (int) (cal.getTimeInMillis () / 1000);

        FileExtra fileExtra = new FileExtra ();
        fileExtra.type = 3;
        fileExtra.size = 11526;
        fileExtra.id = 12058;
        fileExtra.ip = "47.93.115.119";
        fileExtra.port = 80;
        fileExtra.duration = 8;
        chatMsgTmp.fileExtra = fileExtra;

        chatMsgList.add (chatMsgTmp);


        ChatMsgQueryResp chatMsgQueryResp = new ChatMsgQueryResp ();
        chatMsgQueryResp.setChatMsgList (chatMsgList);
        return chatMsgQueryResp;
    }

    @RequestMapping(value = "/U22", method = RequestMethod.POST)
    public @ResponseBody
    NewChatMsgResp u22(@RequestBody NewChatMsgReq newChatMsgReq) {
        logger.info (newChatMsgReq.toString ());
        NewChatMsgResp newChatMsgResp = new NewChatMsgResp ();
        newChatMsgResp.saveTime = new Date ().getTime ();
        newChatMsgResp.msgId = 17;
        return newChatMsgResp;
    }

    @RequestMapping(value = "/U30", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp u30(@RequestBody List<CallRecord> callRecordList) {
        logger.info (callRecordList.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T28", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t28(@RequestBody List<HealthHr> healthHrList) {
        Parameter parameter = new Parameter (getService (), "saveWatchHRList", healthHrList);
        provider.execute (parameter).getResult ();
        logger.info (healthHrList.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T30", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t30(@RequestBody List<HealthPe> healthPeList) {
        Parameter parameter = new Parameter (getService (), "saveWatchPeList", healthPeList);
        provider.execute (parameter).getResult ();
        return new BaseResp ();
    }

    //
//	@RequestMapping(value = "/T50", method = RequestMethod.POST)
//	public @ResponseBody BaseResp t50(@RequestBody List<HealthTe> healthTeList) {
//		logger.info(healthTeList.toString());
//		return new BaseResp();
//	}
//
//	@RequestMapping(value = "/T62", method = RequestMethod.POST)
//	public @ResponseBody BaseResp t62(@RequestBody List<HealthBp> healthBpList) {
//		logger.info(healthBpList.toString());
//		return new BaseResp();
//	}
//
    @RequestMapping(value = "/T91", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t91(@RequestBody List<HealthSl> healthSlList) {
        Parameter parameter = new Parameter (getService (), "saveWatchSleepList", healthSlList);
        provider.execute (parameter).getResult ();
        logger.info (healthSlList.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T97", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t97(@RequestBody List<HealthBs> healthBsList) {
        logger.info (healthBsList.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T86", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t86(@RequestBody Location location) {
        Parameter parameter = new Parameter (getService (), "savePosition", location);
        provider.execute (parameter);
        logger.info (location.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T94", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t94(@RequestBody Location location) {
        Parameter parameter = new Parameter (getService (), "savePosition", location);
        provider.execute (parameter);
        logger.info (location.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T82", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t82(@RequestBody BatteryLow batteryLow) {
        Parameter parameter = new Parameter (getService (), "addLowPowerAlarm", batteryLow);
        provider.execute (parameter);
        logger.info (batteryLow.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T85", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t85(@RequestBody Location location) {
        Parameter parameter = new Parameter (getService (), "savePosition", location);
        provider.execute (parameter);
        logger.info (location.toString ());
        return new BaseResp ();
    }

    @RequestMapping(value = "/T89", method = RequestMethod.POST)
    public @ResponseBody
    BaseResp t89(@RequestBody Location location) {
        Parameter parameter = new Parameter (getService (), "addSOSAlarm", location);
        provider.execute (parameter);
        logger.info (location.toString ());
        return new BaseResp ();
    }

    @Override
    public String getService() {
        return "watchMessageService";
    }
}
