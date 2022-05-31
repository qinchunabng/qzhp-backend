package com.qzhp.common;

/**
 * 用户状态
 *
 * @author qcb
 * @date 2022/05/28 13:41.
 */
public enum UserStatus {
    ENABLE(1,"启用"),
    DISABLE(0,"禁用");

    UserStatus(int code, String desc) {
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
