package cn.ac.sec.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public interface NettyServer extends Server {
    public ChannelInitializer<? extends Channel> getChannelInitializer();

    /**
     * 设置自己的ChannelInitializer
     *
     * @param initializer
     *            pipeline的工厂类，主要为每个新的链接创建一个pipeline
     */
    public void setChannelInitializer(ChannelInitializer<? extends Channel> initializer);

    /**
     * 获取netty server的configuration
     *
     * @return .
     */
    public NettyConfig getNettyConfig();

}
