package com.jy.test;

import com.jy.from.system.finance.reconciliation.lottery.BaseForm;
import com.jy.mybatis.Page;
import com.jy.process.system.common.ManualProcess;
import com.jy.process.system.reconciliation.games.DiamondProcess;
import com.jy.vo.system.reconciliation.games.DiamondVo;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

/**
 * Created by yutao on 2017/4/14.
 */
public class DiamondProcessTest extends BaseJunit4Test {

    @Resource
    DiamondProcess diamondProcess;
    @Resource
    ManualProcess manualProcess;
    @Test   //标明是测试方法
    //@Transactional   //标明此方法需使用事务
    //@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void findByPageTest( ) throws Exception{
//        massertNotNull(null, MsgConst.Srv.ERR_VALICODE_SEND_FAIL);
//        DiamondBalance diamondBalance = new DiamondBalance();
        Page<DiamondVo> pageIn = new Page<DiamondVo>();
        BaseForm form = new BaseForm();
        Page<DiamondVo> page = diamondProcess.findByPage(form, pageIn);
        assertNotNull(page);
    }
}
