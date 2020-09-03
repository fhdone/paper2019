package com.fhdone.paper2019.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhdone.paper2019.model.Student;
import com.fhdone.paper2019.model.StudentExample;
import com.fhdone.paper2019.dao.StudentMapper;
import com.fhdone.paper2019.service.StudentService;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    private Logger logger = LogManager.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
    public List<Student> getAllStudents() {
//		return sqlSessionTemplate.selectList("com.fhdone.java2017.dao.StudentMapper.selectByExample", null);
		StudentExample example = new StudentExample();
		example.setOrderByClause("id asc");
		return studentMapper.selectByExample(example);
	}
	
	@Override
    public List<Student> getStudentsByPage(Integer pageNum, Integer pageSize) {
		int offset=0;
		if(pageNum>1) {
			offset=(pageNum-1)* pageSize;
		}
		logger.info("offset:{},page_size:{}",offset, pageSize);
		StudentExample example = new StudentExample();
		example.setOrderByClause("id asc");
		return sqlSessionTemplate.selectList("com.fhdone.java2017.dao.StudentMapper.selectByExample", example, new RowBounds(offset, pageSize));
	}
	
	
	public List<Student> getAllStudents2() {
		
		List<Student> list = new ArrayList<Student>();
		sqlSessionTemplate.select("com.fhdone.java2017.dao.StudentMapper.selectByExample", new ResultHandler() {
			
            @Override
            public void handleResult(ResultContext resultContext) {
            	Student s = (Student) resultContext.getResultObject();
                System.out.println(resultContext.getResultCount());
                System.out.println(s);
                list.add(s);
            }
		});
		return list;
		
	}
	
	@Override
    public Long countStudents() {
		StudentExample example = new StudentExample();
		return studentMapper.countByExample(example);
	}

	@Override
    public Student getStudentsById(Long id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteStudents(StudentExample example) {
		return studentMapper.deleteByExample(example);
	}

	@Override
	public Integer deleteByPrimaryKey(Long id){
		Integer r=  studentMapper.deleteByPrimaryKey(id);
		return r;
	}


	@Override
	@Transactional
	public int insertStudent(Student stu) {
		int i = studentMapper.insert(stu);
		return i;
	}

}
