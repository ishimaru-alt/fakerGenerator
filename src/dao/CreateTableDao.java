package dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import login.BusinessLogic;
import model.UserDataBaseDto;
import sample.JsonGet1;

public class CreateTableDao {
    String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public CreateTableDao(String create_sentence, String userId) {
        // TODO 自動生成されたコンストラクター・スタブ
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

        //実行結果が成功か失敗か
        boolean isDummySuccess = true;

        Statement stmt = null;

        BusinessLogic logic = new BusinessLogic();
        UserDataBaseDto userDataBaseDto = logic.excuteSelectUserDataBase(userId);

        String JDBC_URL = "jdbc:mysql://localhost/" + userDataBaseDto.getDataBaseName() + "?charracterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
        String USER_ID = userDataBaseDto.getDataBaseId();
        String USER_PASS = userDataBaseDto.getDataBasePass();

        //選択したテーブル名
        System.out.println("選択したテーブル名は" + JsonGet1.radioCheck);

        System.out.println("選択したユーザーは" + userId);

        try {
            con = DriverManager.getConnection(JDBC_URL,USER_ID,USER_PASS);
            //自動コミット機能をオフにする
            con.setAutoCommit(false);

            ps = con.prepareStatement( create_sentence );
            ps.execute();
            System.out.println("DB登録成功sitayo");

        } catch (BatchUpdateException e){
            System.out.println("バッジ処理に失敗しました");
            isDummySuccess = false;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("DB登録失敗");
            isDummySuccess = false;
        }finally {
            //接続の解除
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }

        return ;
    }


}

