package com.jy.service.impl.system.finance.statistics.lottery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jy.common.mybatis.PageCalculation;
import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.entity.system.channels.OrderDetail;

import com.jy.repository.system.finance.reconciliation.lottery.SalesSumDao;
import com.jy.service.impl.base.BaseServiceImp;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.mybatis.Page;
import com.jy.common.utils.CalculationUtils;
import com.jy.common.utils.DateUtils;
import com.jy.entity.system.channels.BaseCommission;
import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.dict.SysDict;
import com.jy.entity.system.finance.statistics.SalesCommissionReport;
import com.jy.entity.system.finance.UserVo;
import com.jy.entity.system.finance.vo.SalesDetailVo;
import com.jy.entity.system.org.Org;
import com.jy.repository.system.channels.BaseCommissionDao;
import com.jy.repository.system.channels.MerchantDao;
import com.jy.repository.system.finance.statistics.lottery.SalesCommissionReportDao;
import com.jy.service.system.dict.SysDictService;
import com.jy.service.system.finance.statistics.lottery.SalesCommissionReportService;

/**
 * Created by Administrator on 2017/1/10.
 */
@Service("SalesCommissionReportService")
public class SalesCommissionReportServiceImpl extends BaseServiceImp<SalesCommissionReport> implements SalesCommissionReportService {

    private static final Logger log = LoggerFactory.getLogger(SalesCommissionReportServiceImpl.class);

    @Autowired
    private SalesCommissionReportDao dao;

    @Autowired
    private SysDictService service;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private BaseCommissionDao bcDao;
    @Autowired
    private SalesSumDao salesSumDao;
    public SalesCommissionReportServiceImpl() {
        // 当创建新的构造函数时，把原始默认的构造函数默认出来
    }

    // public SalesCommissionReportServiceImpl(String type,String date,Merchant m,MerchantDao merchantDao,SalesCommissionReportDao
    // dao,BaseCommissionDao bcDao){
    // this.date = date;
    // this.type = type;
    // this.m = m ;
    // this.merchantDao = merchantDao;
    // this.dao = dao;
    // this.bcDao = bcDao;
    // }
    class StaticFeeThread extends Thread {

        private String date;

        private String type;

        private Merchant m;

        private int startFlag;

        private int endFlag;

        private int insertHalfCount;

        List<Merchant> merchantList;

        public StaticFeeThread(String type, String date, int startFlag, int endFlag, int insertHalfCount, List<Merchant> merchantList, MerchantDao merchantDao, SalesCommissionReportDao dao, BaseCommissionDao bcDao) {
            this.date = date;
            this.type = type;
            this.m = m;
            SalesCommissionReportServiceImpl.this.merchantDao = merchantDao;
            SalesCommissionReportServiceImpl.this.dao = dao;
            SalesCommissionReportServiceImpl.this.bcDao = bcDao;
            this.merchantList = merchantList;
            this.startFlag = startFlag;
            this.endFlag = endFlag;
            this.insertHalfCount = insertHalfCount;
        }

