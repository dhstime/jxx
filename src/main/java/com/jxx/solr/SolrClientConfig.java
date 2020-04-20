package com.jxx.solr;


import org.apache.solr.client.solrj.SolrClient;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author strange
 * @date $
 */
@Configuration
public class SolrClientConfig {

    private String url = "http://127.0.0.1:8983/solr";

    private Integer maxRetries = 1;

    private Integer connectionTimeout=500;

    private SolrClient solrClient;

    public SolrClientConfig() {
    }
}
