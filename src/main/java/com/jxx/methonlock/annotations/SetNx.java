package com.jxx.methonlock.annotations;

import java.lang.annotation.*;

/**
* aop注解
* @Author:strange
* @Date:17:24 2019-12-29
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetNx {
    String field() default "";
    int time() default 0;
    Class className();


}
