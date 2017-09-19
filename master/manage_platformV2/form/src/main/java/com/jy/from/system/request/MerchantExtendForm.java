package com.jy.from.system.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Matthew on 2017/5/22.
 */
public class MerchantExtendForm implements Serializable {


    @JSONField(name = "userID")
    private Integer cpUserId;
    @JSONField(name = "createTime")
    private Date createTime;
    @JSONField(name = "schemeType")
    private String schemeType;
    @JSONField(name = "merchantName")
    private String merchantName;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "vaildDate")
    private String vaildDateStr;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVaildDateStr() {
        return vaildDateStr;
    }

    public void setVaildDateStr(String vaildDateStr) {
        this.vaildDateStr = vaildDateStr;
    }

    public Integer getCpUserId() {
        return cpUserId;
    }

    public void setCpUserId(Integer cpUserId) {
        this.cpUserId = cpUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }
}