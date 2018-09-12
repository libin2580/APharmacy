package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

/**
 * Created by Ansal on 10/20/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SqliteDatabase extends SQLiteOpenHelper{
    private static final  int DATABASE_VERSION =    5;
    private static final  String    DATABASE_NAME = "Add_medicine";
    public String TABLE_PRODUCTS = "Medicine";
    public String COLUMN_ID = "_id";
    public   String COLUMN_PRODUCTNAME = "medinename";
    public String COLUMN_DATE1 = "date1";
    public String COLUMN_DATE2 = "date2";
    public String COLUMN_DATE3 = "date3";
    public String COLUMN_DATE4 = "date4";
    public String COLUMN_TAKE1 = "take1";
    public String COLUMN_TAKE2 = "take2";
    public String COLUMN_TAKE3 = "take3";
    public String COLUMN_TAKE4 = "take4";
    public String COLUMN_DOASGE_NO = "dos_no";
    public String COLUMN_DOSAGE_UNIT= "dos_unit";
    public String COLUMN_INSTRUCN= "instr";
    public String COLUMN_DOC_NAME = "do_name";
    public String COLUMN_DOC_SPCL = "do_spcl";
    public String COLUMN_DOC_CONT= "do_cont";
    public String COLUMN_AMNT_MEDS = "amnt_meds";
    public String COLUMN_REMND_NO = "rm_no";
    public String COLUMN_REMND_TIME = "rm_time";
    public String COLUMN_STRT_DATE = "strt_date";
    public String COLUMN_CONTINUS_DAY = "cont_day";
    public String COLUMN_EVRY_DAY_WEEK= "ev_dy_week";
    public String COLUMN_REMINDER_FLAG= "reminder_flag";
    public String COLUMN_INTERVAL_TIME= "interval_time";
    public String COLUMN_SKIPPED_DATE1= "skipped_date1";
    public String COLUMN_SKIPPED_DATE2= "skipped_date2";
    public String COLUMN_SKIPPED_DATE3= "skipped_date3";
    public String COLUMN_SKIPPED_DATE4= "skipped_date4";



    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE    TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PRODUCTNAME + " TEXT," + COLUMN_DATE1 + " TEXT," + COLUMN_DATE2 + " TEXT," + COLUMN_DATE3 + " TEXT," + COLUMN_DATE4 + " TEXT,"
                + COLUMN_TAKE1 + " DOUBLE," + COLUMN_TAKE2 + " DOUBLE," + COLUMN_TAKE3 + " DOUBLE," + COLUMN_TAKE4 + " DOUBLE,"
                + COLUMN_DOASGE_NO + " INTEGER," + COLUMN_DOSAGE_UNIT + " TEXT," + COLUMN_INSTRUCN + " TEXT," + COLUMN_DOC_NAME + " TEXT," + COLUMN_DOC_SPCL + " TEXT,"
                + COLUMN_DOC_CONT + " TEXT," + COLUMN_AMNT_MEDS + " DOUBLE," + COLUMN_REMND_NO + " INTEGER," + COLUMN_REMND_TIME + " TEXT," + COLUMN_STRT_DATE + " TEXT,"
                + COLUMN_CONTINUS_DAY + " INTEGER," + COLUMN_EVRY_DAY_WEEK + " TEXT," + COLUMN_REMINDER_FLAG + " TEXT," + COLUMN_INTERVAL_TIME+ " TEXT," + COLUMN_SKIPPED_DATE1+ " TEXT," + COLUMN_SKIPPED_DATE2+ " TEXT"+"," + COLUMN_SKIPPED_DATE3+ " TEXT"+"," + COLUMN_SKIPPED_DATE4+ " TEXT"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
    public List<Product> listProducts(){
        String sql = "select * from " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String date1 = (cursor.getString(2));
                String date2 = (cursor.getString(3));
                String date3 = (cursor.getString(4));
                String date4 = (cursor.getString(5));
                double take1 = Double.parseDouble((cursor.getString(6)));
                double take2 =Double.parseDouble((cursor.getString(7)));
                double take3 = Double.parseDouble((cursor.getString(8)));
                double take4 = Double.parseDouble((cursor.getString(9)));
                int dos_no = Integer.parseInt((cursor.getString(10)));
                String dos_unit = cursor.getString(11);
                String instr = (cursor.getString(12));
                String do_name = (cursor.getString(13));
                String do_spcl = (cursor.getString(14));
                String do_cont = (cursor.getString(15));
                double amnt_meds = Double.parseDouble(cursor.getString(16));
                int rm_no = Integer.parseInt((cursor.getString(17)));
                String rm_time = (cursor.getString(18));
                String strt_date = (cursor.getString(19));
                int cont_day = Integer.parseInt((cursor.getString(20)));
                String ev_dy_week = (cursor.getString(21));
                String reminder_flag=(cursor.getString(22));
                String interval_time=(cursor.getString(23));
                String skpd1=(cursor.getString(24));
                String skpd2=(cursor.getString(25));
                String skpd3=(cursor.getString(26));
                String skpd4=(cursor.getString(27));
                storeProducts.add(new Product(id, name, date1,date2,date3,date4,take1,take2,take3,take4,dos_no,dos_unit,instr,do_name,do_spcl,do_cont
                        ,amnt_meds,rm_no,rm_time,strt_date,cont_day,ev_dy_week,reminder_flag,interval_time,skpd1,skpd2,skpd3,skpd4));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }
    public void addProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_DATE1, (product.getdate1()));
        values.put(COLUMN_DATE2, (product.getdate2()));
        values.put(COLUMN_DATE3, (product.getdate3()));
        values.put(COLUMN_DATE4, (product.getdate4()));

        values.put(COLUMN_TAKE1, (product.getTake1()));
        values.put(COLUMN_TAKE2, (product.getTake2()));
        values.put(COLUMN_TAKE3, (product.getTake3()));
        values.put(COLUMN_TAKE4, (product.getTake4()));

        values.put(COLUMN_DOASGE_NO, (product.getDosage_no()));
        values.put(COLUMN_DOSAGE_UNIT, (product.getDosage_unit()));
        values.put(COLUMN_INSTRUCN, (product.getInstruction()));

        values.put(COLUMN_DOC_NAME, (product.getDoc_name()));
        values.put(COLUMN_DOC_SPCL, (product.getDoc_specl()));
        values.put(COLUMN_DOC_CONT, (product.getDoc_cont()));
        values.put(COLUMN_AMNT_MEDS, (product.getAmnt_meds()));
        values.put(COLUMN_REMND_NO, (product.getRemind_no()));
        values.put(COLUMN_REMND_TIME, (product.getRemind_time()));
        values.put(COLUMN_STRT_DATE, (product.getStrt_date()));
        values.put(COLUMN_CONTINUS_DAY, (product.getContinues_day()));
        values.put(COLUMN_EVRY_DAY_WEEK, (product.getEvry_day_week()));
        values.put(COLUMN_REMINDER_FLAG,(product.getReminder_flag()));
        values.put(COLUMN_INTERVAL_TIME,(product.getInterval_time()));
        values.put(COLUMN_SKIPPED_DATE1,"false");
        values.put(COLUMN_SKIPPED_DATE2,"false");
        values.put(COLUMN_SKIPPED_DATE3,"false");
        values.put(COLUMN_SKIPPED_DATE4,"false");
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
    }
    public void updateProduct(Product product,String selected_id){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_DATE1,(product.getdate1()));
        values.put(COLUMN_DATE2, (product.getdate2()));
        values.put(COLUMN_DATE3, (product.getdate3()));
        values.put(COLUMN_DATE4, (product.getdate4()));
        values.put(COLUMN_TAKE1, (product.getTake1()));
        values.put(COLUMN_TAKE2, (product.getTake2()));
        values.put(COLUMN_TAKE3, (product.getTake3()));
        values.put(COLUMN_TAKE4, (product.getTake4()));
        values.put(COLUMN_DOASGE_NO, (product.getDosage_no()));
        values.put(COLUMN_DOSAGE_UNIT, (product.getDosage_unit()));
        values.put(COLUMN_INSTRUCN, (product.getInstruction()));
        values.put(COLUMN_DOC_NAME, (product.getDoc_name()));
        values.put(COLUMN_DOC_SPCL, (product.getDoc_specl()));
        values.put(COLUMN_DOC_CONT, (product.getDoc_cont()));
        values.put(COLUMN_AMNT_MEDS, (product.getAmnt_meds()));
        values.put(COLUMN_REMND_NO, (product.getRemind_no()));
        values.put(COLUMN_REMND_TIME, (product.getRemind_time()));
        values.put(COLUMN_STRT_DATE, (product.getStrt_date()));
        values.put(COLUMN_CONTINUS_DAY, (product.getContinues_day()));
        values.put(COLUMN_EVRY_DAY_WEEK, (product.getEvry_day_week()));
        values.put(COLUMN_REMINDER_FLAG,(product.getReminder_flag()));
        values.put(COLUMN_INTERVAL_TIME,(product.getInterval_time()));
        values.put(COLUMN_SKIPPED_DATE1,"false");
        values.put(COLUMN_SKIPPED_DATE2,"false");
        values.put(COLUMN_SKIPPED_DATE3,"false");
        values.put(COLUMN_SKIPPED_DATE4,"false");
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PRODUCTS, values, COLUMN_ID    + " = ?", new String[] { String.valueOf(selected_id)});
    }
    public Product findProduct(String med_id){
        String query = "Select * from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = "+med_id;
        SQLiteDatabase db = this.getWritableDatabase();
        Product mProduct = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String productName = cursor.getString(1);
            String date1 = (cursor.getString(2));
            String date2 = (cursor.getString(3));
            String date3 = (cursor.getString(4));
            String date4 = (cursor.getString(5));
            double take1 = Double.parseDouble(cursor.getString(6));
            double take2 = Double.parseDouble((cursor.getString(7)));
            double take3 = Double.parseDouble((cursor.getString(8)));
            double take4 = Double.parseDouble((cursor.getString(9)));
            int dos_no = Integer.parseInt((cursor.getString(10)));
            String dos_unit = cursor.getString(11);
            String instr = (cursor.getString(12));
            String do_name = (cursor.getString(13));
            String do_spcl = (cursor.getString(14));
            String do_cont = (cursor.getString(15));
            double amnt_meds = Double.parseDouble(cursor.getString(16));
            int rm_no = Integer.parseInt((cursor.getString(17)));
            String rm_time = (cursor.getString(18));
            String strt_date = (cursor.getString(19));
            int cont_day = Integer.parseInt((cursor.getString(20)));
            String ev_dy_week = (cursor.getString(21));
            String reminder_flag=(cursor.getString(22));
            String interval_time=(cursor.getString(23));
            String skpd1=(cursor.getString(24));
            String skpd2=(cursor.getString(25));
            String skpd3=(cursor.getString(26));
            String skpd4=(cursor.getString(27));
            mProduct=(new Product(id, productName, date1,date2,date3,date4,take1,take2,take3,take4,dos_no,dos_unit,instr,do_name,do_spcl,do_cont
                    ,amnt_meds,rm_no,rm_time,strt_date,cont_day,ev_dy_week,reminder_flag,interval_time,skpd1,skpd2,skpd3,skpd4));

        }
        cursor.close();
        return mProduct;
    }
    public void deleteDate(String date,String med_name,String med_id){
        //String query1="UPDATE "+TABLE_PRODUCTS+" SET "+TABLE_MEMBER_TITLE+"='"+title+"',"+TABLE_MEMBER_OTHER_DETAILS+"='"+other_details+"',"+TABLE_MEMBER_YEAR+"='"+year+"',"+TABLE_MEMBER_MONTH+"='"+month+"',"+TABLE_MEMBER_DAY+"='"+day+"' WHERE "+TABLE_MEMBER_ID+"='"+id+"'";
        String finded_column_name="";
        SQLiteDatabase db = this.getReadableDatabase();
    /*first checking --start*/
        String query1="select "+COLUMN_DATE1+" from "+TABLE_PRODUCTS+" where "+COLUMN_ID+"="+med_id;
        Cursor cursor = db.rawQuery(query1, null);
        if(cursor.moveToFirst()){
            do{
                String date_from_db = cursor.getString(cursor.getColumnIndex(COLUMN_DATE1));
                if(date_from_db!=null){
                    if(date_from_db.equalsIgnoreCase(date))
                        finded_column_name=COLUMN_DATE1;
                }

            }while (cursor.moveToNext());
        }
        cursor.close();
    /*first checking --end*/

