package com.jy.repository.system.finance.commom;

import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

/**
 * Created by shixi on 2017/8/1.
 */
@JYBatis
public interface UserScoreDao {
    /**
     * 积分交易明细txt文档保存入库
     * @param fileName 文件名称
     * @param date 同步时间
     * @return
     */
   int saveBalanceTxT(@Param("fileName") String fileName);

    /**
     * 按照同步日期删除数据
     */
    void deleteBalanceBySycDate(@Param("sycDate")String date);
    /**
     * 积分余额txt文档保存入库
     * @param fileName 文件名称
     * @param date 同步时间
     * @return
     */
    int saveTransTxT(@Param("fileName")String fileName,@Param("sycDate")String date);

    /**
     * 按照同步日期删除数据
     */
    void deleteTransBySycDate(@Param("sycDate")String date);
}
