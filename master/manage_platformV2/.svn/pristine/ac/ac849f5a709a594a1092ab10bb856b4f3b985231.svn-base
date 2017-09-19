package com.jy.repository.system.org;

import java.util.List;
import java.util.Map;

import com.jy.entity.system.org.Org;
import com.jy.entity.utils.ZNodes;
import com.jy.mybatis.Page;
import org.apache.ibatis.annotations.Param;



import com.jy.entity.system.account.Account;
import com.jy.entity.system.org.AccountPosition;
import com.jy.entity.system.org.Position;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
@JYBatis
public interface PositionDao extends BaseDao<Position>{
	

	public List<ZNodes> getOrgTreesByPos(@Param("posId") String id);

	public List<Org> findDataAuthority(@Param("userId") String userId,@Param("orgId") String orgId);

	public List<ZNodes> findRoleIdByUserId(@Param("userId") String userId,@Param("roleId") String roleId);

	public List<Org> findAllAccountId();

	/**
	 * 获得岗位树
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
	 * 增加用户职务表
	 * 
	 * @param list
	 */
   public void insertAccountPosition(Map map);
   	
	/**
	 * 删除用户职务表通过用户Id
	 * 
	 * @param accountId 用户Id
	 */
   public void deleteAccPosByLoginName(String loginName);
   	
	/**
	 * 批量删除用户职务表通过用户Id
	 * 
	 * @param accountId 用户Id
	 */
   public void deleteBatchAccPosByAccId(List<Account> accs);
   	
	/**
	 * 删除用户职务表通过职务Id
	 * 
	 * @param posId 职务Id
	 */
   public void deleteAccPosByPosId(String posId);
   	
	/**
	 * 批量删除用户职务表通过职务Id
	 * 
	 * @param posId 职务Id
	 */
   public void deleteBatchAccPosByPosId(List<Position> poss);
   	
	/**
	 * 通过职位Id寻找可安排用户列表
	 * 
	 * @param Position
	 */
   public List<Account> findArrangeAccByPage(@Param("param") Position o, Page<Account> page);
   	
	/**
	 * 通过职位Id寻找已安排用户列表
	 * 
	 * @param Position
	 */
   public List<Account> findPosByPage(@Param("param") Position o, Page<Account> page);
   	
	/**
	 * 通过组织Id寻找职务表
	 * 
	 * @param orgId
	 */
   public List<Position> findByOrgId(@Param("orgId") String orgId);
   	
	/**
	 * 获取所有职务
	 * 
	 * @return
	 */
   public List<Position> findAllPosByPage(@Param("param") Position o, Page<Position> page);
  /**
   * 
   * @方法功能描述： 删除职务权限 
   * @param posId 
   * void
   */
   public void  deletePos_Org(@Param("posId") String posId);
   /**
    * 
    * @方法功能描述： 插入职务权限 
    * @param list 
    * void
    */
   public void  insertPos_Org(@Param("list") List list);

   public List<Org> findOrgByUserId(String userId);

	public List<Org> findRoleByUserId(String userId);

}
