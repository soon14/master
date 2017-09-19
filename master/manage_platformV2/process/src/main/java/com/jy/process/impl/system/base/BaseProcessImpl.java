package com.jy.process.impl.system.base;

import com.jy.common.ajax.AjaxRes;
import com.jy.common.utils.DownloadUtil;
import com.jy.process.system.base.BaseProcess;
import com.jy.service.system.dict.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.swing.*;
import java.util.List;

/**
 * Created by lijunke on 2017/4/20.
 */
@Service("baseProcess")
public class BaseProcessImpl implements BaseProcess<Object> {

    @Value("${download.suffix}")
    private String suffix;

    @Autowired
    private SysDictService sysDictService;

    @Override
    public Object getDownLoad(String date, String pagePaht, String fileName) {
        AjaxRes ar = new AjaxRes();
        List<Object> list;
        try {
            list = DownloadUtil.download(date, this.sysDictService.byValue("filePath"), fileName, suffix);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "该日期对账文件尚未生成", null, JOptionPane.ERROR_MESSAGE);
            ar.setFailMsg("该日期对账文件尚未生成");
            return pagePaht;
        }
        return new ResponseEntity<byte[]>((byte[]) list.get(0), (MultiValueMap<String, String>) list.get(1), HttpStatus.OK);
    }

}
