package com.jy.entity.system.org;

import com.jy.entity.base.BaseEntity;

/**
 * Created by shixi on 2017/5/3.
 */
public class RoleOrg extends BaseEntity
{

    private String orgId;
    private String roleId;

    public String getOrgId()
    {
        return orgId;
    }

    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }
}
