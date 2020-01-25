package com.fhdone.paper2019.service;

import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.model.Student;
import com.github.pagehelper.PageHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceTest extends BaseTest {


    private Logger logger = LoggerFactory.getLogger(StudentServiceTest.class);

    @Autowired
    private IStudentService studService;

    @Test
    public void testGetAllStudents() {
        PageHelper.startPage(1, 10);
        List<Student> listStu = studService.getAllStudents();
        for(Student stu:listStu){
            logger.info(stu.toString());
        }
    }

}
