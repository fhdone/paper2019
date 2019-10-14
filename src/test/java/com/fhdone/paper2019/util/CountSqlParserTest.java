package com.fhdone.paper2019.util;

import com.github.pagehelper.parser.CountSqlParser;

public class CountSqlParserTest {

	public static void main(String[] args) {
		CountSqlParser csp = new CountSqlParser();
		//System.out.println( csp.getSmartCountSql("select * from amc")) ;
		System.out.println( csp.getSmartCountSql("select amc.* , (select 1 from dual ) from amc left join bmc on amc.id = bmc.id "));
	}
}
