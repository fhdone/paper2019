package com.fhdone.paper2019.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {

    public static final String INDEX_NAME = "exps-prd";

    private String elasticsearchHost="127.0.0.1";

    private int port = 9201;

    @Bean(name = "highClient", destroyMethod = "close")
    public RestHighLevelClient client() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(elasticsearchHost,port)));
    }

    @Bean
    public IndexRequest buildIndexRequest(){
        return new IndexRequest(INDEX_NAME);
    }


}
