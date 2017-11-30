package com.madjava.webService.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		//创建客户端，必须指定服务器端+端口
		Socket client= new Socket("localhost", 8888);
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String echo = br.readLine();
		System.out.println(echo);
	}
}
