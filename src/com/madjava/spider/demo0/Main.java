package com.madjava.spider.demo0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/29.
 */
public class Main
{
    /**
     *@描述 根据url路径发送请求获取页面内容

     *@参数

     *@返回值

     */
    public static String sentGet(String url){
        //定义一个字符串来存储网页内容
        String result = "";
        //定义一个缓冲字符输入流
        BufferedReader in = null;
        try {
            //把String转化为URL对象
            URL realUrl = new URL(url);
            //初始化一个到url的链接
            URLConnection connection = realUrl.openConnection();
            //开始实际链接
            connection.connect();
            //h获取一个输入流
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //用来存储每一行的数据
            String line = "";
            while ((line = in.readLine()) != null){
                //遍历抓取到的每一行并存储到result中
                result += line ;
            }

        } catch (Exception e) {
            System.out.println("发送get请求异常"+e);
            e.printStackTrace();
        }finally {//关闭io流
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //用正则表达式处理字符串获取想要的内容
    public static  String RegaxString(String targetStr,String patternStr){
        //定义一个模板,抓取想要的内容，相当于埋好陷阱匹配的地方就会陷下去
        Pattern pattern = Pattern.compile(patternStr);
        //定义一个matcher来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        while (matcher.find()){
            return  matcher.group(1);
        }
        return "";
    }
    public static void main (String[] args){
        String url = "http://www.baidu.com";
        String result = sentGet(url);
        /*System.out.println(result);*/
        String imgSrc = RegaxString(result,"src=\"(.+?)\"");
        System.out.println(imgSrc);
    }
}
