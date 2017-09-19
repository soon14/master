package com.jy.common.enumerate;

/**
 * Created by lijunke on 2017/6/16.
 */
public enum TxtDataEnum {
    LOGIN_FAIL(1, "登录FTP失败"),
    NOT_FILE(2, "未在FTP服务器找到文件"),
    NOT_GENERATED_BAK(3, "本地服务器未生成bak文件"),
    GENERATED_ERR(4, "本地服务器生成err文件"),
    IN_GENERATED(6, "登录FTP成功,并生成文件");


    TxtDataEnum(int value, String viewName) {
        this.value = value;
        this.viewName = viewName;
    }


    /**
     * 枚举值
     */
    private int value;

    /**
     * 枚举名称
     */
    private String viewName;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }


    /**
     * 根据value取得valueName
     *
     * @param value
     * @return
     */
    public static String getName(int value) {
        for (TxtDataEnum type : TxtDataEnum.values()) {
            if (type.getValue() == value) {
                return type.getViewName();
            }
        }
        return "";
    }
}
