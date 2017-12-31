package com.madjava.spider.demo0;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/30.
 */
public class Spider0 {

    public static String sentGet(String url){
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                InputStream inputStream = entity.getContent();
             /*  System.out.println(EntityUtils.toString(entity));*/
                result = EntityUtils.toString(entity);

            }

        } catch (Exception e) {
            System.out.println("发送get请求异常"+e);
            e.printStackTrace();
        }finally {//关闭io流
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //用正则表达式处理字符串获取想要的内容
    public static List<Zhihu> getZhihu(String targetStr){
        List<Zhihu> results = new ArrayList<>();
        //匹配标题
        Pattern questionPattern = Pattern.compile("question_link.+?>\\s(.+?)\\s<");
        //定义一个matcher来做匹配
        Matcher questionMatcher = questionPattern.matcher(targetStr);
        //匹配url，也就是链接的问题
        Pattern urlPattern = Pattern.compile("question_link.+?href=\\\"(.+?)\\\"");
        Matcher urlMatcher = urlPattern.matcher(targetStr);
        //问题和链接要同时匹配到
        boolean isFind = questionMatcher.find()&&urlMatcher.find();
        Zhihu tempZhihu = null;
        while (isFind){
            tempZhihu = new Zhihu();
            tempZhihu.setQuestion(questionMatcher.group(1));
            tempZhihu.setZhihuUrl("\"http://www.zhihu.com\""+urlMatcher.group(1));
            results.add(tempZhihu);
            isFind = questionMatcher.find() && urlMatcher.find();
        }
        return results;
    }
    public static void main (String[] args){
        String url = "http://www.zhihu.com/explore/recommendations";
        String content = sentGet(url);
//        System.out.println(content);
        /*String imgSrc = RegaxString(result,"question_link.+?>\\s(.+?)\\s<");*/
        List<Zhihu> myZhihu = getZhihu(content);
        System.out.println(myZhihu);
    }
}
