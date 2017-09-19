package com.jy.service.system.org;

import java.util.List;

import com.jy.entity.utils.ZNodes;
import com.jy.entity.system.org.Org;
import com.jy.service.base.BaseService;

public interface OrgService extends BaseService<Org> {

    /**
     * 获取机构树
     *
     * @return
     */
    public List<ZNodes> getOrgTree();

    /**
     * 获取上级机构树
     *
     * @return
     */
    public List<ZNodes> getPreOrgTree();

    /**
     * 权限列表包含按钮
     *
     * @param orgId 组织Id
     * @param layer 显示层级
     * @return
     */
    public List<ZNodes> listAuthorized(String orgId, String layer);

    /**
     * 根据角色Id保存权限列表
     *
     * @param orgId 组织Id
     * @param auss 权限数组
     * @param layer 显示层级
     * @return
     */
    public void saveAuthorized(String orgId, String auss, String layer);

    /**
     * 删除机构
     *
     * @return
     */
    public int delOrg(Org o);

    /**
     * 根据机构ID获取对应的机构数据列表
     *
     * @param orgIds
     * @return
     */
    public List<Org> findOrgsById(List<String> orgIds);

    /**
     * 根据已有的领导部门ID查看子机构部门的ID
     *
     * @param orgIds
     * @return
     */
    public List<String> findSubSectorOrgsById(List<String> paramList);
}
