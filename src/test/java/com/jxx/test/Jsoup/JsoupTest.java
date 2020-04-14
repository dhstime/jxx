package com.jxx.test.Jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;

public class JsoupTest {
    //设置访问路径
    public static String targetUrl = "http://www.dhsstrive.com/";
    /**
    *创建连接
    * @Author:strange
    * @Date:17:21 2020-02-29
    */
    public  Connection.Response creatConnect(String url) throws Exception{
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
        Connection.Response execute = connect.method(Connection.Method.GET).execute();
        return execute;
    }

    /**
    *执行
    * @Author:strange
    * @Date:17:21 2020-02-29
    */
//    @Test
    public void testJsoup1() throws Exception {

        Connection.Response response = creatConnect(targetUrl);
//        String body = response.body();
        //写入文件
//        write(body,"demo.txt");
        //获取元素
        getElement(response);

//        System.out.println(body);
    }
    /**
    *获取主页元素内容
    * @Author:strange
    * @Date:16:06 2020-02-29
    */
    private void getElement(Connection.Response response) throws Exception{
        //获取document,代表整个页面
        Document document = response.parse();
        //body()方法获取爬取得到的html的body标签
        //Element和Elements中的方法都是jQuery中同名方法.功能基本相同
        Element bodyElement = document.body();
        //获取卡片视图
        Elements select = bodyElement.select(".container .row .post-container .post-preview");
        int i = 0 ;
        for (Element element : select) {
            //text()用于获取元素节点内的文本内容
//            String text = element.text();

            //html()方法用于获取元素节点内的html标签
            Elements titleElement1 = element.select(".post-title");
            String title1 = titleElement1.text();
//            System.out.println("标题是: "+title1);

            Elements titleElement2 = element.select(".post-content-preview");
            String title2 = titleElement2.text();
            String html = titleElement1.html();
//            System.out.println("简介是: "+title2);

            //获取子页面中的元素
            String text = getChildElement(element);
//            System.out.println(text);
//             i++ ;
//            write(text,i +".txt");
        }
        Elements select1 = bodyElement.select(".container .row  .sidebar-container .short-about");
        for (Element element : select1) {
            String src = element.select(">img").attr("src");
            System.out.println(src);
        }

    }
    /**
    *获取子页面内容
    * @Author:strange
    * @Date:17:21 2020-02-29
    */
    private String getChildElement(Element element) throws Exception{
        Elements select1 = element.select(">a");
        String href = select1.attr("href");
        Connection.Response response = creatConnect(href);
        Document document = response.parse();
        Element bodyElement = document.body();
        Elements title = bodyElement.select(".row");
        String text = title.text();

        return text;
    }
    /**
    *将内容写入指定文档
    * @Author:strange
    * @Date:17:22 2020-02-29
    */
    public void write(String body,String fileName) throws Exception {
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
