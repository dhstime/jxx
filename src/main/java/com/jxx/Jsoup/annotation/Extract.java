package com.jxx.Jsoup.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  抽取爬取结果赋值给字段
 * @author strange
 * @date $
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extract {

    String cssQuery() default "";

    /**
    * 获取值得方式
     * 1 标识获取text
     * 2 标识获取html
    * @Author:strange
    * @Date:12:56 2020-03-01
    */
    int contentType() default  1;

    /**
    *获取属性 ,不为空时,值从这个属性中获取
    * @Author:strange
    * @Date:13:02 2020-03-01
    */
    String attr() default "";


}
