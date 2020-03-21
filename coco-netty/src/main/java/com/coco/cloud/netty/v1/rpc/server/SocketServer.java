package com.coco.cloud.netty.v1.rpc.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:54
 */
public class SocketServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        while(true){
            ServerSocket socketServer = new ServerSocket(8080);

            Socket accept = socketServer.accept();

            InputStream inputStream = accept.getInputStream();


            byte[] b = new byte[1024];

            inputStream.read(b);

            String serviceName = new String(b);

            if ("userService".equals(serviceName)){
                System.out.println(serviceName);
                OutputStream outputStream = accept.getOutputStream();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                objectOutputStream.writeObject(new UserServiceImpl());

                objectOutputStream.flush();

                objectOutputStream.close();
                inputStream.close();
            }
        }

    }

}
