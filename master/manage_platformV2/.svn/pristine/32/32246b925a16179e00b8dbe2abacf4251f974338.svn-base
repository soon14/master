package com.jy.task.job.request.lottery;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.SpringWebContextUtil;
import com.jy.process.impl.system.request.lottery.UserInfoRequestProcessImpl;
import com.jy.task.job.request.base.BaseTask;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by shixi on 2017/4/28.
 */
public class UserInfoTask extends BaseTask {

    private static final int PAGE_SIZE_USER = 10000;

    public void saveData() {
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户信息开始运行");
        ApplicationContext ac = SpringWebContextUtil.getApplicationContext();
        UserInfoRequestProcessImpl process = (UserInfoRequestProcessImpl) ac.getBean("userInfoRequestProcess");

        try {
            String queryTime = super.getParam()[2];

            if (!"default".equals(queryTime) && !"".equals(queryTime)) {
                if (queryTime.contains("-")) {
                    String[] byDate = queryTime.split("-");
                    List<String> strings = DateUtils.findDatesFormat(byDate[0], byDate[1]);
                    for (String string : strings) {
                        process.synchronizationUserData(string);
                    }
                } else {
                    process.synchronizationUserData(queryTime);
                }
            } else {
                queryTime = DateUtils.getDate("yyyyMMdd");
                process.synchronizationUserData(queryTime);
            }
        } catch (Exception e) {
            log.error("同步用户信息JOB失败！", e);
            e.printStackTrace();
        }
        log.info(DateUtils.getDate("yyyy-MM-dd HH:mm:ss") + " 同步用户信息结束");
    }
}
