package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserDataBaseDto;

public class UserDataBaseDao {

    String DRIVER_NAME = "com.mysql.jdbc.Driver";
    String JDBC_URL = "jdbc:mysql://localhost/test?charracterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
    String USER_ID = "root";
    String USER_PASS = "";

    public UserDataBaseDto doSelect(String inputUserId){

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

        UserDataBaseDto dto = new UserDataBaseDto();

        try {

            con = DriverManager.getConnection(JDBC_URL,USER_ID,USER_PASS);

            StringBuffer buf = new StringBuffer();
            buf.append("SELECT * FROM userinfo join user_db ON user_db.user_id = userinfo.user_id AND userinfo.user_id = ?");

            ps = con.prepareStatement(buf.toString());

            ps.setString(1, inputUserId);

            rs = ps.executeQuery();

            while(rs.next()){
                dto.setDataBaseId(rs.getString("db_user"));
                dto.setDataBasePass(rs.getString("db_pass"));
                dto.setDataBaseName(rs.getString("db_name"));
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