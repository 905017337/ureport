package com.jm.config;

import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class UreportConfig implements BuildinDatasource {

    @Resource
    private DataSource dataSource;

    @Override
    public String name() {
        return "测试内置数据源";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
