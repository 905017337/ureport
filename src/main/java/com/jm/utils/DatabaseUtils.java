package com.jm.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseUtils {

    @Value("${platform.datasource.host}")
    private String host = "localhost";
    @Value("${platform.datasource.port}")
    private String port = "3306";
    @Value("${platform.datasource.dbname}")
    private String dbname = "jm";
    @Value("${platform.datasource.username}")
    private String username ="root";
    @Value("${platform.datasource.password}")
    private String password = "root";

    private List<Connection> connections = new ArrayList<>(1);




    public Connection getConnection(){
        if(CollectionUtil.isEmpty(connections)){
            Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String jdbc = "jdbc:mysql://";
                String url = jdbc+host+":"+port+"/"+dbname;
                String url1 = "jdbc:mysql://XXXX:3306/jm-ureport?autoReconnect=true&useSSL=false&characterEncoding=utf-8";
                String user = "XXXX";
                String pass = "XXXX";
                conn =  DriverManager.getConnection(url1,user,pass);
                connections.add(conn);
                return conn;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            return connections.get(0);
        }
        return null;
    }

    //获取库中所有的表信息
    public List<String> getAllTableName(){
        Connection conn = getConnection();

        try {
            //获取数据库元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            //从元数据中获取到所有的表明
            ResultSet rs = dbMetaData.getTables(null,null,null,new String[]{"TABLE"});
            //存放所有表明
            List<String> tableNames = new ArrayList<>();
            //存放当前表字段
            List<String> fields = new ArrayList<>();
            //存放当前表的字段类型
            List<String> fieldstype = new ArrayList<>();

            /*//查询每个表字段
            for (String record : tableNames) {
                String sql = "select * from" + record;
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rstable = ps.executeQuery();
                //结果集元数据
                ResultSetMetaData meta = rstable.getMetaData();
                //列表数量
                int columnCount = meta.getColumnCount();
                for (int i = 1; i < columnCount; i++) {
                    fields.add(meta.getColumnName(i));
                    fieldstype.add(meta.getColumnTypeName(i));
                }
                //表加字段
                System.out.println("表"+record+"字段："+fields);
                //表加类型
                System.out.println("表"+record+"字段类型"+fieldstype);

            }*/


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
