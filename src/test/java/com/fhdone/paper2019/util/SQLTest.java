package com.fhdone.paper2019.util;

import org.junit.Test;

import com.github.pagehelper.parser.CountSqlParser;

public class SQLTest {


	@Test
	public  void insertOne(){
		CountSqlParser countSqlParser = new CountSqlParser();
		
		String sql = "select * from T order by a ";
		System.out.println(countSqlParser.getSmartCountSql(sql));
		
		sql = "select id,last_name from s_emp 	where dept_id = ( select dept_id from s_emp where last_name = 'Smith' order by a  ";
		System.out.println(countSqlParser.getSmartCountSql(sql,"id"));
		System.out.println(SqlUtils.getCountSql(sql));
		
		
		sql = "select select 1 from dual,id,last_name from s_emp	where dept_id = ( select dept_id from s_emp where last_name = 'Smith' order by a  ";
		System.out.println(countSqlParser.getSmartCountSql(sql,"id"));
		System.out.println(SqlUtils.getCountSql(sql));
	}
}