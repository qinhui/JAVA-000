package com.jkdx.homework;


import com.jkdx.homework.db.ConnectionFactory;
import com.jkdx.homework.util.ObjectUtil;
import com.jkdx.homework.util.StringUtil;
import com.mysql.cj.util.StringUtils;
import sun.rmi.runtime.NewThreadAction;

import java.sql.*;
import java.util.Date;

/**
 * 第一课作业 按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
 * @author qinhui
 * @version 1.0  2020/11/29
 */
public class TestBatchInsert {


    private static final String TEST_DBTABLE_NAME = "user_info";

    //       测试数据量级
    private static final int TEST_MAX_ROW_NUM = 10;

    private static final String SQL_INSERT_ = "insert into " + TEST_DBTABLE_NAME + " (uname,upassword,nickname,phone,status,createtime)";


    public static void main(String[] args) {

        Connection connection_ = null;
        Statement statement_ = null;
        ResultSet resultSet_ = null;


        try {

//            connection_ = ConnectionFactory.getDbConnection("mySqlJdbcPram");

            connection_ = ConnectionFactory.getDbConnection("mySqlJdbcPram_");

            if (null == connection_) {
                System.out.print("db connection error ");
                return;
            }


            connection_.setAutoCommit(false);

            long startMillis = System.currentTimeMillis();

            statement_ = connection_.createStatement();

//10万数据：   time= 27005  20万数据 ： 58438
//            testForInsert(statement_);

//            10万数据 ：  time= 1200   20万数据 ： 2380
            testPingValues(statement_);

//            10万数据 ：   time= 16372  20万数据 time= 31979
//            statement_ =  testPreparedStatement(connection_,false);

//          10万数据 ：    time= 15836  20万   30801
//            statement_ =  testPreparedStatement(connection_,true);

//          20万数据 time= 1809
//            testInsertIntoFrom(statement_);


//            int delcount = clearTestData(statement_, TEST_DBTABLE_NAME, "");
//            System.out.println("删除数据："+delcount);

            connection_.commit();

            long insertend = System.currentTimeMillis();

            System.out.println(" time= " + (insertend - startMillis));



        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            ConnectionFactory.CloseConnection(connection_, statement_, resultSet_);
        }


    }

    /**
     * 测试 insert into tab from (select fielda from tab2)
     * @param statement_
     * @throws SQLException
     */
    private static void testInsertIntoFrom(Statement statement_) throws SQLException {
        String test_sql = " INSERT INTO " + TEST_DBTABLE_NAME + "_new" + "(uname,upassword,nickname,phone,status,createtime) SELECT uname,upassword,nickname,phone,status,createtime FROM " + TEST_DBTABLE_NAME + "  ";
        statement_.executeUpdate(test_sql);
    }

    /**
     * 测试  PreparedStatement executeUpdate 与 executeBatch 性能优化值
     *
     * @param connection_
     * @param batch  是否用批量提交方式
     * @return
     * @throws SQLException
     */
    private static Statement testPreparedStatement(Connection connection_, final boolean batch) throws SQLException {


        PreparedStatement pstatement_ = connection_.prepareStatement(SQL_INSERT_ + " values (?,?,?,?,?,?) ");

        for (int i = 0; i < TEST_MAX_ROW_NUM; i++) {

            pstatement_.setString(1, "uname" + i);
            pstatement_.setString(2, "passord"+i);
            pstatement_.setString(3, "nickname"+i);
            pstatement_.setString(4, "phone"+i);
            pstatement_.setInt(5, 1);
            pstatement_.setLong(6, new Date().getTime());

            if (!batch)
                pstatement_.executeUpdate();
            else
                pstatement_.addBatch();

        }


        if (batch) {
            pstatement_.executeBatch();
            System.out.println("pstatement_ -- execut--- executeBatch---");
        }

//        System.out.println(pstatement_.toString());
        return pstatement_;
    }


    /**
     * 拼接insert user_info () Values(),(),() 方式测试
     *
     * @param statement_
     * @throws SQLException
     */
    private static void testPingValues(Statement statement_) throws SQLException {
        StringBuffer values_sql = new StringBuffer(" values ");

        for (int i = 0; i < TEST_MAX_ROW_NUM; i++) {

            values_sql.append(oneRowValue(i) + ",");

        }

        String values_sql_ = StringUtil.delLast(values_sql.toString(), ",");

        statement_.executeUpdate(SQL_INSERT_ + values_sql_);
    }

    /**
     * 测试 for insert 语句
     *
     * @param statement_
     * @throws SQLException
     */
    private static void testForInsert(Statement statement_) throws SQLException {

        for (int i = 0; i < TEST_MAX_ROW_NUM; i++) {

            String insertSql = SQL_INSERT_ + " values  " + oneRowValue(i);

            statement_.execute(insertSql);


        }
    }


    private static int clearTestData(final Statement statement_, final String tabName, final String where_) throws Exception {

        if (ObjectUtil.isNullOrEmpty(statement_, true)) return -1;

        String sql = " delete  from " + tabName + "  ";

        if (!StringUtils.isNullOrEmpty(where_)) {

            sql = sql + where_;

        }
//        System.out.println(sql);

        return statement_.executeUpdate(sql);


    }


    /**
     * 拼接一行数据
     *
     * @param index_
     * @return
     */
    private static String oneRowValue(int index_) {


        long createtime_ = new Date().getTime();

        String result_ = "  ('" + "uname" + index_ + "' ,'password" + index_ + "','nickname" + index_ + "','phone" + index_ + "',1," + createtime_ + ")";

        return result_;

    }



}
