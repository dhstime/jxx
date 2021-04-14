package com.jxx.crawler.Jsoup.Pojo;

import com.jxx.crawler.Jsoup.annotation.Extract;
import com.jxx.crawler.Jsoup.annotation.JsoupDocument;

/**
 *  博客详情实体类
 * @author strange
 * @date $
 */
@JsoupDocument(domain = "http://www.dhsstrive.com/")
public class Comment {
    @Extract(cssQuery = ".row")
    private String  comment;

    @Extract(cssQuery = "body > header > div > div > div > div > span")
    private String author;

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
