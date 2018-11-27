package cn.ac.sec.sevice;

import cn.ac.sec.dao.BizUserSubscriptionMapper;
import cn.ac.sec.entity.BizUser;
import cn.ac.sec.entity.BizUserSubscription;
import cn.ac.sec.entity.BizUserSubscriptionExample;
import org.ibase4j.core.exception.BusinessException;
import org.ibase4j.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Calendar.MONTH;

@Service
public class UserSubscriptionService extends BaseService<BizUserSubscription> {
    @Autowired
    BizUserSubscriptionMapper subscriptionMapper;


    public BizUserSubscription getLastSubscription(Long userId) {
        BizUserSubscriptionExample example = new BizUserSubscriptionExample ();
        example.createCriteria ().andUserIdEqualTo (userId);
        example.setOrderByClause ("expiration_time desc");
        List<BizUserSubscription> subscriptions = subscriptionMapper.selectByExample (example);
        if (subscriptions.size () == 0) return null;
        else {
            BizUserSubscription subscription = subscriptions.get (0);
            subscription.setCreatedDate (subscriptions.get (subscriptions.size () - 1).getCreatedDate ());
            return subscriptions.get (0);
        }
    }

    public BizUserSubscription updateSubscription(BizUser user) {
        Long userId = user.getId ();
        BizUserSubscriptionExample example = new BizUserSubscriptionExample ();
        example.createCriteria ().andUserIdEqualTo (userId);
        List<BizUserSubscription> subscriptions = subscriptionMapper.selectByExample (example);
        BizUserSubscription subscription = new BizUserSubscription ();
        if (subscriptions.size () == 0) {
            newSub (user);
        } else {
            BizUserSubscription lastSubscription = getLastSubscription (user.getId ());
            if (lastSubscription.getExpirationTime ().before (new Date ())) {
                newSub (user);
            } else {
                subscription.setUserId (userId);
                subscription.setCreatedDate (lastSubscription.getCreatedDate ());
                subscription.setTimeLimit (user.getSubTime ());
                subscription.setCreatedBy (user.getUpdatedBy ());
                subscription.setExpirationTime (DateUtil.addDate (lastSubscription.getExpirationTime (), MONTH, user.getSubTime ()));
                subscriptionMapper.insertSelective (subscription);
            }
        }
        return subscription;
    }

    private int newSub(BizUser user) {
        BizUserSubscription subscription = new BizUserSubscription ();
        Date startDate = new Date ();
        subscription.setUserId (user.getId ());
        subscription.setCreatedDate (new Date ());
        if(user.getSubTime()==null)throw new BusinessException("首次添加用户必须填写服务订阅时长");
        subscription.setTimeLimit (user.getSubTime ());
        subscription.setCreatedBy (user.getCreatedBy ());
        subscription.setExpirationTime (DateUtil.addDate (startDate, MONTH, subscription.getTimeLimit ()));
        return subscriptionMapper.insertSelective (subscription);
    }
}
