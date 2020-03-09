package com.coco.cloud.netty.rpc.client;

import com.coco.cloud.netty.rpc.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:54
 */
public class SocketClient {

    public static void main(String[] args){

        ObjectInputStream objectInputStream = null;
        OutputStream outputStream;
        try{
            while(true){
                Socket socket = new Socket("localhost",8080);
                outputStream = socket.getOutputStream();
                String data = "userService";
                outputStream.write(data.getBytes());
                InputStream inputStream = socket.getInputStream();

                objectInputStream = new ObjectInputStream(inputStream);

                Object object = objectInputStream.readObject();

                UserService userService = new UserServiceProxy(object).getProxy();

                userService.showName();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
