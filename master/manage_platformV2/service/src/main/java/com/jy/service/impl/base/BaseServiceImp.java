package com.jy.service.impl.base;

import java.util.List;

import com.jy.entity.system.dict.SysDict;
import com.jy.mybatis.Page;
import com.jy.repository.system.dict.SysDictDao;
import com.jy.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jy.repository.base.BaseDao;


public class BaseServiceImp<T> implements BaseService<T>{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected BaseDao<T> baseDao;

	@Autowired
	private SysDictDao sysDictDao;

	@Override
	public void insert(T o) {
		baseDao.insert(o);
	}

	@Override
	public void delete(T o) {
		baseDao.delete(o);
	}

	@Override
	public void deleteBatch(List<T> os){
		baseDao.deleteBatch(os);
	}

	@Override
	public void update(T o) {
		baseDao.update(o);
	}

	@Override
	public List<T> find(T o) {
		return baseDao.find(o);
	}

	@Override
	public Page<T> findByPage(T o, Page<T> page) {
		page.setResults(baseDao.findByPage(o, page));
		return page;
	}

	@Override
	public int count(T o) {
		return baseDao.count(o);
	}

	public String byValue(String key) {
		SysDict sysDict = new SysDict();
		sysDict.setParamKey(key);
		String falseStr = sysDictDao.find(sysDict).get(0).getParamValue();
		return falseStr;
	}

}
