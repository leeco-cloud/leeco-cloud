package com.coco.cloud.netty.v1.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/17 22:22
 */
public class CoCoNioServer {

    public static void main(String[] args) throws Exception {

        // 1.建立selector多路复用器
        Selector selector = Selector.open();

        // 2.创建buffer缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putChar('a');

        // 3.建立channel通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(8080));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);

        // 读写操作
        while(true){
            int select = selector.select();
            if (select == 0) continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            if (iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                if (next.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) next.channel();
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);
                    //注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
                    client.register(selector, SelectionKey.OP_READ);
                    //将此对应的channel设置为准备接受其他客户端请求
                    next.interestOps(SelectionKey.OP_ACCEPT);
                    client.write(byteBuffer);
                }
                if (next.isReadable()){
                    SocketChannel client = (SocketChannel) next.channel();
                    client.read(byteBuffer);
                    next.interestOps(SelectionKey.OP_READ);
                    byteBuffer.flip();
                }
            }
        }
    }

}
