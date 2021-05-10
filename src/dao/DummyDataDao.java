package dao;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import login.BusinessLogic;
import model.DataBaseDto;
import model.JsonDto;
import model.UserDataBaseDto;
import sample.JsonGet1;

public class DummyDataDao {
    String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public Boolean doDummy(List<JsonDto> DTOList, String userId){
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //sql文の処理
        Connection con = null;
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

            stmt = con.createStatement();

            ArrayList<DataBaseDto> dto1 = logic.excuteDetailInfoDataBase(userId);

            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO " + JsonGet1.radioCheck);
            sql.append("(");

            for (int i = 0; i < dto1.size(); i++) {
                if (DTOList.get(0).getId() != null) {
                    sql.append(dto1.get(i).getColumName() + ",");
                }
                sql.append(dto1.get(i).getColumName() + ",");
                sql.append(dto1.get(i).getPosition() + ",");
            }
            System.out.println(sql);

            for(int i = 0; i < DTOList.size(); i++) {
                StringBuffer buf = new StringBuffer();
                buf.append("INSERT INTO " + JsonGet1.radioCheck);
                buf.append("(");
                if (DTOList.get(0).getId() != null) {
                    buf.append("id,");
                }
                if (DTOList.get(0).getName() != null) {
                    buf.append("user_name,");
                }
                if (DTOList.get(0).getLastname() != null) {
                    buf.append("lastname,");
                }
                if (DTOList.get(0).getFirstname() != null) {
                    buf.append("firstname,");
                }
                if (DTOList.get(0).getZip() != null) {
                    buf.append("zip,");
                }
                if (DTOList.get(0).getPref() != null) {
                    buf.append("pref,");
                }
                if (DTOList.get(0).getCity() != null) {
                    buf.append("city,");
                }
                if (DTOList.get(0).getAddress() != null) {
                    buf.append("address,");
                }
                if (DTOList.get(0).getPhone() != null) {
                    buf.append("phone,");
                }
                if (DTOList.get(0).getEmail() != null) {
                    buf.append("mail,");
                }
                if (DTOList.get(0).getBirthday() != null) {
                    buf.append("birthday,");
                }
                if (DTOList.get(0).getAge() != null) {
                    buf.append("age,");
                }
                if (DTOList.get(0).getGender() != null) {
                    buf.append("gender,");
                }
                if (DTOList.get(0).getPassword() != null) {
                    buf.append("password,");
                }
                if (DTOList.get(0).getCredit() != null) {
                    buf.append("credit,");
                }
                //bufがある場合は最後のカンマを削除する
                if (buf.length() > 0) {
                    buf.setLength(buf.length() - 1);
                }
                buf.append(")");
                buf.append(" values ");
                buf.append("(");
                if (DTOList.get(0).getId() != null) {
                    buf.append(DTOList.get(i).getId());
                    buf.append(",");
                }
                if (DTOList.get(0).getName() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getName());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getLastname() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getLastname());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getFirstname() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getFirstname());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getZip() != null) {
                    buf.append(DTOList.get(i).getZip());
                    buf.append(",");
                }
                if (DTOList.get(0).getPref() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getPref());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getCity() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getCity());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getAddress() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getAddress());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getPhone() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getPhone());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getEmail() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getEmail());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getBirthday() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getBirthday());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getAge() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getAge());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getGender() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getGender());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getPassword() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getPassword());
                    buf.append("'");
                    buf.append(",");
                }
                if (DTOList.get(0).getCredit() != null) {
                    buf.append("'");
                    buf.append(DTOList.get(i).getCredit());
                    buf.append("'");
                    buf.append(",");
                }
                //bufがある場合は最後のカンマを削除する
                if (buf.length() > 0) {
                    buf.setLength(buf.length() - 1);
                }
                System.out.println(buf.toString());
                buf.append(")");
                stmt.addBatch(buf.toString());
                //stmt.executeBatch();
            }
            int[] updateCounts = stmt.executeBatch();
            System.out.println("登録件数：" + updateCounts.length + "件");
            System.out.println("作業");
            con.commit();
            System.out.println("DB登録成功");

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

        return isDummySuccess;

    }


}
