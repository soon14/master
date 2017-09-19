package com.jy.repository.system.channels;


import com.jy.entity.system.channels.ChannelOperationLog;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@JYBatis
public interface ChannelOperationLogDao extends BaseDao<ChannelOperationLog> {

  public List<ChannelOperationLog> findByPage(@Param("param") ChannelOperationLog form);
  public List<ChannelOperationLog> findFuncNo();
  public Integer insertChannelOperationLog(ChannelOperationLog form);


}
