package com.jy.controller.system.account;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jy.entity.system.org.Position;
//import javafx.geometry.Pos;

/**
 * 账户信息表的辅助类 Created by Administrator on 2017-02-08.
 */
public class AccountHelper {


    /**
	 * 获取当前登录账户的所有所在部门ID
	 */
    public static List<String> getOrgs(List<Position> positions) {
        if (positions == null || positions.isEmpty() || positions.size() == 0) return null;
        List<String> orgs = new ArrayList<String>();
        Iterator<Position> iterators = positions.iterator();
        Position position = new Position();
        while (iterators.hasNext()) {
            position = (Position) iterators.next();
            if (!orgs.contains(position.getOrgId())) {
                orgs.add(position.getOrgId());
            }
        }
        return orgs;
    }


    /**
	 * 获取普通职位的部门ID
	 *
	 * @param positions
	 * @return
	 */
    public static List<String> getOrdinaryOrgId(List<Position> positions) {
        if (positions == null || positions.isEmpty() || positions.size() == 0) return null;
        List<String> poss = new ArrayList<String>();
        Iterator<Position> iterators = positions.iterator();
        Position position = new Position();
        while (iterators.hasNext()) {
            position = (Position) iterators.next();
            if (position.getPosLevel() != 1 && !poss.contains(position.getOrgId())) {
                poss.add(position.getOrgId());
            }
        }
        return poss.size() == 0 || poss.isEmpty() ? null : poss;
    }

    /**
	 * 获取领导职位的部门ID
	 *
	 * @param positions
	 * @return
	 */
	public static List<String> getLeaderOrgId(List<Position> positions) {
		if ((positions == null) || (positions.isEmpty()) || (positions.size() == 0)) return null;
		List poss = new ArrayList();
		Iterator iterators = positions.iterator();
		Position position = new Position();
		while (iterators.hasNext()) {
			position = (Position) iterators.next();
			if ((position.getPosLevel() == 1) && (!poss.contains(position.getOrgId()))) {
				poss.add(position.getOrgId());
        }
		}
		return (poss.size() == 0) || (poss.isEmpty()) ? null : poss;
    }

    /**
	 * 将两个不同部门ID的list进行合并
	 *
	 * @param orgId1
	 * @param orgId2
	 * @return
	 */
    public List<String> mergeId(List<String> orgId1, List<String> orgId2) {
        if (judgeNull(orgId1) && judgeNull(orgId2)) {
            return null;
        } else if (!judgeNull(orgId1) && judgeNull(orgId2)) {
            return orgId1;
        } else if (judgeNull(orgId1) && !judgeNull(orgId2)) {
            return orgId2;
        } else {
            List<String> list = orgId1;
            for (String id : orgId2) {
                if (!list.contains(id)) {
                    list.add(id);
                }
            }
            return list;
        }
    }

    public boolean judgeNull(List<String> list) {
        if (list == null || list.isEmpty() || list.size() == 0) {
            return true;
        }
        return false;
    }


}
