package cn.ac.sec.sevice;


import cn.ac.sec.dao.*;
import cn.ac.sec.entity.*;
import cn.ac.sec.entity.messure.Sleep;
import cn.ac.sec.entity.transport.PageTransport;
import cn.ac.sec.protocol.message.OutMessage;
import cn.ac.sec.util.CommonUtil;
import cn.ac.sec.util.GPSUtil;
import cn.ac.sec.util.JSONUtil;
import cn.ac.sec.util.Point;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Position;
import java.util.*;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

public class WatchService extends BaseService {
    @Autowired
    private BizWatchMapper watchMapper;

    @Autowired
    private WatchSettingService watchSettingService;

    @Autowired
    private UserRelationPhoneNumService numService;

    @Autowired
    private BizUserService userService;

    @Autowired
    private BizMessageStepMapper stepMapper;

    @Autowired
    private BizMessagePositionMapper positionMapper;

    @Autowired
    private BizLogWatchMapper logWatchMapper;

    @Autowired
    private BizDeviceDataMapper dataMapper;

    @Autowired
    private WatchMessageService watchMessageService;

    public BizWatch addWatch(BizWatch watch) {
        if (checkWatchExist (watch)) throw new BusinessException ("该手表已经在使用中");
        watch.setSyncStatus ((byte) 1);
        watchMapper.insertSelective (watch);
        return watch;
    }

    public List<Map<String, Object>> getOperationsByUser(Long userId) {
        return logWatchMapper.getOperationsByUser (userId);
    }

    public List<Map<String, Object>> getStepList(Long userId, Date start, Date end) {
        List<Map<String, Object>> list = new ArrayList<> ();
        while (true) {
            Map<String, Object> map = new HashMap<> ();
            map.put ("date", CommonUtil.getDay (end));
            map.put ("steps", getSteps (userId, end));
            end = CommonUtil.getYesterDay (end);
            if (end.before (start)) break;
            list.add (map);
        }
        return list;
    }

    public List<BizMessagePosition> getPositionList(Long userId, Date start, Date end) {
        BizMessagePositionExample example = new BizMessagePositionExample ();
        example.createCriteria ().andUserEqualTo (userId).andTimeBetween (start, end);
        return positionMapper.selectByExample (example);
    }

    public Point watchPoint(String imei){
        BizMessagePosition position= getLastPosition (watchMessageService.getUserByIMEI (imei));
        Point point=new Point ();
        if(position!=null){
            point.setLatitude (position.getLatitude ());
            point.setLongitude (position.getLongitude ());
        }
        return  GPSUtil.getWGSPosition (point);
    }

    public boolean checkWatchExist(BizWatch watch) {
        BizWatchExample example = new BizWatchExample ();
        example.createCriteria ().andImeiEqualTo (watch.getImei ());
        List<BizWatch> list = watchMapper.selectByExample (example);
        return list != null && list.size () != 0 && list.get (0) != null;
    }

    public BizMessagePosition getLastPosition(Long userId) {
        BizMessagePositionExample positionExample = new BizMessagePositionExample ();
        positionExample.createCriteria ().andUserEqualTo (userId);
        positionExample.setOrderByClause ("time_ desc");
        PageHelper.startPage (0, 1);
        List<BizMessagePosition> positions = positionMapper.selectByExample (positionExample);
        if (positions != null && positions.size () != 0) return positions.get (0);
        else return null;
    }

    public int getOperateCount(Long userId) {
        BizLogWatchExample logWatchExample = new BizLogWatchExample ();
        logWatchExample.createCriteria ().andUserIdEqualTo (userId);
        return logWatchMapper.countByExample (logWatchExample);
    }

    public List<BizLogWatch> getLogListByUser(Long userId) {
        BizLogWatchExample logWatchExample = new BizLogWatchExample ();
        logWatchExample.createCriteria ().andUserIdEqualTo (userId);
        return logWatchMapper.selectByExample (logWatchExample);
    }


    public int updateWatch(BizWatch watch) {
        return watchMapper.updateByPrimaryKeySelective (watch);
    }

    public int deleteWatch(Long id) {
        return watchMapper.deleteByPrimaryKey (id);
    }

    public Page<Map<String, Object>> getWatchListWithUser(PageTransport transport) {
        Page<Map<String, Object>> pager;
        pager = getPage (transport);
        PageHelper.startPage (pager.getCurrent (), pager.getOffset ());
        List<Map<String, Object>> watches = watchMapper.watchWithUser ("%" + transport.getKeyword () + "%");
        pager.setRecords (watches);
        pager.setTotal (watches.size ());
        pager.setTotal ((int) ((com.github.pagehelper.Page) watches).getTotal ());
        return pager;
    }

    protected BizWatch getWatchByUser(Long userId) {
        BizWatchExample example = new BizWatchExample ();
        example.createCriteria ().andUserEqualTo (userId);
        List<BizWatch> watchList = watchMapper.selectByExample (example);
        if (watchList != null && watchList.size () != 0) return watchList.get (0);
        else return null;
    }

