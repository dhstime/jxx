package com.jxx.crawler.Jsoup.annotation;

import org.jsoup.Connection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  标识爬虫该类用来接收爬虫爬取的结果
 * @author strange
 * @date $
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsoupDocument {
    /**
    *目标网站的域名
    * @Author:strange
    * @Date:12:35 2020-03-01
    */
    String domain();
    
    /**
    * 目标url
     * 如果设置了targetUrl ,那么爬取的目标url以targetUrl为主
    * @Author:strange
    * @Date:12:35 2020-03-01
    */
    String targetUrl() default "";

    /**
    *  CSS选择器
    * @Author:strange
    * @Date:12:37 2020-03-01
    */
    String cssQuery() default "";
    /**
    *  请求方式
    * @Author:strange
    * @Date:12:54 2020-03-01
    */
    Connection.Method method() default  Connection.Method.GET;

    /**
    *获取对象中哪个属性
    * @Author:strange
    * @Date:08:38 2020-03-02
    */
    String hrefAttr() default "";
    
}
