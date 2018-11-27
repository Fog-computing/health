package cn.ac.sec.server;

import cn.ac.sec.context.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerManager {

    @Autowired
    private AbstractNettyServer tcpServer;

    public ServerManager() {
    }

    public void startServer(int port) throws Exception {
        tcpServer.startServer(port);
    }

    public void startServer() throws Exception {
        tcpServer.startServer();
    }
    public void stopServer() throws Exception {
        tcpServer.stopServer();
    }

}
