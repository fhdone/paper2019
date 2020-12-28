package com.fhdone.paper2019.service.impl;

import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.service.MangoService;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Log4j2
public class MangoServiceImplTest extends BaseTest {

    @Autowired
    private MangoService mangoService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
        List<Map<String,Object>> list = mangoService.queryKey("applyusername","何吟");
        Assert.assertNotNull(list);
        log.info("list size:{}",list.size());
    }

    @Test
    public void findOne_2() throws Exception {
        List<Map<String,Object>> list = mangoService.queryKeyByRegex("applyusername","^何");
        Assert.assertNotNull(list);
        log.info("list size:{}",list.size());
    }


    @Test
    public void queryKeyGtValue() throws Exception {
        List<Map<String,Object>> list = mangoService.queryKeyGtValue("amount",10000);
        Assert.assertNotNull(list);
        log.info("list size:{}",list.size());
    }


} 
