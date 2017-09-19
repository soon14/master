package com.jy.process.impl.system.reconciliation.games;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.games.Constants;
import com.jy.common.utils.games.HttpClientUtil;

import com.jy.service.system.reconciliation.games.GamesService;
import com.jy.vo.system.reconciliation.games.DataList;
import com.jy.vo.system.reconciliation.games.Items;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZQY on 2017/3/24.
 */
@Service("gamesProcess")
public class GamesProcessImpl {
    private Logger logger = Logger.getLogger(GamesProcessImpl.class);
    @Autowired
    private GamesService gamesService;

    /**
     * 用户信息同步
     */
    public void insertUserGamesInfo() {
        int currentPage = 1;
        for(int i=1;i<currentPage+1;i++) {
            try {
                String paramJson = paramJson(Constants.GAME_GETDATA_METHOD_GETUSERINFO, i);
                Map map = new HashMap();
                String response = HttpClientUtil.post(paramJson, Constants.GAME_GETDATA_URL);
                response = "[" + response + "]";
                String json = JsonStrToJsonObj(response);
                if (json != "") {
                    if(currentPage ==1){
                        currentPage = countPage(response);
                    }
                    JSONArray array = JSONArray.fromObject(json);
                    List<Map> list = (List<Map>) JSONArray.toCollection(array, Map.class);
                    if (list.size() > 0) {
                        map.put("list", list);
                    }
                    gamesService.insertUserGamesInfo(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户信息同步失败"+e.getMessage());
            }
        }
    }
    /**
     * 用户余额同步
     */
    public void insertUserDayBalanceinfo(){
        int currentPage = 1;
        for(int i=1;i<currentPage+1;i++) {
            try {
                Gson gson = new Gson();
                String paramJson = paramJson(Constants.GAME_GETDATA_METHOD_GETUSERBALANCEINFO, currentPage);
                Map balanceMap = new HashMap();
                Map balanceProMap = new HashMap();
                String response = HttpClientUtil.post(paramJson, Constants.GAME_GETDATA_URL);
                response = "[" + response + "]";
                String json = JsonStrToJsonObj(response);
                if (json != "") {
                    if(currentPage ==1){
                        currentPage = countPage(response);
                    }
                    List<Items> itemsLists = new ArrayList<Items>();
                    List<DataList> dataLists = gson.fromJson(json, new TypeToken<List<DataList>>() {
                    }.getType());
                    for (DataList dataList : dataLists) {
                        List<Items> itemsList = dataList.getItems();
                        for (Items items : itemsList) {
                            items.setUserId(dataList.getUserId());
                            items.setQcDate(dataList.getQcDate());
                            itemsLists.add(items);
                        }
                    }
                    balanceMap.put("list", dataLists);
                    balanceProMap.put("list", itemsLists);
                    gamesService.insertUserDayBalanceinfo(balanceMap, balanceProMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("用户余额信息同步失败" + e.getMessage());
            }
        }
    }
    /**
     * 获取DataList
     * @param jsonString
     * @return
     */
    public String JsonStrToJsonObj(String jsonString ) {
        String jsons = "";
        if(!jsonString.contains("DataList")){
            return jsons;
        }
        JSONArray array = JSONArray.fromObject(jsonString);
        JSONObject jsonObject = array.getJSONObject(0);
        String isSuccess = jsonObject.get("success").toString();
        if("true".equals(isSuccess)){
            jsons = jsonObject.get("DataList").toString();
        }
        return jsons;
    }
    /**
     * 计算countPage
     * @param jsonString
     * @return
     */
    public int countPage(String jsonString){
        JSONArray array = JSONArray.fromObject(jsonString);
        JSONObject jsonObject = array.getJSONObject(0);
        int totalRecords = (int)jsonObject.get("totalRecords");
        int countPage = totalRecords/Constants.GAME_GETDATA_PAGESIZE;
        if(totalRecords % Constants.GAME_GETDATA_PAGESIZE>0){
            countPage++;
        }
        return countPage;
    }
    /**
     * 组织参数
     * @param method
     * @return
     */
    public String paramJson(String method,int currentPage){
        String yesterDay = DateUtils.getAfterDayDate("-1").substring(0, 10);
        yesterDay = yesterDay.replaceAll("-","");
        String startDate = "20170320";
        String endDate = "20170328";
        int pageSize = Constants.GAME_GETDATA_PAGESIZE;
        String paramJson = "{ \"method\" : \""+method+"\" , " +
                "\"param\" : {\"startDate\" : \""+startDate+"\", " +
                "\"endDate\" : \""+endDate+"\", \"pageSize\" : "+pageSize+"," +
                " \"currentPage\" : "+currentPage+" } }";
        return paramJson;
    }
}
