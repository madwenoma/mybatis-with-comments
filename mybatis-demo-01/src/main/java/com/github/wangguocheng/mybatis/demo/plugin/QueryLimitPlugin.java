package com.github.wangguocheng.mybatis.demo.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

//mybatis plugin demo
@Intercepts(
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class}))
public class QueryLimitPlugin implements Interceptor {
    Properties properties = null;


    private int limit;
    private String dbType;
    private static final String LIMIT_TABLE_NAME = "limit_table_namexxx";

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler sHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaSmHandler = SystemMetaObject.forObject(sHandler);
        while (metaSmHandler.hasGetter("h")) {
            System.err.println("metaSmHandler.hasGetter(\"h\")");
            Object o = metaSmHandler.getValue("h");
            metaSmHandler = SystemMetaObject.forObject(o);
        }
        while (metaSmHandler.hasGetter("target")) {
            System.err.println("metaSmHandler.hasGetter(\"target\")");
            Object o = metaSmHandler.getValue("target");
            metaSmHandler = SystemMetaObject.forObject(o);
        }
        String sql = (String) metaSmHandler.getValue("delegate.boundSql.sql");
        System.err.println("sql from StatementHandler:" + sql);
        if (dbType.equals("mysql") && sql.indexOf(LIMIT_TABLE_NAME) == -1) {

            sql = "SELECT * FROM (" + sql + ") as " + LIMIT_TABLE_NAME + " limit " + this.limit;
            System.err.println("sql after intercept:" + sql);
            metaSmHandler.setValue("delegate.boundSql.sql", sql);
        }

        Object result = invocation.proceed();
        return result;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.dbType = (String) properties.get("dbType");
        this.limit = Integer.parseInt(properties.getProperty("limitCount", "50"));
        this.properties = properties;
    }
}
