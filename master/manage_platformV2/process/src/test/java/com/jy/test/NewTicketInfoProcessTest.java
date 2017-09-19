package com.jy.test;

import com.jy.process.system.request.lottery.NewTicketInfoProcess;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lijunke on 2017/6/2.
 */
public class NewTicketInfoProcessTest extends BaseJunit4Test{

    @Autowired
    private NewTicketInfoProcess newTicketInfoProcess;

    @Test
    public void test() {

//        RequestParamInfoForm req = new RequestParamInfoForm();
//        req.setPage("true");
//        req.setQueryAll("false");
//        req.setQueryTime("20170601");
//        req.setPageSize(2000);
        try {
            newTicketInfoProcess.synchronizeNewTicketData("20170623");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
