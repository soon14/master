package com.days.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by ZQY on 2017/3/22.
 */
public class JsonTest<T> {
    public static void main(String args[]){
        String jsonString = "[{\n" +
                "    \"Msg\": \"success\", \n" +
                "    \"TotalRecords\": 1, \n" +
                "    \"TotalPages\": 1, \n" +
                "    \"PageStart\": 1, \n" +
                "    \"PageEnd\": 2, \n" +
                "    \"StartDate\": \"20170315\", \n" +
                "    \"EndDate\": \"20170318\", \n" +
                "    \"DataList\": [\n" +
                "        {\n" +
                "            \"batchNo\": \"T2017021401\", \n" +
                "            \"playWay\": \"¸´Ê½\", \n" +
                "            \"schemeType\": \"Âò²Ê\", \n" +
                "            \"subOrderNo\": 26135843, \n" +
                "            \"userId\": 1403109\n" +
                "        },\n" +
                "          {\n" +
                "            \"batchNo\": \"T2017021402\", \n" +
                "            \"playWay\": \"¸´Ê½\", \n" +
                "            \"schemeType\": \"Âò²Ê\", \n" +
                "            \"subOrderNo\": 26135844, \n" +
                "            \"userId\": 1403110\n" +
                "        }\n" +
                "\n" +
                "    ]\n" +
                "}]";
        JSONArray array = JSONArray.fromObject(jsonString);
        JSONObject jsonObject = array.getJSONObject(0);
        String jsons = jsonObject.get("DataList").toString();
        JSONArray array1 = JSONArray.fromObject(jsons);
        List<Datas> list =( List<Datas>)JSONArray.toCollection(array1,Datas.class);
    }
    public  List<T> jSonArrayToObjectList(String  json,Class<T> classs) throws Exception{
        JSONArray array1 = JSONArray.fromObject(json);
        List<T> list =( List<T>)JSONArray.toCollection(array1,classs);
        return list;
    }
}
