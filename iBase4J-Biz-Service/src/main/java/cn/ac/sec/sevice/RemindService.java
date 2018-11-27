package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizRemindMapper;
import cn.ac.sec.entity.BizRemind;
import cn.ac.sec.entity.BizRemindExample;
import cn.ac.sec.protocol.message.OutMessage;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.ibase4j.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RemindService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BizRemindMapper remindMapper;

    @Autowired
    private WatchSettingService watchSettingService;


    private static final SimpleDateFormat format= new SimpleDateFormat ("yyyyMMddhhmm");


    public List<BizRemind> getScheduleRemindList() {
        return remindMapper.getReminds ();
    }

    public int createRemind(BizRemind remind){
        enableRemind (remind);
        return remindMapper.insertSelective (remind);

    }

    public int enableRemind(BizRemind remind) {
        Map<String,Object> watchParams= new HashMap<> ();
        String params="";
        watchParams.put ("userId",remind.getUser ());
        watchParams.put ("type", OutMessage.REMIND);

        switch (remind.getRemindType ()){
            case BizRemind.WatchOnce:
                params+="0,|"+remind.getRemindContent()+"|,"+format.format(remind.getRemindTime());
                watchParams.put ("param",params);
                watchSettingService.watchOperate (watchParams);
                break;
            case BizRemind.WatchSchedule:
                params+="1,|"+remind.getRemindContent()+"|,"+format.format(remind.getRemindTime());
                watchParams.put ("param",params);
                watchSettingService.watchOperate (watchParams);
                break;
            case BizRemind.MobileOnce:
                break;
            case BizRemind.MobileSchedule:
                break;
        }
        return 0;
    }

    public int disableWatchRemind(BizRemind remind) {
        Map<String,Object> watchParams= new HashMap<> ();
        watchParams.put ("userId",remind.getUser ());
        watchParams.put ("type", OutMessage.REMIND);
        remind.setDeleteStatus (true);
        switch (remind.getRemindType ()){
            case BizRemind.WatchOnce:
            case BizRemind.WatchSchedule:
                String params=format.format(remind.getRemindTime())+",0";
                watchParams.put ("param",params);
                watchSettingService.watchOperate (watchParams);
                break;
        }
        return 0;
    }

    public int disableRemind(BizRemind remind) {
        remind.setDeleteStatus (true);
        disableWatchRemind (remind);
        return remindMapper.updateByPrimaryKeySelective (remind);
    }

    public BizRemind getRemindById(Long id){
        return remindMapper.selectByPrimaryKey (id);
    }

//    public int

    public int updateById(BizRemind remind) {
        return remindMapper.updateByPrimaryKeySelective (remind);
    }

    public int deleteRemindById(Long id) {
        return remindMapper.deleteByPrimaryKey (id);
    }

    public List<BizRemind> getRemindListByUser(Long userId, Long parentId) {
        BizRemindExample example=new BizRemindExample ();
        example.createCriteria ().andRemindAddUserEqualTo (userId).andUserEqualTo (parentId);
        List<BizRemind> list = remindMapper.selectByExample (example);
        if (list == null || list.size () == 0) throw new BusinessException ("没有提醒信息");
        return remindMapper.selectByExample (example);
    }

    public int deleteRemind(BizRemind remind) {
        disableWatchRemind (remind);
        return remindMapper.deleteByPrimaryKey (remind.getId ());
    }
}
