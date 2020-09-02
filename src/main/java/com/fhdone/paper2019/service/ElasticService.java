package com.fhdone.paper2019.service;

import java.io.IOException;

public interface ElasticService {

    public void queryDoc(String key, String searchValue) throws IOException;
}
