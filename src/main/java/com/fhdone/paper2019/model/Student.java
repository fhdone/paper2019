package com.fhdone.paper2019.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Student implements Serializable{

	private static final long serialVersionUID = 2442212290500967267L;

	public Student() {
		super();
	}

	public Student(Long id, String stuName, String stuAdd) {
		super();
		this.id = id;
		this.stuName = stuName;
		this.stuAdd = stuAdd;
	}

	private Long id;

	private String stuName;

	private String stuAdd;

	private Long grade;
	
	private Long score;
	
}