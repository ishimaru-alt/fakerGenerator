package login;

import java.util.ArrayList;
import java.util.List;

import dao.DummyDataDao;
import dao.DummyDetailsInfoDao;
import dao.DummyExistenceDao;
import dao.UserDataBaseDao;
import dao.UserDataBaseUpdateDao;
import dao.UserInfoDao;
import dao.UserRegisterDao;
import model.DataBaseDto;
import model.JsonDto;
import model.UserDataBaseDto;
import model.UserInfoDto;

public class BusinessLogic {

    private static ArrayList<DataBaseDto> bookList = null;
    private static ArrayList<DataBaseDto> dummyDetails = null;


    //ログイン情報
    public UserInfoDto excuteSelectUserInfo(String inputUserId,String inputPassword) {
        UserInfoDto dto = new UserInfoDto();

        UserInfoDao dao = new UserInfoDao();

        dto = dao.doSelect(inputUserId,inputPassword);

        return dto;
    }


    //テーブル情報の取得
    public ArrayList<DataBaseDto> excuteSelectDataBase(String userId) {
        // DAOオブジェクト化
        DummyExistenceDao dao = new DummyExistenceDao();

        bookList = dao.selectAll(userId);

        return bookList;

    }

    //テーブルの詳細情報の取得
    public ArrayList<DataBaseDto> excuteDetailInfoDataBase(String userId) {
        // DAOオブジェクト化
        DummyDetailsInfoDao dao = new DummyDetailsInfoDao();

        dummyDetails = dao.selectAll(userId);

        return dummyDetails;

    }


    //新規会員登録
    public boolean excuteInsertUserInfo(UserInfoDto dto) {

        boolean succesInsert = false;

        UserRegisterDao dao = new UserRegisterDao();
        succesInsert = dao.doInsert(dto);

        return succesInsert;

    }


    //DummyData登録
    public boolean excuteInsertDummyData(List<JsonDto> DTOList,String userId) {

        boolean succesInsertDummy = false;

        DummyDataDao dao = new DummyDataDao();
        succesInsertDummy = dao.doDummy(DTOList,userId);

        return succesInsertDummy;
    }

    //DataBase情報
    public UserDataBaseDto excuteSelectUserDataBase(String inputUserId) {
        UserDataBaseDto dto = new UserDataBaseDto();

        UserDataBaseDao dao = new UserDataBaseDao();

        dto = dao.doSelect(inputUserId);

        return dto;
    }

    //DataBase情報更新
    public boolean excuteDatabaseUpdate(UserDataBaseDto userDataBaseDto) {

        boolean succesUpdate = false;

        UserDataBaseUpdateDao dao = new UserDataBaseUpdateDao();
        succesUpdate = dao.doUpdate(userDataBaseDto);

        return succesUpdate;

    }

    //DataBase情報登録
    public boolean excuteDatabaseInsert(UserInfoDto dto) {

        boolean succesInsertDB = false;

        UserDataBaseUpdateDao dao = new UserDataBaseUpdateDao();
        succesInsertDB = dao.doInsert(dto);

        return succesInsertDB;

    }
}
