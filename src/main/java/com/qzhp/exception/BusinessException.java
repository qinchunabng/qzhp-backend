package com.qzhp.exception;

import com.qzhp.common.ResultCode;

/**
 * 业务异常
 *
 * @author qcb
 * @date 2022/05/28 13:46.
 */
public class BusinessException extends RuntimeException{

    private Integer code;

    private String msg;

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        this(ResultCode.FAILED.getCode(), msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
