package com.jxx.test.Jsoup;

import com.jxx.crawler.Jsoup.Pojo.Article;
import com.jxx.crawler.Jsoup.utils.CrawlerUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  测试爬虫框架
 * @author strange
 * @date $
 */
public class TestFramework {

    String url = "http://www.dhsstrive.com/";
//    @Test
    public void test1() throws Exception {
        Article article = (Article)CrawlerUtils.execute(Article.class, url, new Article());
        System.out.println(article.toString());

//        System.out.println(article.getComment().getAuthor());
    }

//    @Test
    public void test2() throws Exception {
        List<Article> list = (List<Article>)CrawlerUtils.execute(Article.class, url,  new ArrayList<Article>());
        for (Article article : list) {
            System.out.println(article.toString());
            System.out.println(article.getComment().toString());
        }

    }
}
