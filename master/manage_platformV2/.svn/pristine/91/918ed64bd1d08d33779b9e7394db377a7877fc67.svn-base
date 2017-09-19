package com.jy.common.utils.task;

import com.jy.from.system.request.RequestPageInfoForm;
import com.jy.from.system.request.RequestParamInfoForm;

/**
 * Created by ZQY on 2017/5/3.
 */
public class ContPageUtil {
    /**
     * 计算总页数
     * @param requestPageInfoForm
     * @param infoForm
     * @return
     */
    public static int countPage(RequestPageInfoForm requestPageInfoForm,RequestParamInfoForm infoForm){
        int count = requestPageInfoForm.getCount();
        int countPage = count/infoForm.getPageSize();
        if(count%infoForm.getPageSize()>0){
            countPage++;
        }
        return countPage;
    }
}
