package com.jxx.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * 功能描述
 *
 * @author strange
 * @date $
 */
@SolrDocument(solrCoreName = "mycore")
public class Books {

    @Field("name")
    private String name;
    @Field("author")
    private String author;
    @Field("id")
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Books{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", id=" + id +
                '}';
    }
}
