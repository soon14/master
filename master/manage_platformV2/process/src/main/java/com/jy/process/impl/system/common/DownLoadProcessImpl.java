package com.jy.process.impl.system.common;

import com.jy.common.utils.DateUtils;
import com.jy.common.utils.DownloadUtil;
import com.jy.process.system.common.DownLoadProcess;
import com.jy.service.system.dict.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
@Service("DownLoadProcess")
public class DownLoadProcessImpl implements DownLoadProcess {

    @Value("${download.suffix}")
    private String suffix;
    @Autowired
    private SysDictService sysDictService;

    @Override
   public Object downLoad(String date, String name, String url){
        String month= DateUtils.getPreDate(0).substring(0,7);
        if(!"".equals(date)){
            month=date.substring(0,7);
        }
        List<Object> list = new ArrayList<Object>();
        try {
            String path=sysDictService.byValue("filePath");
            list = DownloadUtil.download(month,path, name, suffix);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, name+month+"文件尚未生成", null, JOptionPane.ERROR_MESSAGE);

            return url;
        }
        return new ResponseEntity<byte[]>((byte[])list.get(0), (MultiValueMap<String, String>)list.get(1), HttpStatus.OK);
    }
}
