package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

/**
 * Created by Ansal on 10/20/2017.
 */

public class Product {
    private    int    id;
    private    String medine_name,date1,date2,date3,date4,dosage_unit,instruction,doc_name,doc_specl,doc_cont,strt_date,evry_day_week,remind_time;
    int dosage_no;
    double take1,take2,take3,take4;
    int remind_no;
    double amnt_meds;
    int continues_day;
    String reminder_flag;
    String interval_time;
    String skipped_date1,skipped_date2,skipped_date3,skipped_date4;

    public Product(String str_med_name, String str_date1, String str_date2, String str_date3, String str_date4,
                   double str_take1, double str_take2, double str_take3, double str_take4,
                   int dosage_no, String str_dosage_unit, String str_instrcn, double amnt_meds, int remind_no,
                   String str_remind_time, String str_doc_name, String str_doc_spcl, String str_doc_cont, String str_strt_date, int continue_days, String str_evry_day_week,String rem_flag,String intr_time,String skpd1,String skpd2,String skpd3,String skpd4) {

            this.medine_name = str_med_name;
            this.date1 = str_date1;
            this.date2 = str_date2;
            this.date3 = str_date3;
            this.date4 = str_date4;
            this.take1 = str_take1;
            this.take2 = str_take2;
            this.take3 = str_take3;
            this.take4 = str_take4;
            this.dosage_no = dosage_no;
            this.dosage_unit = str_dosage_unit;
            this.instruction = str_instrcn;
            this.doc_name = str_doc_name;
            this.doc_specl = str_doc_spcl;
            this.doc_cont = str_doc_cont;
            this.strt_date = str_strt_date;
            this.evry_day_week = str_evry_day_week;
            this.remind_time = str_remind_time;
            this.remind_no = remind_no;
            this.amnt_meds = amnt_meds;
            this.continues_day = continue_days;
            this.reminder_flag=rem_flag;
        this.interval_time=intr_time;
        this.skipped_date1=skpd1;
        this.skipped_date2=skpd2;
        this.skipped_date3=skpd3;
        this.skipped_date4=skpd4;
    }

    public Product(int id, String name, String date1, String date2, String date3, String date4, double take1, double take2, double take3, double take4,
                   int dos_no, String dos_unit, String instr, String do_name, String do_spcl, String do_cont, double amnt_meds, int rm_no, String rm_time,
                   String strt_date, int cont_day, String ev_dy_week,String rem_flag,String intr_time,String skpd1,String skpd2,String skpd3,String skpd4) {
                  this.id = id;
                  this.medine_name = name;
                  this.date1 = date1;
                  this.date2 = date2;
                  this.date3 = date3;
                  this.date4 = date4;
                this.take1 = take1;
                this.take2 = take2;
                this.take3 = take3;
                this.take4 = take4;
                this.dosage_no = dos_no;
                this.dosage_unit = dos_unit;
                this.instruction = instr;
                this.doc_name = do_name;
                this.doc_specl = do_spcl;
                this.doc_cont = do_cont;
                this.strt_date = strt_date;
                this.evry_day_week = ev_dy_week;
                this.remind_time = rm_time;
                this.remind_no = rm_no;
                this.amnt_meds = amnt_meds;
                this.continues_day = cont_day;
                this.reminder_flag=rem_flag;
        this.interval_time=intr_time;
        this.skipped_date1=skpd1;
        this.skipped_date2=skpd2;
        this.skipped_date3=skpd3;
        this.skipped_date4=skpd4;
    }

    public Product(String date1, String date2, String date3, String date4) {
        this.date1 = date1;
        this.date2 = date2;
        this.date3 = date3;
        this.date4 = date4;
    }


    public double getTake1() {
        return take1;
    }

    public void setTake1(int take1) {
        this.take1 = take1;
    }

    public double getTake2() {
        return take2;
    }

    public void setTake2(int take2) {
        this.take2 = take2;
    }

    public double getTake3() {
        return take3;
    }

    public void setTake3(int take3) {
        this.take3 = take3;
    }

    public double getTake4() {
        return take4;
    }

    public void setTake4(int take4) {
        this.take4 = take4;
    }

    public String getDosage_unit() {
        return dosage_unit;
    }

    public void setDosage_unit(String dosage_unit) {
        this.dosage_unit = dosage_unit;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_specl() {
        return doc_specl;
    }

    public void setDoc_specl(String doc_specl) {
        this.doc_specl = doc_specl;
    }

    public String getDoc_cont() {
        return doc_cont;
    }

    public void setDoc_cont(String doc_cont) {
        this.doc_cont = doc_cont;
    }

    public String getStrt_date() {
        return strt_date;
    }

    public void setStrt_date(String strt_date) {
        this.strt_date = strt_date;
    }

    public String getEvry_day_week() {
        return evry_day_week;
    }

    public void setEvry_day_week(String evry_day_week) {
        this.evry_day_week = evry_day_week;
    }

    public String getRemind_time() {
        return remind_time;
    }

    public void setRemind_time(String remind_time) {
        this.remind_time = remind_time;
    }

    public int getDosage_no() {
        return dosage_no;
    }

    public void setDosage_no(int dosage_no) {
        this.dosage_no = dosage_no;
    }

    public int getRemind_no() {
        return remind_no;
    }

    public void setRemind_no(int remind_no) {
        this.remind_no = remind_no;
    }

    public double getAmnt_meds() {
        return amnt_meds;
    }

    public void setAmnt_meds(int amnt_meds) {
        this.amnt_meds = amnt_meds;
    }

    public int getContinues_day() {
        return continues_day;
    }

    public void setContinues_day(int continues_day) {
        this.continues_day = continues_day;
    }


    public String getSkipped_date1() {
        return skipped_date1;
    }

    public void setSkipped_date1(String skipped_date1) {
        this.skipped_date1 = skipped_date1;
    }

    public String getSkipped_date2() {
        return skipped_date2;
    }

    public void setSkipped_date2(String skipped_date2) {
        this.skipped_date2 = skipped_date2;
    }

    public String getSkipped_date3() {
        return skipped_date3;
    }

    public void setSkipped_date3(String skipped_date3) {
        this.skipped_date3 = skipped_date3;
    }

    public String getSkipped_date4() {
        return skipped_date4;
    }

    public void setSkipped_date4(String skipped_date4) {
        this.skipped_date4 = skipped_date4;
    }

    public String getdate2() {
        return date2;
    }

    public void setdate2(String date2) {
        this.date2 = date2;
    }

    public String getdate3() {
        return date3;
    }

    public void setdate3(String date3) {
        this.date3 = date3;
    }

    public String getdate4() {
        return date4;
    }

    public void setdate4(String date4) {
        this.date4 = date4;
    }

    public String getReminder_flag() {
        return reminder_flag;
    }

    public void setReminder_flag(String reminder_flag) {
        this.reminder_flag = reminder_flag;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return medine_name;
    }
    public void setName(String name) {
        this.medine_name = name;
    }
    public void setdate1(String date) {
        this.date1 = date;
    }

    public String getdate1() {
        return date1;
    }

    public String getInterval_time() {
        return interval_time;
    }

    public void setInterval_time(String interval_time) {
        this.interval_time = interval_time;
    }
}