package com.jy.service.impl.system.finance.common;

import com.jy.common.utils.DateUtils;
import com.jy.entity.system.finance.common.UserTransDetail;
import com.jy.from.system.request.UserTransDetailForm;
import com.jy.repository.system.finance.commom.UserTransDetailDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.common.UserTransDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */
@Service("UserTransDetailService")
public class UserTransDetailServiceImpl extends BaseServiceImp<UserTransDetail> implements UserTransDetailService {
    @Autowired
    private UserTransDetailDao userTransDetailDao;

    @Override
    public int userTransforPojoSave(List<UserTransDetailForm> listAll)throws Exception {
        List<UserTransDetail> list = new ArrayList<>();
        for (UserTransDetailForm item : listAll) {
            if (null != item.getTranTime() && !"".equals(item.getTranTime())) {
                UserTransDetail userTransDetail = new UserTransDetail();
                BeanUtils.copyProperties(item, userTransDetail);
                userTransDetail.setCreateTime(new Date());
                userTransDetail.setTranChannel(ConvertToCode(item.getTranChannel()));
                userTransDetail.setTranType(ConvertToCodeToType(item.getTranChannel()));
                userTransDetail.setSycDate(DateUtils.getDate("yyyy-MM-dd").toString());
                String payFlowNo = ConvertToCode(item.getTranChannel()) == 2 ? item.getPayFlowNo() + "" : item.getPayId() + "";
                userTransDetail.setPayFlowNo(payFlowNo);
                list.add(userTransDetail);
            }
        }
        int num= this.userTransDetailDao.save(list);
        return num;
    }
    private static int ConvertToCode(String code) {
        int result;
        switch (code) {
            case "101":
            case "201":
                result = 1;//微信
                break;
            case "102":
            case "202":
                result = 2;//得仕通
                break;
            case "103":
            case "207":
                result = 3;//银联
                break;
            case "203":
                result = 4;//余额
                break;
            case "503":
                result = 5;//内部存入
                break;
/*            case "402":
                result = 7;
                break;*/
            default:
                result = 99;
                break;

        }

        return result;
    }


    private static int ConvertToCodeToType(String code) {
        int result;
        switch (code) {
            //207,202,201,103,102,101,503 充值
            //510，504 提款
            //400 ，401
            case "207":
            case "202":
            case "201":
            case "103":
            case "102":
            case "101":
            case "503":
                result = 1;//充值
                break;
            case "203":
                result = 2;//余额支付
                break;
            case "510":
            case "504":
                result = 3;//提现
                break;
            case "401":
            case "400":
                result = 4;//退款
                break;
            case "500":
                result = 5;//派奖
                break;
            default:
                result = 6;//其他
                break;
        }
        return result;
    }

    @Override
    public void deleteByAll(String date)throws Exception {
        userTransDetailDao.deleteByAll(date);
    }
    @Override
    public int save(String filePath, String currentDate) {
        String charset = byValue("charset");
        userTransDetailDao.deleteByDate(currentDate);
        return userTransDetailDao.save(filePath, DateUtils.stringToDate(currentDate, "yyyyMMdd"), charset != null ? charset : "utf8mb4");
    }
}

