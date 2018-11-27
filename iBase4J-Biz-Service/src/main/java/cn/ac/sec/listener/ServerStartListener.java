package cn.ac.sec.listener;


import cn.ac.sec.server.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ServerStartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ServerManager sm;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println ("ContextLoad");
        try {
//            sm.startServer ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
