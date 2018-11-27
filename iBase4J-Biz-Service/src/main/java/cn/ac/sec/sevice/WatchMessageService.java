package cn.ac.sec.sevice;


import cn.ac.sec.dao.*;
import cn.ac.sec.entity.*;
import cn.ac.sec.entity.messure.HeartRateBase;
import cn.ac.sec.entity.messure.Sleep;
import cn.ac.sec.entity.watch.*;
import cn.ac.sec.protocol.message.InMessage;
import cn.ac.sec.protocol.message.Message;
import cn.ac.sec.util.GPSUtil;
import cn.ac.sec.util.JSONUtil;
import cn.ac.sec.util.Point;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class WatchMessageService {

    @Autowired
    BizWatchMapper watchMapper;

    @Autowired
    WatchService watchService;


    @Autowired
    AlarmService alarmService;
    @Autowired
    BizDeviceDataMapper deviceDataMapper;

    @Autowired
    BizMessageAlarmMapper alarmMapper;

    @Autowired
    BizMessageAlarmTypeMapper typeMapper;

    @Autowired
    BizMessagePositionMapper positionMapper;

    @Autowired
    PositionService positionService;

    @Autowired
    BizMessageStepMapper stepMapper;

    @Autowired
    BizLogWatchMapper logWatchMapper;


    public void updateWatchLastLoginTime(LoginT1 message) {
        BizWatch watch = new BizWatch ();
        watch.setLastOnlineTime (new Date ());
        BizWatchExample example = new BizWatchExample ();
        example.createCriteria ().andImeiEqualTo (Long.parseLong (message.deviceId));
        watchMapper.updateByExampleSelective (watch,example);
    }

    public Long getUserByMessage(Message message){
        BizWatchExample example = new BizWatchExample ();
                example.createCriteria ().andImeiEqualTo (message.getIMEI ());
        BizWatch watch=watchMapper.selectByExample (example).get (0);
        return  watch.getUser ();
    }

    public Long getUserByIMEI(String imei) {
        BizWatchExample example = new BizWatchExample ();
        example.createCriteria ().andImeiEqualTo (Long.parseLong (imei));
        List<BizWatch> watches = watchMapper.selectByExample (example);
        if (watches.size () == 0) throw new BusinessException ("未找到手表");
        BizWatch watch = watchMapper.selectByExample (example).get (0);
        return watch.getUser ();
    }

    public void addSOSAlarm(Location location) {
        BizMessageAlarm alarm = new BizMessageAlarm ();
        BizMessageAlarmTypeExample example = new BizMessageAlarmTypeExample ();
        example.createCriteria ().andCodeEqualTo (String.valueOf (InMessage.SOS));
        System.out.println(location);
        BizMessageAlarmType type = typeMapper.selectByExample (example).get (0);
        BizMessageAlarmExample exampleAlarm= new BizMessageAlarmExample();
        exampleAlarm.createCriteria().andTimeEqualTo(location.getMeasureDate());
        if(alarmMapper.selectByExample(exampleAlarm).size()>0)return;
        Point point = getBaiduPosition (location);
        alarm.setType (type.getId ());
        alarm.setLatitude (point.getLatitude ());
        alarm.setLongitude (point.getLongitude ());
        alarm.setUser (getUserByIMEI (location.getDeviceId ()));
        alarm.setTime (location.getMeasureDate ());
        alarmService.addAlarm (alarm);
    }

    public void addLowPowerAlarm(BatteryLow batteryLow) {
        Long userId = getUserByIMEI (batteryLow.getDeviceId ());
        BizMessageAlarmTypeExample example = new BizMessageAlarmTypeExample ();
        example.createCriteria ().andCodeEqualTo (String.valueOf (InMessage.LOWPOWER));
        BizMessageAlarmType type = typeMapper.selectByExample (example).get (0);
        BizMessagePosition position = positionService.getLatestPosition (userId);
        alarmService.addAlarm (new BizMessageAlarm (
                userId,
                type.getId (),
                new Date (batteryLow.getWarnDate ()),
                position.getLatitude (),
                position.getLongitude ())
        );
    }

    private Point getBaiduPosition(Location location) {
        Point point;
        if (location.getType () % 2 == 0) {
            point = new Point (location.getLng (), location.getLat ());
            point = GPSUtil.getBaiduPosition (point);
        } else {
            Point gdPoint = GPSUtil.getGDPosition (location);
            point = GPSUtil.getBaduPositionFromGD (gdPoint);
        }
        return point;
    }

    public void saveWatchPeList(List<HealthPe> peList) {
        for (HealthPe pe : peList) {
            saveWatchPe (pe);
        }
    }

    public void saveWatchPe(HealthPe pe) {
        BizMessageStep step = new BizMessageStep ();
        step.setCount (pe.getStepCount ());
        step.setTime (new Date (pe.getEndTime ()));
        step.setUploadDate (new Date (pe.getEndTime ()));
        step.setUser (getUserByIMEI (pe.getDeviceId ()));
        stepMapper.insert (step);
    }

    public void saveWatchHR(HealthHr hr) {
        String dataString = "";
        BizDeviceData dataWatch = new BizDeviceData ();
        dataWatch.setTableName ("biz_data_15");
        dataWatch.setUserId (getUserByIMEI (hr.getDeviceId ()));
        dataWatch.setDatetimeMeasure (new Date (hr.getMeasureDate ()));
        dataWatch.setDatetimeUpdate (new Date ());
        HeartRateBase heartRateBase = new HeartRateBase ();
        heartRateBase.setHeartRate (hr.getHeartRate ());
        heartRateBase.setMeasureDate (new Date (hr.getMeasureDate ()));
        if (heartRateBase.getHeartRate () == 0) return;
        dataString = JSONUtil.toJSon (heartRateBase);
        dataWatch.setData (dataString);
        deviceDataMapper.insert (dataWatch);
    }

    public void saveWatchHRList(List<HealthHr> hrList) {
        for (HealthHr hr : hrList) {
            saveWatchHR (hr);
        }

    }

    public void saveWatchSleep(HealthSl sl) {
        Sleep sleep = new Sleep ();
        sleep.setDeep (sl.getDeep ());
        sleep.setTotal (sl.getDuration ());
        sleep.setMeasureDate (new Date (sl.getMeasureDate ()));
    }

    public void saveWatchSleepList(List<HealthSl> slList) {
        for (HealthSl sl : slList) {
            saveWatchSleep (sl);
        }
    }

    public void savePosition(Location location) {
        BizMessagePosition position = new BizMessagePosition ();
        BizWatch watch = watchService.getWatchByImei (Long.parseLong (location.getDeviceId ()));
        Point point = getBaiduPosition (location);
        position.setLongitude (point.getLongitude ());
        position.setLatitude (point.getLatitude ());
        position.setTime (location.getMeasureDate ());
        position.setUser (watch.getUser ());
        position.setTimestamp (0);
        if (watch.getFence ()) {
            Double distance = GPSUtil.getDistance (watch.getLongitude (), point.getLongitude (), watch.getLatitude (), point.getLatitude ());
            if (distance > watch.getRadius ()) {
                BizMessageAlarm alarm = new BizMessageAlarm ();
                alarm.setType ((byte) 4);
                alarm.setTime (position.getTime ());
                alarm.setUser (position.getUser ());
                alarm.setLatitude (position.getLatitude ());
                alarm.setLongitude (position.getLongitude ());
                alarmService.addAlarm (alarm);
            }
        }
        positionMapper.insertSelective (position);
    }


}
