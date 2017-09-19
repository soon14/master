package com.jy.common.utils.games;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jy.vo.system.reconciliation.games.DataList;
import com.jy.vo.system.reconciliation.games.GamesUserDayBalanceVO;
import com.jy.vo.system.reconciliation.games.Items;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {
    public static String post(String json, String url){
        String response = null;
        try {
            HttpClient httpClient = new HttpClient();
            PostMethod post = new PostMethod(url);
            post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            post.setRequestBody(json);
            HttpMethod method = post;
            httpClient.executeMethod(method);
            response = method.getResponseBodyAsString();
            post.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    public static void main(String args[]){
        String url = "http://192.168.192.26:81/salesManage/makeNewSellerCustomer.do?mobile=13987554321&mName=diaosi";
        String response = "";
        String jsonparm = "{ \"method\" : \"fish.getUserDayBalanceinfo\" , " +
                "\"param\" : {\"startDate\" : \"20170320\", \"endDate\" : \"20170328\", \"pageSize\" : 100, \"currentPage\" : 1 } }";
       // map.put("param",mapparam);
            response = post(jsonparm,url);
        GsonBuilder gsonBuilder=new GsonBuilder();
//可以自定义个性化功能
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        GamesUserDayBalanceVO gamesUserDayBalanceVO = gson.fromJson(response, GamesUserDayBalanceVO.class);

        response = "["+response+"]";
        JSONArray array = JSONArray.fromObject(response);
        JSONObject jsonObject = array.getJSONObject(0);
        String jsonarr =  jsonObject.get("DataList").toString();
        List<Items> itemsLists = new ArrayList<Items>();
        List<DataList> dataLists = gson.fromJson(jsonarr,new TypeToken<List<DataList>>(){}.getType());
        for(DataList dataList : dataLists){
            List<Items> itemsList = new ArrayList<Items>();
            itemsList = dataList.getItems();
            for(Items items :itemsList ){
               items.setUserId(dataList.getUserId());
               items.setQcDate(dataList.getQcDate());
                itemsLists.add(items);
            }
        }
        int totalRecords =(int)jsonObject.get("totalRecords");
        JSONArray array1 = JSONArray.fromObject(jsonarr);
        List<Map> list =  (List<Map>) JSONArray.toCollection(array1, Map.class);
        List<Map> listItem = new ArrayList<Map>();
          for(int i=0;i<array1.size();i++){
              String jsonItems  = array1.getJSONObject(i).get("Items").toString();
              String userID  = array1.getJSONObject(i).get("UserID").toString();
              String qcDate  = array1.getJSONObject(i).get("QcDate").toString();
              JSONArray arrayItems = JSONArray.fromObject(jsonItems);
              List<Map> listItem2 =  (List<Map>) JSONArray.toCollection(arrayItems, Map.class);
              for(int j=0;j<listItem2.size();j++){
                 Map map = listItem2.get(j);
                  map.put("UserID",userID);
                  map.put("QcDate",qcDate);
                  listItem.add(map);
                  System.out.println(response);
              }

          }
        System.out.println(response);
    }
}