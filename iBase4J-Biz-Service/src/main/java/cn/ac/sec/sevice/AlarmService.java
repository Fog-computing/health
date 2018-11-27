package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizMessageAlarmMapper;
import cn.ac.sec.dao.BizMessageAlarmTypeMapper;
import cn.ac.sec.entity.*;
import cn.ac.sec.entity.transport.PageTransport;
import cn.ac.sec.util.CommonUtil;
import cn.ac.sec.util.MQMessageSender;
import cn.ac.sec.util.PushExample;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AlarmService extends BaseService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizMessageAlarmMapper alarmMapper;
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private BizUserService userService;
    @Autowired
    private PositionService positionService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizMessageAlarmTypeMapper alarmTypeMapper;
    @Autowired
    private UserRelationPhoneNumService phoneNumService;

    protected int addAlarm(BizMessageAlarm alarm){
        BizUser user = userService.getUserById (alarm.getUser ());
        List<BizUser> userList = userService.getUserByParent (alarm.getUser ());
        BizMessageAlarmType alarmType = alarmTypeMapper.selectByPrimaryKey (alarm.getType ());
        alarmMapper.insertSelective (alarm);
        for (BizUser children : userList) {
            if(children.getToken()==null||"".equals(children.getToken()))continue;
            PushExample.testSendPushWithCallback (children.getToken(), alarmType.getName (), "" +
                    "用户" + user.getUsername () + "发生" + alarmType.getName () + "报警");
        }
        Integer count = getAlarmCount ();
        try {
            MQMessageSender.sendAlarmMessage ("text", count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return count;
    }


    private Map<String,Object> getAlarmMap(Long userId){
        Map<String,Object> resultMap=new HashMap<> ();
        BizUser user=userService.getUserById (userId);
        List<BizUserRelationPhoneNum> phoneNumList=phoneNumService.getPhoneNumListByUserId (userId);
        resultMap.put ("user",user);
        resultMap.put ("phoneNumList",phoneNumList);
        return resultMap;
    }

    public Integer getAlarmCount() {
        BizMessageAlarmExample example = new BizMessageAlarmExample ();
        example.createCriteria ().andStatusEqualTo ((byte) 0);
        return alarmMapper.countByExample (example);
    }



    public Long checkNotProcess(Long userId){
        BizMessageAlarmExample alarmExample =new BizMessageAlarmExample ();
        alarmExample.createCriteria ().andStatusEqualTo ((byte)1).andProcessUserEqualTo (userId);
        List<BizMessageAlarm> alarms = alarmMapper.selectByExample (alarmExample);
        if(alarms==null||alarms.size ()==0)return 0L;
        else return alarms.get (0).getId ();
    }

    public List<BizMessageAlarmType> getAlarmTypes() {
        return alarmTypeMapper.selectByExample (null);
    }
    public Map<String,Object> getLatestAlarm(){
        BizMessageAlarmExample example =new BizMessageAlarmExample();
        example.createCriteria ().andStatusEqualTo ((byte)0);
        example.setOrderByClause ("time_ desc");
        PageHelper.startPage (0,1,false);
        List<BizMessageAlarm> messageAlarms = alarmMapper.selectByExample (example);
        if(messageAlarms!=null&&messageAlarms.size ()!=0){
            BizMessageAlarm alarm=messageAlarms.get (0);
            Map<String,Object> resultMap=getAlarmMap (alarm.getUser ());
            resultMap.put ("alarm",alarm);
            resultMap.put ("type",alarmTypeMapper.selectByPrimaryKey (alarm.getType ()));
            return resultMap;
        } else throw new BusinessException ("当前没有未处理的报警");
    }

    public List<Map<String, Object>> getAlarmsByDate(Long userId, Date start, Date end) {
        BizMessageAlarmExample example =new BizMessageAlarmExample();
        //显示已经处理和转发给专业人员进行处理的报警
        example.createCriteria ().andStatusBetween ((byte) 2, (byte) 3)
                .andUserEqualTo (userId).
                andTimeBetween (start, end);
        example.setOrderByClause ("time_ desc");
        PageHelper.startPage (0,5,false);
        List<BizMessageAlarm> messageAlarms = alarmMapper.selectByExample (example);
        if(messageAlarms!=null && messageAlarms.size ()!=0){
            List<Map<String,Object>> alarmList = new ArrayList<> ();
            for (BizMessageAlarm alarm:messageAlarms){
                Map<String,Object> alarmMap=new HashMap<> ();
                alarmMap.put ("alarm",alarm);
                alarmMap.put ("type",alarmTypeMapper.selectByPrimaryKey (alarm.getType ()));
                alarmList.add (alarmMap);
            }
            return alarmList;
        }
        else return null;
    }

    public int processAlarm(BizMessageAlarm messageAlarm){
        if (messageAlarm.getStatus () == 2) alarmMapper.updateByPrimaryKeySelective (messageAlarm);
        else alarmTransport (messageAlarm);
        Integer count = getAlarmCount ();
        try {
            MQMessageSender.sendAlarmMessage ("text", count);
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return count;
    }

    @Transactional
    public int alarmTransport(BizMessageAlarm messageAlarm){
        BizProfession profession = new BizProfession ();
        profession.setUserId (messageAlarm.getUser ());
        profession.setStatus ((byte)0);
        if (!professionService.checkProfession (messageAlarm.getUser ()))
            professionService.createProfession (profession);
        return alarmMapper.updateByPrimaryKeySelective (messageAlarm);
    }

    public List<Map<String, Object>> getAlarmListByUser(Long userId) {
        List<Map<String, Object>> alarms = alarmMapper.listWithTypeByUser (userId);
        return alarms;
    }

    public Map<String, Object> getAlarmDetailWithUserById(Long id) {
        return alarmMapper.detailWithProcessUser (id);
    }

    public Page<Map<String,Object>> getAlarmList(PageTransport transport){
        Page<Map<String,Object>> pager;
        pager = getPage (transport);
        System.out.println (pager.getCurrent ());
        System.out.println (pager.getOffset ());
        PageHelper.startPage (pager.getCurrent (),pager.getSize ());
        List<Map<String,Object>> alarms=alarmMapper.listWithType ();
        pager.setRecords (alarms);
        pager.setTotal ((int)((com.github.pagehelper.Page)alarms).getTotal ());
        return pager;
    }

    public Page<Map<String, Object>> getAlarmHistoryList(PageTransport transport, Date start, Date end, Byte status) {
        Page<Map<String, Object>> pager;
        pager = getPage (transport);
        if (start == null) start = new Date (0);
        if (end == null) end = new Date ();
        if (status == null) status = (byte) 4;
        System.out.println (pager.getCurrent ());
        System.out.println (pager.getOffset ());
        PageHelper.startPage (pager.getCurrent (), pager.getSize ());
        List<Map<String, Object>> alarms = alarmMapper.filterHistoryList (
                "%" + transport.getKeyword () + "%", start,
                CommonUtil.getEndDay (end), status);
        pager.setRecords (alarms);
        pager.setTotal ((int) ((com.github.pagehelper.Page) alarms).getTotal ());
        return pager;
    }


    public Map<String,Object> getAlarmDetail(Long id){
        BizMessageAlarm alarm=alarmMapper.selectByPrimaryKey (id);
        Map<String,Object> alarmMap=getAlarmMap (alarm.getUser ());
        alarmMap.put ("alarm",alarm);
        alarmMap.put ("type",alarmTypeMapper.selectByPrimaryKey (alarm.getType ()));
        return alarmMap;
    }

    public void updateProcessing(BizMessageAlarm alarm){
        alarmMapper.updateByPrimaryKeySelective (alarm);
    }

}
