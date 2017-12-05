package com.madjava.webService.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Secrets
 *创建服务器并启动
 * 2017年11月11日上午10:35:01
 */
public class Server
{
	private ServerSocket server;
	//启动方法
	public void start() {
		try
		{
			server = new ServerSocket(8888);
		
			this.receive();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	//接收客户端
	private void receive(){
		try
		{
			Socket client = server.accept();
			String  msg = null;//接收客户端的请求
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while((msg = br.readLine()).length() > 0){
				sb.append(msg);
				sb.append("\r\n");
				if(null == msg){
					break;
				}
			}
			String requestInfo = sb.toString().trim();//接收客户端的请求信息
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		Server server = new Server();
		server.start();
	}
}
