package com.jy.entity.system.finance.reconciliation.lottery;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 *投注系统销售差异明细报表
 */
@Alias("SalesDifferencesDetail")
public class SalesDifferencesDetail extends BaseEntity {
    private Integer dId;
    private String  dDate;                //时间
    private String  dNumber;              //编号
    private String  dDifferenceMoney;     //差异金额
    private String  dDifferenceType;      //差异类型
    private String  dCause;               //具体原因
    private String  dOpinion;             //建议处理意见
    private String  dResult;              //处理结果
    private Integer  dCondition;           //处理状态
    private Integer  dType;           //类型，1：平台总资金差异 2：第三方流水差异
    private String  dCreator;           //处理人
    private String  dCreateTime;           //处理时间
    private Integer  type;           //类型，1：正常2：移动活动（平台总资金差异 ）

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getdCreateTime() {
        return dCreateTime;
    }

    public String getdCreator() {
        return dCreator;
    }

    public void setdCreateTime(String dCreateTime) {
        this.dCreateTime = dCreateTime;
    }

    public void setdCreator(String dCreator) {
        this.dCreator = dCreator;
    }

    public Integer getdType() {
        return dType;
    }

    public void setdType(Integer dType) {
        this.dType = dType;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String dDate) {
        this.dDate = dDate;
    }

    public String getdNumber() {
        return dNumber;
    }

    public void setdNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public String getdDifferenceMoney() {
        return dDifferenceMoney;
    }

    public void setdDifferenceMoney(String dDifferenceMoney) {
        this.dDifferenceMoney = dDifferenceMoney;
    }

    public String getdDifferenceType() {
        return dDifferenceType;
    }

    public void setdDifferenceType(String dDifferenceType) {
        this.dDifferenceType = dDifferenceType;
    }

    public String getdCause() {
        return dCause;
    }

    public void setdCause(String dCause) {
        this.dCause = dCause;
    }

    public String getdOpinion() {
        return dOpinion;
    }

    public void setdOpinion(String dOpinion) {
        this.dOpinion = dOpinion;
    }

    public String getdResult() {
        return dResult;
    }

    public void setdResult(String dResult) {
        this.dResult = dResult;
    }

    public Integer getdCondition() {
        return dCondition;
    }

    public void setdCondition(Integer dCondition) {
        this.dCondition = dCondition;
    }
}
