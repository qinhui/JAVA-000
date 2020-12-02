package com.jkdx.homework.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ConnectionFactory {


    private static ConnectionFactory dBService;

    public static ConnectionFactory getInstance() {
        if (dBService == null) {
            dBService = new ConnectionFactory();
        }
        return dBService;
    }

    public Connection getHikari() throws SQLException {
        try {
            return dataSoruce.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    public boolean stopHikari() throws SQLException {
        dataSoruce.close();
        return true;
    }


    private static HikariDataSource dataSoruce;

    public static void start(final ConnectionPrams dbprams) throws Exception {


        HikariConfig config_ = new HikariConfig();
        config_.setMaximumPoolSize(dbprams.getMaximumPoolSize());
        config_.setDriverClassName(dbprams.getDriverName());
        config_.setJdbcUrl( dbprams.getUrl()+"?useUnicode=true&characterEncoding=utf8&useSSL=false");
        config_.addDataSourceProperty("serverName", "127.0.0.1");
        config_.addDataSourceProperty("port", dbprams.getDbport());
        config_.addDataSourceProperty("databaseName", dbprams.getDbname());
        config_.addDataSourceProperty("user", dbprams.getDbuser());
        config_.addDataSourceProperty("password", dbprams.getDbpassword());
        config_.addDataSourceProperty("cachePrepStmts", true);
        dataSoruce = new HikariDataSource(config_);


    }

    public static Connection getDbConnection(String beanid) throws Exception{

        ApplicationContext context_ = new ClassPathXmlApplicationContext("jdbc.xml");

        ConnectionPrams connprams = (ConnectionPrams) context_.getBean(beanid);

        if(null==connprams)return  null;


        return newConnection(connprams);



    }



    public static Connection newConnection(final ConnectionPrams dbprams) throws Exception {


        return DriverManager.getConnection(dbprams.getUrl(), dbprams.getDbuser(), dbprams.getDbpassword());


    }


    public static void CloseConnection(Connection con, Statement sta, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (sta != null) {
            try {
                sta.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