        @Override
        public void run() {
            try {

                insertReportThread(type, date, startFlag, endFlag, insertHalfCount, merchantList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Page<Merchant> findChildListByMerchantId(Merchant o, Page<Merchant> page) {
        page.setResults(merchantDao.findByPageAndParam(o, page));
        return page;
    }

    // public void run() {
    // try {
    // insertReportThread(type, date, m);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    @Override
    public void insertReport(String type, String date) throws Exception {
        deleteExist(date);
        deleteDetailListIfExist(date);
        List<Merchant> merchantList = merchantDao.findValidMerchantList();
        // FIXME:
        int insertHalfCount = 0;
        int startOneThreadFlag = 0;
        int startTwoThreadFlag = 0;
        int endOneThreadFlag = 0;
        int endTwoThreadFlag = 0;

        if (merchantList.size() % 2 == 0) {
            insertHalfCount = merchantList.size() / 2;
            startTwoThreadFlag = insertHalfCount;
            endOneThreadFlag = insertHalfCount;
            endTwoThreadFlag = merchantList.size();
        } else {
            insertHalfCount = (merchantList.size() + 1) / 2;
            startTwoThreadFlag = insertHalfCount;
            endOneThreadFlag = insertHalfCount;
            endTwoThreadFlag = merchantList.size();
        }

        StaticFeeThread inportOneThread = new StaticFeeThread(type, date, startOneThreadFlag, endOneThreadFlag, insertHalfCount, merchantList, merchantDao, dao, bcDao);

        StaticFeeThread inportTwoThread = new StaticFeeThread(type, date, startTwoThreadFlag, endTwoThreadFlag, insertHalfCount, merchantList, merchantDao, dao, bcDao);
        // if(m.getmId() % 2 ==0){
        // insertReportThread(type,date,startOneThreadFlag,endOneThreadFlag,insertHalfCount,merchantList);
        System.out.println("线程1开始");
        inportOneThread.start();
        // logger.debug("insertCount:"+insertCount++);
        // }else{
        System.out.println("线程2开始");
        inportTwoThread.start();
        // }

        // dao.insertList((SalesCommissionReport) scrList);
    }

    public void insertReportThread(String type, String date, int startFlag, int endFlag, int insertHalfCount, List<Merchant> merchantList) throws Exception {
        int limit = 1000;
        int size = insertHalfCount / limit;
        if (insertHalfCount % limit > 0) {// 分包，一千条为一包
            size++;
        }
        for (int i = 0; i < size; i++) {
            List<SalesCommissionReport> scrList = new ArrayList<SalesCommissionReport>();
            List<OrderDetail> orderDetailLists =  new ArrayList<OrderDetail>();//总结果集
            // for (Merchant m : merchantList) {
            for (int k = startFlag; k < endFlag; k++) {
                List<OrderDetail> orderDetailList =  new ArrayList<OrderDetail>();//单次循环结果集
                List<OrderDetail> orderDetailListMerchant =  new ArrayList<OrderDetail>();//单次循环结果集
                Merchant m = merchantList.get(k);
                SalesCommissionReport scr = new SalesCommissionReport();
                scr.setMerchantId(m.getmId());
                if (m.getmLevel() == 1) {
                    scr.setParentMerchantId(0);
                } else {
                    scr.setParentMerchantId(Integer.parseInt(m.getMParentMerchant()));
                }
                List<Merchant> list = merchantDao.findByParentMerchantId(m.getmId());;// 获取对应商户的下线id
                scr.setChildMerchantCount(list.size());// 下线总户数
                scr.setDate(date);
                scr.setLevel(m.getmLevel());
                Double sumSales = 0.0;
                List idListParamOne = new ArrayList();
                List idListParamTwo = new ArrayList();
                List idListParamThree = new ArrayList();
                List ejQdList = new ArrayList();
                List yjMarkeList = new ArrayList();
                List sjQdList = new ArrayList();
                List ejMarkeList = new ArrayList();
                List sjMarkeList = new ArrayList();
                List merchantIdList = new ArrayList();
                Double bcPercent = 0.0;// 佣金百分比
                Double totalCommssion = 0.0;// 总佣金
                Double childQdSales = 0.0;// 子渠道销售额
                Double childQdCommission = 0.0;// 子渠道佣金
                Double markeCommission = 0.0;// 标签佣金
                Double sumErSales = 0.0;// 二级渠道总销售额
                if (m.getmLevel() == 1) {
                    bcPercent = 5.0;// 一级渠道佣金为5%
                    idListParamOne.add(m.getmId());
                    idListParamTwo.add(m.getmCpUserId());
                    ejQdList = findQdIdList(idListParamOne, date);// 二级渠道用户idList
//                    yjMarkeList = findMarketIdList(idListParamTwo, date);// 一级标签idList
                    merchantIdList.addAll(idListParamTwo);
                    if (ejQdList.size() > 0) {
                        merchantIdList.addAll(ejQdList);
                        sjQdList = findChildQdIdList(ejQdList, date);// 三渠道用户idList
                        ejMarkeList = findMarketIdList(ejQdList, date + " 23:59:59");// 二级标签idList
                        if (sjQdList.size() > 0) {
                            merchantIdList.addAll(sjQdList);
                            sjMarkeList = findMarketIdList(sjQdList, date + " 23:59:59");// 三级标签idList
                        }
                    }
                    sumSales +=sumSalesMerchant(merchantIdList,date);
                    orderDetailListMerchant = findTicketInfoDetailListMerchant(merchantIdList, date + " 00:00:00", date + " 23:59:59", m.getmId());
                    //idListParamThree.addAll(ejQdList);// 二级渠道用户idList
//                    idListParamThree.addAll(yjMarkeList); // 一级标签idList  公司销售只计算渠道的销量计算标签用户的销量
                    //idListParamThree.addAll(sjQdList);// 三渠道用户idList
                    idListParamThree.addAll(ejMarkeList);// 二级标签idList
                    idListParamThree.addAll(sjMarkeList);// 三级标签idList
                    if (idListParamThree.size() > 0) {
                        sumSales += sumSales(idListParamThree, date);// 总销售额
                        orderDetailList = ticketInfoDetailList( idListParamThree, date+" 00:00:00", date+" 23:59:59",m.getmId());
                    }
                    if(orderDetailListMerchant.size()>0){
                        orderDetailList.addAll(orderDetailListMerchant);
                    }
                    bcPercent = _getCommissionByMerchant(m.getmId(), sumSales);
                    if (ejQdList.size() > 0) {
                        // 子渠道佣金
                        for (int j = 0; j < ejQdList.size(); j++) {
                            Double ErSales = sumErSales((Integer) ejQdList.get(j), date);// 二级渠道销售额
                            childQdSales += ErSales;
                            Double eJbcPercent = _getCommissionByMerchantId((Integer) ejQdList.get(j), sumErSales);
                            childQdCommission += Double.valueOf(CalculationUtils.mul((ErSales), (5 - eJbcPercent) / 100));
                        }
                    }
                    // 标签用户产生的佣金计算
//                    markeCommission = Double.valueOf(CalculationUtils.mul((sumSales - childQdSales), 0.05));// 标签佣金
//                    totalCommssion = Double.valueOf(CalculationUtils.mul((markeCommission + childQdCommission), bcPercent / 100));// 该用户的总佣金*分润比例
                    totalCommssion = 0.0;// 该用户的总佣金*分润比例
                } else if (m.getmLevel() == 2) {
                    idListParamOne.add(m.getmId());
                    idListParamTwo.add(m.getmCpUserId());
                    sjQdList = findQdIdList(idListParamOne, date);// 二级的级子渠道用户idList
                    merchantIdList.addAll(idListParamTwo);
                    ejMarkeList = findMarketIdList(idListParamTwo, date + " 23:59:59");// 二级标签idList
                    if (sjQdList.size() > 0) {
                        merchantIdList.addAll(sjQdList);
                        sjMarkeList = findMarketIdList(sjQdList, date + " 23:59:59");// 三级标签idList
                    }
                    sumSales +=sumSalesMerchant(merchantIdList,date);
                    orderDetailListMerchant = findTicketInfoDetailListMerchant(merchantIdList, date + " 00:00:00", date + " 23:59:59", m.getmId());

                    // 二级渠道总销售额
                   // idListParamThree.addAll(idListParamTwo);
                    //idListParamThree.addAll(sjQdList);
                    idListParamThree.addAll(ejMarkeList);
                    idListParamThree.addAll(sjMarkeList);
                    if (idListParamThree.size() > 0) {
                        sumSales += sumSales(idListParamThree, date);
                        orderDetailList = ticketInfoDetailList(idListParamThree, date + " 00:00:00", date + " 23:59:59",m.getmId());
                        bcPercent = _getCommissionByMerchant(m.getmId(), sumSales);
                    }
                    if(orderDetailListMerchant.size()>0){
                        orderDetailList.addAll(orderDetailListMerchant);
                    }
                    totalCommssion = Double.valueOf(CalculationUtils.mul(sumSales, bcPercent / 100));// 该用户的总佣金
                } else {
                    idListParamTwo.add(m.getmCpUserId());
                    sjMarkeList = findMarketIdList(idListParamTwo, date + " 23:59:59");// 三级标签idList
                    merchantIdList.addAll(idListParamTwo);
                    sumSales += sumSalesMerchant(merchantIdList, date);//查询渠道本身的销售额 统计创建渠道用户的时间为标准
                    orderDetailListMerchant = findTicketInfoDetailListMerchant(merchantIdList,date + " 00:00:00", date + " 23:59:59",m.getmId());
                    //idListParamThree.addAll(idListParamTwo);
                    idListParamThree.addAll(sjMarkeList);
                    if (idListParamThree.size() > 0) {
                        sumSales += sumSales(idListParamThree, date);//查询标签的销售额以打标签的时间为准
                        orderDetailList = ticketInfoDetailList(idListParamThree, date + " 00:00:00", date + " 23:59:59",m.getmId());
                        bcPercent = _getCommissionByMerchant(m.getmId(), sumSales);
                    }
                    if(orderDetailListMerchant.size()>0){
                        orderDetailList.addAll(orderDetailListMerchant);
                    }
                    totalCommssion = Double.valueOf(CalculationUtils.mul(sumSales, bcPercent / 100));// 标签佣金
                }
                if(orderDetailList.size()>0){
                    orderDetailLists.addAll(orderDetailList);
                }
                if (sumSales == null) {
                    sumSales = 0.0;
                }
                scr.setSales(sumSales);
                scr.setPercent(bcPercent);
                if (totalCommssion == null) {
                    totalCommssion = 0.0;
                }
                scr.setCommission(totalCommssion);
                scrList.add(scr);
            }
            if(scrList.size()>0){
                dao.insertList(scrList);
            }
            if(orderDetailLists.size()>0){
                salesSumDao.insertOrderList( orderDetailLists);
            }

        }
    }

    /**
     * 清除当天的数据
     *
     * @param date
     */
    public void deleteExist(String date) {
        List<SalesCommissionReport> scrExitList = dao.findReportExistList(date);
        if (scrExitList.size() != 0 && !scrExitList.isEmpty() && scrExitList != null) {
            dao.deleteList(scrExitList);
        }
    }

    /**
     * 标签用户的销量
     *
     * @param userid
     * @param type
     * @param date
     * @param list
     * @return
     */
    private Double _getTotalSaleBySign(String userid, String type, String date, List<Merchant> list) {
        Double totalSales = 0.0;
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        if (type.equals("auto")) {
            beginTime = DateUtils.getAfterDayDate("-1").substring(0, 10) + " 00:00:00";
            endTime = DateUtils.getAfterDayDate("-1").substring(0, 10) + " 23:59:59";
        }
        // 获得该用户所有的标签用户
        List<Integer> userIdList = dao.findUserIdList(userid);
        for (Integer id : userIdList) {
            if (list.size() != 0) {
                for (Merchant m : list) {
                    if (!id.equals(m.getmCpUserId())) {
                        String marketTime = dao.findMartketTimeByUserId(id);
                        Double sales = dao.findTotalSalesBySign(beginTime, endTime, id, marketTime);
                        if (sales == null) {
                            sales = 0.0;
                        }
                        totalSales += Double.valueOf(CalculationUtils.add(totalSales, sales));
                    }
                }
            } else {
                String marketTime = dao.findMartketTimeByUserId(id);
                Double sales = dao.findTotalSalesBySign(beginTime, endTime, id, marketTime);
                if (sales == null) {
                    sales = 0.0;
                }
                totalSales += Double.valueOf(CalculationUtils.add(totalSales, sales));
            }

        }
        if (totalSales == null) {
            totalSales = 0.0;
        }
        return totalSales;
    }

    /**
     * 根据渠道商户id和销量获得具体的佣金规则
     *
     * @param merchantId
     * @param sales
     */
    private Double _getCommissionByMerchant(Integer merchantId, Double sales) {
        if (sales == null) {
            sales = 0.0;
        }
        BaseCommission bc = bcDao.findBaseCommissionByParam(merchantId);
        Double bcs = 0.0;
        if (bc != null) {
            if (sales <= bc.getOneRankMax() * 10000 && sales >= bc.getOneRankMin() * 10000) {
                bcs = bc.getOnePercent();
            } else if (sales <= bc.getTwoRankMax() * 10000 && sales >= bc.getTwoRankMin() * 1000) {
                bcs = bc.getTwoPercent();
            } else if (sales >= bc.getThreeRankMin() * 10000) {
                bcs = bc.getThreePercent();
            }
        }
        return bcs;
    }

    /**
     * 根据渠道商户id和销量获得具体的佣金规则
     *
     * @param sales
     */
    private Double _getCommissionByMerchantId(Integer cpUserId, Double sales) {
        if (sales == null) {
            sales = 0.0;
        }
        BaseCommission bc = bcDao.findBaseCommissionByParamId(cpUserId);
        Double bcs = 0.0;
        if (bc != null) {
            if (sales <= bc.getOneRankMax() * 10000 && sales >= bc.getOneRankMin() * 10000) {
                bcs = bc.getOnePercent();
            } else if (sales <= bc.getTwoRankMax() * 10000 && sales >= bc.getTwoRankMin() * 10000) {
                bcs = bc.getTwoPercent();
            } else if (sales >= bc.getThreeRankMin() * 10000) {
                bcs = bc.getThreePercent();
            }
        }
        return bcs;
    }

    /**
     * 获取该渠道商户发展的子渠道商户
     *
     * @param parentMerchantId
     * @return
     */
    private List<Merchant> _getChildListByMerchant(Integer parentMerchantId) {
        List<Merchant> list = new ArrayList<>();
        list = merchantDao.findByParentMerchantId(parentMerchantId);
        return list;
    }

    /**
     * 渠道商户的发展用户的总销量
     *
     * @param list
     * @return
     */
    private Double _getTotalSalesByChildMerchant(List<Merchant> list, String type, String date) {
        Double totalSales = 0.0;
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        if (type.equals("auto")) {
            beginTime = DateUtils.getAfterDayDate("-1").substring(0, 10) + " 00:00:00";
            endTime = DateUtils.getAfterDayDate("-1").substring(0, 10) + " 23:59:59";
        }
        for (Merchant m : list) {
            totalSales += dao.findTotalSalesByMId(3, beginTime, endTime, m.getmCpUserId(), m.getmCreateTime());// todo word
        }
        if (totalSales == null) {
            totalSales = 0.0;
        }
        return totalSales;
    }

    private Double _getEachSalesByChildMerchant(Merchant m, String type, String date) {
        Double totalSales = 0.0;
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        if (type.equals("auto")) {
            beginTime = DateUtils.getAfterDayDate("-1").substring(0, 10) + " 00:00:00";
            endTime = DateUtils.getAfterDayDate("-1").substring(0, 10) + " 23:59:59";
        }
        totalSales = dao.findTotalSalesByMId(2, beginTime, endTime, m.getmCpUserId(), m.getmCreateTime());
        return totalSales;
    }

    @Override
    public Page<SalesDetailVo> findSalesDetail(SalesDetailVo o, Page<SalesDetailVo> page) {
        page.setResults(dao.findSalesDetail(o, page));
        return page;
    }

    @Override
    public Page<SalesCommissionReport> findByList(String date, List<String> orgList) {
        Page<SalesCommissionReport> page = new Page<SalesCommissionReport>();
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("date", date);
        params.put("orgList", orgList);
        page.setResults(dao.findByList(params));
        return page;
    }

    public List findMerchandByList(List orgList) {
        Map map = new HashMap();
        map.put("list", orgList);
        return dao.findMerchandByList(map);
    }

    @Override
    public Page<SalesCommissionReport> findSMRByList(String beginTime, String endTime, List<String> orgList) {
        Page<SalesCommissionReport> page = new Page<SalesCommissionReport>();
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("orgList", orgList);
        page.setResults(dao.findSMRByList(params));
        return page;
    }

    @Override
    public Org findOrgById(String oid) {
        return dao.findOrgById(oid);
    }

    /**
     * 获取标签用户
     *
     * @param map
     * @return
     */
    @Override
    public List<Integer> findUserIdListByMerchantId(Map map) {
        return dao.findUserIdListByMerchantId(map);
    }

    /**
     * 获取标签用户name
     *
     * @param vo
     * @return
     */
    @Override
    public List<UserVo> findUserListByMerchantId(Integer merchantId, UserVo vo) {
        return dao.findUserListByMerchantId(merchantId, vo);
    }

    @Override
    public String findMartketTimeByUserId(Integer userId) {
        return dao.findMartketTimeByUserId(userId);
    }

    /**
     * @param beginTime
     * @param endTime
     * @param m
     * @return
     */
    @Override
    public Double findSalesByMerchant(String beginTime, String endTime, Merchant m) {
        return dao.findSalesByMerchant(beginTime, endTime, m);
    }

    @Override
    public String getLastOrderTimeByUser(UserVo lastVo) {
        return dao.findLastOrderTimeByUser(lastVo);
    }

    @Override
    public Double getSignSale(String beginTime, String endTime, Integer userId, String marketTime) {
        return dao.findTotalSalesBySign(beginTime, endTime, userId, marketTime);
    }

    @Override
    public Double findTotalSalesByParam(SalesCommissionReport o) {
        return dao.findTotalSalesByParam(o);
    }

    @Override
    public Double findTotalCommissionByParam(SalesCommissionReport o) {
        return dao.findTotalCommissionByParam(o);
    }

    @Override
    public Double findTotalSalesByParamList(String beginTime, String endTime,  List<Merchant> accountList) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("accountList", accountList);
        return dao.findTotalSalesByParamList(params);
    }

    @Override
    public Double findTotalCommissionByParamList(String beginTime, String endTime,  List<Merchant> accountList) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("accountList", accountList);
        return dao.findTotalCommissionByParamList(params);
    }

    /**
     * 查询自己和子渠道报表
     *
     * @param o
     * @param page
     * @return
     */
    @Override
    public Page<SalesCommissionReport> findSelfAndChild(SalesCommissionReport o, Page<SalesCommissionReport> page) {
        page.setResults(dao.findSelfAndChild(o, page));
        return page;
    }

    /**
     * 统计标签销售额
     *
     * @return
     */
    public Double sumSales(List marketIdList, String date) throws Exception {
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        Map map = new HashMap();
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("list", marketIdList);
        Double sales = dao.sumSales(map);
        return sales;
    }
    /**
     * 统计渠道本身销售额
     *
     * @return
     */
    public Double sumSalesMerchant(List marketIdList, String date) throws Exception {
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        Map map = new HashMap();
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("list", marketIdList);
        Double sales = dao.sumSalesMerchant(map);
        return sales;
    }

    /**
     * 要统计销售额的标签idList
     *
     * @return
     */
    public List findMarketIdList(List pareMarketIdList, String date) throws Exception {
        Map map = new HashMap();
        map.put("list", pareMarketIdList);
        map.put("date", date);
        List idList = dao.findMarketIdList(map);
        return idList;
    }

    /**
     * 1级子渠道idList
     *
     * @param pareIdList
     * @param date
     * @return
     */
    public List findQdIdList(List pareIdList, String date) throws Exception {
        Map map = new HashMap();
        map.put("list", pareIdList);
        // map.put("date",date);
        List idList = dao.findQdIdList(map);
        return idList;
    }

    /**
     * 2级子渠道idList
     *
     * @param pareIdList
     * @param date
     * @return
     */
    public List findChildQdIdList(List pareIdList, String date) throws Exception {
        Map map = new HashMap();
        map.put("list", pareIdList);
        map.put("date", date);
        List idList = dao.findChildQdIdList(map);
        return idList;
    }

    // 获取子渠道的佣金
    public Double sumChildQdCommission(int ejQdid, String date, Double percent) throws Exception {
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        Map map = new HashMap();
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("ejQdid", ejQdid);
        map.put("percentOne", percent);
        map.put("percentTwo", percent);
        map.put("percentThree", percent);
        return dao.sumChildQdCommission(map);
    }

    // 获取子渠道销售额
    public Double sumErSales(Integer ejQdid, String date) throws Exception {
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        Map map = new HashMap();

        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("qdidOne", ejQdid);
        map.put("qdidTwo", ejQdid);
        map.put("qdidThree", ejQdid);
        map.put("qdidFour", ejQdid);
        map.put("qdidFive", ejQdid);
        map.put("qdidSix", ejQdid);
        return dao.sumErSales(map);
    }

    // 获取三级子渠道销售额
    public Double sumSjSales(Integer ejQdid, String date) throws Exception {
        String beginTime = date + " 00:00:00";
        String endTime = date + " 23:59:59";
        Map map = new HashMap();

        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("qdidOne", ejQdid);
        map.put("qdidTwo", ejQdid);
        return dao.sumSjSales(map);
    }

    @Override
    public void findExportReport() {
        Workbook wb = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = wb.createSheet("渠道商销量日报");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date d = cal.getTime();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
        List<SalesCommissionReport> list = dao.findExportReport(date);
        Object[][] data = new Object[list.size()][list.size()];
        for (int i = 0; i < list.size(); i++) {
            String time = list.get(i).getDate();
            String contactUser = list.get(i).getContactUser();
            String name = list.get(i).getName();
            String mobile = list.get(i).getMobile();
            Double sales = list.get(i).getSales();
            Double percent = list.get(i).getPercent();
            Double commission = list.get(i).getCommission();
            if (null == commission) {
                commission = 0.00;
            }
            if (i == list.size() - 1) {
                data[i] = new Object[] {
                        "", "", "", "", "", "合计：", commission };
            } else {
                data[i] = new Object[] {
                        time.substring(0, 10), contactUser, name, mobile, sales, percent + "%", commission };
            }
        }
        // 显示标题
        Row title_row = sheet.createRow(0);
        title_row.setHeight((short) (40 * 20));
        Cell title_cell = title_row.createCell(0);
        String headers[] = new String[] {
                "日期", "渠道/客户名称", "联系人", "联系方式", "销量", "分润比例", "返佣金额" };
        Row header_row = sheet.createRow(1);
        header_row.setHeight((short) (20 * 24));
        // 创建单元格的 显示样式
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER); // 水平方向上的对其方式
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直方向上的对其方式
        title_cell.setCellStyle(style);
        title_cell.setCellValue("渠道商销量日报");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headers.length - 1));
        for (int i = 0; i < headers.length; i++) {
            // 设置列宽 基数为256
            sheet.setColumnWidth(i, 20 * 256);
            Cell cell = header_row.createCell(i);
            // 应用样式到 单元格上
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 2);
            row.setHeight((short) (20 * 20)); // 设置行高 基数为20
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(data[i][j].toString());
            }
        }
        FileOutputStream fileOut;
        try {
            SysDict o = new SysDict();
            o.setParamKey("filePath");
            List<SysDict> dictList = service.find(o);
            SysDict obj = dictList.get(0);
            String day = date.replace("-", "");
            String filePath = obj.getParamValue() + day + "/";
            File dirFile = new File(filePath);
            if (!dirFile.exists()) {// 文件路径不存在时，自动创建目录
                dirFile.mkdir();
            }
            fileOut = new FileOutputStream(filePath + "渠道商销量日报" + date + ".xls");
            wb.write(fileOut);
            fileOut.close();
            log.info("生成渠道商销量日报成功" + date);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("生成渠道商销量日报失败" + e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("生成渠道商销量日报失败" + e);
        }
    }

    /**
     * 用户销量统计
     *
     * @param map
     * @return
     * @throws Exception
     */
    public List<SalesCommissionReport> findSalesCommission(Map map) throws Exception {
        return dao.findSalesCommission(map);
    }

    @Override
    public Page<SalesCommissionReport> findByPageList(SalesCommissionReport o, Page<SalesCommissionReport> page) {
        List<SalesCommissionReport> list = null;
        Map<String,Object> param = new HashMap<String,Object>();
        String userId = AccountShiroUtil.getCurrentUser().getAccountId();
        param.put("userId", userId);
        if (o.getOrgSel() != null && o.getOrgSel() != "") {
            String[] org_ids = o.getOrgSel().split(";");
            param.put("orgList", org_ids);
        } else {
            param.put("orgList", null);
        }
        param.put("level", o.getLevel());
        param.put("beginTime", o.getBeginTime());
        param.put("endTime", o.getEndTime());
        param.put("parentMerchantId", o.getParentMerchantId());
        param.put("merchantId", o.getMerchantId());
        param.put("date", o.getDate());
        if ((null != param) && (param.size() != 0)) {
            list = dao.findByPageList(param);
        }
        page.setResults(list);
        page.setTotalRecord(list.size());
        page = PageCalculation.getPageCalculation(page);
        return page;
    }
    public List<OrderDetail> ticketInfoDetailList(List idList,String beginTime,String endTime,Integer preMerchantId ) throws Exception{
        Map map = new HashMap();
        map.put("list",idList);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("preMerchantId",preMerchantId);
        return  salesSumDao.findTicketInfoDetailList(map);
    }
    public List<OrderDetail> findTicketInfoDetailListMerchant(List idList,String beginTime,String endTime,Integer preMerchantId ) throws Exception{
        Map map = new HashMap();
        map.put("list",idList);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("preMerchantId",preMerchantId);
        return  salesSumDao.findTicketInfoDetailListMerchant(map);
    }
    public void deleteDetailListIfExist(String date) throws Exception{
        Map map = new HashMap();
        map.put("beginTime",date+" 00:00:00");
        map.put("endTime", date+" 23:59:59");
        salesSumDao.deleteDetailListIfExist(map);
    }
}
