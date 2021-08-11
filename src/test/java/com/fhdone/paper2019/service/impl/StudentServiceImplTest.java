package com.fhdone.paper2019.service.impl;

import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.model.Student;
import com.fhdone.paper2019.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Ignore
public class StudentServiceImplTest extends BaseTest {

    private Logger logger = LogManager.getLogger(StudentServiceImplTest.class);

    @Autowired
    private StudentService studentService;

    @Test
    public void testGetAllStudents() {
        PageHelper.startPage(1, 10);
        List<Student> listStu = studentService.getAllStudents();
        for(Student stu:listStu){
            logger.info(stu.toString());
        }
    }

}
