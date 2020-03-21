package com.coco.cloud.netty.v2.register;


import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/21 18:50
 */
public class Register {

    private int port;

    public Register(int port){
        this.port = port;
    }

    public void start() {
        /*
            1.定义两个线程池 boss线程池和worker线程池
         */
        //用于处理服务器端接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //进行网络通信（读写）
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            // 2.核心服务类BootStrap
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup,workerGroup)
                    //指定NIO的模式
                    .channel(NioServerSocketChannel.class)
                    //配置具体的数据处理方式
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    /* 配置编码解码方式
                                     入参有5个，分别解释如下
                                     maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
                                     lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                                     lengthFieldLength：长度字段的长度。如：长度字段是int型表示，那么这个值就是4（long型就是8）
                                     lengthAdjustment：要添加到长度字段值的补偿值
                                     initialBytesToStrip：从解码帧中去除的第一个字节数
                                     */
                                    .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4))
                                    .addLast(new LengthFieldPrepender(4))
                                    //对象参数类型编码器
                                    .addLast("encoder",new ObjectEncoder())
                                    //对象参数类型解码器
                                    .addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                    // 自定义业务处理
                                    .addLast(new RegisterHandler());
                        }
                    })
                    //设置TCP缓冲区
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置发送数据缓冲大小
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    //设置接受数据缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    //保持连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture sync = server.bind(port).sync();
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
