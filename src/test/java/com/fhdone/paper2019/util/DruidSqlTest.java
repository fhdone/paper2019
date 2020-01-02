package com.fhdone.paper2019.util;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Test;

public class DruidSqlTest {

    @Test
    public void processSql(){

        String sql = "select id,count(1) from user where id in (1,2,3)  group by id order by id";

        // 新建 MySQL Parser
        SQLStatementParser parser = new MySqlStatementParser(sql);

        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement statement = parser.parseStatement();

        // 使用visitor来访问AST
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);

        // 从visitor中拿出你所关注的信息
        System.out.println(visitor.getTables());
        System.out.println(visitor.getColumns());
        System.out.println(visitor.getConditions());
        System.out.println(visitor.getGroupByColumns());
        System.out.println(visitor.getOrderByColumns());

        System.out.println(PagerUtils.count(sql, JdbcConstants.MYSQL));
        System.out.println(PagerUtils.count(sql, JdbcConstants.ORACLE));

    }
}
