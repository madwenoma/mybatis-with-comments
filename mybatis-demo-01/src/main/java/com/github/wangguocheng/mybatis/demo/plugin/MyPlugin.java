package com.github.wangguocheng.mybatis.demo.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

//mybatis plugin demo
@Intercepts(
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class}))
public class MyPlugin implements Interceptor {
    Properties properties = null;

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept before...");
        Object result = invocation.proceed();
        System.out.println("intercept after...");
        return result;
    }

    public Object plugin(Object target) {
        System.out.println("plugin..");
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {
        System.err.println(properties.get("dbType"));
        this.properties = properties;
    }
}