    public BizWatch getWatchByImei(Long imei) {
        BizWatchExample example = new BizWatchExample ();
        example.createCriteria ().andImeiEqualTo (imei);
        return watchMapper.selectByExample (example).get (0);
    }

    public Sleep getLatestSleep(Long userId) {
        String tableName = "biz_data_watch_sleep";
        String dataString = dataMapper.selectLatestByUser (userId, tableName);
        return JSONUtil.readValue (dataString, Sleep.class);
    }

    public List<Sleep> getSleepInfo(Long userId, Date start, Date end) {
        String tableName = "biz_data_watch_sleep";
        BizDeviceDataExample deviceDataExample = new BizDeviceDataExample ();
        deviceDataExample.setTableName (tableName);
        deviceDataExample.createCriteria ()
                .andDatetimeMeasureBetween (start, CommonUtil.getEndDay (end))
                .andUserIdEqualTo (userId);
        List<BizDeviceData> dataList = dataMapper.selectByExample (deviceDataExample);
        List<Sleep> sleeps = new ArrayList<> ();
        for (BizDeviceData deviceData : dataList) {
            Sleep sleep = JSONUtil.readValue (deviceData.getData (), Sleep.class);
            sleeps.add (sleep);
        }
        return sleeps;
    }

    public void connectCheck(Long IMEI) {
//        BizWatch watch = getWatchByImei (IMEI);
//        if (watch.getSyncStatus ().equals ((byte) 1)) return;
//        /** 联系人设置 **/
//        watchSettingService.setWatchPhoneNum (watch.getUser ());
//        /** 联系人设置结束 **/
//        /* 用户信息设置 */
//        watchSettingService.setUserInfo (watch.getUser ());
//        /* 用户信息设置结束 */
//        if (watch.getSitLock ()) watchSettingService.setWatchSit (watch.getUser (), "1");
//        else watchSettingService.setWatchSit (watch.getUser (), "0");
//        if (watch.getHrAlarmLock ()) watchSettingService.setHRAlarm (watch.getUser (), "1");
//        else watchSettingService.setHRAlarm (watch.getUser (), "0");
//        watchSettingService.setHRSchedule (watch.getUser (), watch.getHrSpan ());
//        watchSettingService.setGPSSpan (watch.getUser (), watch.getPositionSpan ());
//        watchSettingService.setStepSchedule (watch.getUser (), 60);
    }

    public int getSteps(Long userId) {
        Date date = new Date ();
        return getSteps (userId, date);
    }

    public int getSteps(Long userId, Date date) {
        BizMessageStepExample example = new BizMessageStepExample ();
        CommonUtil.getDay (date);
        CommonUtil.getEndDay (date);
        example.createCriteria ().andTimeBetween (CommonUtil.getDay (date), CommonUtil.getEndDay (date)).andUserEqualTo (userId);
        example.setOrderByClause ("time_ desc");
        List<BizMessageStep> stepList = stepMapper.selectByExample (example);
        if (stepList != null && stepList.size () != 0) return stepList.get (0).getCount ();
        else return 0;
    }

    public int updateWatchByUser(BizWatch watch, BizLogWatch logWatch) {
        BizWatchExample watchExample = new BizWatchExample ();
        System.out.println (watch);
        if (watch.getSitLock () != null) {
            if (watch.getSitLock ()) watchSettingService.setWatchSit (watch.getUser (), "1");
            else watchSettingService.setWatchSit (watch.getUser (), "0");
        }
        if (watch.getPositionSpan () != null) {
            watchSettingService.setGPSSpan (watch.getUser (), watch.getPositionSpan ());
        }
        if (watch.getHrAlarmLock () != null) {
            if (watch.getHrAlarmLock ()) watchSettingService.setHRAlarm (watch.getUser (), "1");
            else watchSettingService.setHRAlarm (watch.getUser (), "0");
        }
        if(watch.getSleepStatus ()!=null){
            Map<String,Object> params=new HashMap<> ();
            if(watch.getSleepStatus ())params.put ("status","1");
            else params.put ("status","0");
            params.put ("type", OutMessage.SLEEPSET);
            params.put ("userId",watch.getUser ());
            params.put ("startTime",watch.getSleepStartTime ());
            params.put ("endTime",watch.getSleepEndTime ());
            watchSettingService.watchOperate (params);
        }
        watchExample.createCriteria ().andUserEqualTo (watch.getUser ());
        watchMapper.updateByExampleSelective (watch, watchExample);
        return watchSettingService.insertLog (logWatch);
    }

    public BizMessagePosition getPosition(Long userId) {
        PageHelper.startPage (0, 1);
        List<BizMessagePosition> positions = positionMapper.selectByExample (null);
        if (positions.size () != 0 && positions != null) return positions.get (0);
        else return null;
    }
}
