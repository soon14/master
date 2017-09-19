package com.jy.entity.task.cpSystem;

import com.jy.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by Dingj on 2017-01-15.
 */
@Alias("JobExcuteProcess")
public class JobExcuteProcess extends BaseEntity {
    private String id;//表id
    private String scheduleClass;//类名称
    private Date jobDate;//定时任务的执行日期
    private int type;//任务执行类型(1:用户增量信息 2:用户资金交易明细读取 3:用户资金交易明细读取 4:用户购彩交易明细读取 5:用户兑奖明细读取 6:用户购彩出票销售明细 )
    private int excuteStatus;//执行状态(0:无数据可执行 1:执行中 2:执行成功  3:执行失败)
    private String createTime;// 创建时间
    private int isValid;//是否有效(1:有效  0：无效)

    @Override
    public String toString() {
        return "JobExcuteProcess{" +
                "id='" + id + '\'' +
                ", scheduleClass='" + scheduleClass + '\'' +
                ", jobDate=" + jobDate +
                ", type=" + type +
                ", excuteStatus=" + excuteStatus +
                ", createTime='" + createTime + '\'' +
                ", isValid=" + isValid +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleClass() {
        return scheduleClass;
    }

    public void setScheduleClass(String scheduleClass) {
        this.scheduleClass = scheduleClass;
    }

    public Date getJobDate() {
        return jobDate;
    }

    public void setJobDate(Date jobDate) {
        this.jobDate = jobDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getExcuteStatus() {
        return excuteStatus;
    }

    public void setExcuteStatus(int excuteStatus) {
        this.excuteStatus = excuteStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }
}
