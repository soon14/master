package com.jy.service.system.finance.statistics.lottery;

import java.util.List;
import java.util.Map;

import com.jy.mybatis.Page;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.finance.statistics.SalesCommissionReport;
import com.jy.entity.system.finance.UserVo;
import com.jy.entity.system.finance.vo.SalesDetailVo;
import com.jy.entity.system.org.Org;
import com.jy.service.base.BaseService;

/**
 * Created by Administrator on 2017/1/10.
 */
public interface SalesCommissionReportService extends BaseService<SalesCommissionReport>
{


    public void insertReport(String type,String date) throws  Exception;
//    public void insertReportThread(String type,String date,Merchant m) throws  Exception;

    /**
     * 获得该商户的发展商户（子渠道商户）
     *
     * @param o
     * @param page
     * @return
     */
    public Page<Merchant> findChildListByMerchantId(Merchant o, Page<Merchant> page);

    public Page<SalesDetailVo> findSalesDetail(SalesDetailVo o, Page<SalesDetailVo> page);


    Page<SalesCommissionReport> findByList(String date, List<String> orgList);
    List findMerchandByList(List orgList);

    /**
     * 通过员工id获取某一时间段的销售报表
     *
     * @param beginTime
     * @param endTime
     * @param orgList
     * @return
     */
    Page<SalesCommissionReport> findSMRByList(String beginTime, String endTime, List<String> orgList);

    public Org findOrgById(String oid);

    /**
     * 获取标签用户Id
     *
     * @param map
     * @return
     */
    public List<Integer> findUserIdListByMerchantId(Map map);

    /**
     * 获取标签用户
     *
     * @param userId
     * @return
     */
    public List<UserVo> findUserListByMerchantId(Integer userId,UserVo vo);

    public String findMartketTimeByUserId(Integer userId);

    /**
     * 通过渠道对象，获取渠道某一时间段的销售报表
     *
     * @param beginTime
     * @param endTime
     * @param m
     * @return
     */
    public Double findSalesByMerchant(String beginTime, String endTime, Merchant m);


    public String getLastOrderTimeByUser(UserVo lastVo);

    public Double getSignSale(String beginTime, String endTime, Integer userId, String marketTime);

    public  Double findTotalSalesByParam(SalesCommissionReport o);

    public Double findTotalCommissionByParam(SalesCommissionReport o);

    public Double findTotalSalesByParamList(String beginTime, String endTime,  List<Merchant> accountList);

    public Double findTotalCommissionByParamList(String beginTime, String endTime,  List<Merchant> accountList);

    public Page<SalesCommissionReport> findSelfAndChild(SalesCommissionReport o, Page<SalesCommissionReport> page);

    public void findExportReport();

    public List<SalesCommissionReport> findSalesCommission(Map map ) throws Exception;
    /**
     * 获得对象列表
     *
     * @param o 集合对象
     * @param page 分页对象
     * @return List
     */
    public Page<SalesCommissionReport> findByPageList(SalesCommissionReport o, Page<SalesCommissionReport> page);
}
