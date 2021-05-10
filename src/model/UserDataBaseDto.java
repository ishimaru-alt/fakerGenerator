package model;

public class UserDataBaseDto {

    private String DataBaseId;
    private String DataBasePass;
    private String DataBaseName;
    private String UserId;


    public String getUserId() {
        return UserId;
    }
    public void setUserId(String userId) {
        UserId = userId;
    }
    public String getDataBaseId() {
        return DataBaseId;
    }
    public void setDataBaseId(String dataBaseId) {
        DataBaseId = dataBaseId;
    }
    public String getDataBasePass() {
        return DataBasePass;
    }
    public void setDataBasePass(String dataBasePass) {
        DataBasePass = dataBasePass;
    }
    public String getDataBaseName() {
        return DataBaseName;
    }
    public void setDataBaseName(String dataBaseName) {
        DataBaseName = dataBaseName;
    }

}
