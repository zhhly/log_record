package com.zh.log.aop;

import com.zh.log.annotation.SysLogs;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.zh.log.utils.IpUtils.getIpAddr;

/**
 * <p>
 * SysAspect类
 * 切面类：用于日志捕获
 * </p>
 *
 * @author zh
 * @date 2025-06-19 21:31
 */
@Component
@Aspect
public class SysAspect {
    private static final Logger log = LoggerFactory.getLogger(SysAspect.class);
    // 定义捕获那个注解，这里捕获SysLogs注解
    @Pointcut("@annotation(com.zh.log.annotation.SysLogs)")
    private void pointcut() {
    }

    // 使用环绕通知，捕获
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取用户名

        // 获取session或者token，解析出用户名

        // 获取被增强类和方法的信息
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        // 获取被增强的方法对象
        Method method = methodSignature.getMethod();

        // 打印获取到的方法是什么？getById？
        SysLogs logAnnotation = method.getAnnotation(SysLogs.class);
        log.info("注解参数1：{},注解参数2：{}", logAnnotation.operation(),logAnnotation.type());
        log.info("请求方法：{}", method.getName());

        // 获取到请求对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sras = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sras.getRequest();

        // 访问的url
        String url = request.getRequestURI().toString();
        //System.out.println(url);
        log.info("url：{}", url);
        // 请求方式
        String methodName = request.getMethod();
        //System.out.println(methodName);
        log.info("请求方式：{}", methodName);
        // 操作时间
        // 创建日期格式化对象（线程不安全，单线程可用）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化当前时间
        String formattedDate = sdf.format(new Date());
        //System.out.println(formattedDate);
        log.info("时间：{}", formattedDate);
        // 登录IP
        String ipaddr = getIpAddr(request);
        //System.out.println(ipaddr);
        log.info("登录IP：{}", ipaddr);

        return joinPoint.proceed();
    }

}
