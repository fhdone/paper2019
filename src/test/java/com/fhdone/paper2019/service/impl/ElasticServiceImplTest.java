package com.fhdone.paper2019.service.impl;

import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.service.ElasticService;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class ElasticServiceImplTest extends BaseTest {

    @Autowired
    private ElasticService elasticService;

    private SearchResponse searchResponse_1;
    private SearchResponse searchResponse_2;

    @Before
    public void before() throws IOException {
        searchResponse_1 = elasticService.queryDoc("applyusername.keyword","何吟");
        searchResponse_2 = elasticService.queryDoc("remark","保证金");
    }


    @Test
    public void queryDoc_1() throws Exception {
        Assert.assertNotNull(searchResponse_1);
    }

    @Test
    public void queryDoc_2() throws Exception {
        Assert.assertNotNull(searchResponse_2);
        for (SearchHit hit : searchResponse_2.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
//            log.info("map={}", map.toString());
            log.debug("docId={}", hit.getId());
        }
    }

    @Test
    public void saveSearchHitToMongo() throws Exception {
        List<Document> documents = new ArrayList<>();
        for (SearchHit hit : searchResponse_1.getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            Document document = elasticService.generateDocument(hit);
            documents.add(document);
            elasticService.saveSearchHitToMongo(hit,MangoServiceImpl.ES);
        }
    }

    @Test
    public void saveSearchResponseToMongoBatch() throws Exception {
        elasticService.saveSearchResponseToMongoBatch(searchResponse_2,MangoServiceImpl.ES);
    }


} 
