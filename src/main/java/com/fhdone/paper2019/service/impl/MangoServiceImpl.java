package com.fhdone.paper2019.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.paper2019.service.MangoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
    public Map<String,Object> findOne() {
        Map<String,Object> map = mongoTemplate.findOne(new Query(where("applyusername").is("何吟")),
                Map.class,MangoServiceImpl.ES);
        log.info("map:{}", JSON.toJSONString(map));
        return map;
    }
}
