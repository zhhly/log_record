package com.zh.log.exception;

import com.zh.log.annotation.SysLogs;
import com.zh.log.response.Resp;
import jakarta.servlet.ServletException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * <p>
 * GlobalExceptionHandler类: 全局统一异常处理类
 * </p>
 *
 * @author zh
 * @date 2025-06-26 22:51
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // 自定义网络错误状态码
    private static final int NETWORK_ERROR_CODE = 503;

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    @SysLogs
    public <T>Resp<T> exceptionHandler(Exception e) {
        // 1.判断异常是否是自定义的异常
        if(e instanceof AppException){
            AppException appException = (AppException)e;
            return Resp.error(appException.getCode(), appException.getMsg());
        }
        // 2. 处理网络相关异常
        if (isNetworkException(e)) {
            return Resp.error(NETWORK_ERROR_CODE, "网络连接异常: " + e.getMessage());
        }

        // 3. 处理客户端错误（4xx）
        if (e instanceof ServletException) {
            return Resp.error(400, "客户端请求错误: " + e.getMessage());
        }

        // 4. 默认服务器内部错误
        return Resp.error(500, "服务器内部错误: " + e.getClass().getSimpleName());
        // return Resp.error(500, "服务器内部错误！");
    }
    // 判断是否为网络异常
    private boolean isNetworkException(Throwable e) {
        // 检查异常链中的所有原因
        while (e != null) {
            if (e instanceof ConnectException ||
                    e instanceof SocketTimeoutException ||
                    e instanceof java.net.UnknownHostException ||
                    e instanceof org.springframework.web.client.ResourceAccessException) {
                return true;
            }
            // 获取根本原因
            e = e.getCause();
        }
        return false;
    }

}
