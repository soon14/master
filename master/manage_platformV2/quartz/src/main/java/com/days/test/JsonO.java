package com.days.test;

import java.util.List;

/**
 * Created by ZQY on 2017/3/22.
 */
public class JsonO {
    private  String Msg;
    private  String TotalRecords;
    private  String TotalPages;
    private  String PageStart;
    private  String PageEnd;
    private  String StartDate;
    private  String EndDate;
    private List<Datas> DataList;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(String totalRecords) {
        TotalRecords = totalRecords;
    }

    public String getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(String totalPages) {
        TotalPages = totalPages;
    }

    public String getPageStart() {
        return PageStart;
    }

    public void setPageStart(String pageStart) {
        PageStart = pageStart;
    }

    public String getPageEnd() {
        return PageEnd;
    }

    public void setPageEnd(String pageEnd) {
        PageEnd = pageEnd;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public List<Datas> getDataList() {
        return DataList;
    }

    public void setDataList(List<Datas> dataList) {
        DataList = dataList;
    }
}
