package cn.ac.sec.sevice;


import cn.ac.sec.ConfigureString;
import cn.ac.sec.dao.BizLogWatchMapper;
import cn.ac.sec.dao.BizUserMapper;
import cn.ac.sec.dao.BizWatchMapper;
import cn.ac.sec.entity.BizLogWatch;
import cn.ac.sec.entity.BizUser;
import cn.ac.sec.entity.BizUserRelationPhoneNum;
import cn.ac.sec.entity.BizWatch;
import cn.ac.sec.entity.watch.BaseResp;
import cn.ac.sec.entity.watch.SpeedDial;
import cn.ac.sec.protocol.message.OutMessage;
import cn.ac.sec.protocol.util.MessageCaster;
import cn.ac.sec.server.NettyTCPServer;
import cn.ac.sec.util.CommonUtil;
import cn.ac.sec.util.JSONUtil;
import io.netty.channel.Channel;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("watchSettingService")
public class WatchSettingService extends cn.ac.sec.sevice.BaseService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    BizLogWatchMapper logWatchMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    BizWatchMapper watchMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    BizUserMapper userMapper;

    @Autowired
    WatchService watchService;

    @Autowired
    private T9SettingsService t9SettingsService;

    @Autowired
    private UserRelationPhoneNumService relationPhoneNumService;


    public void saveWatch(BizWatch watch){
        watchMapper.insertSelective (watch);
    }

    public void setWatchQuickPhoneNum(Long userId) {
        Map<String, Object> params = new HashMap<> ();
        List<SpeedDial> speedDials = new ArrayList<> ();
        params.put ("type", OutMessage.QUICKNUM);
        params.put ("userId", userId);
        List<BizUserRelationPhoneNum> phoneNumList = relationPhoneNumService.getPhoneNumListByUserId (userId);
        int count = 1;
        for (BizUserRelationPhoneNum phoneNum : phoneNumList) {
            if (phoneNum.getType ().equals ((byte) 2) || phoneNum.getType ().equals ((byte) 3)) {
                speedDials.add (new SpeedDial (count, phoneNum.getPhoneNum (), phoneNum.getName ()));
                count += 1;
            }
        }
        while (count<=4){
            speedDials.add (new SpeedDial (count, "", ""));
            count += 1;
        }
        params.put ("phoneNumList", speedDials);
        watchOperate (params);
    }


    public void setWatchSOSNum(Long userId) {
        Map<String, Object> params = new HashMap<> ();
        params.put ("type", OutMessage.SOSNUM);
        params.put ("userId", userId);
        List<BizUserRelationPhoneNum> phoneNumList = relationPhoneNumService.getPhoneNumListByUserId (userId);
        String sosNum = "";
        for (BizUserRelationPhoneNum phoneNum : phoneNumList) {
            if (phoneNum.getType ().equals ((byte) 1) || phoneNum.getType ().equals ((byte) 3)) {
                sosNum += phoneNum.getPhoneNum () + ",";
            }
        }
        params.put ("phoneNumList", sosNum);
        watchOperate (params);
    }

    int setWatchPhoneNum(Long userId) {
        setWatchQuickPhoneNum (userId);
        setWatchSOSNum (userId);
        return 0;
    }


    public void setUserInfo(Long userId) {
        Map<String, Object> params = new HashMap<> ();
        params.put ("type", OutMessage.INFOSET);
        params.put ("userId", userId);
        watchOperate (params);
    }

    public void setSleepSapn(Map<String,Object> params){
        watchOperate (params);
    }

    public void setGPSSpan(Long userId,int span){
        Map<String,Object> params= new HashMap<> ();
        params.put ("userId",userId);
        params.put ("span",""+span);
        params.put ("type",OutMessage.POSITIONSCHEDULE);
        watchOperate (params);
    }

    public void setStepSchedule(Long userId,int span){
        Map<String,Object> params= new HashMap<> ();
        params.put ("userId",userId);
        params.put ("span",""+span);
        params.put ("type",OutMessage.STEPSET);
        watchOperate (params);
    }

    public void getGPS(Long userId) {
        BizWatch watch = watchService.getWatchByUser (userId);
        OutMessage message = new OutMessage (watch.getImei ());
        message.setType (OutMessage.POSITIONDETECT);
        Channel channel = NettyTCPServer.getClient_map ().get (message.getIMEI ());
        channel.writeAndFlush (MessageCaster.getStringFromMessage (message));
    }
    public void setHRAlarm(Long userId,String status){
        Map<String,Object> params= new HashMap<> ();
        params.put ("userId",userId);
        params.put ("status",status);
        params.put ("type", OutMessage.HRRATE);
        List<String> paramList =new ArrayList<> ();
        params.put ("content","55,140,"+status);
        params.put ("param",paramList);
        watchOperate (params);
    }
    /**
     *
     * @param userId
     * @param status 1为开 0为关
     */
    public void setWatchSit(Long userId,String status){
        Map<String, Object> params = new HashMap<> ();
        params.put ("userId", userId);
        params.put ("status", status);
        params.put ("type", OutMessage.SITSET);
        watchOperate (params);
    }
    public void setHRSchedule(Long userId,int span){
            Map<String,Object> params= new HashMap<> ();
            params.put ("userId",userId);
            params.put ("span",""+span);
            params.put ("type",OutMessage.HRSCHEDULE);
            watchOperate (params);
    }

    public WatchSettingService() {
    }

    /**
     * 用于配置手表信息的服务
     *
     * @param params 传入的参数Map,包含内容：
     *               userId : 用户Id
     *               type : 下发设置指令类型
     *               param : 手表配置参数
     *               status : 开关标识
     *               span : 周期标识
     */
    public Integer watchOperate(Map<String, Object> params) {
        Long userId = (Long) params.get ("userId");
        int type = (int) params.get ("type");
        BizWatch watch = watchService.getWatchByUser (userId);
        if (watch == null) throw new BusinessException ("请先添加手表设备");
        String msgContent = "";
        String resultString = "";
        try {
            Thread.sleep (1000);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        switch (type) {
            case  OutMessage.REMIND:
                t9SettingsService.setTodo(watch.getImei().toString(),(String)params.get("param"));
                break;
            case OutMessage.DE_REMIND:
                t9SettingsService.delTodo(watch.getImei().toString(),(String)params.get("param"));
                break;
            case OutMessage.HRRATE:
                t9SettingsService.setHrAlarmRange (watch.getImei ().toString (), (String) params.get ("content"));
            case OutMessage.HRDETECT:
                resultString = t9SettingsService.realTimeHeartrate (watch.getImei ().toString ());
                break;
            case OutMessage.INFOSET:
                BizUser user = userMapper.selectByPrimaryKey (userId);
                msgContent = String.valueOf (CommonUtil.getAge (user.getBirthday ())) + "," + user.getHeight ().toString () + ",60," + user.getWeight ().toString () + ",";
                if (user.getGender ()) msgContent += "1";
                else msgContent += "0";
                resultString = t9SettingsService.setPersonalInfo (watch.getImei ().toString (), msgContent);
                break;
            case OutMessage.POSITIONDETECT:
                resultString = t9SettingsService.realTimeLocate (watch.getImei ().toString ());
                break;
            case OutMessage.POSITIONSCHEDULE:
                msgContent = ConfigureString.GPS_START + "," + ConfigureString.GPS_END + "," + params.get ("span");
                resultString = t9SettingsService.setLocateTimer (watch.getImei ().toString (), msgContent);
                break;
            case OutMessage.SITSET:
                msgContent = ConfigureString.SIT_START + "," + ConfigureString.SIT_END + "," + params.get ("status");
                resultString = t9SettingsService.setSedTimer (watch.getImei ().toString (), msgContent);
                break;
            case OutMessage.SLEEPSET:
                msgContent = params.get ("startTime") + "," + params.get ("endTime")+ "," + params.get ("status");
                watch.setSleepStartTime ((String) params.get ("startTime"));
                watch.setSleepEndTime ((String)params.get ("endTime"));
                System.out.println (msgContent);
                resultString = t9SettingsService.setSleepTimer (watch.getImei ().toString (), msgContent);
                break;
            case OutMessage.STEPSET:
                msgContent = ConfigureString.STEP_START + "," + ConfigureString.STEP_END + "," + params.get ("span");
                resultString = t9SettingsService.setPeTimer (watch.getImei ().toString (), msgContent);
                break;
            case OutMessage.HRSCHEDULE:
                msgContent = ConfigureString.HR_START + "," + ConfigureString.HR_END + "," + params.get ("span");
                resultString = t9SettingsService.setHrTimer (watch.getImei ().toString (), msgContent);
                break;
            case OutMessage.QUICKNUM:
                List<SpeedDial> quickList = (List<SpeedDial>) params.get ("phoneNumList");
                resultString = t9SettingsService.setT9sSpeedDial (watch.getImei ().toString (), quickList);
                break;
            case OutMessage.SOSNUM:
                msgContent = (String) params.get ("phoneNumList");
                resultString = t9SettingsService.setT9sSosPhoneAndSms (watch.getImei ().toString (), msgContent);
                break;
        }
        System.out.println (resultString);
        BaseResp resp = JSONUtil.readValue (resultString, BaseResp.class);
        if (resp.rCode == 200) {
            BizWatch updateWatch = new BizWatch ();
            updateWatch.setId (watch.getImei ());
            updateWatch.setSyncStatus ((byte) 1);
            watchMapper.updateByPrimaryKeySelective (updateWatch);
            return 0;
        }
        return 0;
    }


    public int insertLog(BizLogWatch logWatch) {
        return logWatchMapper.insert (logWatch);
    }



}
