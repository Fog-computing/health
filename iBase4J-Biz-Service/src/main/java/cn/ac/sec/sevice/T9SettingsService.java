package cn.ac.sec.sevice;

import cn.ac.sec.config.Contants;
import cn.ac.sec.config.S2TMsgType;
import cn.ac.sec.entity.watch.*;
import cn.ac.sec.util.HttpClientUtil;
import cn.ac.sec.util.JsonUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("t9SettingsService")
public class T9SettingsService {

    //	@Value("${t9.server.http.url}")
    private String ServerHttpUrl = "http://127.0.0.1:18090";

    /**
     * @param imei
     * @param msgContent "|Joe|"
     */
    public String bindDevice(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S0);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    public boolean isDeviceOnline(String imei) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S200);
        careSettings.setDeviceId (imei);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
        BaseResp baseResp = JsonUtils.jsonToPojo (result, BaseResp.class);
        return baseResp.rCode == Contants.Rcode.SUCCESS_HANDLE;
    }

    public int getDevicesNum() {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S201);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
        return 0;

    }

    /**
     * @param imei
     * @param msgContent "104.063469,30.551357,2.00"
     */
    public void setFence(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S84);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param msgContent "0,|have a walk|,201712151830"
     */
    public void setTodo(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S83);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param msgContent "201712151830,0"
     */
    public void delTodo(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S92);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     */
    public void delAllTodo(String imei) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S93);
        careSettings.setDeviceId (imei);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param msgContent "13896689660,13654673567,13654673568,13654673569"
     */
    public void setSpeedDial(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S70);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param msgContent "13896689660,13654673567,13654673568,13654673569"
     */
    public String setT9sSpeedDial(String imei, List<SpeedDial> speedDialList) {
        JSONObject jsonObj = new JSONObject ();
        jsonObj.put ("msgType", S2TMsgType.D11);
        jsonObj.put ("deviceId", imei);
        jsonObj.put ("list", speedDialList);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, jsonObj.toString ());
        return result;
    }

    /**
     * @param imei
     * @param msgContent "help!"
     */
    public void setSosSms(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S74);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param msgContent "13654678948,13654673567,13654673568"
     */
    public void setSosPhone(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S71);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param msgContent "13654678948,13654673567,13654673568"
     */
    public String setT9sSosPhoneAndSms(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S71);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    /**
     * @param imei
     * @param msgContent "55,140,1"
     */
    public String setHrAlarmRange(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S39);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    /**
     * @param imei
     * @param msgContent "18,170,60,65,1"
     */
    public String setPersonalInfo(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S77);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    /**
     * @param imei
     * @param msgContent "10:00:00,20:30:00,60"
     */
    public String setHrTimer(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S8);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    /**
     * @param imei
     * @param msgContent "10:00:00,20:30:00,60"
     */
    public String setPeTimer(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S78);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    /**
     * @param imei
     * @param msgContent "10:00:00,20:30:00,60"
     */
    public String setLocateTimer(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S29);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    /**
     * @param imei
     * @param msgContent "22:00:00,07:30:00,1"
     */
    public String setSleepTimer(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S75);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    /**
     * @param imei
     * @param msgContent "07:30:00,22:30:00,1"
     */
    public String setSedTimer(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S87);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        return result;
    }

    public String realTimeHeartrate(String imei) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S5);
        careSettings.setDeviceId (imei);
        return HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
    }

    public String realTimeLocate(String imei) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S13);
        careSettings.setDeviceId (imei);
        return HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
    }

    /**
     * 服务器向腕表设备设置备用服务器IP地址.
     *
     * @param imei
     * @param msgContent "域名:端口,IP地址:端口" or "IP地址:端口"
     *                   example: "zxcn.movocom.com:12345,123.56.148.205:12345" or "123.56.148.205:12345"
     */
    public void setReserveServerIp(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.S12);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param msgContent "07:30:00,22:30:00,1"
     */
    public void setT9sPushMsgToDev(String imei, String msgContent) {
        CareSettings careSettings = new CareSettings ();
        careSettings.setMsgType (S2TMsgType.D15);
        careSettings.setDeviceId (imei);
        careSettings.setMsgContent (msgContent);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, JsonUtils.objectToJson (careSettings));
        System.out.println (result);
    }

    /**
     * @param imei
     * @param firendList
     */
    public void setT9sFriends(String imei, List<Friend> firendList) {
        JSONObject jsonObj = new JSONObject ();
        jsonObj.put ("msgType", S2TMsgType.D13);
        jsonObj.put ("deviceId", imei);
        jsonObj.put ("list", firendList);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, jsonObj.toString ());
        System.out.println (result);
    }

    /**
     * @param imei
     * @param TodoList
     */
    public void setT9sTodos(String imei, List<Todo> todoList) {
        JSONObject jsonObj = new JSONObject ();
        jsonObj.put ("msgType", S2TMsgType.D12);
        jsonObj.put ("deviceId", imei);
        jsonObj.put ("list", todoList);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, jsonObj.toString ());
        System.out.println (result);
    }

    public void newChatMsgToDev(String imei, ChatMsg chatMsg) {
        JSONObject jsonObj = new JSONObject ();
        jsonObj.put ("msgType", S2TMsgType.D21);
        jsonObj.put ("deviceId", imei);
        jsonObj.put ("chatMsg", chatMsg);
        String result = HttpClientUtil.doPostJson (ServerHttpUrl, jsonObj.toString ());
        System.out.println (result);
    }
}
