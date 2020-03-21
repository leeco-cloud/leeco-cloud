package com.coco.cloud.netty.v1.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/17 22:22
 */
public class CoCoNioClient {

    public static void main(String[] args) throws Exception {

        // 1.建立selector多路复用器
        Selector selector = Selector.open();

        // 2.创建buffer缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putChar('a');

        // 3.建立channel通道
        SocketChannel clint = SocketChannel.open(new InetSocketAddress("localhost", 8080));
        clint.configureBlocking(false);
        clint.register(selector, SelectionKey.OP_READ);

        // 读写操作
        while(true){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            if (iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                if (next.isReadable()){
                    SocketChannel client = (SocketChannel) next.channel();
                    client.configureBlocking(false);
                    //注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
                    client.register(selector, SelectionKey.OP_READ);
                    //将此对应的channel设置为准备接受其他客户端请求
                    next.interestOps(SelectionKey.OP_READ);
                    client.read(byteBuffer);
                }
                if (next.isWritable()){
                    SocketChannel client = (SocketChannel) next.channel();
                    client.write(byteBuffer);
                    next.interestOps(SelectionKey.OP_WRITE);
                    byteBuffer.flip();
                }
            }
        }

    }

}
