package com.jy.service.impl.system.channels;


import com.jy.entity.system.channels.ChannelOperationLog;
import com.jy.repository.system.channels.ChannelOperationLogDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.channels.ChannelOperationLogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ChannelOperationLogService")
public class ChannelOperationLogServiceImpl extends BaseServiceImp<ChannelOperationLog> implements ChannelOperationLogService {
    @Autowired
    private ChannelOperationLogDao dao;
    public List<ChannelOperationLog> findByPage(ChannelOperationLog form){
        return dao.findByPage(form);
    }

    @Override
    public List<ChannelOperationLog> findFuncNo() {
        return dao.findFuncNo();
    }

    @Override
    public Integer insertChannelOperationLog(ChannelOperationLog form) {
        return dao.insertChannelOperationLog(form);
    }


}


