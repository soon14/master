package com.jy.service.impl.system.channels;

import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.Prepayment;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.from.system.request.OutLineDataForm;
import com.jy.from.system.request.TerminalForm;
import com.jy.repository.system.channels.OutLineDataDao;
import com.jy.repository.system.channels.TerminalDao;
import com.jy.service.system.channels.OutLineDataService;
import com.jy.service.system.channels.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ZQY on 2017/5/17.
 */
@Service
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    private TerminalDao terminalDao;


    @Override
    public void addTerminal(TerminalForm terminalForm) throws Exception {
        terminalDao.addTerminal(terminalForm);
    }
    @Override
    public void delTerminal(TerminalForm terminalForm) throws Exception {
        terminalDao.delTerminal(terminalForm);
    }
    @Override
    public void updateTerminal(TerminalForm terminalForm) throws Exception {
        terminalDao.updateTerminal(terminalForm);
    }

    @Override
    public List<TerminalForm> findTerminal(Map map) throws Exception {
        return terminalDao.findTerminal(map);
    }
    @Override
    public TerminalForm findTerminals(Map map) throws  Exception{
        return terminalDao.findTerminals(map);
    }

    @Override
    public List<TerminalForm> findTerminal(Map map,int a) throws Exception {
        return terminalDao.findTerminalTotal(map);
    }
}
