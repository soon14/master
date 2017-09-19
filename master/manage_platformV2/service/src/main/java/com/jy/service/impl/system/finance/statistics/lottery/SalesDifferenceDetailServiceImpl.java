package com.jy.service.impl.system.finance.statistics.lottery;

import com.jy.mybatis.Page;
import com.jy.entity.system.finance.reconciliation.lottery.SalesDifferencesDetail;
import com.jy.repository.system.finance.reconciliation.lottery.SalesDifferencesDetailDao;
import com.jy.service.impl.base.BaseServiceImp;
import com.jy.service.system.finance.statistics.lottery.SalesDifferenceDetailService;
import com.jy.vo.system.reconciliation.lottery.SalesDifferencesDetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/13.
 */
@Service("SalesDifferenceDetailService")
public class SalesDifferenceDetailServiceImpl extends BaseServiceImp<SalesDifferencesDetail> implements SalesDifferenceDetailService {

    @Autowired
    private SalesDifferencesDetailDao dao;


    @Override
    public  Page<SalesDifferencesDetailVo> findSalesDifferencesDetailByPage(SalesDifferencesDetail detail, Page<SalesDifferencesDetailVo> pageVo){
       Page<SalesDifferencesDetail> page=new Page<>();
        page.setPageNum(pageVo.getPageNum());
        page.setPageSize(pageVo.getPageSize());
        List<SalesDifferencesDetail> detailList=dao.findDetailByDateAndType(detail,page);
        page.setResults(detailList);

        List<SalesDifferencesDetailVo> voList=new ArrayList<>();
        if(detailList==null){
            return new Page<SalesDifferencesDetailVo>();
        }else{
            pageVo.setPageSize(page.getPageSize());
            pageVo.setTotalPage(page.getTotalPage());
            pageVo.setTotalRecord(page.getTotalRecord());
            for(SalesDifferencesDetail diffDetail:detailList){
                SalesDifferencesDetailVo vo=new SalesDifferencesDetailVo();
                BeanUtils.copyProperties(diffDetail,vo);
                voList.add(vo);
            }
            pageVo.setResults(voList);
            return pageVo;
        }

    }

}
