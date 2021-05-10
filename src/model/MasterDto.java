package model;

import java.util.ArrayList;

public class MasterDto {

    public ArrayList<DataBaseDto> dtolist;

    public ArrayList<DataBaseDto> columlist;

    public ArrayList<DataBaseDto> getDtolist() {
        return dtolist;
    }

    public void setDtolist(ArrayList<DataBaseDto> dtolist) {
        this.dtolist = dtolist;
    }

    public ArrayList<DataBaseDto> getColumlist() {
        return columlist;
    }

    public void setColumlist(ArrayList<DataBaseDto> columlist) {
        this.columlist = columlist;
    }



}
