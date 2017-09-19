package com.jy.repository.system.channels;

import com.jy.entity.system.channels.Merchant;
import com.jy.entity.system.channels.Prepayment;
import com.jy.entity.system.channels.PrepaymentExtend;
import com.jy.from.system.request.OutLineDataForm;
import com.jy.from.system.request.TerminalForm;
import com.jy.repository.base.JYBatis;

import java.util.List;
import java.util.Map;

/**
 * Created by ZQY on 2017/5/17.
 */
@JYBatis
public interface TerminalDao {
    public void addTerminal(TerminalForm terminalForm) throws Exception;
    public void delTerminal(TerminalForm terminalForm) throws Exception;
    public void updateTerminal(TerminalForm terminalForm) throws Exception;
    public List<TerminalForm> findTerminal(Map map) throws  Exception;
    public TerminalForm findTerminals(Map map) throws  Exception;
    public List<TerminalForm> findTerminalTotal(Map map) throws  Exception;

}
