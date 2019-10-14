package com.fhdone.paper2019;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-servlet.xml"
		,"classpath:spring-dispatcher.xml"})
@Rollback(true)
public class BaseTest {
	@Test
	@Ignore
	public  void  empty(){
		
	}

}
