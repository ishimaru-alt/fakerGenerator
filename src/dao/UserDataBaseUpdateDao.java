package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.UserDataBaseDto;
import model.UserInfoDto;

public class UserDataBaseUpdateDao {

    String DRIVER_NAME = "com.mysql.jdbc.Driver";
    String JDBC_URL = "jdbc:mysql://localhost/test?charracterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
    String USER_ID = "root";
    String USER_PASS = "";

    public Boolean doUpdate(UserDataBaseDto userDataBaseDto){

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
        boolean succesUpdate = true;

        try {

            con = DriverManager.getConnection(JDBC_URL,USER_ID,USER_PASS);

            con.setAutoCommit(true);

            StringBuffer buf = new StringBuffer();
            buf.append("UPDATE user_db SET db_user = ?,db_pass = ?,db_name = ? WHERE user_id = ?");

            ps = con.prepareStatement(buf.toString());

            ps.setString(1, userDataBaseDto.getDataBaseId());
            ps.setString(2, userDataBaseDto.getDataBasePass());
            ps.setString(3, userDataBaseDto.getDataBaseName());
            ps.setString(4, userDataBaseDto.getUserId());

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
        return succesUpdate;

    }

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
        boolean successInsertDB = true;

        try {

            con = DriverManager.getConnection(JDBC_URL,USER_ID,USER_PASS);

            con.setAutoCommit(true);

            StringBuffer buf = new StringBuffer();
            buf.append("INSERT INTO user_db VALUES (?,null,null,null)");

            ps = con.prepareStatement(buf.toString());

            ps.setString(1, dto.getUserId());

            System.out.println("userdbinsert:" + dto.getUserId());

            //SQL文の実行
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            successInsertDB = false;
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
        return successInsertDB;

    }


}