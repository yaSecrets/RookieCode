package com.madjava.spider.demo0;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/31.
 */
public class Zhihu {
    private String question;//问题
    private String zhihuUrl;//链接
    private String questionDescription;// 问题描述
    private ArrayList<String> answers;//存储所有回答问题的数组

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getZhihuUrl() {
        return zhihuUrl;
    }

    public void setZhihuUrl(String zhihuUrl) {
        this.zhihuUrl = zhihuUrl;
    }

    public Zhihu(String url) {
        question = "";
        question = "";
        zhihuUrl = "";
        answers = new ArrayList<>();
        if(getRealUrl(url)){
            System.out.println("正则抓取"+zhihuUrl);
            String content = Spider0.sentGet(zhihuUrl);
            Pattern pattern;
            Matcher matcher;
            //匹配标题
            pattern = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");
            matcher = pattern.matcher(content);
            if(matcher.find()){
                question =  matcher.group(1);
            }
            //匹配描述
            pattern = Pattern.compile("zh-question-detail.+?<div.+?>(.*?)</div>");
            matcher = pattern.matcher(content);
            if(matcher.find()){
                question =  matcher.group(1);
            }
            //匹配答案
            pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
            matcher = pattern.matcher(content);
            if(matcher.find()){
                answers.add(matcher.group(1));
            }
        }
    }
    //处理url
    public boolean getRealUrl(String url){
        Pattern pattern = Pattern.compile("question/(.*?)/");
        Matcher matcher = pattern.matcher(url);
        if(matcher.find()){
            zhihuUrl = "http://www.zhihu.com/question/"+matcher.group(1);
        }else {
            return false;
        }
        return true;
    }
    public String  toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("问题").append(question)
                .append("\n描述").append(questionDescription)
                .append("\n链接").append(zhihuUrl)
                .append("\n回答").append(answers).append("\n");
        return sb.toString();
    }
}
