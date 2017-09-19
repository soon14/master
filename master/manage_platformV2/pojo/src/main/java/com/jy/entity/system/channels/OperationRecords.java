package com.jy.entity.system.channels;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/15.
 */
public class OperationRecords {
    private Integer aId;                //
    private String  aReviewer;          //操作人
    private Date    aUpdateDate;        //操作时间
    private String  aOperation;         //什么操作
    private String  abeOperation;       //被操作的是什么

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getaReviewer() {
        return aReviewer;
    }

    public void setaReviewer(String aReviewer) {
        this.aReviewer = aReviewer;
    }

    public Date getaUpdateDate() {
        return aUpdateDate;
    }

    public void setaUpdateDate(Date aUpdateDate) {
        this.aUpdateDate = aUpdateDate;
    }

    public String getaOperation() {
        return aOperation;
    }

    public void setaOperation(String aOperation) {
        this.aOperation = aOperation;
    }

    public String getAbeOperation() {
        return abeOperation;
    }

    public void setAbeOperation(String abeOperation) {
        this.abeOperation = abeOperation;
    }
}
