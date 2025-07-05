package com.zh.log.exception;

/**
 * @author: 朱鹏
 * @since: 2025-06-26 H O U R : {HOUR}:HOUR:{MINUTE}
 * @description: 枚举类：错误信息提示【业务异常的枚举】
 */

public enum AppExceptionCodeMsg {

    INVALID_CODE(10000, "验证码错误"),
    USERNAME_NOT_EXISTS(10001, "用户名不存在");
    ;

    private int code;
    private String msg;

    AppExceptionCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
