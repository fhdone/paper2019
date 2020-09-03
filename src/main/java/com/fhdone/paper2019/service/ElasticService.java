package com.fhdone.paper2019.service;

import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

public interface ElasticService {

    public SearchResponse queryDoc(String key, String searchValue) throws IOException;
}
