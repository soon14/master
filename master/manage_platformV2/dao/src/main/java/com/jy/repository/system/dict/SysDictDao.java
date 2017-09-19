package com.jy.repository.system.dict;

import com.jy.entity.system.dict.SysDict;
import com.jy.repository.base.BaseDao;
import com.jy.repository.base.JYBatis;
import org.apache.ibatis.annotations.Param;

/**
 * 系统字典数据层
 */
@JYBatis
public interface SysDictDao extends BaseDao<SysDict>{
    /**
     * 查询数据字典得到生成和下载报表的路径
     * @return
     */
    public SysDict findFilePath(@Param("key") String key);
}
