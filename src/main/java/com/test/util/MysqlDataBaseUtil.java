package com.test.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接工具
 * Created by dml on  2018/7/5
 */
public class MysqlDataBaseUtil {

    private static final Logger LOGGER = Logger.getLogger(MysqlDataBaseUtil.class);

    private static Connection conn = null;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hq_test";
    private static final String USER_NAME = "root";
    private static final String USER_P = "root2018";

    public static Connection openConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL,USER_NAME,USER_P);
        } catch (Exception e) {
            LOGGER.error("连接数据库发生异常！",e);
        }
        return conn;
    }

    public static void closeConnection() {
        try {
           if(conn!=null){
               conn.close();
           }
        } catch (Exception e) {
            LOGGER.error("关闭数据库发生异常！",e);
        }
    }
}
