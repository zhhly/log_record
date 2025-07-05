package com.zh.log.annotation;

/**
 * <p>
 *  日志注解，用于记录日志
 * </p>
 *
 * @author zh
 * @date 2025-06-19 21:14
 */
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogs {
    //操作名称
    String operation() default "";
    //操作类型
    String type() default "INFO";
}
