package com.jxx.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Strange
 * @ClassName Test1.java
 * @Description TODO
 * @createTime 2020年08月18日 23:30:00
 */
public class ScokerTest1 {
    public static void main(String[] args) throws Exception{
        int portNumber = 8080;
        //创建一个新的ServerSocket，用以监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);
        //对accept()方法的调用将被阻塞，直到一个连接创建
        Socket clientSocket= serverSocket.accept();
        //这些流对象都派生于该套接字的流对象
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());

        String request , respone;

        while((request = reader.readLine()) != null){
            //处理循环开始
            if("Done".equals(request)){
                //如果客户端发送了done则退出处理循环
                break;
            }
            writer.write(request);
        }
    }
}
