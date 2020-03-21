package com.coco.cloud.netty.v2.register;

import com.coco.cloud.netty.v2.protocol.InvokerProtocol;
import com.coco.cloud.netty.v2.server.UserServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/21 19:36
 */
public class RegisterHandler extends ChannelInboundHandlerAdapter {

    /**
     * 保存所有服务
     */
    private final List<Class<?>> allServiceImpl = new ArrayList<>();

    /**
     * 保存所有服务 模拟bean
     */
    private final Map<String,Object> beanSingletonCache = new ConcurrentHashMap<>(1 << 2);

    public RegisterHandler() throws Exception {
        // 1.扫描获取所有服务 如果是分布式环境下 应该让服务端自己主动注册进来
        scannerServer();
        // 2.注册
        doRegisterBeanSingleton();
    }

    private void scannerServer() throws Exception {
        URL resource = this.getClass().getClassLoader().getResource("com/coco/cloud/netty/v2/server/UserServiceImpl");
        assert resource != null;
        String file = resource.getFile();
        if (file != null && !" ".equals(file)){
            File userServiceImplFile = new File(file);
            Class<?> userServiceImpl = Class.forName(userServiceImplFile.getName());
            allServiceImpl.add(userServiceImpl);
        }
    }

    private void doRegisterBeanSingleton() {
        allServiceImpl.forEach(serviceImpl -> {
            try {
                beanSingletonCache.put(serviceImpl.getInterfaces()[0].getSimpleName(), serviceImpl.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 重写read方法 接受请求
     * @param ctx ChannelHandlerContext
     * @param msg Object
     * @throws Exception Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol invokerProtocol = (InvokerProtocol) msg;
        if (beanSingletonCache.containsKey(invokerProtocol.getServiceName())){
            Object server = beanSingletonCache.get(invokerProtocol.getServiceName());
            Method method = server.getClass().getMethod(invokerProtocol.getMethodName(), invokerProtocol.getParams());
            method.invoke(server,invokerProtocol.getValues());
        }
        // 相应给客户端
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

}
