package com.jy.service.impl.system.dict;

import com.jy.repository.system.dict.SysDictDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.dict.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.entity.system.dict.SysDict;

@Service("SysDictService")
public class SysDictServiceImp extends BaseServiceImp<SysDict> implements SysDictService {

    @Autowired
    private SysDictDao sysDictDao;

    /**
     * @方法功能描述： 根据 key,返回value
     */
    public String byValue(String key) {
        SysDict sysDict1 = new SysDict();
        sysDict1.setParamKey(key);
        String falseStr = sysDictDao.find(sysDict1).get(0).getParamValue();
        return falseStr;
    }

    @Override
    public SysDict findFilePath(String key) {
        return sysDictDao.findFilePath(key);
    }
}
