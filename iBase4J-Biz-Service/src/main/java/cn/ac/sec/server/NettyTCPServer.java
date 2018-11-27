package cn.ac.sec.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class NettyTCPServer extends AbstractNettyServer{
    private static final Logger LOG = LogManager
            .getLogger(NettyTCPServer.class);

    private ServerBootstrap serverBootstrap;

    private static final Map<Long,Channel> client_map = new ConcurrentHashMap<> () ;
    private static final Map<Long,Long> user_map =new ConcurrentHashMap<> ();


    private static final Map<String,Long> identifier=new ConcurrentHashMap<> ();
    public NettyTCPServer(NettyConfig nettyConfig,
                          ChannelInitializer<? extends Channel> channelInitializer)
    {
        super(nettyConfig, channelInitializer);
    }

    @Override
    public void startServer() throws Exception {
        try {
            serverBootstrap = new ServerBootstrap();
            Map<ChannelOption<?>, Object> channelOptions = nettyConfig.getChannelOptions();
            if(null != channelOptions){
                Set<ChannelOption<?>> keySet = channelOptions.keySet();
                // 获取configuration配置到channelOption
                for(ChannelOption option : keySet)
                {
                    serverBootstrap.option(option, channelOptions.get(option));
                }
            }
            // reactor多线程模型，配置bossGroup和workGroup
            // bossGroup和workGroup使用spring容器管理
            serverBootstrap.group(getBossGroup(),getWorkerGroup())
                    .channel(NioServerSocketChannel.class)
                    .childHandler(getChannelInitializer());
            // 绑定端口，启动并监听
            Channel serverChannel = serverBootstrap.bind(nettyConfig.getSocketAddress()).sync()
                    .channel();
            ALL_CHANNELS.add(serverChannel);
        } catch(Exception e) {
            LOG.error("TCP Server start error {}, going to shut down", e);
            super.stopServer();
            throw e;
        }
        finally {
            LOG.debug ("server started");
        }
    }

    @Override
    public TransmissionProtocol getTransmissionProtocol() {
        return TRANSMISSION_PROTOCOL.TCP;
    }

    // 配置自己的initializer
    @Override
    public void setChannelInitializer(ChannelInitializer<? extends Channel> initializer) {
        this.channelInitializer = initializer;
        serverBootstrap.childHandler(initializer);
    }


    public static Map<Long,Channel> getClient_map(){
        return client_map;
    }
    @Override
    public String toString() {
        return "NettyTCPServer [socketAddress=" + nettyConfig.getSocketAddress()
                + ", portNumber=" + nettyConfig.getPortNumber() + "]";
    }

    public static Map<Long,Long> getUser_map(){
        return user_map;
    }

    public static Map<String, Long> getIdentifier() {
        return identifier;
    }


}
