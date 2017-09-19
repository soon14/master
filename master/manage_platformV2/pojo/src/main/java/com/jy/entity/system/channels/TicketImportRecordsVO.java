package com.jy.entity.system.channels;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by Matthew on 2017/1/15 0015.
 */
@Alias("BaseTicketImportRecords")
public class TicketImportRecordsVO {
    private String id;
    private String fileName;
    private String originalName;
    private Date createTime;
    private String createDate;
    /*查询条件*/
    private String beginTimes;
    private String endTimes;
    private String transType;
    private String transTypeName;
    private String transStatus;
    private String transStatusName;
    private String username;
    private String isValid;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransTypeName() {
        return transTypeName;
    }

    public void setTransTypeName(String transTypeName) {
        this.transTypeName = transTypeName;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getBeginTimes() {
        return beginTimes;
    }

    public void setBeginTimes(String beginTimes) {
        this.beginTimes = beginTimes;
    }

    public String getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(String endTimes) {
        this.endTimes = endTimes;
    }

    public String getTransStatusName() {
        return transStatusName;
    }

    public void setTransStatusName(String transStatusName) {
        this.transStatusName = transStatusName;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
}
