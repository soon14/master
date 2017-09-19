package com.jy.service.system.finance.common;

import com.jy.from.system.request.UserTransDetailForm;

import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */
public interface UserTransDetailService {

    public int userTransforPojoSave(List<UserTransDetailForm> listAll) throws Exception;
    public  void deleteByAll(String date) throws Exception;
    public int save(String filePath, String currentDate);
}
