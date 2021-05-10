package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import model.UserInfoDto;

public class UserInfoDao {

    String DRIVER_NAME = "com.mysql.jdbc.Driver";
    String JDBC_URL = "jdbc:mysql://localhost/test?charracterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
    String USER_ID = "root";
    String USER_PASS = "";

    public UserInfoDto doSelect(String inputUserId,String inputUserPassword){

        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //sql文の処理
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs= null;

        UserInfoDto dto = new UserInfoDto();

        try {

            con = DriverManager.getConnection(JDBC_URL,USER_ID,USER_PASS);

            StringBuffer buf = new StringBuffer();
            buf.append("SELECT USER_ID,USER_NAME,USER_PASSWORD FROM userinfo WHERE USER_ID = ?");

            ps = con.prepareStatement(buf.toString());


            ps.setString(1, inputUserId);

            rs = ps.executeQuery();


            if (rs.next()) {
                String password =  rs.getString("USER_PASSWORD");
                if (BCrypt.checkpw(inputUserPassword, password)) {
                    dto.setUserId(rs.getString("USER_ID"));
                    dto.setUserName(rs.getString("USER_NAME"));
                    dto.setUserPassword(rs.getString("USER_PASSWORD"));
                }else {
                }
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //接続の解除
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

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

        return dto;

    }

}
