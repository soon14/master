package com.jy.service.system.channels;


import java.util.List;
import java.util.Map;

import com.jy.entity.system.finance.CpOrderInfo;
import com.jy.entity.system.finance.UserVo;
import org.apache.ibatis.annotations.Param;

import com.jy.mybatis.Page;
import com.jy.entity.system.channels.Merchant;
import com.jy.service.base.BaseService;

/**
 * 渠道商户
 */
public interface MerchantService extends BaseService<Merchant> {

    /**
     * 新增商户
     *
     * @param merchant 商户信息
     * @return
     */
    public Integer createMerchant(Merchant merchant);

    /**
     * 查询自己的渠道ID
     *
     * @param userId
     * @return
     */
    public Merchant findId(String userId,String mobile);
    public List<Merchant> findChildCustomerByMId(Merchant merchant);
    public List<Merchant> findChildMerchantByMId(Merchant merchant);
    public  List<Merchant> findMerchantByPreId(Map map);
    public  List<Merchant> findMerchantByPreIdEj(Map map);
    public  List<Merchant> findMerchantByPreIdorg(Map map);
    public  List<Merchant> findChildMerchantId(Map map);

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    public Integer deleteMerchant(List id);
    public Integer deleteMerchantByMId(String mId);

    /**
     * 通过渠道ID批量查询渠道
     *
     * @param id
     * @return
     */
    public List<Merchant> findMerchantId(List id);


    /**
     * 查询自己渠道的次级渠道数
     *
     * @param merchant
     * @return
     */
    public Integer findCount(Merchant merchant);

    /**
     * 获得对象列表
     *
     * @param paramMap 集合对象
     * @param paramPage 分页对象
     * @return List
     */
    public Page findByPageId(Map paramMap, Page paramPage);
    
    public Page findLabelMerchant(Map paramMap, Page paramPage);

    /**
     * 统计个人和企业数
     *
     * @param merchant
     * @return
     */
    public Merchant findPersonAndEnterprise(Merchant merchant);
    public Merchant countPersonAndEnterprise(Map map);

    /**
     * 修改商户
     *
     * @param merchant
     * @return
     */
    public Integer updateMerchant(Merchant merchant);

    public Integer updateMLicense(Merchant merchant);

    public List<Merchant> findByParentMerchantId(Integer parentMerchantId);

    public Merchant findMerchant(String accountId);


    /**
     * 查询自己渠道的次级渠道
     *
     * @param merchant
     * @return
     */
    public List<Merchant> findChannel(Merchant merchant);

    public List<Merchant> findBaseCommission(Merchant merchant);

    /**
     * 查询商户ID
     *
     * @param merchant
     * @return
     */
    public List<Merchant> findByPage(@Param("param") Merchant merchant);

    public Merchant findMerchantById(Integer merchantId);

    public List<Merchant> findMerchantByIdAndName(Integer merchantId, String merchantName);

    /**
     * 查询子渠道id列表
     * @param map
     * @return
     * @throws Exception
     */
    public List findChildMerchantIdList(Map map) throws Exception;

    /**
     * 查询商户标签用户idList
     * @param map
     * @return
     * @throws Exception
     */
    public List findMarketerUserIdList(Map map) throws Exception;

    /**
     * 查询商户信息
     * @param merchantId
     * @return
     * @throws Exception
     */
    public Merchant findMerchantPO(String merchantId) throws Exception;

    /**
     * 获取子级渠道IdList
     * @param merchantId
     * @return
     * @throws Exception
     */
    public List findZjMerchantIdList(Integer merchantId,String beginTime,String endTime) throws Exception;
    /**
     * 获取孙级渠道IdList
     * @param merchantIdList
     * @return
     * @throws Exception
     */
    public List findSjMerchantIdList(List merchantIdList) throws Exception;
    /**
     * 获取下级渠道标签用户IdList
     * @param userIdList
     * @return
     * @throws Exception
     */
    public List findXjMarketerIdList(List userIdList,String beginTime,String endTime) throws Exception;
    public List findMarketIdListCount(List userIdList,String beginTime,String endTime) throws Exception;

    /**
     * 查询购彩明细
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public List<CpOrderInfo> ticketInfoList(Integer preMerchantId,String beginTime,String endTime,String userName,String phone,int pageNum,int pageSize) throws Exception;
    public Double sumSalesTicketInfo(Integer preMerchantId,String beginTime,String endTime,String userName,String phone) throws Exception;
    public Integer sumFindTicketInfoList(Integer preMerchantId,String beginTime,String endTime,String userName,String phone) throws Exception;
    /**
     * 查询客户明细信息
     * @param idList
     * @return
     * @throws Exception
     */
    public List<UserVo> salesUserList(List idList,String userName,String phone,int pageNum,int pageSize) throws Exception;
    public Integer sumUserList(List idList,String userName,String phone) throws Exception;

    public String findChildMerchant(int mId);

    public String findChildCustomer(int mId);

    public String findRoleName(int mId);

    public List<Merchant> findDataAuthorityMerchant(Map o);

    public List<Merchant> findDataAuthoritySalesManager(Map o);

    public List<Merchant> findParentMerchant(Map o);

    public List<Merchant> findOwn(Map o);

    public List<Merchant> findUserLevel(Map o);

    public Integer findmName(String mName);

    public Integer findmMobile(String mMobile,String mId);

}
