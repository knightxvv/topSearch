package com.tx.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyJdbcUtil {
//    static Connection con=null;
//    static Statement statement=null;
//    static {
//        try {
//            System.out.println(config.driver);
//            Class.forName(config.driver);
//            con = DriverManager.getConnection(config.url, config.user, config.password);
//            if (!con.isClosed())
//                System.out.println("成功以 " + config.user + " 身份连接到数据库！！！");
//            statement = con.createStatement();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
    @Autowired
    public JdbcTemplate jdbcTemplate;
    
    public boolean insert(String sql) {
        try {
            jdbcTemplate.execute(sql);
            return true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
