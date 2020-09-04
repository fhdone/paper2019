package com.fhdone.paper2019.service.impl;

import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.service.MangoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

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
        Map<String,Object> map = mangoService.findOne("applyusername","何吟");
        Assert.assertNotNull(map);
    }


} 
