package com.jy.process.system.base;

/**
 * Created by lijunke on 2017/4/20.
 */
public interface BaseProcess<T> {

    /**
     * 下载
     * @param date
     * @param pagePaht
     * @param fileName
     * @return
     */
    Object getDownLoad(String date, String pagePaht, String fileName);
}
