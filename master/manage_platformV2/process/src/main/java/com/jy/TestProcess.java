package com.jy;

import com.jy.entity.system.channels.Merchant;
import com.jy.service.system.channels.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZQY on 2017/3/24.
 */
@Service("testProcess")
public class TestProcess {
    @Autowired
    private MerchantService merchantService;
    public void test(){
        List<Merchant> list = new ArrayList<Merchant>();
        try {
            list = merchantService.findByParentMerchantId(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
