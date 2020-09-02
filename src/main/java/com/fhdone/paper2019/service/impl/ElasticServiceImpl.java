package com.fhdone.paper2019.service.impl;

import com.fhdone.paper2019.service.ElasticService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountResponse;
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
public class ElasticServiceImpl implements ElasticService {

    private Logger logger = LogManager.getLogger(ElasticServiceImpl.class);

    @Qualifier("highClient")
    @Autowired
    private RestHighLevelClient client;

    private SearchRequest infoSearch = new SearchRequest("exps-prd");

    @Override
    public void queryDoc(String key, String searchValue) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchPhraseQuery(key, searchValue));
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        infoSearch.source(sourceBuilder);

        SearchResponse response = client.search(infoSearch, RequestOptions.DEFAULT);
        long cnt =  response.getHits().getTotalHits().value;
        logger.info("result.hit={}", cnt);

        for (SearchHit hit : response.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            logger.info("map={}", map.toString());
            logger.info("docId={}", hit.getId());
        }

    }

}
