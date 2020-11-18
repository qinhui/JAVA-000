package com.jkdx.example.homework05.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context_ = new ClassPathXmlApplicationContext("jdbc.xml");

        ConnectionPrams connprams = (ConnectionPrams) context_.getBean("mySqlJdbcPram");

//        crud(connprams);
//
//        Transaction(connprams);
//        batch(connprams);
        ConnectionFactory.start(connprams);
        hikari(connprams);

    }

    public static void hikari(ConnectionPrams connprams) {
        {

            Statement statement_ = null;
            Connection connection_ = null;
            ResultSet rs_ = null;
            try {


                connection_ = ConnectionFactory.getInstance().getHikari();
                statement_ = connection_.createStatement();


                String sql_ = "select t.* from oms_order t where t.order_sn like '20200914010100000%'";

                rs_ = statement_.executeQuery(sql_);

                while (rs_.next()) {
                    System.out.println(rs_.getString("order_sn"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ConnectionFactory.CloseConnection(connection_, statement_, rs_);
            }

        }
    }


    public static void crud(ConnectionPrams connprams) {
        Statement statement_ = null;
        Connection connection_ = null;
        ResultSet rs_ = null;
        try {

            System.out.print(connprams);

            connection_ = ConnectionFactory.newConnection(connprams);

            statement_ = connection_.createStatement();
            String sql_ = "select t.* from oms_order t ";

            rs_ = statement_.executeQuery(sql_);
            while (rs_.next()) {
                System.out.println(rs_.getString("promotion_info"));
            }

            int updateNumber = statement_.executeUpdate("update oms_order set order_sn='202009140101000003' where order_sn='201809140101000003' ");

            System.out.println(updateNumber);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.CloseConnection(connection_, statement_, rs_);
        }
    }


    public static void batch(ConnectionPrams connprams) {

        Statement statement_ = null;
        Connection connection_ = null;
        ResultSet rs_ = null;
        try {

            connection_ = ConnectionFactory.newConnection(connprams);
            statement_ = connection_.createStatement();


            String sql_ = "select t.* from oms_order t where t.order_sn like '20200914010100000%'";

            rs_ = statement_.executeQuery(sql_);

            while (rs_.next()) {
                System.out.println(rs_.getString("order_sn"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.CloseConnection(connection_, statement_, rs_);
        }

    }


    public static void Transaction(ConnectionPrams connprams) {

        Statement statement_ = null;
        Connection connection_ = null;
        ResultSet rs_ = null;
        try {


            connection_ = ConnectionFactory.newConnection(connprams);
            connection_.setAutoCommit(false);
            statement_ = connection_.createStatement();

            try {
                statement_.execute("update oms_order set order_sn='202009140101000006' where order_sn='202009140101000005' ");

                statement_.execute("update oms_order set order_sn='202009140101000007' where order_sn='202009140101000006' ");


            } catch (Exception e) {
                connection_.rollback();
//                e.printStackTrace();
            }
            connection_.commit();

            String sql_ = "select t.* from oms_order t where t.order_sn like '20200914010100000%'";

            rs_ = statement_.executeQuery(sql_);

            while (rs_.next()) {
                System.out.println(rs_.getString("order_sn"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.CloseConnection(connection_, statement_, rs_);
        }

    }


}
