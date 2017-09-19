package com.jy.service.system.channels;

import com.jy.common.ajax.AjaxRes;
import com.jy.entity.system.channels.Commission;
import com.jy.mybatis.Page;
import com.jy.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 渠道佣金明细
 */
public interface CommissionService extends BaseService<Commission>
{


    public AjaxRes synchroCommison(String startDate, String endDate) throws Exception;

    public AjaxRes synchroCommisonOffline() throws Exception;

    public Page<Commission> findByPage(Map map, Page page)throws Exception;

    /**
     * 统计
     *
     */
    public Integer counts(Map map);


    /**
     * 合并查找
     *
     */
    public List<Commission> findByIds(List<String> list);

    /**
     * 更改返佣状态
     *
     */
    public void updateStatus(List<String> list);

//    /**
//     * 新增
//     *
//     * @param commission
//     */
//    public void save(SalesChannelToCommission commission) {
//        this.createObject(commission);
//    }
//
//    /**
//     * 修改
//     *
//     * @param commission
//     */
//    public void update(SalesChannelToCommission commission) {
//        this.updateObject(commission);
//    }
//
//    /**
//     * 删除
//     *
//     * @param commission
//     */
//    public void delete(SalesChannelToCommission commission) {
//        getHibernateTemplate().delete(commission);
//    }
//
//    /**
//     * 根据抢包记录的ID 查找记录
//     *
//     * @param id
//     * @return
//     */
//    public SalesChannelToCommission findById(Integer id) {
//        return (SalesChannelToCommission) findObjectById(id);
//    }
//
//
//    /**
//     * 根据实体类的属性 查找记录
//     *
//     * @param propertyName 实体类的属性
//     * @param value        实体类属性对应的值
//     * @return
//     */
//    public List findByProperty(String propertyName, Object value) {
//        return getByProperty(propertyName, value);
//    }
//
//    /**
//     * 新增或者修改记录
//     *
//     * @param commission
//     */
//    public void saveOrUpdate(SalesChannelToCommission commission) {
//        getHibernateTemplate().saveOrUpdate(commission);
//    }
//
//    /**
//     *
//     * @param channelId
//     * @return
//     */
//    public List<SalesChannelToCommission> findByChannelId(Integer channelId) {
//        StringBuffer hql = new StringBuffer(" from SalesChannelToCommission  ");
//        hql.append(" where valid =1 and    channelId= ?");
//        List<Object> args = new ArrayList<>();
//        args.add(channelId);
//        return (List<SalesChannelToCommission>) getList(hql.toString(), args);
//    }
//
//    /**
//     *
//     * @param channelId
//     * @param id
//     * @return
//     */
//    public List<SalesChannelToCommission> findByChannelIdAndCommissionId(Integer channelId, Integer id) {
//        StringBuffer hql = new StringBuffer(" from SalesChannelToCommission  ");
//        hql.append(" where valid =?");
//        hql.append(" and    channelId= ?");
//        hql.append(" and    commissionId= ?");
//        List<Object> args = new ArrayList<>();
//        args.add(1);
//        args.add(channelId);
//        args.add(id);
//        return (List<SalesChannelToCommission>) getList(hql.toString(), args);
//    }
}
