package com.jy.from.system.request;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijunke on 2017/4/24.
 */
public class RequestParamInfoForm {

    private String queryTime;
    private String queryAll;    //true or false 是否查询所有的数据
    private String page;        //true or false 是否启动分页
    private int pageSize;    //分页大小，数字
    private int currentPage; //当前页
    private int type;         //(1：增加追期期初余额接口2：修改追期明细数据-出票明细3：修改追期明细数据-退款明细4：修改追期明细数据-新增追期)

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
}

    public String getQueryAll() {
        return queryAll;
    }

    public void setQueryAll(String queryAll) {
        this.queryAll = queryAll;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

}
