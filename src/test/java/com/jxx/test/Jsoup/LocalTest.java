package com.jxx.test.Jsoup;

import com.jxx.Jsoup.utils.CrawlerUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import sun.security.provider.MD5;

import java.util.Map;

/**
 *
 * @author strange
 * @date $
 */
public class LocalTest {
    //设置访问路径
    public static String targetUrl = "http://app1.sfda.gov.cn/datasearchcnda/face3/content.jsp?tableId=26&tableName=TABLE26&tableView=%E8%8D%AF%E5%93%81%E6%B3%A8%E5%86%8C%E8%A1%A5%E5%85%85%E7%94%B3%E8%AF%B7%E5%A4%87%E6%A1%88%E6%83%85%E5%86%B5%E5%85%AC%E7%A4%BA&Id=138150";
    public static Connection creatConnect(String url){
        Connection connect = Jsoup.connect(url);
        //设置请求头
        connect.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        connect.header("Accept-Encoding","gzip, deflate");
        connect.header("Accept-Language","zh-CN,zh;q=0.9");
        connect.header("Cache-Control","max-age=0");
        connect.header("Connection","keep-alive");
//        connect.header("Cookie","JSESSIONID=F587B8E2F676B6815C3FD73F956A0C21.7; FSSBBIl1UgzbN7N80S=Es2XOaa6QYvRhkmGrAIxsP8RQKCVV.CKrfeL1EtqQZ.CnaCnv4yNW8b4w0VlzZkd; FSSBBIl1UgzbN7N80T=3QjOEtd0R1dLqIcG3x3dH5O6YCmyxpgUe6GSc1HlaUy7dJAPjN9pF2x7.T5Lu2t77j2EqqQACv.DpsKZYcVBCWvK0jrvdPohE48IevepJWJM.IBpkv0VzBUNmLEETBGfEDYORyTLVtcg7RqqWQTr.JTi1kCL6Ss5.itABg4SclGltw1YGopX5knaT6OZZFTbApJb_wrGU0QMEGtR5t..C1adXhchwGKwA1UwdCn5mjjRWsGI0hWinql4qhm47Jo0wpMHf62.OxIk64fn83XRXCXtl.xQpmnBbCeaMYHrDw67dYvPkVlI.qzyMoqV0TLS4gh.jYXXJMFSsOiBETxoFK0YCRjxvKE8_UIu8AhQSwlTnEa");
        connect.header("Host","app1.sfda.gov.cn");
//        connect.header("Referer","http://app1.sfda.gov.cn/datasearchcnda/face3/base.jsp?tableId=26&tableName=TABLE26&title=%B9%FA%B2%FA%D2%BD%C1%C6%C6%F7%D0%B5%B2%FA%C6%B7%A3%A8%D7%A2%B2%E1%A3%A9&bcId=152904417281669781044048234789");
        connect.header("Upgrade-Insecure-Requests","1");
        connect.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36");
        connect.ignoreHttpErrors(true);
        //执行获取页面
        return connect;
    }
    @Test
    public void test() throws Exception{
        Connection connection = creatConnect(targetUrl);
        Connection.Response response = connection.method(Connection.Method.GET).execute();
        Element body = response.parse().body();
        System.out.println(body.html());

    }
}
