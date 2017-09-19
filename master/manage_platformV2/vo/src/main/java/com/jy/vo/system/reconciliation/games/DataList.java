package com.jy.vo.system.reconciliation.games;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ZQY on 2017/3/29.
 */
public class DataList {
    @SerializedName(value = "UserID")
    private BigDecimal userId;
    @SerializedName(value = "QcDate")
    private  String qcDate;
    @SerializedName(value = "DiamondBalance")
    private  BigDecimal diamondBalance;
    @SerializedName(value = "GoldBalance")
    private BigDecimal goldBalance;
    @SerializedName(value = "ValuelessBalance_db")
    private BigDecimal valuelessBalance_db;
    @SerializedName(value = "ValuelessBalance_gd")
    private BigDecimal valuelessBalance_gd;
    @SerializedName(value = "Items")
    private List<Items> items;

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getQcDate() {
        return qcDate;
    }

    public void setQcDate(String qcDate) {
        this.qcDate = qcDate;
    }

    public BigDecimal getDiamondBalance() {
        return diamondBalance;
    }

    public void setDiamondBalance(BigDecimal diamondBalance) {
        this.diamondBalance = diamondBalance;
    }

    public BigDecimal getGoldBalance() {
        return goldBalance;
    }

    public void setGoldBalance(BigDecimal goldBalance) {
        this.goldBalance = goldBalance;
    }

    public BigDecimal getValuelessBalance_db() {
        return valuelessBalance_db;
    }

    public void setValuelessBalance_db(BigDecimal valuelessBalance_db) {
        this.valuelessBalance_db = valuelessBalance_db;
    }

    public BigDecimal getValuelessBalance_gd() {
        return valuelessBalance_gd;
    }

    public void setValuelessBalance_gd(BigDecimal valuelessBalance_gd) {
        this.valuelessBalance_gd = valuelessBalance_gd;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
