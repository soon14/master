package com.jy.from.system.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lijunke on 2017/4/24.
 */
public class RequestPageInfoForm<T> implements Serializable {
    private int count;
    private String flag;
    private String msg;
    private String pageEnd;
    private String pageSize;
    private String pageStart;
    @JSONField(name = "list")
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(String pageEnd) {
        this.pageEnd = pageEnd;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageStart() {
        return pageStart;
    }

    public void setPageStart(String pageStart) {
        this.pageStart = pageStart;
    }
}
