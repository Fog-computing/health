package cn.ac.sec.schedule;

import cn.ac.sec.entity.BizUser;
import cn.ac.sec.entity.BizUserSubscription;
import cn.ac.sec.sevice.BizUserService;
import cn.ac.sec.sevice.UserSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class DisableSchedule {

    @Autowired
    BizUserService bizUserService;
    @Autowired
    UserSubscriptionService userSubscriptionService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanExpiredSessions() {
        List<BizUser> users = bizUserService.getUserList ();
        for (BizUser user : users) {
            if (user.getUserType ().equals ((byte) 2)) continue;
            BizUserSubscription subscription = userSubscriptionService.getLastSubscription (user.getId ());
            if (subscription == null) continue;
            if (subscription.getExpirationTime ().before (new Date ())) {
                BizUser disableUser = new BizUser ();
                disableUser.setId (user.getId ());
                disableUser.setEnableStatus ((byte) 0);
                disableUser.setDisableComment ("订阅时间到期");
                bizUserService.updateUserByPrimaryKey (disableUser);
            }
        }
    }

}
