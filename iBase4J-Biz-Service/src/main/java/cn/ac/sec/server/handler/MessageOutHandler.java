package cn.ac.sec.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

public class MessageOutHandler  extends ChannelOutboundHandlerAdapter{
    @Override
    public void write(ChannelHandlerContext ctx, Object msg,
                      ChannelPromise promise) throws Exception {
        if (msg instanceof byte[]) {
            byte[] bytesWrite = (byte[])msg;
            ByteBuf buf = ctx.alloc().buffer(bytesWrite.length);
            buf.writeBytes(bytesWrite);
            ctx.writeAndFlush(buf).addListener((ChannelFutureListener) future -> System.out.println("下发成功"));
        }
        if(msg instanceof String){
            byte[] bytesWrite = ((String) msg).getBytes ();
            ByteBuf byteBuf=ctx.alloc ().buffer (bytesWrite.length);
            byteBuf.writeBytes (bytesWrite);
            System.out.println ((String)msg);
            ctx.writeAndFlush (byteBuf).addListener ((ChannelFutureListener) channelFuture -> System.out.println ("下发命令成功"));
        }
    }
}
