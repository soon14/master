package com.jy.test;

import com.jy.from.system.request.RequestParamInfoForm;
import com.jy.process.system.request.lottery.UserBalanceRequestProcess;
import com.jy.process.system.request.lottery.UserInfoRequestProcess;
import com.jy.process.system.request.lottery.UserTransDetailRequestProcess;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lijunke on 2017/4/24.
 */
public class UserInfoRequestProcessImplTester extends BaseJunit4Test {

    @Autowired
    public UserInfoRequestProcess userInfoRequestProcess;

    @Autowired
    public UserBalanceRequestProcess userBalanceRequestProcess;

    @Autowired
    public UserTransDetailRequestProcess userTrans;

    @Test
    public void testSynchronizationUserData() {
/*
        RequestParamInfoForm req = new RequestParamInfoForm();
        req.setPage("true");
        req.setQueryAll("true");
        //req.setCurrentPage(5);
        //req.setQueryTime("20170325");
        req.setPageSize(5000);
        try {
            userInfoRequestProcess.synchronizationUserData(req);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            userInfoRequestProcess.synchronizationUserData("20170622");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSynchronizationUserData2() {

        RequestParamInfoForm req = new RequestParamInfoForm();
        req.setPage("true");
        req.setQueryAll("true");
        req.setCurrentPage(4);
        //req.setQueryTime("20170325");
        req.setPageSize(5000);
       /* try {
            userInfoRequestProcess.synchronizationUserData2(req);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void testSynchronizationUserData3() {

        String data = null;
        String datas[] = new String[9];
        RequestParamInfoForm req = new RequestParamInfoForm();
        req.setPage("true");
        req.setQueryAll("true");
        req.setPageSize(5000);
      /*  try {
            for (int i = 1; i <= 9; i++) {
                req.setCurrentPage(i);
                data = userInfoRequestProcess.synchronizationUserData3(req);
                datas[i] = data;
            }
            for (int j = 1; j <= datas.length; j++) {
                RequestPageInfoForm requestPageInfoForm = JSONObject.parseObject(datas[j], RequestPageInfoForm.class);
                List<UserInfoForm> userList = JSONObject.parseArray(requestPageInfoForm.getList().toString(), UserInfoForm.class);
                userInfoRequestProcess.transforPojoSave(userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void testUserBalance() {

        try {
            userBalanceRequestProcess.synchronizationUserBalanceData("20160622");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserTrans() {

        RequestParamInfoForm req = new RequestParamInfoForm();
        req.setPage("true");
        req.setQueryAll("true");
        req.setCurrentPage(1);
        req.setQueryTime("20170407");
        req.setPageSize(5000);
        try {
//            userTrans.synchronizationUserTransData(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
