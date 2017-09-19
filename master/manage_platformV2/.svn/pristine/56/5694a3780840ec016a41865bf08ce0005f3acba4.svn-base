package com.jy.task.job.request.lottery;


import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.from.system.request.RequestParamInfoForm;
import com.jy.process.system.request.lottery.MerchantExtendProcess;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

/**
 * 商户扩展信息入库
 */
public class MerchantExtendTask extends BaseTask {

    public void saveData() {
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——商户扩展任务开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        MerchantExtendProcess process = (MerchantExtendProcess) ac.getBean("merchantExtendProcess");
        RequestParamInfoForm req = new RequestParamInfoForm();
        try {
            process.synchronizeMerEData(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
		log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " ——商户扩展任务结束");
    }
}
