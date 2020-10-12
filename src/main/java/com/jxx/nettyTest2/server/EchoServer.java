package com.jxx.nettyTest2.server;

import com.jxx.nettyTest2.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Strange
 * @ClassName EchoServer.java
 * @Description TODO  Echo服务器
 * @createTime 2020年08月24日 10:31:00
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        //设置端口值,参数格式不对抛异常
        int port = 8080;
        new EchoServer(port).start();
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //1.创建Event-LoopGroup实例以进行事件的处理,如接受新连接以及读/写数据
        EventLoopGroup group = new NioEventLoopGroup();
         try {
             //2.创建server-bootStrap的实例用以绑定服务器
             ServerBootstrap bootstrap = new ServerBootstrap();

             bootstrap.group(group)
                     //3.指定所使用的NIO传输channel
                     .channel(NioServerSocketChannel.class)
                     //4.使用指定的端口设置套接字地址
                     .localAddress(new InetSocketAddress(port))
                     //5.添加一个EchoServer-handler到子Channel的ChannelPipeline
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel channel) throws Exception {
                             //EchoServerHandler被标注为@Shareable,所以我们可以总是使用同样的实例
                             //使用EchoServerHandler的实例初始化每一个新的Channel
                             channel.pipeline().addLast(serverHandler);
                         }
                     });
             //6.ServerBootstrap.bind()方法绑定服务器,异步的绑定服务器;调用sync()方法阻塞等待直到绑定完成
             ChannelFuture channelFuture = bootstrap.bind().sync();
             //7.获取Channel的CloseFuture,并且阻塞当前线程直到它完成
             channelFuture.channel().closeFuture().sync();
         } catch (Exception e) {
            e.printStackTrace();
         }finally {
             //8.关闭EventLoopGroup,释放所有的资源
             group.shutdownGracefully().sync();
         }

    }

}
