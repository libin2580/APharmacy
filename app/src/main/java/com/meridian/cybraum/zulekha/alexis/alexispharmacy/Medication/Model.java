package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

/**
 * Created by Ansal on 10/21/2017.
 */

public class Model {
    int id;
    String date1,date2,date3,date4;
    public String getMedi_name() {
        return medi_name;
    }

    public void setMedi_name(String medi_name) {
        this.medi_name = medi_name;
    }

    String medi_name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }

    public void setDate4(String date4) {
        this.date4 = date4;
    }

    public String getdate4() {
        return date4;
    }

    public String getdate3() {
        return date3;
    }

    public String getdate2() {
        return date2;
    }

    public String getdate1() {
        return date1;
    }
}
