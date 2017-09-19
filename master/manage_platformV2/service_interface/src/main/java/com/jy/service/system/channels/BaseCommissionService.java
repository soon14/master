package com.jy.service.system.channels;

import java.util.List;
import java.util.Map;

import com.jy.entity.system.account.Account;
import com.jy.mybatis.Page;
import com.jy.entity.system.channels.BaseCommission;
import com.jy.entity.system.channels.Merchant;
import com.jy.service.base.BaseService;
import org.apache.ibatis.annotations.Param;


/**
 * 渠道佣金规则
 */
public interface BaseCommissionService extends BaseService<BaseCommission>
{

//    /**
	// * 新增
//     *
//     * @param commission
//     */
//    public void save(SalesCommission commission) {
//        this.createObject(commission);
//    }
//

	public void deleteBaseCommission(BaseCommission commission);

	public void updateBaseCommission(BaseCommission commission);

	public void insertBaseCommission(BaseCommission commission);

	/**
	 * 根据商户id获取对应的有效佣金配置
	 *
	 * @param merchant
	 * @return
	 */
	public List<BaseCommission> findCommissionByMerchant(Merchant merchant);


	public Page findByPages(BaseCommission o);

	public Page<BaseCommission> findByUserName(Page<BaseCommission> page,Map m);
	public List<Account> findUserName(Map o);

	public Page<BaseCommission> findAllCommission(Page<BaseCommission> page,Map m);

	public List<BaseCommission> findById(Map m);

//
//    /**
	// * 删除
//     *
//     * @param commission
//     */
//    public void delete(SalesCommission commission) {
//        getHibernateTemplate().delete(commission);
//    }
//
//    /**
	// * 根据ID 查找记录
//     *
//     * @param id
//     * @return
//     */
//    public SalesCommission findById(Integer id) {
//        return (SalesCommission) findObjectById(id);
//    }
//
//
//    /**
	// * 根据实体类的属性 查找记录
//     *
	// * @param propertyName 实体类的属性
	// * @param value 实体类属性对应的值
//     * @return
//     */
//    public List findByProperty(String propertyName, Object value) {
//        return getByProperty(propertyName, value);
//    }
//
//    /**
	// * 新增或者修改记录
//     *
//     * @param commission
//     */
//    public void saveOrUpdate(SalesCommission commission) {
//        getHibernateTemplate().saveOrUpdate(commission);
//    }
//
//
//    /**
	// * 查询渠道佣金
//     *
//     * @param type
//     * @param valid
//     * @param userId
//     */
//    public List<SalesCommission> findSalesCommissions(int type, int valid, Integer userId) {
//        StringBuffer hql = new StringBuffer(" from SalesCommission where 1=1 ");
//        List<Object> parameters = new ArrayList<>();
//        if (type != -1) {
//            hql.append(" and type=? ");
//            parameters.add(type);
//        }
//        if (valid != -1) {
//            hql.append(" and valid=? ");
//            parameters.add(valid);
//        }
//        if (userId != null && userId != 0) {
//            hql.append(" and creator= ? ");
//            parameters.add(userId);
//        }
//        /*else{
//            hql.append(" and creator is null ");
//        }*/
//        hql.append(" order by id desc ");
//        return getList(hql.toString(), parameters);
//
//    }
//
//    /**
	// * 查询渠道佣金是否存在
//     *
//     * @param type
//     * @param numberFrom
//     * @param numberTo
//     * @param userId
//     */
//    public int findCommissionsExsit(int type, Double numberFrom, Double numberTo, Integer userId) throws Exception {
//        StringBuffer hql = new StringBuffer(" from SalesCommission where valid=1 and creator= ? and type = ? ");
//        hql.append(" and ((numberFrom < ? and numberTo > ? ) ");
//        hql.append(" or (numberFrom < ? and numberTo > ? ) ");
//        hql.append(" or (numberFrom = ? and numberTo = ? )) ");
//        List<Object> parameters = new ArrayList<>();
//        parameters.add(userId);
//        parameters.add(type);
//        parameters.add(numberFrom);
//        parameters.add(numberFrom);
//        parameters.add(numberTo);
//        parameters.add(numberTo);
//        parameters.add(numberFrom);
//        parameters.add(numberTo);
//        List<SalesCommission> list = getList(hql.toString(), parameters);
//        if (list == null || list.isEmpty() || list.size() == 0) {
//            return 0;
//        }
//        return list.size();
//    }
//
//    /**
	// * 删除渠道信息
//     *
//     * @param commissionId
//     * @param updater
//     * @param name
//     */
//    public void deleteCommissionById(Integer commissionId, Integer updater, String name) {
//        StringBuffer hql = new StringBuffer(" update SalesCommission set valid= 0,updater=?,updaterName=?,updatetime=? where id = ? ");
//        List<Object> parameters = new ArrayList<>();
//        parameters.add(updater);
//        parameters.add(name);
//        parameters.add(new Date());
//        parameters.add(commissionId);
//        executeUpdate(hql.toString(), parameters);
//    }
//
//    /**
	// * 查询是否有渠道佣金信息
//     *
//     * @param sysId
//     * @param type
//     * @param level
//     * @return
//     */
//    public List<SalesCommission> findCommissionsBySysId(Integer sysId, int type, int level) {
//        StringBuffer hql = new StringBuffer(" from SalesCommission where creator=? and valid=1");
//        hql.append(" and type=? ");
//        hql.append(" and level=? ");
//        List<Object> args = new ArrayList<>();
//        args.add(sysId);
//        args.add(type);
//        args.add(level);
//        return (List<SalesCommission>) getList(hql.toString(), args);
//    }
//
//    /**
//     *
//     *
//     * @param sysUserId
//     * @param channelId
//     * @param type
//     * @param level
//     * @return
//     */
//    public List<SalesCommission> findCommissionDetails(Integer sysUserId, Integer channelId, int type, int level) {
//        StringBuffer hql = new StringBuffer(" from SalesCommission sc ");
//        //hql.append(" left join SalesChannelToCommission sctc on sc.id=sctc.commissionId ");
//        //hql.append(" where sctc.valid=1 and sc.creator= ? and sctc.channelId= ? ");
//        hql.append(" where sc.creator= ?");
//        List<Object> args = new ArrayList<>();
//        args.add(sysUserId);
//        //args.add(channelId);
//        return (List<SalesCommission>) getList(hql.toString(), args);
//    }
}
