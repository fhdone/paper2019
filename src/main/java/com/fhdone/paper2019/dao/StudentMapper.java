package com.fhdone.paper2019.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.fhdone.paper2019.model.Student;
import com.fhdone.paper2019.model.StudentExample;

public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Long id);
    
    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Student> selectJoinTest();
    
    List<Student> selectOneToMany();
    
    List<Student> selectOneToManyBySQL();
    
}