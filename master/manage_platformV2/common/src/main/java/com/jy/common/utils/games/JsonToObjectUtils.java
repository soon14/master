package com.jy.common.utils.games;

import com.jy.common.utils.DateUtils;
import com.jy.vo.system.reconciliation.games.GamesUserVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * Created by ZQY on 2017/3/24.
 */
public class JsonToObjectUtils {
    /**
     * jsonÊý×é×ªObjectList
     * @param json
     * @param classs
     * @return
     * @throws Exception
     */
    public static List<T> jSonArrayToObjectList(String  json,Class<T> classs){
        List<T> list = null;
        try {
            JSONArray array1 = JSONArray.fromObject(json);
            list = (List<T>) JSONArray.toCollection(array1, classs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String args[]){
        String yesterDay = DateUtils.getAfterDayDate("-1").substring(0, 10);
        yesterDay = yesterDay.replaceAll("-","");
        String jsonString = "{\"totalRecords\":100,\"pageSize\":100,\"currentPage\":1,\"startDate\":\"20170320\",\"endDate\":\"20170321\",\"errorMsg\":\"\",\"success\":\"true\",\"DataList\":[{\"UserID\":20399,\"NickName\":\"123\",\"UserType\":\"1\",\"UserAccount\":\"13917468125\",\"RegistTime\":\"2000-01-01 00:00:00\"}]}";
        jsonString = "["+jsonString+"]";
        JSONArray array1 = JSONArray.fromObject(jsonString);

        JSONArray array = JSONArray.fromObject(jsonString);
        JSONObject jsonObject = array.getJSONObject(0);
        String isSuccess = jsonObject.get("success").toString();
        String jsons = "";
        if("true".equals(isSuccess)){
            jsons = jsonObject.get("DataList").toString();
        }
        JSONArray array2 = JSONArray.fromObject(jsons);
        List<Map> list = (List<Map>) JSONArray.toCollection(array2,Map.class);
        System.out.println(jsons.toString());
    }
}
