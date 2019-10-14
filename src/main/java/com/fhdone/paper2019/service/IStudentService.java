package com.fhdone.paper2019.service;

import java.util.List;

import com.fhdone.paper2019.bean.Student;
import com.fhdone.paper2019.bean.StudentExample;

public interface IStudentService {
	
	public List<Student> getAllStudents();

	public List<Student> getStudentsByPage(Integer pageNum,  Integer page_size);
	
	public Student getStudentsById(Long id);
	
	public int insertStudent(Student stu);
	
	public Integer deleteStudents(StudentExample example);
	
	public Integer deleteByPrimaryKey(Long id);
	
	public Long countStudents();

}
