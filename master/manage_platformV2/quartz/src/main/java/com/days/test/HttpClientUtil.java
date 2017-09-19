package com.days.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {
    public static String post(Map<String, Object> sParaTemp, String url) throws Exception{
        HttpClient httpClient = new HttpClient();

        PostMethod post = new PostMethod(url);
        post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        List<String> keys = new ArrayList<String>(sParaTemp.keySet());
        NameValuePair[] param = new NameValuePair[keys.size()+1];
        for (int i = 0; i < keys.size(); i++) {
            String name = keys.get(i);
            Object object = sParaTemp.get(name);
            String value = "";
            if (object != null) {
                value = (String) sParaTemp.get(name);
            }
            //Ìí¼Ó²ÎÊý
            param[i] = new NameValuePair(name, value);
            post.setParameter(param[i].getName(),param[i].getValue());
            //System.out.println(param[i].getName());
        }
        HttpMethod method = post;
        httpClient.executeMethod(method);
        String response = method.getResponseBodyAsString();
        post.releaseConnection();
        return response;
    }
    public static void main(String args[]){
        Map map= new HashMap();
        Map mapparam= new HashMap();
        String url = "http://192.168.193.167:15702/balance/users";
        String response = "";
        map.put("method","fish.getUserInfo");
        map.put("startDate","20100101");
        map.put("endDate","20180505");
        map.put("pageSize","10");
        map.put("currentPage","1");
       // map.put("param",mapparam);
        try {
            response = post(map,url);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}