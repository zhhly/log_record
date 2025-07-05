package com.zh.log.response;

import com.zh.log.exception.AppExceptionCodeMsg;

/**
 * <p>
 * Resp类: 统一返回结果类
 * </p>
 *
 * @author zh
 * @date 2025-06-26 22:39
 */
public class Resp<T> {
    private int code = 200;
    private String msg = "success";
    private T data;

    public Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Resp success(T data){
        return new Resp(200, "success", data);
    }

    public static <T> Resp success(String msg, T data){
        return new Resp(200, msg, data);
    }

    // 异常错误返回
    public static <T> Resp error(AppExceptionCodeMsg appExceptionCodeMsg){
        return new Resp(appExceptionCodeMsg.getCode(), appExceptionCodeMsg.getMsg(), null);
    }

    public static <T> Resp error(int code,String msg){
        return new Resp(code, msg,null);
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public T getData() {
        return data;
    }
}
