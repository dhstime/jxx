package com.jxx.crawler.Jsoup.Pojo;

import com.jxx.crawler.Jsoup.annotation.Extract;
import com.jxx.crawler.Jsoup.annotation.JsoupDocument;

/**
 *  页面信息实体列
 * @author strange
 * @date $
 */
@JsoupDocument(domain = "http://www.dhsstrive.com/",
        targetUrl = "http://www.dhsstrive.com/" ,
        cssQuery = ".container .row .post-container .post-preview")
public class Article {

    @Extract(cssQuery = ".post-title")
    private String  title;

    @Extract(cssQuery = ".post-content-preview")
    private String  type;

    @Extract(cssQuery = ">a" ,attr = "href")
    private String url;

    /**
    *子页面内容
    * @Author:strange
    * @Date:23:19 2020-03-01
    */
    @JsoupDocument(domain = "http://www.dhsstrive.com/",hrefAttr = "url")
    private Comment comment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
