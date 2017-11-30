package com.madjava.webService.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器 指定端口 
 * @author Secrets
 *
 * 2017年11月10日下午9:18:17
 */
public class Server
{
	public static void main(String[] args) throws IOException
	{
		//创建服务器指定端口
		ServerSocket server = new ServerSocket(8888);
		while(true){//死循环 一个accept接收一个客户端
			//接收客户端的连接 阻塞式
			Socket socket= server.accept();
			System.out.println("一个客户端建立链接");
			//发送数据
			String msg = "欢迎使用";
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(msg);
			bw.newLine();
			bw.flush();
		}
	}
}
