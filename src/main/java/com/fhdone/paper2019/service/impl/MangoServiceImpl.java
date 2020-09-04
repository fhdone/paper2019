package com.fhdone.paper2019.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.paper2019.service.MangoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service("mangoServiceImpl")
@Log4j2
public class MangoServiceImpl implements MangoService {

    public static final String ES = "es";

    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Map<String,Object>> queryKey(String key, String value) {
        List list = mongoTemplate.find(new Query(where(key).is(value)),
                Map.class,MangoServiceImpl.ES);
        log.info("map:{}", JSON.toJSONString(list));
        return list;
    }

    @Override
    public List<Map<String,Object>> queryKeyByRegex(String key, String regexValue) {
        List list = mongoTemplate.find(new Query(where(key).regex(regexValue)),
                Map.class,MangoServiceImpl.ES);
        log.info("map:{}", JSON.toJSONString(list));
        return list;
    }
}
