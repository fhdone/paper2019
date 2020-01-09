package com.fhdone.paper2019.stu;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.model.Student;
import com.github.pagehelper.PageInfo;

public class SpringMvcTest extends BaseTest{
	@Autowired
	WebApplicationContext context;

	MockMvc mockMvc;

	@Before
	public void initMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
    @Ignore
	public void  testSpringMvc() throws Exception {
		MvcResult result =mockMvc.perform(
				MockMvcRequestBuilders.get("/student/list")
				.param("pn", "1"))
				.andReturn();
		MockHttpServletRequest  request =result.getRequest();
		//		 List<Student> students=(List<Student>)request.getAttribute("studs");
		//	     for(Student student:students){
		//	    	 System.out.println(student);
		//	     }
		PageInfo  pi =(PageInfo)request.getAttribute("pageInfo");
		List<Student> students=pi.getList();
		for(Student student:students){
			System.out.println(student.getStuName()+"----------"+student.getStuAdd());
		}
	}

}
