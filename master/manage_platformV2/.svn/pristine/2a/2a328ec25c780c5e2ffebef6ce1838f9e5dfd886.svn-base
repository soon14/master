package com.jy.repository.system.log;

import java.util.List;

import com.jy.mybatis.Page;
import org.apache.ibatis.annotations.Param;


import com.jy.entity.system.log.LoginLog;
import com.jy.repository.base.JYBatis;

@JYBatis
public interface LoginLogDao {

	public List<LoginLog> findByPage(@Param("param") LoginLog o, Page<LoginLog> page);
	
	public void insert(LoginLog o);
	
	public void deleteBatch(List<LoginLog> os);
}
