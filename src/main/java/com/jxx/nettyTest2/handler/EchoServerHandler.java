package com.jxx.nettyTest2.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Strange
 * @ClassName EchoServerHandler.java
 * @Description TODO echo服务端处理器
 * @createTime 2020年08月22日 16:47:00
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 	当前channel从远端读取到数据触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //支撑数组
        if(in.hasArray()){ //检查ByteBuf是否有一个支撑数组
            byte[] array = in.array();//如果有,则获取对该数组的引用
            int offset = in.arrayOffset() + in.readerIndex();//计算第一个字节的偏移量
            int length = in.readableBytes();//获取可读字节数
//            handleArray(array,offset,length); 使用数组,偏移量和长度作为参数调用你的方法
            System.out.println(array);
        }
        System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
        //将接受到的消息写给发送者,而不冲刷出站消息
        ctx.write(in);
    }

    /**
     * channel read消费完读取的数据的时候被触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息冲刷到远程节点,并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 在发生异常时,记录错误并关闭Channel
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}