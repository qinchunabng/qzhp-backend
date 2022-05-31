package com.qzhp.common;

/**
 * description
 *
 * @author qcb
 * @date 2022/05/29 15:43.
 */
public enum DelFlag {
    NOT_DELETED(0,"未删除"),
    DELETED(1,"已删除");

    DelFlag(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
