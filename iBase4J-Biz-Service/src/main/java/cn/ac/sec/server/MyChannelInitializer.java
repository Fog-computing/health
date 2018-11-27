package cn.ac.sec.server;

//import cn.ac.sec.server.handler.MessageInHandler;
import cn.ac.sec.server.handler.MessageOutHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class MyChannelInitializer extends
        ChannelInitializer<SocketChannel> {
    private static final int MAX_IDLE_SECONDS = 60;

    @Override
    protected void initChannel(SocketChannel ch) {
        // 添加到pipeline中的handler会被串行处理(PS: 类似工业生产中的流水线)
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast ("keep_alive",new IdleStateHandler (60,60,60));
        pipeline.addLast ("messageOut",new MessageOutHandler ());
        pipeline.addLast(new IdleStateHandler (60,0,0)
        );
    }

}

