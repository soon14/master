package com.jy.service.impl.system.account;

import com.jy.common.utils.security.AccountShiroUtil;
import com.jy.common.utils.security.CipherUtil;

import com.jy.entity.system.account.Account;
import com.jy.entity.system.org.AccountPosition;
import com.jy.entity.system.org.Org;
import com.jy.entity.system.org.Position;
import com.jy.entity.utils.ZNodes;
import com.jy.repository.system.account.AccountDao;
import com.jy.repository.system.org.PositionDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.account.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("AccountService")
public class AccountServiceImp extends BaseServiceImp<Account> implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PositionDao positionDao;

    @Override
    public Account findFormatByLoginName(String loginName) {
        Account a = null;
        try {
            a = accountDao.findFormatByLoginName(loginName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }


    public Account findAccountByIdAndName(String userId){
        return accountDao.findAccountByIdAndName(userId);
    }



    @Transactional
    public void setSetting(String skin) {
        Account currentAccount = AccountShiroUtil.getCurrentUser();
        currentAccount.setSkin(skin);
        accountDao.setSetting(currentAccount);
        AccountShiroUtil.getRealCurrentUser().setSkin(skin);
    }

    public Account getPerData() {
        Account pd = accountDao.getPerData(AccountShiroUtil.getCurrentUser().getAccountId());
        return pd;
    }

    public void setPerData(Account account) {
        Account cu = AccountShiroUtil.getRealCurrentUser();
        account.setAccountId(cu.getAccountId());
        account.setUpdateTime((new Date()));
        accountDao.setPerData(account);
        cu.setName(account.getName());
        cu.setEmail(account.getEmail());
    }

    public void setHeadpic(Account account) {
        Account cu = AccountShiroUtil.getRealCurrentUser();
        account.setAccountId(cu.getAccountId());
        account.setUpdateTime((new Date()));
        accountDao.setHeadpic(account);
        cu.setPicUrl(account.getPicUrl());
    }

    @Override
    public int insertAccount(Account o) throws Exception {
        int res = 0;
        String loginName = o.getLoginName();
        //查询数据库是否已经存在用户名
        if (StringUtils.isNotBlank(loginName) && (accountDao.findCountByLoginName(loginName,o.getAccountId()) == 0)) {
    //        String pwrs = CipherUtil.createRandomString(6);//随机密码,以后发邮箱
            String pwrs = "888888";//，默认密码
            o.setDescription(pwrs);//用户随机密码暂时保存在描述里
            String pwrsMD5 = CipherUtil.generatePassword(pwrs);//第一次加密md5，
            o.setSkin("skin-0");//默认皮肤
            String salt = CipherUtil.createSalt();
            o.setPassword(CipherUtil.createPwdEncrypt(loginName, pwrsMD5, salt));
            o.setSalt(salt);
            o.setCreateTime(new Date());

            String[] roleIds = o.getRoleId().split(",");
            for (int i = 0; i < roleIds.length; i++) {
                String roleId = roleIds[i].substring(4,roleIds[i].length());
                accountDao.insetAccountRole(o.getAccountId(),roleId);
            }
            accountDao.insert(o);
            res = 1;
        }
        return res;
    }

    @Override
    public void updateRoleId(Account o){
        accountDao.deleteRole(o.getAccountId());
        String[] roleIds = o.getRoleId().split(",");
        for (int i = 0; i < roleIds.length; i++) {
            String roleId = roleIds[i].substring(4,roleIds[i].length());
            accountDao.insetAccountRole(o.getAccountId(),roleId);
        }
    }

    @Override
    public List<ZNodes> getRoles() {
        return accountDao.getRoles();
    }

    @Override
    public List<ZNodes> getRolesByRoleId(String userId) {
        List<ZNodes> list = new ArrayList<ZNodes>();
        List<Org> roleId = positionDao.findRoleByUserId(userId);

        for (int i = 0; i < roleId.size(); i++) {
            List<ZNodes> users = positionDao.findRoleIdByUserId(userId,roleId.get(i).getRoleId());
            for (int j = 0; j < users.size(); j++) {

                if(list.size()==0){
                    list.add(users.get(j));
                }else{
                    boolean f=false;
                    for (int k = 0; k < list.size(); k++) {
                        if(list.get(k).getId().equals(users.get(j).getId())){
                            f=true;
                        }
                    }
                    if(!f){
                        list.add(users.get(j));
                    }
                }

            }
        }
        return list;
    }

    @Override
    public int sysResetPwd(Account o) {
        int res = 0;
        String pwd = o.getPassword();
        o.setUpdateTime(new Date());
        String accountId = o.getAccountId();
        if (StringUtils.isNotBlank(pwd) && StringUtils.isNotBlank(accountId)) {
            Account odata = accountDao.find(o).get(0);
            String loginName = odata.getLoginName();
            //随机密码,以后发邮箱
            String salt = CipherUtil.createSalt();
            String pwrsMD5 = CipherUtil.generatePassword(pwd);
            o.setPassword(CipherUtil.createPwdEncrypt(loginName, pwrsMD5, salt));
            o.setSalt(salt);
            accountDao.resetPwd(o);
            res = 1;
        } else {
            res = 2;
        }
        return res;
    }

    @Override
    public int preResetPwd(String opwd, String npwd, String qpwd) {
        int res = 0;
        String accountId = AccountShiroUtil.getRealCurrentUser().getAccountId();
        String loginName = AccountShiroUtil.getRealCurrentUser().getLoginName();
        if (StringUtils.isNotBlank(opwd) && StringUtils.isNotBlank(npwd)) {
            if (StringUtils.equals(npwd, qpwd)) {
                Account o = new Account();
                o.setAccountId(accountId);
                Account odata = accountDao.findFormatByLoginName(loginName);
                String oPwdEncrypt = CipherUtil.createPwdEncrypt(loginName, opwd.toUpperCase(), odata.getSalt());
                String odataPwdEncrypt = odata.getPassword();
                if (StringUtils.equals(oPwdEncrypt, odataPwdEncrypt)) {
                    String salt = CipherUtil.createSalt();
                    String pwrsMD5 = npwd.toUpperCase();
                    o.setPassword(CipherUtil.createPwdEncrypt(loginName, pwrsMD5, salt));
                    o.setSalt(salt);
                    accountDao.resetPwd(o);
                    res = 1;
                } else {
                    res = 2;//密码不正确
                }
            } else {
                res = 3;//两次密码不一致
            }
        }
        return res;
    }

    @Override
    @Transactional
    public void deleteAccount(Account o) {
        //事务删除
        accountDao.delete(o);
        //删除人员角色关系表
        accountDao.deleteRole(o.getAccountId());
    }

    @Override
    @Transactional
    public void deleteBatchAccount(String chks) {
        //事务删除
        if (StringUtils.isNotBlank(chks)) {
            String[] chk = chks.split(",");
            List<Account> list = new ArrayList<Account>();
            for (String s : chk) {
                Account sd = new Account();
                sd.setAccountId(s);
                list.add(sd);
            }
            accountDao.deleteBatch(list);
            positionDao.deleteBatchAccPosByAccId(list);
        }

    }

    /**
     * 获取个人所有的职位信息
     *
     * @return
     */
    @Override
    public List<Position> getPerPoss() {
        List<Position> positions = accountDao.getPoss(AccountShiroUtil.getCurrentUser().getAccountId());
        return positions;
    }

    /**
     * 根据部门ID 获取所有部门的的人员ID
     *
     * @param orgIds
     * @return
     */
    @Override
    public List<String> getAllAccountByOrgId(List<String> orgIds) {
        List<AccountPosition> list = accountDao.findAccountByOrgId(orgIds);
        if (list == null || list.isEmpty() || list.size() == 0) return null;
        List<String> accountIds = new ArrayList<>();
        for (AccountPosition ap : list) {
            accountIds.add(ap.getAccountId());
        }
        return accountIds;
    }

    @Override
    public List<Account> findRoleById(String accountId){
        return accountDao.findRoleById(accountId);
    }

}
