package com.madjava.spider.demo0;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/31.
 */
public class Zhihu {
    private String question;//问题
    private String zhihuUrl;//链接
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

    public Zhihu() {
        question = "";
        zhihuUrl = "";
        answers = new ArrayList<>();
    }

    public String  toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("问题").append(question).append("\n链接").append(zhihuUrl)
                 .append("\n回答").append(answers).append("\n");
        return sb.toString();
    }
}
