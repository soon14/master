package com.jy.common.mybatis;

import com.jy.mybatis.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @descript : 自定义返回当前页数据
 * Created by Dingj on 2017-02-24.
 */
public class PageCalculation {
    /**
     * 重新自定义获取当前页数据的入口
     *
     * @param page
     * @return
     */
    public static Page getPageCalculation(Page page) {
        //检查参数
        List list = page.getResults();
        if (list == null || list.isEmpty() || list.size() == 0) {
            return page;
        }
        int totalRecord = list.size();//总数量
        int pageSize = page.getPageSize();//每页数量
        int pageNum = page.getPageNum();//当前页
        int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        //设置获取数据的总数
        page.setTotalRecord(totalRecord);
        //设置总数页
        page.setTotalPage(totalPage);

        page.setResults(getCurrentPageData(pageNum, totalPage, pageSize, list, totalRecord));
        return page;
    }

    /**
     * 获取当前页数据
     *
     * @param pageNum
     * @param totalPage
     * @param pageSize
     * @param list
     * @param totalRecord
     * @return
     */
    private static List getCurrentPageData(int pageNum, int totalPage, int pageSize, List list, int totalRecord) {
        List temp = new ArrayList<>();
        int startIndex = (pageNum - 1) * pageSize;//开始下标索引
        int endIndex = pageNum * pageSize;//截止下标索引
        int breakIndex = totalRecord - 1;
        for (int i = startIndex; i < endIndex; i++) {
            if (i > breakIndex) break;
            temp.add(list.get(i));
        }
        return temp;
    }
}
