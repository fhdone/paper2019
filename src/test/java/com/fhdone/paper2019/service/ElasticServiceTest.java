package com.fhdone.paper2019.service;

import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.service.ElasticService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ElasticServiceTest extends BaseTest {

    @Autowired
    ElasticService elasticService;

    @Test
    public void queryDoc_1() throws Exception {
        elasticService.queryDoc("remark","保证金");
        Assert.assertTrue(true);
    }

    @Test
    public void queryDoc_2() throws Exception {
        elasticService.queryDoc("applyusername.keyword","何吟");
        Assert.assertTrue(true);
    }

} 
