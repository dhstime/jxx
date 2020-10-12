package com.jxx.nettyTest2.client;

import com.jxx.nettyTest2.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Strange
 * @ClassName EchoClient.java
 * @Description TODO
 * @createTime 2020年08月24日 15:01:00
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
         try {
             //指定EventLoopGroup以处理客户端时间;需要适用于NIO的实现
             Bootstrap bootstrap = new Bootstrap();
             bootstrap.group(group)
             .channel(NioSocketChannel.class)
             .remoteAddress(new InetSocketAddress(host,port))
             .handler(new ChannelInitializer<SocketChannel>() {
                 //创建Channel时,向ChannPipeline中添加一个EchoClientHandler实例
                 @Override
                 protected void initChannel(SocketChannel socketChannel) throws Exception {
                     socketChannel.pipeline().addLast(new EchoClientHandler());

                 }
             });
             //连接到远程节点,阻塞等待直到连接完成
             ChannelFuture channelFuture = bootstrap.connect().sync();
             //阻塞,直到Channel关闭
             channelFuture.channel().closeFuture().sync();
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
             //关闭线程池并且释放所有的资源
             group.shutdownGracefully().sync();
         }
    }

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        Integer port = 8080;
        new EchoClient(host,port).start();
    }
}
