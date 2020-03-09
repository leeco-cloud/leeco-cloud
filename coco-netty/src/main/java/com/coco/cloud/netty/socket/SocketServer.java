package com.coco.cloud.netty.socket;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:54
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = new ServerSocket(8080);

        Socket accept = socketServer.accept();

        InputStream inputStream = accept.getInputStream();


        byte[] b = new byte[1024];

        int read = inputStream.read(b);

        System.out.println(new String(b));


    }

}