/*second checking --start*/
        String query2="select "+COLUMN_DATE2+" from "+TABLE_PRODUCTS+" where "+COLUMN_ID+"="+med_id;
        Cursor cursor2 = db.rawQuery(query2, null);
        if(cursor2.moveToFirst()){
            do{
                String date_from_db = cursor2.getString(cursor2.getColumnIndex(COLUMN_DATE2));
                if(date_from_db!=null){
                    if(date_from_db.equalsIgnoreCase(date))
                        finded_column_name=COLUMN_DATE2;
                }
            }while (cursor2.moveToNext());
        }
        cursor2.close();
    /*second checking --end*/



    /*second checking --start*/
        String query3="select "+COLUMN_DATE3+" from "+TABLE_PRODUCTS+" where "+COLUMN_ID+"="+med_id;
        Cursor cursor3 = db.rawQuery(query3, null);
        if(cursor3.moveToFirst()){
            do{
                String date_from_db = cursor3.getString(cursor3.getColumnIndex(COLUMN_DATE3));
                if(date_from_db!=null){
                    if(date_from_db.equalsIgnoreCase(date))
                        finded_column_name=COLUMN_DATE3;
                }
            }while (cursor3.moveToNext());
        }
        cursor3.close();
    /*second checking --end*/


    /*second checking --start*/
        String query4="select "+COLUMN_DATE4+" from "+TABLE_PRODUCTS+" where "+COLUMN_ID+"="+med_id;
        Cursor cursor4 = db.rawQuery(query4, null);
        if(cursor4.moveToFirst()){
            do{
                String date_from_db = cursor4.getString(cursor4.getColumnIndex(COLUMN_DATE4));
                if(date_from_db!=null){
                    if(date_from_db.equalsIgnoreCase(date))
                        finded_column_name=COLUMN_DATE4;
                }
            }while (cursor4.moveToNext());
        }
        cursor4.close();
    /*second checking --end*/

    boolean only_one_date_exist=false;

    /*checking if only one date remains --start*/
        String query5="select "+COLUMN_DATE1+","+COLUMN_DATE2+","+COLUMN_DATE3+","+COLUMN_DATE4+" from "+TABLE_PRODUCTS+" where "+COLUMN_ID+"="+med_id;
        Cursor cursor5 = db.rawQuery(query5, null);
        if(cursor5.moveToFirst()){
            do{
                String date1 = cursor5.getString(cursor5.getColumnIndex(COLUMN_DATE1));
                String date2 = cursor5.getString(cursor5.getColumnIndex(COLUMN_DATE2));
                String date3 = cursor5.getString(cursor5.getColumnIndex(COLUMN_DATE3));
                String date4 = cursor5.getString(cursor5.getColumnIndex(COLUMN_DATE4));

                System.out.println("&&&&&&& date 1 : "+date1);
                System.out.println("&&&&&&& date 2 : "+date2);
                System.out.println("&&&&&&& date 3 : "+date3);
                System.out.println("&&&&&&& date 4 : "+date4);

                int flg=0;
                if(date1==null || date1=="" || date1.length()==0 || date1.equals("null"))
                    flg++;
                if(date2==null || date2=="" || date2.length()==0 || date2.equals("null"))
                    flg++;
                if(date3==null || date3=="" || date3.length()==0 || date3.equals("null"))
                    flg++;
                if(date4==null || date4=="" || date4.length()==0 || date4.equals("null"))
                    flg++;


                System.out.println("flg : "+flg);
                if(flg>=3){
                    only_one_date_exist=true;
                }



            }while (cursor5.moveToNext());
        }
        cursor5.close();
    /*checking if only one date remains --end*/


