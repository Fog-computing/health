package cn.ac.sec.schedule;

import cn.ac.sec.entity.BizRemind;
import cn.ac.sec.sevice.BizUserService;
import cn.ac.sec.sevice.RemindService;
import cn.ac.sec.util.PushExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class RemindSchedule {
    private final Logger logger = LogManager.getLogger ();

    @Autowired
    private RemindService remindService;

    @Autowired
    private BizUserService userService;

    /**
     * 定时进行消息推送
     */
    @Scheduled(cron = "0 0/1 * * * *")
    public void cleanExpiredSessions() {
        List<BizRemind> reminds = remindService.getScheduleRemindList ();
        for (BizRemind remind : reminds) {
            if (remind.getRemindType ().equals (BizRemind.MobileOnce))
                remindService.disableRemind (remind);
            String token = userService.getUserById(remind.getUser()).getToken();
            if(token==null||"".equals(token))continue;
            PushExample.testSendPushWithCallback (token, "定时提醒", remind.getRemindContent ());
        }
    }

}
