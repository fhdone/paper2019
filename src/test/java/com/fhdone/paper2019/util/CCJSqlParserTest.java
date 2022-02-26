package com.fhdone.paper2019.util;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.Test;

public class CCJSqlParserTest{

    @Test
    public void selectParse() throws Exception {
        Statement stmt = CCJSqlParserUtil.parse("select t.name, t.id," +
            " (select x.name from X x where x.id = t.id)" +
            " from t_user t where t.create_time > '2020-12-26'");
        Select select = (Select) stmt;
        Expression sourceWhere = ((PlainSelect)select.getSelectBody()).getWhere();
        System.out.println(sourceWhere.toString() );
    }

}
