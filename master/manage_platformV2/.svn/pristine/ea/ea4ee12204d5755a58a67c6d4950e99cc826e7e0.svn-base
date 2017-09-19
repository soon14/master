package com.jy.service.system.org;

import java.util.List;
import java.util.Map;

import com.jy.entity.system.org.Org;
import com.jy.mybatis.Page;
import com.jy.entity.utils.ZNodes;
import com.jy.entity.system.account.Account;
import com.jy.entity.system.org.Position;
import com.jy.service.base.BaseService;

public interface PositionService extends BaseService<Position>{

	/**
	 * 获得岗位书树
	 *
	 * @return
	 */
	public List<ZNodes> getOrgAndPositionTree();

	/**
	 * 获取组织角色数
	 * @return
	 */
	public List<ZNodes> getOrgRoleTree();

	/**
	 * 获得上级组织树
	 *
	 * @return
	 */
	public List<ZNodes> getPreOrgTree();
	/**
	 *
	 * @方法功能描述： 根据人获取权限数
	 * @return
	 * List<ZNodes>
	 */
	public List<ZNodes> getOrgTreesByPos(String id);

	/**
	 * 保存用户职务表
	 *
	 * @param list
	 */
	public void saveAccountPosition(String posId,String ids);

	/**
	 * 通过职位Id寻找可安排用户列表
	 *
	 * @param Position
	 */
	public Page<Account> findArrangeAccByPage(Position o,Page<Account> page);

	/**
	 * 通过职位Id寻找已安排用户列表
	 *
	 * @param Position
	 */
	public Page<Account> findPosByPage(Position o,Page<Account> page);

	/**
	 * 删除用户职务表
	 *
	 * @param accountId 用户Id
	 */
	public void deleteAccPosByLoginName(String loginName);

	/**
	 * 删除职务
	 */
	public void deletePos(Position o);

	/**
	 * 获取所有职务
	 *
	 * @return
	 */
	public Page<Position> findAllPosByPage(Position o, Page<Position> page);
	/**
	 *
	 * @方法功能描述： 删除职务权限
	 * @param posId
	 * void
	 */
	public void  savePosOrg(String posId,String orgs);

	public List<Org> findDataAuthority(String userId);

}
