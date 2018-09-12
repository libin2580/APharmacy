package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meridian.cybraum.zulekha.alexis.alexispharmacy.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ansal on 10/11/2017.
 */

public class Days_Activity extends AppCompatActivity {
    String day_section,day_time;
    TextView Zulekha_day_text,Date,Day_time;
    RecyclerView recyclerView;
    private SqliteDatabase mDatabase;
    ArrayList<String> sameDateProductNames;
    ArrayList<MedicinModel>medicinArraylist;
    //int medicin_id=0;
    String medicin_date="";
    MedicinModel mm;
    List<Product> allProducts;
    String todays_date;
    List<Product>productsFromDb;
    SharedPreferences preference_days;
    LinearLayout Back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication_day_sections);

        recyclerView = (RecyclerView)findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);
        Back=(LinearLayout)findViewById(R.id.menu);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Days_Activity.this, Medication_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        preference_days = getApplicationContext().getSharedPreferences("day_sectcion", MODE_PRIVATE);
        productsFromDb=mDatabase.listProducts();

       // List<Product> allProducts = mDatabase.listProducts();
        allProducts=new ArrayList<>();

        for(Product p:productsFromDb){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("p.getId() : "+p.getId());
            System.out.println("p.getName() : "+p.getName());
            System.out.println("p.getdate1() : "+p.getdate1());
            System.out.println("p.getdate2() : "+p.getdate2());
            System.out.println("p.getdate3() : "+p.getdate3());
            System.out.println("p.getdate4() : "+p.getdate4());
            System.out.println("p.getTake1() : "+p.getTake1());
            System.out.println("p.getTake2() : "+p.getTake2());
            System.out.println("p.getTake3() : "+p.getTake3());
            System.out.println("p.getTake4() : "+p.getTake4());
            System.out.println("p.getDosage_no() : "+p.getDosage_no());
            System.out.println("p.getDosage_unit() : "+p.getDosage_unit());
            System.out.println("p.getInstruction() : "+p.getInstruction());
            System.out.println("p.getDoc_name() : "+p.getDoc_name());
            System.out.println("p.getDoc_specl() : "+p.getDoc_specl());
            System.out.println("p.getDoc_cont() : "+p.getDoc_cont());
            System.out.println("p.getAmnt_meds() : "+p.getAmnt_meds());
            System.out.println("p.getRemind_no() : "+p.getRemind_no());
            System.out.println("p.getRemind_time() : "+p.getRemind_time());
            System.out.println("p.getStrt_date() : "+p.getStrt_date());
            System.out.println("p.getContinues_day() : "+p.getContinues_day());
            System.out.println("p.getEvry_day_week() : "+p.getEvry_day_week());
            System.out.println("p.getReminder_flag() : "+p.getReminder_flag());
            System.out.println("p.getInterval_time() : "+p.getInterval_time());
            System.out.println("p.getSkipped_date1() : "+p.getSkipped_date1());
            System.out.println("p.getSkipped_date2() : "+p.getSkipped_date2());
            System.out.println("p.getSkipped_date3() : "+p.getSkipped_date3());
            System.out.println("p.getSkipped_date4() : "+p.getSkipped_date4());
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        }

        String pref_tdy_date = preference_days.getString("todays_date", null);
        System.out.println("pref_tdy_date : "+pref_tdy_date);
        if(pref_tdy_date!=null){
            todays_date=pref_tdy_date;
        }else{
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            todays_date = format1.format(cal.getTime());

        }

        System.out.println("todays_date : "+todays_date);
        LoadMedssss(todays_date);





    }

    boolean contains_date1(ArrayList<MedicinModel> list, String date) {
        for (MedicinModel item : list) {
            if (item.getDate1() != null) {
                if (item.getDate1().equalsIgnoreCase(date)) {
                    System.out.println(">>>>>>>>> inside item.getDate1().equalsIgnoreCase(date)");
                    return true;
                }
            }
        }
        return false;
    }
    boolean contains_date2(ArrayList<MedicinModel> list, String date) {
        for (MedicinModel item : list) {
            if (item.getDate2() != null) {
                if (item.getDate2().equalsIgnoreCase(date)) {
                    System.out.println(">>>>>>>>> inside item.getDate2().equalsIgnoreCase(date)");
                    return true;
                }
            }
        }
        return false;
    }
    boolean contains_date3(ArrayList<MedicinModel> list, String date) {
        for (MedicinModel item : list) {
            if (item.getDate3() != null) {
                if (item.getDate3().equalsIgnoreCase(date)) {
                    System.out.println(">>>>>>>>> inside item.getDate3().equalsIgnoreCase(date)");
                    return true;
                }
            }
        }
        return false;
    }
    boolean contains_date4(ArrayList<MedicinModel> list, String date) {
        for (MedicinModel item : list) {
            if (item.getDate4() != null) {
                if (item.getDate4().equalsIgnoreCase(date)) {
                    System.out.println(">>>>>>>>> inside item.getDate4().equalsIgnoreCase(date)");
                    return true;
                }
            }
        }
        return false;
    }



    public void LoadMedssss(String selected_date)
    {

        todays_date=selected_date;

        for(Product p:productsFromDb){
            Product modl;
            //if 'DURATION' is selected 'Continuous' --start
            if(p.getContinues_day()==1000){
                //if 'DAYS' is selected 'Every day' --start
                if(p.getEvry_day_week().equalsIgnoreCase("every_day")){
                    System.out.println("%%%%%%%%%% inside 'continuous' -> 'every day'");
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date current_date=null;
                    java.util.Date strt_date = null;
                    try {
                        strt_date=formatter.parse(p.getStrt_date());
                        current_date=formatter.parse(todays_date);
                        System.out.println("%%%%%%%%%% startdate : "+strt_date);
                        System.out.println("%%%%%%%%%% current_date : "+current_date);
                        System.out.println("%%%%%%%%%% current_date.after(strt_date) : "+current_date.after(strt_date));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(strt_date!=null && current_date!=null){
                        if(strt_date.equals(current_date) || current_date.after(strt_date)){
                            modl=new Product(p.getId(), p.getName(), p.getdate1(),p.getdate2(),p.getdate3(),p.getdate4(),p.getTake1(),p.getTake2(),p.getTake3(),p.getTake4(),p.getDosage_no(),p.getDosage_unit(),p.getInstruction(),p.getDoc_name(),p.getDoc_specl(),p.getDoc_cont()
                                    ,p.getAmnt_meds(),p.getRemind_no(),p.getRemind_time(),p.getStrt_date(),p.getContinues_day(),p.getEvry_day_week(),p.getReminder_flag(),p.getInterval_time(),p.getSkipped_date1(),p.getSkipped_date2(),p.getSkipped_date3(),p.getSkipped_date4());
                            allProducts.add(modl);
                        }
                    }
                }
                //if 'DAYS' is selected 'Every day' --end

                //if 'DAYS' is selected 'Specific days of week' --start
                else{
                    System.out.println("%%%%%%%%%% inside 'continuous' -> 'specific days of week'");

                    String[] days_of_week = p.getEvry_day_week().split(",");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date current_date=null;
                    java.util.Date strt_date = null;

                    String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                    Date today_dt=new Date();

                    SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                    String selected_day="";
                    String tody_day=simpleDateformatnew.format(today_dt).toString();
                    Date first_date=null;
                    for (int i=0;i<days_of_week.length;i++) {
                        selected_day = days_of_week[i];
                        System.out.println("???????????????????? -- " + selected_day + ".equalsIgnoreCase(" + tody_day + ")");
                        if (selected_day.equalsIgnoreCase(tody_day)) {
                            first_date = new Date();
                        } else {
                            for (int u = 0; u < days.length; u++) {
                                System.out.println("???????????????????? " + selected_day + ".equalsIgnoreCase(" + days[u] + ")");

                                if (selected_day.equalsIgnoreCase(days[u])) {
                                    System.out.println("???????????????????? inside equals");
                                    Calendar calnew = Calendar.getInstance();
                                    calnew.setTime(today_dt);
                                    if(days[u].equalsIgnoreCase("sun"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("mon"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("tue"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("wed"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("thu"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("fri"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("sat"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    first_date = calnew.getTime();
                                }
                            }
                        }
                        System.out.println("%%%%%%%%%% first_date : "+first_date);

                        //////////////////////////////////////////////
                        try {
                            String founded_date=formatter.format(first_date);
                            strt_date=formatter.parse(founded_date);
                            current_date=formatter.parse(todays_date);
                            System.out.println("%%%%%%%%%% startdate : "+strt_date);
                            System.out.println("%%%%%%%%%% current_date : "+current_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        java.util.Date[] nxt_dates=new Date[365];
                        java.util.Date array_added_date=strt_date;
                        Calendar ading_cal=Calendar.getInstance();
                        for (int k = 0; k < 365; k++) {
                            ading_cal.setTime(array_added_date);
                            nxt_dates[k] = ading_cal.getTime();
                            ading_cal.add(Calendar.DATE, 7);
                            array_added_date = ading_cal.getTime();
                        }
                        System.out.println("%%%%%%%%%% nxt_dates are ");
                        for (int k = 0; k < nxt_dates.length; k++) {
                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                            if(nxt_dates[k].equals(current_date)){
                                modl=new Product(p.getId(), p.getName(), p.getdate1(),p.getdate2(),p.getdate3(),p.getdate4(),p.getTake1(),p.getTake2(),p.getTake3(),p.getTake4(),p.getDosage_no(),p.getDosage_unit(),p.getInstruction(),p.getDoc_name(),p.getDoc_specl(),p.getDoc_cont()
                                        ,p.getAmnt_meds(),p.getRemind_no(),p.getRemind_time(),p.getStrt_date(),p.getContinues_day(),p.getEvry_day_week(),p.getReminder_flag(),p.getInterval_time(),p.getSkipped_date1(),p.getSkipped_date2(),p.getSkipped_date3(),p.getSkipped_date4());
                                allProducts.add(modl);
                            }
                        }
                        /////////////////////////////////////////////

                    }

                }
                //if 'DAYS' is selected 'Specific days of week' --end
            }
            //if 'DURATION' is selected 'Continuous' --end

            //if 'DURATION' is selected 'No of days' --start
            else{
                //if 'DAYS' is selected 'Every day' --start
                if(p.getEvry_day_week().equalsIgnoreCase("every_day")){
                    System.out.println("%%%%%%%%%% inside 'No of days' -> 'every day'");
                    System.out.println("%%%%%%%%%% p.getContinues_day() : "+p.getContinues_day());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date current_date=null;
                    java.util.Date strt_date = null;
                    try {
                        strt_date=formatter.parse(p.getStrt_date());
                        current_date=formatter.parse(todays_date);
                        System.out.println("%%%%%%%%%% startdate : "+strt_date);
                        System.out.println("%%%%%%%%%% current_date : "+current_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }




                    //finding the next days date --start
                    java.util.Date[] nxt_dates=new Date[p.getContinues_day()-1];
                    java.util.Date array_added_date=strt_date;
                    Calendar ading_cal=Calendar.getInstance();
                    if(p.getContinues_day()-1!=0) {//if user selected 'no of days' as '1' , 'p.getContinues_day()-1' will be '0'
                        for (int i = 0; i < p.getContinues_day() - 1; i++) {
                            ading_cal.setTime(array_added_date);
                            ading_cal.add(Calendar.DATE, 1);
                            nxt_dates[i] = ading_cal.getTime();
                            array_added_date = ading_cal.getTime();
                        }
                        System.out.println("%%%%%%%%%% nxt_dates are ");
                        for (int i = 0; i < nxt_dates.length; i++) {
                            System.out.println("%%%%%%%%%% nxt_dates[" + i + "] : " + nxt_dates[i]);
                        }
                    }
                    //finding the next days date --end

                    if(strt_date!=null && current_date!=null){
                        if(strt_date.equals(current_date)){
                            modl=new Product(p.getId(), p.getName(), p.getdate1(),p.getdate2(),p.getdate3(),p.getdate4(),p.getTake1(),p.getTake2(),p.getTake3(),p.getTake4(),p.getDosage_no(),p.getDosage_unit(),p.getInstruction(),p.getDoc_name(),p.getDoc_specl(),p.getDoc_cont()
                                    ,p.getAmnt_meds(),p.getRemind_no(),p.getRemind_time(),p.getStrt_date(),p.getContinues_day(),p.getEvry_day_week(),p.getReminder_flag(),p.getInterval_time(),p.getSkipped_date1(),p.getSkipped_date2(),p.getSkipped_date3(),p.getSkipped_date4());
                            allProducts.add(modl);
                        }
                        else{
                            if(nxt_dates.length>0) {
                                for (int i = 0; i < nxt_dates.length; i++) {
                                    if (current_date.equals(nxt_dates[i])) {
                                        modl = new Product(p.getId(), p.getName(), p.getdate1(), p.getdate2(), p.getdate3(), p.getdate4(), p.getTake1(), p.getTake2(), p.getTake3(), p.getTake4(), p.getDosage_no(), p.getDosage_unit(), p.getInstruction(), p.getDoc_name(), p.getDoc_specl(), p.getDoc_cont()
                                                , p.getAmnt_meds(), p.getRemind_no(), p.getRemind_time(), p.getStrt_date(), p.getContinues_day(), p.getEvry_day_week(), p.getReminder_flag(), p.getInterval_time(), p.getSkipped_date1(), p.getSkipped_date2(), p.getSkipped_date3(), p.getSkipped_date4());
                                        allProducts.add(modl);
                                    }
                                }
                            }
                        }
                    }
                }
                //if 'DAYS' is selected 'Every day' --end

                //if 'DAYS' is selected 'Specific days of week' --start
                else{
                    System.out.println("%%%%%%%%%% inside 'No of days' -> 'specific days of week'");
                    String[] days_of_week = p.getEvry_day_week().split(",");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date current_date=null;
                    java.util.Date strt_date = null;
                    java.util.Date founded_date = null;

                    String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                    Date today_dt=new Date();

                    SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                    String selected_day="";
                    String tody_day=simpleDateformatnew.format(today_dt).toString();
                    Date first_date=null;
                    for (int i=0;i<days_of_week.length;i++) {
                        selected_day = days_of_week[i];
                        System.out.println("???????????????????? -- " + selected_day + ".equalsIgnoreCase(" + tody_day + ")");
                        if (selected_day.equalsIgnoreCase(tody_day)) {
                            first_date = new Date();
                        } else {
                            for (int u = 0; u < days.length; u++) {
                                System.out.println("???????????????????? " + selected_day + ".equalsIgnoreCase(" + days[u] + ")");

                                if (selected_day.equalsIgnoreCase(days[u])) {
                                    System.out.println("???????????????????? inside equals");
                                    Calendar calnew = Calendar.getInstance();
                                    calnew.setTime(today_dt);
                                    if(days[u].equalsIgnoreCase("sun"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("mon"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("tue"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("wed"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("thu"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("fri"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    else if(days[u].equalsIgnoreCase("sat"))
                                    {
                                        while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                            calnew.add(Calendar.DATE, 1);
                                        }
                                    }
                                    first_date = calnew.getTime();
                                }
                            }
                        }
                        System.out.println("%%%%%%%%%% first_date : "+first_date);

                        //////////////////////////////////////////////
                        try {
                            //String founded_date=formatter.format(first_date);
                            String dttt=formatter.format(first_date);
                            founded_date=formatter.parse(dttt);
                            System.out.println("%%%%%%%%%% founded_date : "+founded_date);
                            strt_date=formatter.parse(p.getStrt_date());
                            current_date=formatter.parse(todays_date);
                            System.out.println("%%%%%%%%%% startdate : "+strt_date);
                            System.out.println("%%%%%%%%%% current_date : "+current_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        java.util.Date[] nxt_dates=new Date[p.getContinues_day()];
                        java.util.Date array_added_date=strt_date;
                        Calendar ading_cal=Calendar.getInstance();
                        for (int k = 0; k < p.getContinues_day(); k++) {
                            ading_cal.setTime(array_added_date);
                            nxt_dates[k] = ading_cal.getTime();
                            ading_cal.add(Calendar.DATE, 1);
                            array_added_date = ading_cal.getTime();
                        }
                        System.out.println("%%%%%%%%%% nxt_dates are ");
                        for (int k = 0; k < nxt_dates.length; k++) {
                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                            //System.out.println("%%%%%%%%%% "+nxt_dates[ k ]+".equals("+founded_date+")");
                            if(nxt_dates[k].equals(founded_date)){
                                System.out.println("inside nxt_dates[" + k + "] : equals to founded date");
                                if(current_date.equals(founded_date)) {
                                    System.out.println("inside current date equals to founded date");
                                    modl = new Product(p.getId(), p.getName(), p.getdate1(), p.getdate2(), p.getdate3(), p.getdate4(), p.getTake1(), p.getTake2(), p.getTake3(), p.getTake4(), p.getDosage_no(), p.getDosage_unit(), p.getInstruction(), p.getDoc_name(), p.getDoc_specl(), p.getDoc_cont()
                                            , p.getAmnt_meds(), p.getRemind_no(), p.getRemind_time(), p.getStrt_date(), p.getContinues_day(), p.getEvry_day_week(), p.getReminder_flag(), p.getInterval_time(), p.getSkipped_date1(), p.getSkipped_date2(), p.getSkipped_date3(), p.getSkipped_date4());
                                    allProducts.add(modl);
                                }
                            }
                        }
                        /////////////////////////////////////////////
                    }
                }
                //if 'DAYS' is selected 'Specific days of week' --end
            }
            //if 'DURATION' is selected 'No of days' --end
        }


        sameDateProductNames=new ArrayList<>();


        day_section = preference_days.getString("section", null);
        day_time = preference_days.getString("time", null);
        Zulekha_day_text= (TextView) findViewById(R.id.medi_title);
        Zulekha_day_text.setText(day_section);
        Zulekha_day_text.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "Mark Simonson - Proxima Nova Alt Regular-webfont.ttf"));

        Date= (TextView) findViewById(R.id.date);
        Day_time= (TextView) findViewById(R.id.day_time);

        Day_time.setText(day_time);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd");
        System.out.println("[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date display_date=formatter.parse(todays_date);

            String currentDateandTime = sdf.format(display_date);
            Date.setText(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final SharedPreferences preferences = getApplicationContext().getSharedPreferences("slect_country", MODE_PRIVATE);
        medicinArraylist=new ArrayList<>();
        mm=new MedicinModel();
        System.out.println("allProducts.size() : "+allProducts.size());

        if (day_section.equalsIgnoreCase("morning")) {
            int i=0;
            sameDateProductNames.clear();
            for(Product p1:allProducts) {
                i++;
                System.out.println("**********************************inside " + i + " iteration");
                mm = new MedicinModel();
                if (p1.getdate1() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate1!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("03.59");
                        Date endtime = simpleDateFormat.parse("12.00");

                        String converted_time = "";
                        String time = p1.getdate1().substring(0, 5).trim();
                        String am_or_pm = p1.getdate1().substring(Math.max(p1.getdate1().length() - 2, 0)).trim();
                        String hour_value = p1.getdate1().substring(0, Math.min(p1.getdate1().length(), 2)).trim();
                        String minut_value = p1.getdate1().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date1(medicinArraylist, p1.getdate1())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate1() != null && p2.getdate1() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate1() + ".equalsIgnoreCase(" + p2.getdate1() + ")");
                                        if (p1.getdate1().equalsIgnoreCase(p2.getdate1())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate1(p1.getdate1());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate2() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate2!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("03.59");
                        Date endtime = simpleDateFormat.parse("12.00");

                        String converted_time = "";
                        String time = p1.getdate2().substring(0, 5).trim();
                        String am_or_pm = p1.getdate2().substring(Math.max(p1.getdate2().length() - 2, 0)).trim();
                        String hour_value = p1.getdate2().substring(0, Math.min(p1.getdate2().length(), 2)).trim();
                        String minut_value = p1.getdate2().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date2(medicinArraylist, p1.getdate2())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate2() != null && p2.getdate2() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate2() + ".equalsIgnoreCase(" + p2.getdate2() + ")");
                                        if (p1.getdate2().equalsIgnoreCase(p2.getdate2())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate2(p1.getdate2());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate3() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate3!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("03.59");
                        Date endtime = simpleDateFormat.parse("12.00");

                        String converted_time = "";
                        String time = p1.getdate3().substring(0, 5).trim();
                        String am_or_pm = p1.getdate3().substring(Math.max(p1.getdate3().length() - 2, 0)).trim();
                        String hour_value = p1.getdate3().substring(0, Math.min(p1.getdate3().length(), 2)).trim();
                        String minut_value = p1.getdate3().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date3(medicinArraylist, p1.getdate3())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate3() != null && p2.getdate3() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate3() + ".equalsIgnoreCase(" + p2.getdate3() + ")");
                                        if (p1.getdate3().equalsIgnoreCase(p2.getdate3())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate3(p1.getdate3());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate4() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate4!=null");
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("03.59");
                        Date endtime = simpleDateFormat.parse("12.00");

                        String converted_time = "";
                        String time = p1.getdate4().substring(0, 5).trim();
                        String am_or_pm = p1.getdate4().substring(Math.max(p1.getdate4().length() - 2, 0)).trim();
                        String hour_value = p1.getdate4().substring(0, Math.min(p1.getdate4().length(), 2)).trim();
                        String minut_value = p1.getdate4().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date4(medicinArraylist, p1.getdate4())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate4() != null && p2.getdate4() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate4() + ".equalsIgnoreCase(" + p2.getdate4() + ")");
                                        if (p1.getdate4().equalsIgnoreCase(p2.getdate4())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate4(p1.getdate4());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }

        if (day_section.equalsIgnoreCase("afternoon")) {
            sameDateProductNames.clear();
            int i=0;
            for(Product p1:allProducts) {
                i++;
                System.out.println("**********************************inside " + i + " iteration");
                mm = new MedicinModel();

                if (p1.getdate1() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate1!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("11.59");
                        Date endtime = simpleDateFormat.parse("16.00");

                        String converted_time = "";
                        String time = p1.getdate1().substring(0, 5).trim();
                        String am_or_pm = p1.getdate1().substring(Math.max(p1.getdate1().length() - 2, 0)).trim();
                        String hour_value = p1.getdate1().substring(0, Math.min(p1.getdate1().length(), 2)).trim();
                        String minut_value = p1.getdate1().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {
                            System.out.println(":::::: inside current_time.after(startime) && current_time.before(endtime)");
                            System.out.println(":::::: p1.getName() : "+p1.getName());
                            System.out.println(":::::: p1.getdate1() : "+p1.getdate1());
                            System.out.println(":::::: sameDateProductNames : "+sameDateProductNames);
                            for(MedicinModel mnm:medicinArraylist){
                                System.out.println("???????????? mm.getDate1() : "+mnm.getDate1());
                                System.out.println("???????????? mm.getMedine_name() : "+mnm.getMedine_name());
                            }
                            if (!contains_date1(medicinArraylist, p1.getdate1())) {//to prevent twon same dates being added to arraylist
                                System.out.println(":::::: inside !contains(medicinArraylist, p1.getdate1())");
                                for (Product p2 : allProducts) {

                                    if (p1.getdate1() != null && p2.getdate1() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate1() + ".equalsIgnoreCase(" + p2.getdate1() + ")");
                                        if (p1.getdate1().equalsIgnoreCase(p2.getdate1())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate1(p1.getdate1());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate2() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate2!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("11.59");
                        Date endtime = simpleDateFormat.parse("16.00");

                        String converted_time = "";
                        String time = p1.getdate2().substring(0, 5).trim();
                        String am_or_pm = p1.getdate2().substring(Math.max(p1.getdate2().length() - 2, 0)).trim();
                        String hour_value = p1.getdate2().substring(0, Math.min(p1.getdate2().length(), 2)).trim();
                        String minut_value = p1.getdate2().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date2(medicinArraylist, p1.getdate2())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate2() != null && p2.getdate2() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate2() + ".equalsIgnoreCase(" + p2.getdate2() + ")");
                                        if (p1.getdate2().equalsIgnoreCase(p2.getdate2())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate2(p1.getdate2());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate3() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate3!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("11.59");
                        Date endtime = simpleDateFormat.parse("16.00");

                        String converted_time = "";
                        String time = p1.getdate3().substring(0, 5).trim();
                        String am_or_pm = p1.getdate3().substring(Math.max(p1.getdate3().length() - 2, 0)).trim();
                        String hour_value = p1.getdate3().substring(0, Math.min(p1.getdate3().length(), 2)).trim();
                        String minut_value = p1.getdate3().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date3(medicinArraylist, p1.getdate3())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate3() != null && p2.getdate3() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate3() + ".equalsIgnoreCase(" + p2.getdate3() + ")");
                                        if (p1.getdate3().equalsIgnoreCase(p2.getdate3())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate3(p1.getdate3());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate4() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate4!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("11.59");
                        Date endtime = simpleDateFormat.parse("16.00");

                        String converted_time = "";
                        String time = p1.getdate4().substring(0, 5).trim();
                        String am_or_pm = p1.getdate4().substring(Math.max(p1.getdate4().length() - 2, 0)).trim();
                        String hour_value = p1.getdate4().substring(0, Math.min(p1.getdate4().length(), 2)).trim();
                        String minut_value = p1.getdate4().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date4(medicinArraylist, p1.getdate4())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate4() != null && p2.getdate4() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate4() + ".equalsIgnoreCase(" + p2.getdate4() + ")");
                                        if (p1.getdate4().equalsIgnoreCase(p2.getdate4())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate4(p1.getdate4());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

        }

        if (day_section.equalsIgnoreCase("evening")) {
            int i=0;
            sameDateProductNames.clear();
            for(Product p1:allProducts) {
                i++;
                System.out.println("**********************************inside " + i + " iteration");
                mm = new MedicinModel();
                if (p1.getdate1() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate1!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("15.59");
                        Date endtime = simpleDateFormat.parse("20.00");

                        String converted_time = "";
                        String time = p1.getdate1().substring(0, 5).trim();
                        String am_or_pm = p1.getdate1().substring(Math.max(p1.getdate1().length() - 2, 0)).trim();
                        String hour_value = p1.getdate1().substring(0, Math.min(p1.getdate1().length(), 2)).trim();
                        String minut_value = p1.getdate1().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date1(medicinArraylist, p1.getdate1())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate1() != null && p2.getdate1() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate1() + ".equalsIgnoreCase(" + p2.getdate1() + ")");
                                        if (p1.getdate1().equalsIgnoreCase(p2.getdate1())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate1(p1.getdate1());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate2() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate2!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("15.59");
                        Date endtime = simpleDateFormat.parse("20.00");

                        String converted_time = "";
                        String time = p1.getdate2().substring(0, 5).trim();
                        String am_or_pm = p1.getdate2().substring(Math.max(p1.getdate2().length() - 2, 0)).trim();
                        String hour_value = p1.getdate2().substring(0, Math.min(p1.getdate2().length(), 2)).trim();
                        String minut_value = p1.getdate2().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date2(medicinArraylist, p1.getdate2())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate2() != null && p2.getdate2() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate2() + ".equalsIgnoreCase(" + p2.getdate2() + ")");
                                        if (p1.getdate2().equalsIgnoreCase(p2.getdate2())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate2(p1.getdate2());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate3() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate3!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("15.59");
                        Date endtime = simpleDateFormat.parse("20.00");

                        String converted_time = "";
                        String time = p1.getdate3().substring(0, 5).trim();
                        String am_or_pm = p1.getdate3().substring(Math.max(p1.getdate3().length() - 2, 0)).trim();
                        String hour_value = p1.getdate3().substring(0, Math.min(p1.getdate3().length(), 2)).trim();
                        String minut_value = p1.getdate3().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date3(medicinArraylist, p1.getdate3())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate3() != null && p2.getdate3() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate3() + ".equalsIgnoreCase(" + p2.getdate3() + ")");
                                        if (p1.getdate3().equalsIgnoreCase(p2.getdate3())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate3(p1.getdate3());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate4() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate4!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("15.59");
                        Date endtime = simpleDateFormat.parse("20.00");

                        String converted_time = "";
                        String time = p1.getdate4().substring(0, 5).trim();
                        String am_or_pm = p1.getdate4().substring(Math.max(p1.getdate4().length() - 2, 0)).trim();
                        String hour_value = p1.getdate4().substring(0, Math.min(p1.getdate4().length(), 2)).trim();
                        String minut_value = p1.getdate4().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)) {

                            if (!contains_date4(medicinArraylist, p1.getdate4())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate4() != null && p2.getdate4() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate4() + ".equalsIgnoreCase(" + p2.getdate4() + ")");
                                        if (p1.getdate4().equalsIgnoreCase(p2.getdate4())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate4(p1.getdate4());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        if (day_section.equalsIgnoreCase("night")) {
            int i=0;
            sameDateProductNames.clear();
            for(Product p1:allProducts) {
                i++;
                System.out.println("**********************************inside " + i + " iteration");
                mm = new MedicinModel();
                if (p1.getdate1() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate1!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("19.59");
                        Date endtime = simpleDateFormat.parse("23.59");
                        Date startime2 = simpleDateFormat.parse("00.00");
                        Date endtime2 = simpleDateFormat.parse("04.00");

                        String converted_time = "";
                        String time = p1.getdate1().substring(0, 5).trim();
                        String am_or_pm = p1.getdate1().substring(Math.max(p1.getdate1().length() - 2, 0)).trim();
                        String hour_value = p1.getdate1().substring(0, Math.min(p1.getdate1().length(), 2)).trim();
                        String minut_value = p1.getdate1().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)|| current_time.after(startime2) && current_time.before(endtime2)) {

                            if (!contains_date1(medicinArraylist, p1.getdate1())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate1() != null && p2.getdate1() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate1() + ".equalsIgnoreCase(" + p2.getdate1() + ")");
                                        if (p1.getdate1().equalsIgnoreCase(p2.getdate1())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate1(p1.getdate1());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate2() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate2!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("19.59");
                        Date endtime = simpleDateFormat.parse("23.59");
                        Date startime2 = simpleDateFormat.parse("00.00");
                        Date endtime2 = simpleDateFormat.parse("04.00");

                        String converted_time = "";
                        String time = p1.getdate2().substring(0, 5).trim();
                        String am_or_pm = p1.getdate2().substring(Math.max(p1.getdate2().length() - 2, 0)).trim();
                        String hour_value = p1.getdate2().substring(0, Math.min(p1.getdate2().length(), 2)).trim();
                        String minut_value = p1.getdate2().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)|| current_time.after(startime2) && current_time.before(endtime2)) {

                            if (!contains_date2(medicinArraylist, p1.getdate2())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate2() != null && p2.getdate2() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate2() + ".equalsIgnoreCase(" + p2.getdate2() + ")");
                                        if (p1.getdate2().equalsIgnoreCase(p2.getdate2())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            // sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate2(p1.getdate2());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate3() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate3!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("19.59");
                        Date endtime = simpleDateFormat.parse("23.59");
                        Date startime2 = simpleDateFormat.parse("00.00");
                        Date endtime2 = simpleDateFormat.parse("04.00");

                        String converted_time = "";
                        String time = p1.getdate3().substring(0, 5).trim();
                        String am_or_pm = p1.getdate3().substring(Math.max(p1.getdate3().length() - 2, 0)).trim();
                        String hour_value = p1.getdate3().substring(0, Math.min(p1.getdate3().length(), 2)).trim();
                        String minut_value = p1.getdate3().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)|| current_time.after(startime2) && current_time.before(endtime2)) {

                            if (!contains_date3(medicinArraylist, p1.getdate3())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate3() != null && p2.getdate3() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate3() + ".equalsIgnoreCase(" + p2.getdate3() + ")");
                                        if (p1.getdate3().equalsIgnoreCase(p2.getdate3())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate3(p1.getdate3());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (p1.getdate4() != null) {
                    System.out.println("%%%%%%%%%%%%%%%%%%%%% inside getdate4!=null");
                    try {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH.mm");

                        Date startime = simpleDateFormat.parse("19.59");
                        Date endtime = simpleDateFormat.parse("23.59");
                        Date startime2 = simpleDateFormat.parse("00.00");
                        Date endtime2 = simpleDateFormat.parse("04.00");

                        String converted_time = "";
                        String time = p1.getdate4().substring(0, 5).trim();
                        String am_or_pm = p1.getdate4().substring(Math.max(p1.getdate4().length() - 2, 0)).trim();
                        String hour_value = p1.getdate4().substring(0, Math.min(p1.getdate4().length(), 2)).trim();
                        String minut_value = p1.getdate4().substring(3, 5).trim();
                        if (am_or_pm.equalsIgnoreCase("pm")) {
                            if (hour_value.equalsIgnoreCase("12")) {
                                converted_time = "12" + "." + minut_value;
                            } else {
                                converted_time = Integer.toString(12 + Integer.parseInt(hour_value)) + "." + minut_value;
                            }
                        } else {
                            converted_time = time;
                        }
                        Date current_time = simpleDateFormat.parse(converted_time);
                        if (current_time.after(startime) && current_time.before(endtime)|| current_time.after(startime2) && current_time.before(endtime2)) {

                            if (!contains_date4(medicinArraylist, p1.getdate4())) {//to prevent twon same dates being added to arraylist
                                for (Product p2 : allProducts) {
                                    if (p1.getdate4() != null && p2.getdate4() != null) {
                                        System.out.println("p1.getdate().equalsIgnoreCase(p2.getdate()) : " + p1.getdate4() + ".equalsIgnoreCase(" + p2.getdate4() + ")");
                                        if (p1.getdate4().equalsIgnoreCase(p2.getdate4())) {
                                            System.out.println("-----------------------------------------------added name : " + p2.getName());
                                            //sameDateProductNames = sameDateProductNames + p2.getName().toString() + ",";
                                            if(!sameDateProductNames.contains(p2.getName().toString())){
                                                sameDateProductNames.add(p2.getName().toString());
                                            }
                                            System.out.println("||||||||||||||||||||||||| sameDateProductNames : " + sameDateProductNames);
                                        }
                                    }
                                }
                            }
                            mm.setId(p1.getId());
                            mm.setMedine_name(p1.getName());
                            mm.setDate4(p1.getdate4());
                            System.out.println("######## sameDateProductNames : "+sameDateProductNames.toString());
                            String med_name_appended="";
                            //if(i==allProducts.size()-1) {
                            // if (sameDateProductNames.size() > 0) {
                            for (String med_nme : sameDateProductNames) {
                                med_name_appended = med_name_appended + med_nme + ",";
                            }

                            if (med_name_appended != null && med_name_appended.length() > 0 && med_name_appended.charAt(med_name_appended.length() - 1) == ',') {
                                med_name_appended = med_name_appended.substring(0, med_name_appended.length() - 1);
                            }
                            System.out.println("######## med_name_appended : " + med_name_appended.toString());
                            //mm.setMedine_name(med_name_appended.toString());
                            medicinArraylist.add(mm);

                            //  }
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }




        for(MedicinModel m:medicinArraylist){
            System.out.println("-----------------------------");
            System.out.println("m.getId() : "+m.getId());
            System.out.println("m.getDate() : "+m.getDate1());

            if(m.getMedine_name()!=null) {
                ArrayList<String> aListnames = new ArrayList<String>(Arrays.asList(m.getMedine_name()));
                System.out.println("aListnames : "+aListnames);

                System.out.println("m.getMedine_name() : "+m.getMedine_name().toString());
                System.out.println("m.getMedine_name().size() : " + aListnames.size());
                System.out.println("m.getNames are :-- ");
                for (int k=0;k<aListnames.size();k++) {
                    System.out.println(aListnames.get(k));
                }
            }
        }
        ProductAdapter mAdapter = new ProductAdapter(this,medicinArraylist);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Days_Activity.this,Medication_Activity.class);
        startActivity(intent);
        finish();
    }

}
