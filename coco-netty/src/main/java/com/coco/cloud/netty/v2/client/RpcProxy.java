package com.coco.cloud.netty.v2.client;

import com.coco.cloud.netty.v2.protocol.InvokerProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/21 19:47
 */
public class RpcProxy implements InvocationHandler {

    private Class target;

    public Object getProxy(Class clazz){
        this.target = clazz;
        return Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        InvokerProtocol invokerProtocol = new InvokerProtocol();
        invokerProtocol.setServiceName(this.target.getSimpleName());
        invokerProtocol.setMethodName(method.getName());
        invokerProtocol.setValues(args);
        invokerProtocol.setParams(method.getParameterTypes());

        ClientHandler clientHandler = new ClientHandler();
        // 开启netty客户端
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            Bootstrap client = new Bootstrap();
            client.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                                    //自定义协议编码器
                                    .addLast("frameEncoder", new LengthFieldPrepender(4))
                                    //对象参数类型编码器
                                    .addLast("encoder", new ObjectEncoder())
                                    //对象参数类型解码器
                                    .addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                    .addLast("handler",clientHandler);
                        }
                    });
            ChannelFuture future = client.connect("localhost", 8080).sync();
            future.channel().writeAndFlush(invokerProtocol);
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
        return clientHandler.getResponse();
    }
}
