package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import login.BusinessLogic;
import model.DataBaseDto;
import model.UserDataBaseDto;
import sample.JsonGet1;

public class DummyDetailsInfoDao {
    String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public ArrayList<DataBaseDto> selectAll(String userId){

        BusinessLogic logic = new BusinessLogic();
        UserDataBaseDto userDataBaseDto = logic.excuteSelectUserDataBase(userId);

        String JDBC_URL = "jdbc:mysql://localhost/" + userDataBaseDto.getDataBaseName() + "?charracterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
        String USER_ID = userDataBaseDto.getDataBaseId();
        String USER_PASS = userDataBaseDto.getDataBasePass();


        // 配列宣言
        ArrayList<DataBaseDto> columnlist = new ArrayList<DataBaseDto>();
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

        try {
            //データベース情報を登録しているか？
            if(userDataBaseDto.getDataBaseId() != null && userDataBaseDto.getDataBasePass() != null && userDataBaseDto.getDataBaseName() != null) {
                con = DriverManager.getConnection(JDBC_URL,USER_ID,USER_PASS);

                //informatoin_schemaのselect文
                String sql = "";
                sql += " SELECT * FROM information_schema.columns WHERE ";
                sql += " table_name = '" + JsonGet1.radioCheck + "' ";
                sql += " ORDER BY ordinal_position ";

                try {
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    //テーブルの情報を配列に格納
                    while(rs.next()) {
                        DataBaseDto dtodata = new DataBaseDto();
                        dtodata.setColumName(rs.getString("column_name"));
                        dtodata.setCharLength(rs.getString("character_maximum_length"));
                        dtodata.setPosition(rs.getString("ordinal_position"));
                        columnlist.add(dtodata);
                    }
                }catch (SQLException e) {
                    // TODO: handle exception
                    columnlist = null;
                }
                System.out.println("sql文は" + sql);
            }else {
                columnlist = null;
            }
        } catch (SQLException e) {
            System.out.println("Errorが発生しました！\n"+e);
            columnlist = null;
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
        return columnlist;

    }

}