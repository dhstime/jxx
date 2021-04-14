package com.jxx.crawler.Jsoup.utils;

import com.jxx.crawler.Jsoup.annotation.Extract;
import com.jxx.crawler.Jsoup.annotation.JsoupDocument;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
public class CrawlerUtils {


    /**
    *获取连接
    * @Author:strange
    * @Date:13:05 2020-03-01
    */

    public static Connection creatConnect(String url){
        Connection connect = Jsoup.connect(url);
        //设置请求头
        connect.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        connect.header("Accept-Encoding","gzip, deflate");
        connect.header("Accept-Language","zh-CN,zh;q=0.9");
        connect.header("Cache-Control","max-age=0");
        connect.header("Connection","keep-alive");
        connect.header("Host","www.dhsstrive.com");
        connect.header("Upgrade-Insecure-Requests","1");
        connect.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36");
        connect.ignoreHttpErrors(true);
        //执行获取页面
        return connect;
    }
    /**
    *执行爬虫程序
    * @Author:strange
    * @Date:16:31 2020-03-01
    */
    public static Object execute(Class<?> clazz ,String url,Object obj) throws Exception{
        //获取JsoupDocument
        JsoupDocument jsoupDocument = clazz.getAnnotation(JsoupDocument.class);
        if(jsoupDocument == null){
            throw new RuntimeException("该实体类不是爬虫实体类");
        }
        String targetUrl = jsoupDocument.targetUrl();
        //如果设置了targetUrl ,那么爬取的目标url以targetUrl为主
        if(StringUtils.isNotBlank(targetUrl)){
            url = targetUrl;
        }
        if(StringUtils.isBlank(url)){
            throw new RuntimeException("未指定爬取目标url");
        }
        Connection connection = creatConnect(url);
        //获取爬取方式
        Connection.Method method = jsoupDocument.method();
        //获取document
        Element bodyElement = connection.method(method).execute().parse().body();
        //获取类上的css选择器
        String cssQuery = jsoupDocument.cssQuery();
        //获取改类所有字段
        Field[] declaredFields = clazz.getDeclaredFields();

        //判断返回对象的类型
        if(obj instanceof List ){

            //获取目标列表
            Elements entityElements = bodyElement.select(cssQuery);
            return getList(clazz, (List) obj, entityElements, declaredFields);

        }else {
            if(StringUtils.isBlank(cssQuery)){
                return setFieldObj(obj,declaredFields,bodyElement);
            }
            //获取目标列表
            Elements entityElements = bodyElement.select(cssQuery);
            if(entityElements.size() == 0){
                return obj;
            }
            Element element = entityElements.get(0);
            return   setFieldObj(obj,declaredFields,element);
        }
    }


    private static List getList(Class<?> clas, List obj, Elements entityElements, Field[] declaredFields) throws Exception {
        List list = obj;
        for (Element entityElement : entityElements) {
            Object object = clas.newInstance();
            object = setFieldObj(object,declaredFields,entityElement);

            list.add(object);
        }
        return list;
    }

    private static Object setFieldObj(Object object, Field[] declaredFields, Element entityElement) throws Exception {
        for (Field field : declaredFields) {
            field.setAccessible(true);
            //获取子页面属性
            JsoupDocument jsoupDocument = field.getAnnotation(JsoupDocument.class);
            if(jsoupDocument != null){
                childObj(object, field, jsoupDocument);
                continue;
            }
            //获取Extract注解
            Extract extract = field.getAnnotation(Extract.class);
            if(extract != null){
                //不为空抽取字段
                String fieldCssQuery = extract.cssQuery();
                Elements fieldElements = entityElement.select(fieldCssQuery);
                String attr = extract.attr();
                //判断该字段属性的值是否要从属性中获取
                if(StringUtils.isBlank(attr)){
                    int contentType = extract.contentType();
                    if( contentType == 1){
                        //执行text
                        String text = fieldElements.text();
                        field.set(object,text);
                    }else if(contentType == 2){
                        String html = fieldElements.html();

                        field.set(object,html);
                    }else {
                        //如果存在1和2以外的值,抛异常
                        throw new RuntimeException("字段"+field.getName()+"contentType属性类型只能是1或者2");
                    }
                }else{
                    //获取属性值,赋值给字段
                    String attrValue = fieldElements.attr(attr);
                    field.set(object,attrValue);
                }
            }
        }
        return object;
    }

    private static void childObj(Object object, Field field, JsoupDocument jsoupDocument) throws Exception {
        String hrefAttr = jsoupDocument.hrefAttr();
        //获取子页面实体类字节码对象
        Class<?> type = field.getType();
        Object childObj = type.newInstance();
        //获取子页面url
        if(StringUtils.isBlank(hrefAttr)){
            throw new RuntimeException("指定字段上JsoupDocument注解的hreAttr字段为空");
        }
        //获取主页面对象
        Class<?> objectClass = object.getClass();
        Field hrefField = objectClass.getDeclaredField(hrefAttr);
        //暴力破解
        hrefField.setAccessible(true);
        //获取主页面对象hrefAttr指定字段
        String hrefUrl = (String) hrefField.get(object);
        Object childValue = execute(type, hrefUrl, childObj);
        field.set(object,childValue);
    }
    /**
     *将内容写入指定文档
     * @Author:strange
     * @Date:17:22 2020-02-29
     */
    public static void write(String body,String fileName) throws Exception {
        File dir = new File("/Users/dhs/Documents/jsoup");
        File file = new File(dir,fileName);

        RandomAccessFile raf = new RandomAccessFile(file,"rw");
//        String file="abc/pw.txt";
        FileOutputStream fos=new FileOutputStream(file);
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        OutputStreamWriter osw =new OutputStreamWriter(bos, "utf-8");
        //PrintWriter：是高级流，扩展了println方法和print
        // true 自动清理缓存功能，每个println方法之后会执行一个flush方法
        PrintWriter out=new PrintWriter(osw, true);
        out.println(body);
        out.close();

    }

}