if(only_one_date_exist==true){
    //deleting entire row--start
    SQLiteDatabase dbnew = this.getWritableDatabase();
    dbnew.beginTransaction();
    try {
        String update_query = "delete from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "='" + med_id + "'";
        System.out.println("&&&&&&&&&&   delete query : " + update_query);
        dbnew.execSQL(update_query);
        dbnew.setTransactionSuccessful();
    } catch (Exception e) {
        e.printStackTrace();

    } finally {
        dbnew.endTransaction();
        dbnew.close();
    }
    //deleting entire row--end

}else {
    System.out.println("finded_column_name : " + finded_column_name);
    SQLiteDatabase dbnew = this.getWritableDatabase();
    dbnew.beginTransaction();
    try {
        String update_query = "UPDATE " + TABLE_PRODUCTS + " SET " + finded_column_name + "='" + null + "' WHERE " + COLUMN_ID + "='" + med_id + "'";
        System.out.println("&&&&&&&&&&   update query : " + update_query);
        dbnew.execSQL(update_query);
        dbnew.setTransactionSuccessful();


    } catch (Exception e) {
        e.printStackTrace();

    } finally {
        dbnew.endTransaction();
        dbnew.close();
    }
}


    }
    public void rescheduleDate(String date,String[] med_ids,String new_date){
        System.out.println("inside rescheduleDate");
        System.out.println("date : "+date);
        System.out.println("med_ids : "+med_ids.toString());
        System.out.println("new_date : "+new_date);



        for(int i=0;i<med_ids.length;i++) {//changind date for each ids in the array
            String finded_column_name = "";
            SQLiteDatabase db = this.getReadableDatabase();
    /*first checking --start*/
            String query1 = "select " + COLUMN_DATE1 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor = db.rawQuery(query1, null);
            if (cursor.moveToFirst()) {
                do {
                    String date_from_db = cursor.getString(cursor.getColumnIndex(COLUMN_DATE1));
                    if(date_from_db!=null){
                        if(date_from_db.equalsIgnoreCase(date))
                            finded_column_name=COLUMN_DATE1;
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
    /*first checking --end*/

/*second checking --start*/
            String query2 = "select " + COLUMN_DATE2 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor2 = db.rawQuery(query2, null);
            if (cursor2.moveToFirst()) {
                do {
                    String date_from_db = cursor2.getString(cursor2.getColumnIndex(COLUMN_DATE2));
                    if(date_from_db!=null){
                        if(date_from_db.equalsIgnoreCase(date))
                            finded_column_name=COLUMN_DATE2;
                    }
                } while (cursor2.moveToNext());
            }
            cursor2.close();
    /*second checking --end*/

    /*second checking --start*/
            String query3 = "select " + COLUMN_DATE3 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor3 = db.rawQuery(query3, null);
            if (cursor3.moveToFirst()) {
                do {
                    String date_from_db = cursor3.getString(cursor3.getColumnIndex(COLUMN_DATE3));
                    if(date_from_db!=null){
                        if(date_from_db.equalsIgnoreCase(date))
                            finded_column_name=COLUMN_DATE3;
                    }
                } while (cursor3.moveToNext());
            }
            cursor3.close();
    /*second checking --end*/

    /*second checking --start*/
            String query4 = "select " + COLUMN_DATE4 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor4 = db.rawQuery(query4, null);
            if (cursor4.moveToFirst()) {
                do {
                    String date_from_db = cursor4.getString(cursor4.getColumnIndex(COLUMN_DATE4));
                    if(date_from_db!=null){
                        if(date_from_db.equalsIgnoreCase(date))
                            finded_column_name=COLUMN_DATE4;
                    }
                } while (cursor4.moveToNext());
            }
            cursor4.close();
    /*second checking --end*/

    /*checking if only one date remains --end*/

            System.out.println("finded_column_name : " + finded_column_name);
            if(finded_column_name!=null && finded_column_name!="" && finded_column_name.length()!=0) {
                SQLiteDatabase dbnew = this.getWritableDatabase();
                dbnew.beginTransaction();
                try {
                    String update_query = "UPDATE " + TABLE_PRODUCTS + " SET " + finded_column_name + "='" + new_date + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    System.out.println("&&&&&&&&&&   update query : " + update_query);
                    dbnew.execSQL(update_query);
                    dbnew.setTransactionSuccessful();


                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    dbnew.endTransaction();
                    dbnew.close();
                }
            }

        }

    }

    public String getMedDosageById(String med_id){

            String query = "select "+COLUMN_DOASGE_NO+","+COLUMN_DOSAGE_UNIT+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = "+med_id;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
        String value_to_return="";
            if (cursor.moveToFirst()){

                int dos_no = Integer.parseInt((cursor.getString(0)));
                String dos_unit = cursor.getString(1);
                if(dos_no!=0){
                    value_to_return="( "+Integer.toString(dos_no)+" "+dos_unit+" )";
                }
                else {
                    value_to_return="";
                }

            }
            cursor.close();
            return value_to_return;

    }

    public void decermentMedCountOnTake(String date,String[] med_ids){

/*
        for(int i=0;i<med_ids.length;i++){


            String query = "select "+COLUMN_TAKE1+","+COLUMN_DOSAGE_UNIT+","+COLUMN_AMNT_MEDS+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = "+med_ids[i];
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()){

                double dos_no = Double.parseDouble(((cursor.getString(0))));
                String dos_unit = cursor.getString(1);
                double amount_of_meds =Double.parseDouble((cursor.getString(2)));
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("dos_no : "+dos_no);
                System.out.println("dos_unit : "+dos_unit);
                System.out.println("amount_of_meds : "+amount_of_meds);
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

                if(amount_of_meds>0) {
                    double medicin_after_take = amount_of_meds - dos_no;
                    System.out.println("medicin_after_take : " + medicin_after_take);


                SQLiteDatabase dbnew = this.getWritableDatabase();
                dbnew.beginTransaction();
                try {
                    String update_query = "UPDATE " + TABLE_PRODUCTS + " SET "+COLUMN_AMNT_MEDS+" = '" + medicin_after_take + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    System.out.println("&&&&&&&&&&   update query : " + update_query);
                    dbnew.execSQL(update_query);
                    dbnew.setTransactionSuccessful();

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    dbnew.endTransaction();
                    dbnew.close();
                }
                }
            }
            cursor.close();

        }
*/
        for(int i=0;i<med_ids.length;i++) {//changind date for each ids in the array
            String finded_column_name = "";
            double medicin_after_take=0.0;
            SQLiteDatabase db = this.getWritableDatabase();
    /*first checking --start*/
            String query1 = "select "+COLUMN_TAKE1+","+COLUMN_DATE1+","+COLUMN_AMNT_MEDS+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = "+med_ids[i];
            Cursor cursor = db.rawQuery(query1, null);
            if (cursor.moveToFirst()) {
                do {
                    double dos_no = Double.parseDouble(((cursor.getString(0))));
                    String dos_unit = cursor.getString(1);
                    double amount_of_meds =Double.parseDouble((cursor.getString(2)));
                    String date_from_db = cursor.getString(cursor.getColumnIndex(COLUMN_DATE1));
                    if(!Objects.equals(date_from_db, "")) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE1;
                        if(amount_of_meds>=0.0) {
                            medicin_after_take = amount_of_meds - dos_no;
                            System.out.println("medicin_after_take : " + medicin_after_take);
                            System.out.println("dos_no : " + dos_no);
                            System.out.println("amount_of_meds : " + amount_of_meds);
                        }
                    }

                    } while (cursor.moveToNext());
            }
            cursor.close();
    /*first checking --end*/

/*second checking --start*/
            String query2 = "select "+COLUMN_TAKE2+","+COLUMN_DATE2+","+COLUMN_AMNT_MEDS+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = "+med_ids[i];
            Cursor cursor2 = db.rawQuery(query2, null);
            if (cursor2.moveToFirst()) {
                do {
                    double dos_no = Double.parseDouble(((cursor2.getString(0))));
                    String dos_unit = cursor2.getString(1);
                    double amount_of_meds =Double.parseDouble((cursor2.getString(2)));
                    String date_from_db = cursor2.getString(cursor2.getColumnIndex(COLUMN_DATE2));
                    if(!Objects.equals(date_from_db, "")) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE2;
                        if(amount_of_meds>=0.0) {
                            medicin_after_take = amount_of_meds - dos_no;
                            System.out.println("medicin_after_take : " + medicin_after_take);
                        }
                    }

                } while (cursor2.moveToNext());
            }
            cursor2.close();
    /*second checking --end*/

    /*second checking --start*/
            String query3 = "select "+COLUMN_TAKE3+","+COLUMN_DATE3+","+COLUMN_AMNT_MEDS+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = "+med_ids[i];
            Cursor cursor3 = db.rawQuery(query3, null);
            if (cursor3.moveToFirst()) {
                do {
                    double dos_no = Double.parseDouble(((cursor3.getString(0))));
                    String dos_unit = cursor3.getString(1);
                    double amount_of_meds =Double.parseDouble((cursor3.getString(2)));
                    String date_from_db = cursor3.getString(cursor3.getColumnIndex(COLUMN_DATE3));
                    if(!Objects.equals(date_from_db, "")) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE3;
                        if(amount_of_meds>=0.0) {
                            medicin_after_take = amount_of_meds - dos_no;
                            System.out.println("medicin_after_take : " + medicin_after_take);
                        }
                    }

                } while (cursor3.moveToNext());
            }
            cursor3.close();
    /*second checking --end*/

    /*second checking --start*/
            String query4 ="select "+COLUMN_TAKE4+","+COLUMN_DATE4+","+COLUMN_AMNT_MEDS+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = "+med_ids[i];
            Cursor cursor4 = db.rawQuery(query4, null);
            if (cursor4.moveToFirst()) {
                do {
                    double dos_no = Double.parseDouble(((cursor4.getString(0))));
                    String dos_unit = cursor4.getString(1);
                    double amount_of_meds =Double.parseDouble((cursor4.getString(2)));
                    String date_from_db = cursor4.getString(cursor4.getColumnIndex(COLUMN_DATE4));
                    if(!Objects.equals(date_from_db, "")) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE4;
                        if(amount_of_meds>=0.0) {
                            medicin_after_take = amount_of_meds - dos_no;
                            System.out.println("medicin_after_take : " + medicin_after_take);
                        }
                    }

                } while (cursor4.moveToNext());
            }
            cursor4.close();
    /*second checking --end*/

    /*checking if only one date remains --end*/

            System.out.println("finded_column_name : " + finded_column_name);
            if(finded_column_name!=null && finded_column_name!="" && finded_column_name.length()!=0) {
                SQLiteDatabase dbnew = this.getWritableDatabase();
                dbnew.beginTransaction();
                try {
                    String update_query="";
                    if(finded_column_name.equalsIgnoreCase(COLUMN_DATE1)) {

                        update_query = "UPDATE " + TABLE_PRODUCTS + " SET "+COLUMN_AMNT_MEDS+" = '" + medicin_after_take + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";

                    }
                    else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE2)) {
                        update_query = "UPDATE " + TABLE_PRODUCTS + " SET "+COLUMN_AMNT_MEDS+" = '" + medicin_after_take + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    }
                    else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE3)) {
                        update_query = "UPDATE " + TABLE_PRODUCTS + " SET "+COLUMN_AMNT_MEDS+" = '" + medicin_after_take + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    }
                    else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE4)) {
                        update_query = "UPDATE " + TABLE_PRODUCTS + " SET "+COLUMN_AMNT_MEDS+" = '" + medicin_after_take + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    }
                    System.out.println("&&&&&&&&&&   update query : " + update_query);
                    dbnew.execSQL(update_query);
                    dbnew.setTransactionSuccessful();

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    dbnew.endTransaction();
                    dbnew.close();
                }
            }

        }

    }

    public void setSkipStatus(String date,String[] med_ids,String skip_status){
        System.out.println("inside setSkipStatus");
        System.out.println("date : "+date);
        System.out.println("med_ids : "+med_ids.toString());
        System.out.println("skip_status : "+skip_status);



        for(int i=0;i<med_ids.length;i++) {//changind date for each ids in the array
            String finded_column_name = "";
            SQLiteDatabase db = this.getWritableDatabase();
    /*first checking --start*/
            String query1 = "select " + COLUMN_DATE1 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor = db.rawQuery(query1, null);
            if (cursor.moveToFirst()) {
                do {
                    String date_from_db = cursor.getString(cursor.getColumnIndex(COLUMN_DATE1));
                    if(date_from_db!=null) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE1;
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
    /*first checking --end*/

/*second checking --start*/
            String query2 = "select " + COLUMN_DATE2 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor2 = db.rawQuery(query2, null);
            if (cursor2.moveToFirst()) {
                do {
                    String date_from_db = cursor2.getString(cursor2.getColumnIndex(COLUMN_DATE2));
                    if(date_from_db!=null) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE2;
                    }
                } while (cursor2.moveToNext());
            }
            cursor2.close();
    /*second checking --end*/

    /*second checking --start*/
            String query3 = "select " + COLUMN_DATE3 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor3 = db.rawQuery(query3, null);
            if (cursor3.moveToFirst()) {
                do {
                    String date_from_db = cursor3.getString(cursor3.getColumnIndex(COLUMN_DATE3));
                    if(date_from_db!=null) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE3;
                    }
                } while (cursor3.moveToNext());
            }
            cursor3.close();
    /*second checking --end*/

    /*second checking --start*/
            String query4 = "select " + COLUMN_DATE4 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[i];
            Cursor cursor4 = db.rawQuery(query4, null);
            if (cursor4.moveToFirst()) {
                do {
                    String date_from_db = cursor4.getString(cursor4.getColumnIndex(COLUMN_DATE4));
                    if(date_from_db!=null) {
                        if (date_from_db.equalsIgnoreCase(date))
                            finded_column_name = COLUMN_DATE4;
                    }
                } while (cursor4.moveToNext());
            }
            cursor4.close();
    /*second checking --end*/

    /*checking if only one date remains --end*/

            System.out.println("finded_column_name : " + finded_column_name);
            if(finded_column_name!=null && finded_column_name!="" && finded_column_name.length()!=0) {
                SQLiteDatabase dbnew = this.getWritableDatabase();
                dbnew.beginTransaction();
                try {
                    String update_query="";
                    if(finded_column_name.equalsIgnoreCase(COLUMN_DATE1)) {
                         update_query = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_SKIPPED_DATE1 + "='" + skip_status + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    }
                    else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE2)) {
                        update_query = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_SKIPPED_DATE2 + "='" + skip_status + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    }
                    else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE3)) {
                        update_query = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_SKIPPED_DATE3 + "='" + skip_status + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    }
                    else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE4)) {
                        update_query = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_SKIPPED_DATE4 + "='" + skip_status + "' WHERE " + COLUMN_ID + "='" + med_ids[i] + "'";
                    }
                    System.out.println("&&&&&&&&&&   update query : " + update_query);
                    dbnew.execSQL(update_query);
                    dbnew.setTransactionSuccessful();

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    dbnew.endTransaction();
                    dbnew.close();
                }
            }

        }

    }


    public String checkIfSkipped(String date,String[] med_ids){
        System.out.println("inside checkIfSkipped");
        System.out.println("date : "+date);
        System.out.println("med_ids : "+med_ids.toString());

        String return_status="";
        //for(int i=0;i<med_ids.length;i++) {//changind date for each ids in the array
        String finded_column_name = "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
/*first checking --start*/
        String query1 = "select " + COLUMN_DATE1 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[0];
        System.out.println(query1);
        Cursor cursor = db.rawQuery(query1, null);
        if (cursor.moveToFirst()) {
            do {
                String date_from_db = cursor.getString(cursor.getColumnIndex(COLUMN_DATE1));
                System.out.println("date_from_db : "+date_from_db);
                if(date_from_db!=null) {
                    if (date_from_db.equalsIgnoreCase(date))
                        finded_column_name = COLUMN_DATE1;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
/*first checking --end*/

/*second checking --start*/
        String query2 = "select " + COLUMN_DATE2 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[0];
        System.out.println(query2);
        Cursor cursor2 = db.rawQuery(query2, null);
        if (cursor2.moveToFirst()) {
            do {
                String date_from_db = cursor2.getString(cursor2.getColumnIndex(COLUMN_DATE2));
                System.out.println("date_from_db : "+date_from_db);
                if(date_from_db!=null) {
                    if (date_from_db.equalsIgnoreCase(date))
                        finded_column_name = COLUMN_DATE2;
                }
            } while (cursor2.moveToNext());
        }
        cursor2.close();
/*second checking --end*/

/*second checking --start*/
        String query3 = "select " + COLUMN_DATE3 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[0];
        System.out.println(query3);
        Cursor cursor3 = db.rawQuery(query3, null);
        if (cursor3.moveToFirst()) {
            do {
                String date_from_db = cursor3.getString(cursor3.getColumnIndex(COLUMN_DATE3));
                System.out.println("date_from_db : "+date_from_db);
                if(date_from_db!=null) {
                    if (date_from_db.equalsIgnoreCase(date))
                        finded_column_name = COLUMN_DATE3;
                }
            } while (cursor3.moveToNext());
        }
        cursor3.close();
/*second checking --end*/

/*second checking --start*/
        String query4 = "select " + COLUMN_DATE4 + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + med_ids[0];
        System.out.println(query4);
        Cursor cursor4 = db.rawQuery(query4, null);
        if (cursor4.moveToFirst()) {
            do {
                String date_from_db = cursor4.getString(cursor4.getColumnIndex(COLUMN_DATE4));
                System.out.println("date_from_db : "+date_from_db);
                if(date_from_db!=null) {
                    if (date_from_db.equalsIgnoreCase(date))
                        finded_column_name = COLUMN_DATE4;
                }
            } while (cursor4.moveToNext());
        }
        cursor4.close();
/*second checking --end*/

/*checking if only one date remains --end*/

        System.out.println("finded_column_name : " + finded_column_name);
        Cursor cursor5 = null;

        if(finded_column_name!=null && finded_column_name!="" && finded_column_name.length()!=0) {

            try {
                String query="";
                if(finded_column_name.equalsIgnoreCase(COLUMN_DATE1)) {
                    query = "select "+COLUMN_SKIPPED_DATE1+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "='" + med_ids[0] + "'";
                    cursor5 = db.rawQuery(query, null);
                    if(cursor5.getCount()>0) {
                        while (cursor5.moveToNext()) {
                            return_status= cursor5.getString(0);
                        }
                    }

                }
                else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE2)) {
                    query = "select "+COLUMN_SKIPPED_DATE2+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "='" + med_ids[0] + "'";
                    cursor5 = db.rawQuery(query, null);
                    if(cursor5.getCount()>0) {
                        while (cursor5.moveToNext()) {
                            return_status= cursor5.getString(0);
                        }
                    }
                }
                else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE3)) {
                    query = "select "+COLUMN_SKIPPED_DATE3+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "='" + med_ids[0] + "'";
                    cursor5 = db.rawQuery(query, null);
                    if(cursor5.getCount()>0) {
                        while (cursor5.moveToNext()) {
                            return_status= cursor5.getString(0);
                        }
                    }
                }
                else if(finded_column_name.equalsIgnoreCase(COLUMN_DATE4)) {
                    query = "select "+COLUMN_SKIPPED_DATE4+" from " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "='" + med_ids[0] + "'";
                    cursor5 = db.rawQuery(query, null);
                    if(cursor5.getCount()>0) {
                        while (cursor5.moveToNext()) {
                            return_status= cursor5.getString(0);
                        }
                    }
                }
                System.out.println("&&&&&&&&&&   select query : " + query);
                System.out.println("&&&&&&&&&&   return_status : " + return_status);
                //cursor5.close();

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                db.endTransaction();
                db.close();
            }
        }
        // }
        return return_status;
    }
public String getIntervelTime(String id){
    String finded_time = "";
    SQLiteDatabase db = this.getWritableDatabase();
    /*first checking --start*/
    String query1 = "select " + COLUMN_INTERVAL_TIME + " from " + TABLE_PRODUCTS + " where " + COLUMN_ID + "=" + id;
    Cursor cursor = db.rawQuery(query1, null);
    if (cursor.moveToFirst()) {
        do {
            String int_time = cursor.getString(cursor.getColumnIndex(COLUMN_INTERVAL_TIME));
            if(int_time!=null) {

                    finded_time = int_time;
            }
        } while (cursor.moveToNext());
    }
    cursor.close();
    /*first checking --end*/
    return finded_time;
}
}