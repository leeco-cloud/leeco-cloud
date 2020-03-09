package com.coco.cloud.netty.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2020/3/9 19:54
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8080);
        OutputStream outputStream = socket.getOutputStream();
        String data = "haha";
        outputStream.write(data.getBytes());
    }

}
