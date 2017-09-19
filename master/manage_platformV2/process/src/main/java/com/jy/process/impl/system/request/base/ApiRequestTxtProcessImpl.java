package com.jy.process.impl.system.request.base;

import com.jy.common.enumerate.TxtDataEnum;
import com.jy.common.utils.DateUtils;
import com.jy.common.utils.FtpUtil;
import com.jy.entity.system.finance.reconciliation.jobTask.JobTaskStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.net.ftp.FtpClient;

import java.util.Map;

/**
 * Created by lijunke on 2017/6/16.
 * 模板类
 */
@Component("apiRequestTxtProcess")
public abstract class ApiRequestTxtProcessImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestTxtProcessImpl.class);


    @Value("${ftpAddress}")
    private String ftpAddress;          //ftp登录信息
    @Value("${ftpDownLoad}")
    private String ftpDownLoad;         //ftp文件的路径
    @Value("${localhostAddr}")
    private String localhostAdd;       //下载到本地的路径

    public void ftpCommonsFunction(String ftpFileName, String currentDate) throws Exception {
        String[] ftpAddr = ftpAddress.split("/");   //ip,端口、账户、密码
        String ftpPath = stringBuffer(ftpDownLoad, currentDate, "/");
        String ftpFileNames = stringBuffer(ftpFileName, currentDate, "_");
        byFTPData(ftpAddr[0], ftpAddr[1], ftpAddr[2], ftpAddr[3], ftpPath, ftpFileNames, localhostAdd, currentDate);
    }


    /**
     * 登录ffp、获取ftp文件、生成文件、返回文件地址
     *
     * @param req
     * @return
     */
    public void byFTPData(String... req) throws Exception {
        FtpClient ftpClient = FtpUtil.connectFTP(req[0], Integer.parseInt(req[1]), req[2], req[3]);
        int lineCount = 0;
        int storageNumber = 0;
        String description;
        if (ftpClient.isLoggedIn()) {
            LOGGER.info("Logon TFP Successful!");
            Map<String, String> stringMap = FtpUtil.downloadAndChekData(req[4], req[5], req[6], ftpClient);
            if (null != stringMap) {
                String filePath = stringMap.get("path");
                if (null != filePath) {
                    LOGGER.info("返回文件ok文件的地址为:{}",filePath);
                    lineCount = Integer.parseInt(stringMap.get("count"));
                    LOGGER.info("取得文本数据总条数为:{}",lineCount);
                    storageNumber = saveFTPData(filePath, req[7]);
                    LOGGER.info("数据库保存的条数为:{}",storageNumber);
                    description = TxtDataEnum.IN_GENERATED.getViewName();
                } else {
                    String notBakFile = stringMap.get("notErrFile");
                    if (null != notBakFile) {
                        description = TxtDataEnum.GENERATED_ERR.getViewName();
                    } else {
                        LOGGER.info("File Not generated!");
                        description = TxtDataEnum.NOT_GENERATED_BAK.getViewName();
                    }
                }
            } else {
                //map 返回为空
                description = TxtDataEnum.NOT_GENERATED_BAK.getViewName();
            }
        } else {
            LOGGER.info("Logon TFP failed!");
            description = TxtDataEnum.LOGIN_FAIL.getViewName();
        }
        fullJobObject(lineCount, storageNumber, description, req[7]);
    }


    public void fullJobObject(int txtLines, int storageNumber, String description,String sycDate) {
        JobTaskStatistics jobTaskStatistics = new JobTaskStatistics();
        jobTaskStatistics.setJobName(this.byJobName());
        jobTaskStatistics.setDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        jobTaskStatistics.setDescription(description);
        jobTaskStatistics.setSycDate(sycDate);
        jobTaskStatistics.setStorageNumber(storageNumber);
        jobTaskStatistics.setInterfaceNumber(txtLines);
        this.saveJobLog(jobTaskStatistics);
    }


    public static String stringBuffer(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    public abstract String byJobName();

    public abstract void saveJobLog(JobTaskStatistics jobTaskStatistics);

    public abstract int saveFTPData(String filePath, String date);
}
