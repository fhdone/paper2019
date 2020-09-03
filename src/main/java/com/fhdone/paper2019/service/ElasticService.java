package com.fhdone.paper2019.service;

import org.bson.Document;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;

public interface ElasticService {

    public SearchResponse queryDoc(String key, String searchValue) throws IOException;

    public Document generateDocument(SearchHit hit);

    public void saveSearchHitToMongo(SearchHit hit, String collectionName);

    public void saveSearchResponseToMongoBatch(SearchResponse searchResponse, String collectionName);
}
