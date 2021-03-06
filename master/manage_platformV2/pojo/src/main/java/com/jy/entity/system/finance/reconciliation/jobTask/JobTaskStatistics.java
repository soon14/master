package com.jy.entity.system.finance.reconciliation.jobTask;

public class JobTaskStatistics {

    private String date;//入库日期
    private String endDate;
    private String jobName;//job名称
    private Integer interfaceNumber;//接口笔数
    private Integer storageNumber;//入库笔数
    private String description;  //描述
    private String sycDate;//同步时间

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getInterfaceNumber() {
        return interfaceNumber;
    }

    public void setInterfaceNumber(Integer interfaceNumber) {
        this.interfaceNumber = interfaceNumber;
    }

    public Integer getStorageNumber() {
        return storageNumber;
    }

    public void setStorageNumber(Integer storageNumber) {
        this.storageNumber = storageNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSycDate() {
        return sycDate;
    }

    public void setSycDate(String sycDate) {
        this.sycDate = sycDate;
    }
}
