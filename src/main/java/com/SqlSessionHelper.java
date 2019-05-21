package com;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

public class SqlSessionHelper {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //创建SqlSessionFactory
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
            //创建数据库
//            SqlSession session = null;
//            try {
//                session = sqlSessionFactory.openSession();
//                Connection conn = session.getConnection();
//                reader = Resources.getResourceAsReader("hsqldb.sql");
//                ScriptRunner runner = new ScriptRunner(conn);
//                runner.setLogWriter(null);
//                runner.runScript(reader);
//                reader.close();
//            } finally {
//                if (session != null) {
//                    session.close();
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Session
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
