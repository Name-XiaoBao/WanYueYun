package com.xiaobao.Util;

import java.lang.annotation.*;

/**
 * time()限制时间 单位：毫秒
 * count()允许请求的次数
 * @author 小暴
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequest {
    long time() default Integer.MAX_VALUE;
    int count() default Integer.MAX_VALUE;
}
