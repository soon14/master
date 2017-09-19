package com.jy.task.job.helper;

import com.jy.common.Const.UploadFileConst;
import com.jy.common.utils.DateUtils;
import com.jy.service.task.cpSystem.ReadDzFileJobService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户增量信息读取（只要增量信息）
 * Created by Dingj on 2017-01-15.
 */
public class ReadCpUserInfoHelper {

    private static StringBuffer fileName = new StringBuffer();
    @Autowired
    private ReadDzFileJobService service;


    //检查任务执行状态
    public static boolean checkJobExcute(String jobId,int type,String date) {
        //查询任务

        return true;
    }

    /**
     * 类方法的统一入口处
     */
    public static void excute() {
        if (fileName != null || fileName.length() != 0) {
            fileName.delete(0,fileName.length());
        }
        fileName.append(UploadFileConst.CP_T_USERINFO_PATH);
        fileName.append(DateUtils.getPreDate());
        fileName.append(UploadFileConst.CP_T_USERINFO_NAME);
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        /*File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }*/
    }
}
