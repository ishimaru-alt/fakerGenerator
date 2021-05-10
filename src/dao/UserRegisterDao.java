package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.UserInfoDto;

public class UserRegisterDao {

    String DRIVER_NAME = "com.mysql.jdbc.Driver";
    String JDBC_URL = "jdbc:mysql://localhost/test?charracterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
    String USER_ID = "root";
    String USER_PASS = "";

    public Boolean doInsert(UserInfoDto dto){

        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //sql文の処理
        Connection con = null;
        PreparedStatement ps = null;

        //実行結果が成功か失敗か
        boolean isSuccess = true;

        try {

            con = DriverManager.getConnection(JDBC_URL,USER_ID,USER_PASS);

            con.setAutoCommit(true);

            StringBuffer buf = new StringBuffer();
            buf.append("INSERT INTO userinfo (USER_ID,USER_NAME,USER_PASSWORD) VALUES (?,?,?)");

            ps = con.prepareStatement(buf.toString());

            ps.setString(1, dto.getUserId());
            ps.setString(2, dto.getUserName());
            ps.setString(3, dto.getUserPassword());

            //SQL文の実行
            ps.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //接続の解除
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }

        return isSuccess;

    }


}