package com.madjava.webService.udp;

import java.io.IOException;import java.io.StreamCorruptedException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 创建服务端+端口
 * @author Secrets
 *
 * 2017年11月9日下午10:19:37
 */
public class MyServer
{
	public static void main(String[] args) throws IOException
	{
		//创建服务器+端口
		DatagramSocket server = new DatagramSocket(8888);
		//准备接受容器
		byte[] container = new byte[1024];
		//封装成包
		DatagramPacket packet = new DatagramPacket(container, container.length);
		//接受数据
		server.receive(packet);
		//分析数据
		byte[] data =packet.getData();
		int len = packet.getLength();
		System.out.println(new String(data, 0, len));
		//释放
		server.close();
	}
}
