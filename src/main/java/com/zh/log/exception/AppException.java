package com.zh.log.exception;

/**
 * <p>
 * AppException类: 自定义异常，运行时异常
 * </p>
 *
 * @author zh
 * @date 2025-06-26 23:06
 */
public class AppException extends RuntimeException {

    private int code;
    private String msg;

    public AppException(AppExceptionCodeMsg appExceptionCodeMsg) {
        super();
        this.code = appExceptionCodeMsg.getCode();
        this.msg = appExceptionCodeMsg.getMsg();
    }


    public AppException(int code, String msg) {
        super();
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
