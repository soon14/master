package com.jy.service.impl.system.org;

import com.jy.entity.system.account.Account;
import com.jy.entity.system.org.AccountPosition;
import com.jy.entity.system.org.Org;
import com.jy.entity.system.org.PosOrg;
import com.jy.entity.system.org.Position;
import com.jy.entity.utils.ZNodes;
import com.jy.mybatis.Page;
import com.jy.repository.system.org.OrgDao;
import com.jy.repository.system.org.PositionDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.org.PositionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PositionService")
public class PositionServiceImp extends BaseServiceImp<Position> implements PositionService{

	@Autowired
	private PositionDao positionDao;

	@Autowired
	private OrgDao orgDao;

	@Override
	public List<ZNodes> getOrgAndPositionTree() {
		return positionDao.getOrgAndPositionTree();
	}

	@Override
	public List<ZNodes> getOrgRoleTree() {
		return positionDao.getOrgRoleTree();
	}

	@Override
	public List<ZNodes> getPreOrgTree() {
		return positionDao.getPreOrgTree();
	}

	@Override
	public void saveAccountPosition(String posId,String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] chk =ids.split(",");
			List aps=new ArrayList();
			Map map = new HashMap();
			for(String id:chk){
				aps.add(id);
			}
			map.put("list",aps);
			map.put("posId",posId);
			positionDao.insertAccountPosition(map);
		}
	}

	@Override
	public Page<Account> findArrangeAccByPage(Position o,Page<Account> page) {
		page.setResults(positionDao.findArrangeAccByPage(o,page));
		return page;
	}

	@Override
	public Page<Account> findPosByPage(Position o, Page<Account> page) {
		page.setResults(positionDao.findPosByPage(o,page));
		return page;
	}

	@Override
	public void deleteAccPosByLoginName(String loginName) {
		positionDao.deleteAccPosByLoginName(loginName);
	}

	@Override@Transactional
	public void deletePos(Position o) {
		positionDao.delete(o);
		positionDao.deleteAccPosByPosId(o.getId());
	}

	@Override
	public Page<Position> findAllPosByPage(Position o, Page<Position> page) {
		String orgId=o.getOrgId();
		StringBuffer orgIds=new StringBuffer();
		List<Org> orgs=orgDao.findAllOrg(orgId);
		// 设置父和子组织id
		orgIds.append("'"+orgId+"'"+",");
		dealOrg(orgs,orgIds);
		orgIds.deleteCharAt(orgIds.length()-1);
		o.setAllOrgIds(orgIds.toString());
		List<Position> poss=positionDao.findAllPosByPage(o, page);
		page.setResults(poss);
		return page;
	}

	private void dealOrg(List<Org> orgChilds,StringBuffer orgIds){
		// 递归处理所有机构
		for(Org orgChild:orgChilds){
			List<Org> orgs=orgChild.getOrgs();
			for(Org o:orgs){orgIds.append("'"+o.getId()+"'"+",");}
			// 递归
			dealOrg(orgs,orgIds);
		}
	}

	@Override
	public void savePosOrg(String posId, String orgs) {
		if(posId!=null&&posId!=""){
			positionDao.deletePos_Org(posId);
		}
		String[] orgIds=orgs.split(",");
		List<PosOrg> list = new ArrayList();
		for (int i = 0; i < orgIds.length; i++)
		{
			PosOrg posOrg = new PosOrg();
			posOrg.setPosId(posId);
			posOrg.setOrgId(orgIds[i]);
			list.add(posOrg);
		}
		positionDao.insertPos_Org(list);
	}

	@Override
	public List<Org> findDataAuthority(String userId) {
		List<Org> list = new ArrayList<Org>();
		List<Org> orgs = positionDao.findOrgByUserId(userId);
		if(userId.equals("2")){
			return positionDao.findAllAccountId();
		}else {
			for (int i = 0; i < orgs.size(); i++) {
				List<Org> users = positionDao.findDataAuthority(userId,orgs.get(i).getOrgId());

				for (int j = 0; j < users.size(); j++) {
					list.add(users.get(j));
				}
			}
			return  list;
		}
	}

	@Override
	public List<ZNodes> getOrgTreesByPos(String id) {
		return positionDao.getOrgTreesByPos(id);
	}
}
