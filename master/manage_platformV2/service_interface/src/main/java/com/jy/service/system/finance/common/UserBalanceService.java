package com.jy.service.system.finance.common;

/**
 * Created by shixi on 2017/4/27.
 */
public interface UserBalanceService
{

    public int userBalanceforPojoSave(String fileName,String date) throws Exception;

    public  void deleteByAll(String date) throws Exception;

}
