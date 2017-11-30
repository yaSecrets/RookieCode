package com.madjava.webService.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class MyCilent
{
	public static void main(String[] args) throws IOException
	{
		//创建客户端+端口
		DatagramSocket client = new DatagramSocket(6666);
		//准备数据
		String msg = "udp编程";
		byte[] data = msg.getBytes();
		//打包
 		DatagramPacket packet = new DatagramPacket(data, data.length,new InetSocketAddress("localhost",8888));
 		//发送
 		client.send(packet);
		//释放
 		client.close();
	}
}
