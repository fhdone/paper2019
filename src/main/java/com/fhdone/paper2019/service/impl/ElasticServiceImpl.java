package com.fhdone.paper2019.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.paper2019.service.ElasticService;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

@Service("elasticService")
@Log4j2
public class ElasticServiceImpl implements ElasticService {

    @Qualifier("highClient")
    @Autowired
    private RestHighLevelClient client;

    private SearchRequest infoSearch = new SearchRequest("exps-prd");

    @Override
    public SearchResponse queryDoc(String key, String searchValue) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchPhraseQuery(key, searchValue));
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));

        infoSearch.source(sourceBuilder);
        SearchResponse response = client.search(infoSearch, RequestOptions.DEFAULT);
        long cnt =  response.getHits().getTotalHits().value;
//        log.info("result.hit={}", cnt);
//        for (SearchHit hit : response.getHits()) {
//            Map<String, Object> map = hit.getSourceAsMap();
//            log.info("map={}", map.toString());
//            log.info("docId={}", hit.getId());
//        }
        log.info("response:{}",JSON.toJSONString(response));
        return response;

    }

}
