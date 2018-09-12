package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.meridian.cybraum.zulekha.alexis.alexispharmacy.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Created by Ansal on 10/25/2017.
 */

public class Update_medicine extends AppCompatActivity {


    RadioButton rad_one_day,rad_two_day,rad_three_day,rad_four_day;
    String country;
    LinearLayout linr_time1,linr_time2,linr_time3,linr_time4;
    TextView Zulekha_medication,Alexi_medication;
    EditText dosage;
    LinearLayout Linr_Update,refil_reminder_layout,layout_when_checked_prescription;
    Spinner spinner;
    ImageView plus,minus;
    LinearLayout Rmndr_time1,Rmndr_time2,Rmndr_time3,Rmndr_time4;
    TextView Rmndr_timetxt1,Rmndr_timetxt2,Rmndr_timetxt3,Rmndr_timetxt4,Strt_Rmndr_timetxt;
    Context context;
    LinearLayout Linr_freeqncy,Linr_intrvl,Strt_reminder_time;
    static RadioButton Evry_12,Evry_6,Evry_8,Evry_4;
    SwitchCompat reminderSwitch,refil_reminder_switch;
    LinearLayout Reminder,Back;
    int hour,min;

    LocalData localData;
    EditText edit_medication_name,edit_instruction,edit_doc_name,edit_doc_spcl,edit_doc_cont;
    CheckedTextView checkedTextView_prescription,checketed_txt_when_click_doctor;
    View custompopup_view,custompopup_view1,custompopup_view2,custompopup_view3;
    LinearLayout inflate_layout;
    PopupWindow refill_popup,take_popup,schedule_day_popup,schedule_week_popup;
    TextView Set_time;
    private SqliteDatabase mDatabase;
    String str_med_name,str_date1,str_date2,str_date3,str_date4,str_dosage_unit,str_instrcn,str_doc_name,str_doc_spcl,str_doc_cont,str_evry_day_week,str_remind_time,str_strt_date;
    int  dosage_no,continue_days,remind_no;
    double str_take1,str_take2,str_take3,str_take4,amnt_meds;
    private int mYear, mMonth, mDay;
    LinearLayout Linr_take1,Linr_take2,Linr_take3,Linr_take4,Linr_take5,Okbutton;
    TextView Txt_tak1,Txt_tak2,Txt_tak3,Txt_tak4,Txt_tak5;
    RadioButton rad_continuse,rad_specifc_day,rad_evry_day,rad_no_of_day;
    TextView Select_text1,Select_text2,Select_text3,txt;
    int[] number = {0};
    int[] number0 = {0};
    double[] number1 = {1};
    double[] number2 = {0};
    int[] number3 = {1};
    String[] one = new String[1];
    String[] two = new String[1];
    String[] three = new String[1];
    String[] four = new String[1];
    String[] five = new String[1];
    String[] six = new String[1];
    String[] seven = new String[1];
    String txt_week;
    private String reminder_flag;
    String selected_id;
    Product med_detailsby_id;
    private String interval_time;
    String[] list;
    RadioGroup type,interval;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_medication);
        context = this;
        mDatabase = new SqliteDatabase(context);
        refil_reminder_layout = (LinearLayout) findViewById(R.id.refil_reminder_layout);
        //---------------------------------------------alexi&zulekha----------------------------------------------
        Back=(LinearLayout)findViewById(R.id.menu);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update_medicine.super.onBackPressed();

            }
        });
        Zulekha_medication = (TextView) findViewById(R.id.home_txt);

        Zulekha_medication.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "Mark Simonson - Proxima Nova Alt Regular-webfont.ttf"));

        final SharedPreferences preferences = getApplicationContext().getSharedPreferences("slect_country", MODE_PRIVATE);

        selected_id=getIntent().getStringExtra("selected_id");
        System.out.println("selected_id : "+selected_id);
        med_detailsby_id=mDatabase.findProduct(selected_id);
        System.out.println("reminder flag in db : "+med_detailsby_id.getReminder_flag());
        list = new String[]{"mg", "gm","ml","mcg", "units"};
        System.out.println("**************************************************");
        System.out.println("med_detailsby_id.getId() : "+med_detailsby_id.getId());
        System.out.println("med_detailsby_id.getName() : "+med_detailsby_id.getName());
        System.out.println("med_detailsby_id.getdate1() : "+med_detailsby_id.getdate1());
        System.out.println("med_detailsby_id.getdate2() : "+med_detailsby_id.getdate2());
        System.out.println("med_detailsby_id.getdate3() : "+med_detailsby_id.getdate3());
        System.out.println("med_detailsby_id.getdate4() : "+med_detailsby_id.getdate4());
        System.out.println("med_detailsby_id.getTake1() : "+med_detailsby_id.getTake1());
        System.out.println("med_detailsby_id.getTake2() : "+med_detailsby_id.getTake2());
        System.out.println("med_detailsby_id.getTake3() : "+med_detailsby_id.getTake3());
        System.out.println("med_detailsby_id.getTake4() : "+med_detailsby_id.getTake4());
        System.out.println("med_detailsby_id.getDosage_no() : "+med_detailsby_id.getDosage_no());
        System.out.println("med_detailsby_id.getDosage_unit() : "+med_detailsby_id.getDosage_unit());
        System.out.println("med_detailsby_id.getInstruction() : "+med_detailsby_id.getInstruction());
        System.out.println("med_detailsby_id.getDoc_name() : "+med_detailsby_id.getDoc_name());
        System.out.println("med_detailsby_id.getDoc_specl() : "+med_detailsby_id.getDoc_specl());
        System.out.println("med_detailsby_id.getDoc_cont() : "+med_detailsby_id.getDoc_cont());
        System.out.println("med_detailsby_id.getAmnt_meds() : "+med_detailsby_id.getAmnt_meds());
        System.out.println("med_detailsby_id.getRemind_no() : "+med_detailsby_id.getRemind_no());
        System.out.println("med_detailsby_id.getRemind_time() : "+med_detailsby_id.getRemind_time());
        System.out.println("med_detailsby_id.getStrt_date() : "+med_detailsby_id.getStrt_date());
        System.out.println("med_detailsby_id.getContinues_day() : "+med_detailsby_id.getContinues_day());
        System.out.println("med_detailsby_id.getEvry_day_week() : "+med_detailsby_id.getEvry_day_week());
        System.out.println("med_detailsby_id.getReminder_flag() : "+med_detailsby_id.getReminder_flag());
        System.out.println("med_detailsby_id.getInterval_time() : "+med_detailsby_id.getInterval_time());
        System.out.println("med_detailsby_id.getSkipped_date1() : "+med_detailsby_id.getSkipped_date1());
        System.out.println("med_detailsby_id.getSkipped_date2() : "+med_detailsby_id.getSkipped_date2());
        System.out.println("med_detailsby_id.getSkipped_date3() : "+med_detailsby_id.getSkipped_date3());
        System.out.println("med_detailsby_id.getSkipped_date4() : "+med_detailsby_id.getSkipped_date4());
        System.out.println("**************************************************");





        //---------------------------------------------medi_name----------------------------------------------
        edit_medication_name = (EditText) findViewById(R.id.edit_medication_name);
        final CheckedTextView checketed_txt_medication= (CheckedTextView) findViewById(R.id.medi_name);
        final LinearLayout Medi_layout=(LinearLayout) findViewById(R.id.medi_layout);
        checketed_txt_medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checketed_txt_medication.isChecked()){
                    checketed_txt_medication.setChecked(false);
                    Medi_layout.setVisibility(View.GONE);
                }
                else{
                    checketed_txt_medication.setChecked(true);
                    Medi_layout.setVisibility(View.VISIBLE);
                }


            }
        });

        //---------------------------------------------reminder----------------------------------------------
        final CheckedTextView checketed_txt_reminder= (CheckedTextView) findViewById(R.id.reminder_me);
        localData = new LocalData(getApplicationContext());
        hour = localData.get_hour();
        min = localData.get_min();
        reminderSwitch = (SwitchCompat) findViewById(R.id.timerSwitch);
        type=(RadioGroup)findViewById(R.id.type);
        interval=(RadioGroup)findViewById(R.id.intevl);
        Reminder = (LinearLayout) findViewById(R.id.reminder);

        rad_one_day = (RadioButton) findViewById(R.id.one_day);
        rad_two_day = (RadioButton) findViewById(R.id.two_day);
        rad_three_day = (RadioButton) findViewById(R.id.three_day);
        rad_four_day = (RadioButton) findViewById(R.id.four_day);

        Evry_4 = (RadioButton) findViewById(R.id.evry_4);
        Evry_6 = (RadioButton) findViewById(R.id.evry_6);
        Evry_8 = (RadioButton) findViewById(R.id.evry_8);
        Evry_12 = (RadioButton) findViewById(R.id.evry_12);

        Linr_freeqncy = (LinearLayout) findViewById(R.id.Linre_freeqncy);
        Linr_intrvl = (LinearLayout) findViewById(R.id.Linre_intrvl);

        linr_time1 = (LinearLayout) findViewById(R.id.linr_one);
        linr_time2 = (LinearLayout) findViewById(R.id.linr_two);
        linr_time3 = (LinearLayout) findViewById(R.id.linr_three);
        linr_time4 = (LinearLayout) findViewById(R.id.linr_four);

        Rmndr_time1 = (LinearLayout) findViewById(R.id.rmndr_time1);
        Rmndr_time2 = (LinearLayout) findViewById(R.id.rmndr_time2);
        Rmndr_time3 = (LinearLayout) findViewById(R.id.rmndr_time3);
        Rmndr_time4 = (LinearLayout) findViewById(R.id.rmndr_time4);

        Rmndr_timetxt1 = (TextView) findViewById(R.id.rmndr_txt1);
        Rmndr_timetxt2 = (TextView) findViewById(R.id.rmndr_txt2);
        Rmndr_timetxt3 = (TextView) findViewById(R.id.rmndr_txt3);
        Rmndr_timetxt4 = (TextView) findViewById(R.id.rmndr_txt4);

        Strt_reminder_time = (LinearLayout) findViewById(R.id.strt_rmndr_time);
        Strt_Rmndr_timetxt = (TextView) findViewById(R.id.start_rmndr_txt);

        Linr_take1 = (LinearLayout) findViewById(R.id.linr_take1);
        Linr_take2 = (LinearLayout) findViewById(R.id.linr_take2);
        Linr_take3 = (LinearLayout) findViewById(R.id.linr_take3);
        Linr_take4 = (LinearLayout) findViewById(R.id.linr_take4);
        Linr_take5 = (LinearLayout) findViewById(R.id.linr_take5);

        Txt_tak1 = (TextView) findViewById(R.id.txt_tak1);
        Txt_tak2 = (TextView) findViewById(R.id.txt_tak2);
        Txt_tak3 = (TextView) findViewById(R.id.txt_tak3);
        Txt_tak4 = (TextView) findViewById(R.id.txt_tak4);
        Txt_tak5 = (TextView) findViewById(R.id.txt_tak5);

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    Reminder.setVisibility(View.VISIBLE);
                    checketed_txt_reminder.setChecked(true);

                    Reminder.setAlpha(1f);
                } else {
                    checketed_txt_reminder.setChecked(false);
                    Reminder.setVisibility(View.GONE);
                    Reminder.setAlpha(0.4f);



                }

            }
        });
        checketed_txt_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checketed_txt_reminder.isChecked()){
                    checketed_txt_reminder.setChecked(false);
                    Reminder.setVisibility(View.GONE);
                }
                else{

                    if(reminderSwitch.isChecked()){
                        Reminder.setVisibility(View.VISIBLE);
                        checketed_txt_reminder.setChecked(true);
                    }

                }


            }
        });
        Txt_tak1.setText("Take"+number1[0]);
        Txt_tak2.setText("Take"+number1[0]);
        Txt_tak3.setText("Take"+number1[0]);
        Txt_tak4.setText("Take"+number1[0]);
        str_take1= number1[0];
        str_take2= number1[0];
        str_take3= number1[0];
        str_take4= number1[0];

        String kk="00";
        String k1="08";
        str_date1=k1 + "." + kk + " " + "AM";
        str_date2 ="";
        str_date3="";
        str_date4="";
        Rmndr_timetxt1.setText(str_date1);
        rad_one_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder_flag="frequency";
                interval.clearCheck();

                linr_time1.setVisibility(View.VISIBLE);
                linr_time2.setVisibility(View.GONE);
                linr_time3.setVisibility(View.GONE);
                linr_time4.setVisibility(View.GONE);

                Linr_intrvl.setVisibility(View.GONE);
                Linr_freeqncy.setVisibility(View.VISIBLE);

                Txt_tak1.setText("Take "+number1[0]);
                str_take1= number1[0];
                String kk="00";
                String k="08";
                str_date1=k + "." + kk + " " + "AM";
                Rmndr_timetxt1.setText(str_date1);
                str_date2 ="";
                str_date3="";
                str_date4="";


            }
        });

        rad_two_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reminder_flag="frequency";
                interval.clearCheck();
                linr_time1.setVisibility(View.VISIBLE);
                linr_time2.setVisibility(View.VISIBLE);
                linr_time3.setVisibility(View.GONE);
                linr_time4.setVisibility(View.GONE);

                Linr_intrvl.setVisibility(View.GONE);
                Linr_freeqncy.setVisibility(View.VISIBLE);
                Txt_tak2.setText("Take"+number1[0]);
                str_take2= number1[0];
                String kk="00";
                String k="01";
                str_date2=k + "." + kk + " " + "PM";

                Rmndr_timetxt2.setText(str_date2);



            }
        });
        rad_three_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reminder_flag="frequency";
                interval.clearCheck();
                linr_time1.setVisibility(View.VISIBLE);
                linr_time2.setVisibility(View.VISIBLE);
                linr_time3.setVisibility(View.VISIBLE);
                linr_time4.setVisibility(View.GONE);

                Linr_intrvl.setVisibility(View.GONE);
                Linr_freeqncy.setVisibility(View.VISIBLE);
                Txt_tak3.setText("Take"+number1[0]);
                str_take3= number1[0];
                str_take2= number1[0];
                String kk="00";
                String k="05";
                String k2="01";
                str_date2=k2 + "." + kk + " " + "PM";
                Rmndr_timetxt2.setText(str_date2);
                str_date3=k + "." + kk + " " + "PM";
                Rmndr_timetxt3.setText(str_date3);
                str_date4="";


            }
        });
        rad_four_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reminder_flag="frequency";

                interval.clearCheck();

                linr_time1.setVisibility(View.VISIBLE);
                linr_time2.setVisibility(View.VISIBLE);
                linr_time3.setVisibility(View.VISIBLE);
                linr_time4.setVisibility(View.VISIBLE);

                Linr_intrvl.setVisibility(View.GONE);
                Linr_freeqncy.setVisibility(View.VISIBLE);
                Txt_tak4.setText("Take"+number1[0]);
                str_take4= number1[0];
                str_take3= number1[0];
                str_take2= number1[0];
                String kk="00";
                String k1="08";
                String k2="01";
                String k3="05";
                str_date2=k2 + "." + kk + " " + "PM";
                Rmndr_timetxt2.setText(str_date2);
                str_date3=k3 + "." + kk + " " + "PM";
                Rmndr_timetxt3.setText(str_date3);
                str_date4=k1 + "." + kk + " " + "PM";
                Rmndr_timetxt4.setText(str_date4);



            }
        });

        Rmndr_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showTimePicker1();

            }
        });
        Rmndr_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showTimePicker2();


            }
        });
        Rmndr_time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showTimePicker3();

            }
        });
        Rmndr_time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showTimePicker4();

            }
        });

        Evry_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder_flag="interval";
                interval_time=Evry_12.getText().toString();
                type.clearCheck();

                Linr_intrvl.setVisibility(View.VISIBLE);
                Linr_freeqncy.setVisibility(View.GONE);
                Txt_tak5.setText("Take"+number1[0]);
                str_take1= number1[0];
                String AM_PM = "";
                Calendar calendar = Calendar.getInstance();
                Calendar datetime = Calendar.getInstance();
                datetime.set(Calendar.HOUR_OF_DAY, 8);
                datetime.set(Calendar.MINUTE, 0);
                datetime.set(Calendar.SECOND, 0);
                if (datetime.before(calendar))
                    datetime.add(Calendar.DATE, 1);
                if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                    AM_PM = "AM";
                else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                    AM_PM = "PM";

                String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                        .get(Calendar.HOUR) + "";

                int hours1= Integer.parseInt(hours);

                String minute1;
                if(hours1>=0&&hours1<10) {
                    hours = String.format("%02d", hours1);
                    if (0 >= 0 && 0 < 10) {

                        minute1 = (String.format("%02d", 0));
                        str_date1 = hours + "." + minute1 + " " + AM_PM;
                        Strt_Rmndr_timetxt.setText(hours + "." + minute1 + " " + AM_PM);
                    }
                }



            }
        });
        Evry_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder_flag="interval";
                interval_time=Evry_8.getText().toString();
                type.clearCheck();
                Linr_intrvl.setVisibility(View.VISIBLE);
                Linr_freeqncy.setVisibility(View.GONE);
                Txt_tak5.setText("Take"+number1[0]);
                str_take1= number1[0];
                String AM_PM = "";
                Calendar calendar = Calendar.getInstance();
                Calendar datetime = Calendar.getInstance();
                datetime.set(Calendar.HOUR_OF_DAY, 8);
                datetime.set(Calendar.MINUTE, 0);
                datetime.set(Calendar.SECOND, 0);
                if (datetime.before(calendar))
                    datetime.add(Calendar.DATE, 1);
                if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                    AM_PM = "AM";
                else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                    AM_PM = "PM";

                String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                        .get(Calendar.HOUR) + "";

                int hours1= Integer.parseInt(hours);

                String minute1;
                if(hours1>=0&&hours1<10) {
                    hours = String.format("%02d", hours1);
                    if (0 >= 0 && 0 < 10) {

                        minute1 = (String.format("%02d", 0));
                        str_date1 = hours + "." + minute1 + " " + AM_PM;
                        Strt_Rmndr_timetxt.setText(hours + "." + minute1 + " " + AM_PM);
                    }
                }


            }
        });
        Evry_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminder_flag="interval";
                interval_time=Evry_6.getText().toString();
                type.clearCheck();
                Linr_intrvl.setVisibility(View.VISIBLE);
                Linr_freeqncy.setVisibility(View.GONE);
                Txt_tak5.setText("Take"+number1[0]);
                str_take1= number1[0];
                String AM_PM = "";
                Calendar calendar = Calendar.getInstance();
                Calendar datetime = Calendar.getInstance();
                datetime.set(Calendar.HOUR_OF_DAY, 8);
                datetime.set(Calendar.MINUTE, 0);
                datetime.set(Calendar.SECOND, 0);
                if (datetime.before(calendar))
                    datetime.add(Calendar.DATE, 1);
                if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                    AM_PM = "AM";
                else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                    AM_PM = "PM";

                String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                        .get(Calendar.HOUR) + "";

                int hours1= Integer.parseInt(hours);

                String minute1;
                if(hours1>=0&&hours1<10) {
                    hours = String.format("%02d", hours1);
                    if (0 >= 0 && 0 < 10) {

                        minute1 = (String.format("%02d", 0));
                        str_date1 = hours + "." + minute1 + " " + AM_PM;
                        Strt_Rmndr_timetxt.setText(hours + "." + minute1 + " " + AM_PM);
                    }
                }


            }
        });

        Evry_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reminder_flag="interval";
                interval_time=Evry_4.getText().toString();
                type.clearCheck();

                Linr_intrvl.setVisibility(View.VISIBLE);
                Linr_freeqncy.setVisibility(View.GONE);
                Txt_tak5.setText("Take"+number1[0]);
                str_take1= number1[0];
                String AM_PM = "";
                Calendar calendar = Calendar.getInstance();
                Calendar datetime = Calendar.getInstance();
                datetime.set(Calendar.HOUR_OF_DAY, 8);
                datetime.set(Calendar.MINUTE, 0);
                datetime.set(Calendar.SECOND, 0);
                if (datetime.before(calendar))
                    datetime.add(Calendar.DATE, 1);
                if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                    AM_PM = "AM";
                else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                    AM_PM = "PM";

                String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                        .get(Calendar.HOUR) + "";

                int hours1= Integer.parseInt(hours);

                String minute1;
                if(hours1>=0&&hours1<10) {
                    hours = String.format("%02d", hours1);
                    if (0 >= 0 && 0 < 10) {

                        minute1 = (String.format("%02d", 0));
                        str_date1 = hours + "." + minute1 + " " + AM_PM;
                        Strt_Rmndr_timetxt.setText(hours + "." + minute1 + " " + AM_PM);
                    }
                }



            }
        });

        Strt_reminder_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // showTimePickerDialog5(localData.get_hour(), localData.get_min());
                showTimePicker5();

            }
        });

        final LayoutInflater inflator = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        custompopup_view1 = inflator.inflate(R.layout.number_picker, null);

        try {
            Select_text1=(TextView) custompopup_view1.findViewById(R.id.select_text);
            LinearLayout Add=(LinearLayout) custompopup_view1.findViewById(R.id.plus);
            LinearLayout Minus=(LinearLayout) custompopup_view1.findViewById(R.id.minus);
            Okbutton=(LinearLayout) custompopup_view1.findViewById(R.id.ok_btn);
            LinearLayout Closebutton=(LinearLayout) custompopup_view1.findViewById(R.id.cancel_btn);
            final TextView txt=(TextView)custompopup_view1.findViewById(R.id.no);
            txt.setText(""+ number1[0]);


            if(med_detailsby_id.getReminder_flag()!=null) {
                if (med_detailsby_id.getReminder_flag().equalsIgnoreCase("interval")) {
                    reminderSwitch.setChecked(true);

                    if (med_detailsby_id.getReminder_flag().equalsIgnoreCase("interval")) {
                        number1[0]= (int) med_detailsby_id.getTake4();
                        txt.setText(""+ number1[0]);
                        //txt.setText(""+ number1[]);;
                    }
                }
            }

            Minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number1[0] == .5){
                        txt.setText("" + number1[0]);
                    }

                    if (number1[0] > 1){

                        number1[0] = number1[0]-.5;
                        txt.setText(""+ number1[0]);
                    }
                }
            });
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number1[0] == 10) {
                        txt.setText("" + number1[0]);
                    }
                    if (number1[0] < 10) {
                        number1[0] = number1[0] + .5;
                        txt.setText("" + number1[0]);

                    }
                }
            });


            Closebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    take_popup.dismiss();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        Linr_take1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaypopup_take();
                Select_text1.setText("Select number of medicine you want to take at this time");
                Okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Txt_tak1.setText("Take"+number1[0]);
                        str_take1= number1[0];
                        take_popup.dismiss();
                    }
                });
            }
        });
        Linr_take2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaypopup_take();
                Select_text1.setText("Select number of medicine you want to take at this time");
                Okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Txt_tak2.setText("Take"+number1[0]);
                        str_take2= number1[0];
                        take_popup.dismiss();
                    }
                });
            }
        });
        Linr_take3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaypopup_take();
                Select_text1.setText("Select number of medicine you want to take at this time");
                Okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Txt_tak3.setText("Take"+number1[0]);
                        str_take3= number1[0];
                        take_popup.dismiss();
                    }
                });
            }
        });
        Linr_take4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaypopup_take();
                Select_text1.setText("Select number of medicine you want to take at this time");
                Okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Txt_tak4.setText("Take"+number1[0]);
                        str_take4= number1[0];
                        take_popup.dismiss();
                    }
                });
            }
        });
        Linr_take5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaypopup_take();
                Select_text1.setText("Select number of medicine you want to take at this time");
                Okbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Txt_tak5.setText("Take"+number1[0]);
                        str_take1= number1[0];
                        take_popup.dismiss();
                    }
                });
            }
        });

        //---------------------------------------------dosage----------------------------------------------
        final CheckedTextView checketed_txt_dosage = (CheckedTextView) findViewById(R.id.dosage);
        final LinearLayout dosage_layout=(LinearLayout) findViewById(R.id.dosage_layout);
        checketed_txt_dosage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checketed_txt_dosage.isChecked()){
                    checketed_txt_dosage.setChecked(false);
                    dosage_layout.setVisibility(View.GONE);
                }
                else{
                    checketed_txt_dosage.setChecked(true);
                    dosage_layout.setVisibility(View.VISIBLE);
                }


            }
        });
        spinner=(Spinner)findViewById(R.id.spinner);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        plus = (ImageView)findViewById(R.id.plus);
        minus = (ImageView)findViewById(R.id.minus);
        dosage = (EditText) findViewById(R.id.sizeno);

        number[0]=med_detailsby_id.getDosage_no();
        dosage.setText(""+ number[0]);
        dosage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    System.out.println("editable.toString() : "+editable.toString());
                    if (editable.toString() != "" || editable.toString() != null) {
                        number[0] = Integer.parseInt(editable.toString());
                    }
                    /*else {
                        number[0]=0;
                        dosage.setHint("0");
                    }*/
                }catch (Exception e){
                    number[0]=0;
                    e.printStackTrace();
                }

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 0){
                    dosage.setText("" + number[0]);
                }

                if (number[0] > 0){

                    number[0] = number[0]-1;
                    dosage.setText(""+ number[0]);
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 1000) {
                    dosage.setText("" + number[0]);
                }

                if (number[0] < 1000) {

                    number[0] = number[0] + 1;
                    dosage.setText("" + number[0]);

                }
            }
        });

        //---------------------------------------------prescription refill----------------------------------------------
        inflate_layout=(LinearLayout)findViewById(R.id.layout);
        final TextView   refil_txt = (TextView) findViewById(R.id.refil_txt);

        LinearLayout  Ad = (LinearLayout)findViewById(R.id.add);
        LinearLayout mins = (LinearLayout)findViewById(R.id.mins);
        final TextView txt1=(TextView)findViewById(R.id.no);

        number2[0]= (int) med_detailsby_id.getAmnt_meds();
        txt1.setText(""+ number2[0]);

        mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number2[0] ==0){
                    txt1.setText("" + number2[0]);
                }

                if (number2[0] >0){

                    number2[0] = number2[0]-1;
                    txt1.setText(""+ number2[0]);
                }
            }
        });
        Ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number2[0] == 500) {
                    txt1.setText("" + number2[0]);
                }

                if (number2[0] < 500) {

                    number2[0] = number2[0] +1;
                    txt1.setText("" + number2[0]);

                }
            }
        });

        Set_time = (TextView) findViewById(R.id.set_time);
        refil_reminder_switch = (SwitchCompat) findViewById(R.id.refil_reminder_switch);
        refil_reminder_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    refil_reminder_layout.setVisibility(View.VISIBLE);
                    refil_reminder_layout.setAlpha(1f);
                } else {
                    refil_reminder_layout.setVisibility(View.GONE);
                    refil_reminder_layout.setAlpha(0.4f);


                }

            }
        });
        checkedTextView_prescription = (CheckedTextView) findViewById(R.id.checkedTextView_prescription);

        layout_when_checked_prescription = (LinearLayout) findViewById(R.id.layout_when_checked_prescription);
        checkedTextView_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedTextView_prescription.isChecked()){
                    checkedTextView_prescription.setChecked(false);
                    layout_when_checked_prescription.setVisibility(View.GONE);
                }
                else{
                    checkedTextView_prescription.setChecked(true);
                    layout_when_checked_prescription.setVisibility(View.VISIBLE);
                }


            }
        });
        final LayoutInflater inflator1 = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        custompopup_view = inflator1.inflate(R.layout.number_picker, null);
        try {
            Select_text2=(TextView) custompopup_view.findViewById(R.id.select_text);
            LinearLayout   Add=(LinearLayout) custompopup_view.findViewById(R.id.plus);
            LinearLayout  Minus=(LinearLayout) custompopup_view.findViewById(R.id.minus);
            LinearLayout Okbutton1=(LinearLayout) custompopup_view.findViewById(R.id.ok_btn);
            LinearLayout  Closebutton=(LinearLayout) custompopup_view.findViewById(R.id.cancel_btn);
            txt=(TextView)custompopup_view.findViewById(R.id.no);
            number0[0]=med_detailsby_id.getRemind_no();
            txt.setText(""+ number0[0]);

            Minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number0[0] == 1){
                        txt.setText("" + number0[0]);
                    }

                    if (number0[0] > 1){

                        number0[0] = number0[0]-1;
                        txt.setText(""+ number0[0]);
                    }
                }
            });
            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number0[0] == number2[0]-1) {
                        txt.setText("" + number0[0]);
                    }
                    if (number0[0] < number2[0]-1) {

                        number0[0] = number0[0] + 1;
                        txt.setText("" + number0[0]);

                    }
                }
            });

            Closebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refil_txt.setText("When?");
                    refill_popup.dismiss();

                }
            });
            Okbutton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    refil_txt.setText("When i have "+number0[0]+" meds remaining");
                    refill_popup.dismiss();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
  /*      try {

            LinearLayout  reset_okbutton=(LinearLayout) custompopup_view.findViewById(R.id.ok_btn);
            LinearLayout  reset_closebutton=(LinearLayout) custompopup_view.findViewById(R.id.cancel_btn);
            final EditText  amt_medicine=(EditText) custompopup_view.findViewById(R.id.amount_f_refill_edit);

            reset_okbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt=amt_medicine.getText().toString();
                    refil_txt.setText("When i have "+txt+" meds remaining");
                    refill_popup.dismiss();
                }
            });
            reset_closebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refill_popup.dismiss();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }*/
        refil_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number2[0]==0){
                    Toast.makeText(getApplicationContext(),"Slect amount of medicine",Toast.LENGTH_LONG).show();
                }
                else {
                    Select_text2.setText("Select number of medicin to get a refill reminder");
                    number0[0]=0;
                    txt.setText(""+number0[0]);
                    displaypopup_refill();

                }

            }
        });
        Set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number2[0]==0){
                    Toast.makeText(getApplicationContext(),"Slect amount of medicine",Toast.LENGTH_LONG).show();
                }
                else {
                    showTimePicker6();
                }

            }
        });
        //---------------------------------------------doctor----------------------------------------------
        checketed_txt_when_click_doctor = (CheckedTextView) findViewById(R.id.checketed_txt_when_click_doctor);
        final LinearLayout doctor_layout=(LinearLayout) findViewById(R.id.doctor_layout);
        edit_doc_name=(EditText) findViewById(R.id.doc_name);
        edit_doc_spcl=(EditText) findViewById(R.id.doc_spcl);
        edit_doc_cont=(EditText) findViewById(R.id.doc_cont);
        checketed_txt_when_click_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checketed_txt_when_click_doctor.isChecked()){
                    checketed_txt_when_click_doctor.setChecked(false);
                    doctor_layout.setVisibility(View.GONE);
                }
                else{
                    checketed_txt_when_click_doctor.setChecked(true);
                    doctor_layout.setVisibility(View.VISIBLE);
                }


            }
        });
        //---------------------------------------------instruction----------------------------------------------
        edit_instruction = (EditText) findViewById(R.id.add_instr);

        final CheckedTextView checketed_txt_instruction = (CheckedTextView) findViewById(R.id.instruction);
        final LinearLayout Instr_layout=(LinearLayout) findViewById(R.id.instruction_layout);
        checketed_txt_instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checketed_txt_instruction.isChecked()){
                    checketed_txt_instruction.setChecked(false);
                    Instr_layout.setVisibility(View.GONE);
                }
                else{
                    checketed_txt_instruction.setChecked(true);
                    Instr_layout.setVisibility(View.VISIBLE);
                }


            }
        });
        //---------------------------------------------schedule----------------------------------------------

        LinearLayout Startdate=(LinearLayout)findViewById(R.id.linr_strt_date) ;
        final TextView Startdate_txt=(TextView)findViewById(R.id.txt_stsrt_date) ;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        Startdate_txt.setText(formattedDate);
        str_strt_date= df.format(c.getTime());
        Startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Update_medicine.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Startdate_txt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                str_strt_date=(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        rad_continuse = (RadioButton) findViewById(R.id.rad_continuse);
        rad_no_of_day = (RadioButton) findViewById(R.id.rad_no_of_day);
        rad_evry_day = (RadioButton) findViewById(R.id.rad_evry_day);
        rad_specifc_day = (RadioButton) findViewById(R.id.rad_specifc_day);


        final LayoutInflater inflator2 = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        custompopup_view2 = inflator2.inflate(R.layout.number_picker, null);
        try {

            Select_text3=(TextView) custompopup_view2.findViewById(R.id.select_text);
            LinearLayout Add_day=(LinearLayout) custompopup_view2.findViewById(R.id.plus);
            LinearLayout Minus_day=(LinearLayout) custompopup_view2.findViewById(R.id.minus);
            LinearLayout Okbutton2=(LinearLayout) custompopup_view2.findViewById(R.id.ok_btn);
            LinearLayout Cancel=(LinearLayout) custompopup_view2.findViewById(R.id.cancel_btn);
            final TextView txt2=(TextView)custompopup_view2.findViewById(R.id.no);

            txt2.setText(""+ number3[0]);

            Minus_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number3[0] == 1){
                        txt2.setText("" + number3[0]);
                    }

                    if (number3[0] > 1){

                        number3[0] = number3[0]-1;
                        txt2.setText(""+ number3[0]);
                    }
                }
            });
            Add_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (number3[0] == 100) {
                        txt2.setText("" + number3[0]);
                    }
                    if (number3[0] < 100) {

                        number3[0] = number3[0] + 1;
                        txt2.setText("" + number3[0]);

                    }
                }
            });

            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rad_continuse.setChecked(true);
                    rad_no_of_day.setChecked(false);
                    rad_no_of_day.setText("Number of Days");
                    schedule_day_popup.dismiss();

                }
            });
            Okbutton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rad_no_of_day.setText("Number of Days:"+number3[0]);
                    schedule_day_popup.dismiss();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
        rad_continuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rad_continuse.setChecked(true);
                rad_no_of_day.setChecked(false);
                rad_no_of_day.setText("Number of Days");
            }
        });
        rad_no_of_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rad_continuse.setChecked(false);
                rad_no_of_day.setChecked(true);
                Select_text3.setText("Select number of days");
                displaypopup_schedule_day();

            }
        });
        rad_evry_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rad_evry_day.setChecked(true);
                rad_specifc_day.setChecked(false);
                rad_specifc_day.setText("Specific days of week");
            }
        });
        rad_specifc_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rad_specifc_day.setChecked(true);
                rad_evry_day.setChecked(false);
                displaypopup_schedule_week();
            }
        });
        final LayoutInflater inflator3 = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        custompopup_view3 = inflator3.inflate(R.layout.week_picker, null);
        try {

            LinearLayout Okbutton2=(LinearLayout)custompopup_view3.findViewById(R.id.ok_btn);
            LinearLayout Cancel=(LinearLayout)custompopup_view3.findViewById(R.id.cancel_btn);
            final CheckBox sun_check=(CheckBox)custompopup_view3.findViewById(R.id.checkbox1);
            final CheckBox mon_check=(CheckBox)custompopup_view3.findViewById(R.id.checkbox2);
            final CheckBox tue_check=(CheckBox)custompopup_view3.findViewById(R.id.checkbox3);
            final CheckBox wen_check=(CheckBox)custompopup_view3.findViewById(R.id.checkbox4);
            final CheckBox thi_check=(CheckBox)custompopup_view3.findViewById(R.id.checkbox5);
            final CheckBox fri_check=(CheckBox)custompopup_view3.findViewById(R.id.checkbox6);
            final CheckBox sat_check=(CheckBox)custompopup_view3.findViewById(R.id.checkbox7);

            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sun_check.isChecked()&&!mon_check.isChecked()&&!sat_check.isChecked()&&!tue_check.isChecked()&&!wen_check.isChecked()&&!thi_check.isChecked()&&!fri_check.isChecked()){

                        rad_evry_day.setChecked(true);
                        rad_specifc_day.setChecked(false);
                        rad_specifc_day.setText("Specific days of week");

                    }
                    schedule_week_popup.dismiss();

                }
            });
            Okbutton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sun_check.isChecked()&&!mon_check.isChecked()&&!sat_check.isChecked()&&!tue_check.isChecked()&&!wen_check.isChecked()&&!thi_check.isChecked()&&!fri_check.isChecked()){

                        rad_evry_day.setChecked(true);
                        rad_specifc_day.setChecked(false);
                        rad_specifc_day.setText("Specific days of week");

                    }
                    if (sun_check.isChecked()&&mon_check.isChecked()&&sat_check.isChecked()&&tue_check.isChecked()&&wen_check.isChecked()&&thi_check.isChecked()&&fri_check.isChecked()){

                        rad_evry_day.setChecked(true);
                        rad_specifc_day.setChecked(false);
                        rad_specifc_day.setText("Specific days of week");

                    }
                    else {
                        if (sun_check.isChecked()){
                            one[0] ="Sun,";
                        }
                        else {
                            one[0] ="";
                        }
                        if (mon_check.isChecked()){
                            two[0] ="Mon,";
                        }
                        else {
                            two[0] ="";
                        }
                        if (tue_check.isChecked()){
                            three[0] ="Tue,";
                        }
                        else {
                            three[0] ="";
                        }
                        if (wen_check.isChecked()){
                            four[0] ="Wed,";
                        }
                        else {
                            four[0] ="";
                        }
                        if (thi_check.isChecked()){
                            five[0] ="Thu,";
                        }
                        else {
                            five[0] ="";
                        }
                        if (fri_check.isChecked()){
                            six[0] ="Fri,";
                        }
                        else {
                            six[0] ="";
                        }
                        if (sat_check.isChecked()){
                            seven[0] ="Sat";
                        }
                        else {
                            seven[0] ="";
                        }
                        txt_week= one[0]+ two[0]+ three[0]+ four[0]+ five[0]+ six[0]+ seven[0];
                        if (txt_week.endsWith(",")) {
                            txt_week = txt_week.substring(0, txt_week.length() - 1);
                        }
                        if (txt_week.startsWith(",")) {
                            txt_week = txt_week.substring(0, txt_week.length() - 1);
                        }
                        rad_specifc_day.setText("Specific days of week:"+txt_week);
                    }

                    schedule_week_popup.dismiss();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("med_detailsby_id.getdate1(); : "+med_detailsby_id.getdate1());
        System.out.println("med_detailsby_id.getdate2(); : "+med_detailsby_id.getdate2());
        System.out.println("med_detailsby_id.getdate3(); : "+med_detailsby_id.getdate3());
        System.out.println("med_detailsby_id.getdate4(); : "+med_detailsby_id.getdate4());
        System.out.println("med_detailsby_id.getTake1(); : "+med_detailsby_id.getTake1());
        System.out.println("med_detailsby_id.getTake2(); : "+med_detailsby_id.getTake2());
        System.out.println("med_detailsby_id.getTake3(); : "+med_detailsby_id.getTake3());
        System.out.println("med_detailsby_id.getTake4(); : "+med_detailsby_id.getTake4());

        edit_medication_name.setText(med_detailsby_id.getName());
        if(med_detailsby_id.getReminder_flag()!=null) {
            if (med_detailsby_id.getReminder_flag().equalsIgnoreCase("frequency") || med_detailsby_id.getReminder_flag().equalsIgnoreCase("interval")) {
               // reminderSwitch.setChecked(true);

                if (med_detailsby_id.getReminder_flag().equalsIgnoreCase("frequency")){

                    int cnt=0;
                    if(med_detailsby_id.getdate1()!=null && med_detailsby_id.getdate1().length()!=0)
                        cnt++;
                    if(med_detailsby_id.getdate2()!=null && med_detailsby_id.getdate2().length()!=0)
                        cnt++;
                    if(med_detailsby_id.getdate3()!=null && med_detailsby_id.getdate3().length()!=0)
                        cnt++;
                    if(med_detailsby_id.getdate4()!=null && med_detailsby_id.getdate4().length()!=0)
                        cnt++;

                    System.out.println("date count : "+cnt);

                    if(cnt==1){
                        rad_one_day.setChecked(true);
                        rad_one_day.performClick();
                        if(med_detailsby_id.getdate1()==null || med_detailsby_id.getdate1().length()==0|| med_detailsby_id.getdate1().equals("null")){
                            Rmndr_timetxt1.setText("Set Time");
                        }else {
                            Rmndr_timetxt1.setText(med_detailsby_id.getdate1());
                        }
                        Txt_tak1.setText("Take "+(med_detailsby_id.getTake1()));
                    }
                    if(cnt==2){
                        rad_two_day.setChecked(true);
                        rad_two_day.performClick();
                        if(med_detailsby_id.getdate1()==null|| med_detailsby_id.getdate1().length()==0|| med_detailsby_id.getdate1().equals("null")){
                            Rmndr_timetxt1.setText("Set Time");
                        }else {
                            Rmndr_timetxt1.setText(med_detailsby_id.getdate1());
                        }if(med_detailsby_id.getdate2()==null|| med_detailsby_id.getdate2().length()==0|| med_detailsby_id.getdate2().equals("null")){
                            Rmndr_timetxt2.setText("Set Time");
                        }else {
                            Rmndr_timetxt2.setText(med_detailsby_id.getdate2());
                        }
                        Txt_tak1.setText("Take "+(med_detailsby_id.getTake1()));
                        Txt_tak2.setText("Take "+(med_detailsby_id.getTake2()));
                    }
                    if(cnt==3){
                        rad_three_day.setChecked(true);
                        rad_three_day.performClick();
                        if(med_detailsby_id.getdate1()==null|| med_detailsby_id.getdate1().length()==0|| med_detailsby_id.getdate1().equals("null")){
                            Rmndr_timetxt1.setText("Set Time");
                        }else {
                            Rmndr_timetxt1.setText(med_detailsby_id.getdate1());
                        }
                        if(med_detailsby_id.getdate2()==null|| med_detailsby_id.getdate2().length()==0|| med_detailsby_id.getdate2().equals("null")){
                            Rmndr_timetxt2.setText("Set Time");
                        }else {
                            Rmndr_timetxt2.setText(med_detailsby_id.getdate2());
                        }
                        if(med_detailsby_id.getdate3()==null|| med_detailsby_id.getdate3().length()==0|| med_detailsby_id.getdate3().equals("null")){
                            Rmndr_timetxt3.setText("Set Time");
                        }else {
                            Rmndr_timetxt3.setText(med_detailsby_id.getdate3());
                        }
                        Txt_tak1.setText("Take "+(med_detailsby_id.getTake1()));
                        Txt_tak2.setText("Take "+(med_detailsby_id.getTake2()));
                        Txt_tak3.setText("Take "+(med_detailsby_id.getTake3()));
                    }
                    if(cnt==4){
                        rad_four_day.setChecked(true);
                        rad_four_day.performClick();
                        if(med_detailsby_id.getdate1()==null|| med_detailsby_id.getdate1().length()==0|| med_detailsby_id.getdate1().equals("null")){
                            Rmndr_timetxt1.setText("Set Time");
                        }else {
                            Rmndr_timetxt1.setText(med_detailsby_id.getdate1());
                        }
                        if(med_detailsby_id.getdate2()==null|| med_detailsby_id.getdate2().length()==0|| med_detailsby_id.getdate2().equals("null")){
                            Rmndr_timetxt2.setText("Set Time");
                        }else {
                            Rmndr_timetxt2.setText(med_detailsby_id.getdate2());
                        }
                        if(med_detailsby_id.getdate3()==null|| med_detailsby_id.getdate3().length()==0|| med_detailsby_id.getdate3().equals("null")){
                            Rmndr_timetxt3.setText("Set Time");
                        }else {
                            Rmndr_timetxt3.setText(med_detailsby_id.getdate3());
                        }
                        if(med_detailsby_id.getdate4()==null|| med_detailsby_id.getdate4().length()==0|| med_detailsby_id.getdate4().equals("null")){
                            Rmndr_timetxt4.setText("Set Time");
                        }else {
                            Rmndr_timetxt4.setText(med_detailsby_id.getdate4());
                        }
                        Txt_tak1.setText("Take "+(med_detailsby_id.getTake1()));
                        Txt_tak2.setText("Take "+(med_detailsby_id.getTake2()));
                        Txt_tak3.setText("Take "+(med_detailsby_id.getTake3()));
                        Txt_tak4.setText("Take "+(med_detailsby_id.getTake4()));

                    }


                }else{

                    if(med_detailsby_id.getInterval_time().equalsIgnoreCase(Evry_12.getText().toString()))
                    {
                        Evry_12.setChecked(true);
                        Evry_12.performClick();
                        //Txt_tak1.setText("Take "+Integer.toString(med_detailsby_id.getTake1()));
                        //Txt_tak2.setText("Take "+Integer.toString(med_detailsby_id.getTake2()));
                        //Txt_tak3.setText("Take "+Integer.toString(med_detailsby_id.getTake3()));
                        //Txt_tak4.setText("Take "+Integer.toString(med_detailsby_id.getTake4()));
                        Txt_tak5.setText("Take "+(med_detailsby_id.getTake1()));
                        Strt_Rmndr_timetxt.setText(med_detailsby_id.getdate1());
                    }
                    if(med_detailsby_id.getInterval_time().equalsIgnoreCase(Evry_8.getText().toString()))
                    {
                        Evry_8.setChecked(true);
                        Evry_8.performClick();
                        Txt_tak5.setText("Take "+(med_detailsby_id.getTake1()));
                        Strt_Rmndr_timetxt.setText(med_detailsby_id.getdate1());
                    }
                    if(med_detailsby_id.getInterval_time().equalsIgnoreCase(Evry_6.getText().toString()))
                    {
                        Evry_6.setChecked(true);
                        Evry_6.performClick();
                        Txt_tak5.setText("Take "+(med_detailsby_id.getTake1()));
                        Strt_Rmndr_timetxt.setText(med_detailsby_id.getdate1());
                    }
                    if(med_detailsby_id.getInterval_time().equalsIgnoreCase(Evry_4.getText().toString()))
                    {
                        Evry_4.setChecked(true);
                        Evry_4.performClick();
                        Txt_tak5.setText("Take "+(med_detailsby_id.getTake1()));
                        Strt_Rmndr_timetxt.setText(med_detailsby_id.getdate1());

                    }


                }



            }
        }

        dosage.setText(Integer.toString(med_detailsby_id.getDosage_no()));
        edit_instruction.setText(med_detailsby_id.getInstruction());
        txt1.setText(Double.toString( med_detailsby_id.getAmnt_meds()));

        edit_doc_name.setText(med_detailsby_id.getDoc_name());
        edit_doc_spcl.setText(med_detailsby_id.getDoc_specl());
        edit_doc_cont.setText(med_detailsby_id.getDoc_cont());


        if(med_detailsby_id.getAmnt_meds()>0){
            refil_reminder_switch.setChecked(true);
            refil_txt.setText("When i have "+med_detailsby_id.getRemind_no()+" meds remining");
            Set_time.setText(med_detailsby_id.getRemind_time());
        }

        Startdate_txt.setText(med_detailsby_id.getStrt_date());

        if(med_detailsby_id.getContinues_day()==1000){
            rad_continuse.setChecked(true);
            rad_continuse.performClick();
        }
        else {
            rad_no_of_day.setChecked(true);
            rad_no_of_day.setText("Number of Days: "+Integer.toString(med_detailsby_id.getContinues_day()));
        }

        if(med_detailsby_id.getEvry_day_week().equalsIgnoreCase("every_day")){
            rad_evry_day.setChecked(true);
            rad_evry_day.performClick();
        }
        else {
            rad_specifc_day.setChecked(true);
            rad_specifc_day.setText("Specific days of week : "+med_detailsby_id.getEvry_day_week());
        }

        for(int i=0;i<list.length;i++){
            if(list[i].equalsIgnoreCase(med_detailsby_id.getDosage_unit()))
            {
                spinner.setSelection(i);
            }
        }


        //---------------------------------------------update----------------------------------------------
        Linr_Update = (LinearLayout) findViewById(R.id.linr_nxt);

        Linr_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!reminderSwitch.isChecked()){
                    SweetAlertDialog dialog = new SweetAlertDialog(Update_medicine.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Please turn on the reminder!")
                            .setConfirmText("Ok")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();

                                }
                            })
                            .show();
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                    return;
                }
                int error_flag=0;

                if(Rmndr_timetxt1.getText().toString().equalsIgnoreCase("set time")){
                    str_date1=null;
                }else {
                    str_date1=Rmndr_timetxt1.getText().toString();
                }
                if(Rmndr_timetxt2.getText().toString().equalsIgnoreCase("set time")){
                    str_date2=null;
                }else {
                    str_date2=Rmndr_timetxt2.getText().toString();
                }

                if(Rmndr_timetxt3.getText().toString().equalsIgnoreCase("set time")){
                    str_date3=null;
                }else {
                    str_date3=Rmndr_timetxt3.getText().toString();
                }
                if(Rmndr_timetxt4.getText().toString().equalsIgnoreCase("set time")){
                    str_date4=null;
                }else {
                    str_date4=Rmndr_timetxt4.getText().toString();
                }

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("str_date1 : "+str_date1);
                System.out.println("str_date2 : "+str_date2);
                System.out.println("str_date3 : "+str_date3);
                System.out.println("str_date4 : "+str_date4);



                if(rad_continuse.isChecked()){
                    continue_days=1000;
                }
                if(rad_no_of_day.isChecked()){
                    continue_days=number3[0];
                }
                if(rad_evry_day.isChecked()){
                    str_evry_day_week="every_day";
                }
                if(rad_specifc_day.isChecked()){
                    str_evry_day_week=one[0]+ two[0]+ three[0]+ four[0]+ five[0]+ six[0]+ seven[0];
                    if (str_evry_day_week.endsWith(",")) {
                        str_evry_day_week = str_evry_day_week.substring(0, str_evry_day_week.length() - 1);
                    }
                    if (str_evry_day_week.startsWith(",")) {
                        str_evry_day_week = str_evry_day_week.substring(0, str_evry_day_week.length() - 1);
                    }
                }

                dosage_no=number[0];
                amnt_meds=  number2[0];
                remind_no=number0[0];
                str_med_name=edit_medication_name.getText().toString();
                str_dosage_unit=spinner.getSelectedItem().toString();
                str_instrcn=edit_instruction.getText().toString();
                str_doc_name=edit_doc_name.getText().toString();
                str_doc_spcl=edit_doc_spcl.getText().toString();
                str_doc_cont=edit_doc_cont.getText().toString();
                System.out.println("reminder_flag inside addmedicine : "+reminder_flag);
                System.out.println("str_take1 : "+str_take1);
                System.out.println("str_take2 : "+str_take2);
                System.out.println("str_take3 : "+str_take3);
                System.out.println("str_take4 : "+str_take4);

               /* if(dosage_no==0 || dosage.getText().toString()=="" || dosage.getText().toString()==null)
                {
                    error_flag=1;
                    Alerter.create(Update_medicine.this)
                            .setTitle("Dosage")
                            .setText("Please Set Dosage")
                            .setBackgroundColor(R.color.yellow)
                            .setDuration(1000)
                            .setIcon(R.drawable.alerter_ic_notifications)
                            .show();
                }
*/

                if(error_flag==0) {
                    System.out.println(str_med_name + "\n" + str_date1 + "\n" + str_date2 + "\n" + str_date3 + "\n" + str_date4 + "\n" + str_take1 + "\n" + str_take2 + "\n" + str_take3 + "\n" + str_take4 + "\n" +
                            dosage_no + "\n" + str_dosage_unit + "\n" + str_instrcn + "\n" + amnt_meds + "\n" + remind_no + "\n" + str_remind_time + "\n" + str_doc_name + "\n" + str_doc_spcl + "\n" + str_doc_cont + "\n" + str_strt_date + "\n" + continue_days + "\n" + str_evry_day_week + "\n" + reminder_flag + "\n" + interval_time);

                    Product newProduct = new Product(str_med_name, str_date1, str_date2, str_date3, str_date4, str_take1, str_take2, str_take3, str_take4,
                            dosage_no, str_dosage_unit, str_instrcn, amnt_meds, remind_no, str_remind_time, str_doc_name, str_doc_spcl, str_doc_cont, str_strt_date, continue_days, str_evry_day_week, reminder_flag, interval_time, "false", "false", "false", "false");
                    mDatabase.updateProduct(newProduct, selected_id);
                    List<Product> allProducts = mDatabase.listProducts();
                    for (Product p : allProducts) {
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("p.getdate1() : " + p.getdate1());
                        System.out.println("p.getdate2() : " + p.getdate2());
                        System.out.println("p.getdate3() : " + p.getdate3());
                        System.out.println("p.getdate4() : " + p.getdate4());
                        System.out.println("p.getName() : " + p.getName());
                        System.out.println("p.getId() : " + p.getId());
                        System.out.println("getDosage_no : " + p.getDosage_no());
                        System.out.println("getAmnt_meds : " + p.getAmnt_meds());
                        System.out.println("getRemind_time : " + p.getRemind_time());
                        System.out.println("getRemind_no : " + p.getRemind_no());
                        System.out.println("getInterval_time() : " + p.getInterval_time());
                        System.out.println("getSkipped_date1 : " + p.getSkipped_date1());
                        System.out.println("getSkipped_date2 : " + p.getSkipped_date2());
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm aaa");
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            String current = format.format(new Date());
                            String strtdate = p.getStrt_date();
                            Date curentt_date = format.parse(current);
                            Date strt_dat = format.parse(strtdate);
                            System.out.println("curentt_date : " + curentt_date);
                            System.out.println("strt_dat: " + strt_dat);
                            Calendar call = Calendar.getInstance();
                            call.setTime(strt_dat);
                            System.out.println("MONTH@@@@@@@@@@@@: " + (call.get(Calendar.MONTH)));
                            System.out.println("Date@@@@@@@@@@@@: " + call.get(Calendar.DATE));
                            System.out.println("YEAR@@@@@@@@@@@@: " + call.get(Calendar.YEAR));
                            if (p.getdate1() != null) {
                                if (Objects.equals(p.getInterval_time(), "Every 12 hours")) {
                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        System.out.println("getStrt_date()------------------" + p.getStrt_date());
                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int reqstcode = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();

                                        if (Objects.equals(p.getSkipped_date1(), "false")) {

                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 12, pendingIntent1);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent1);
                                            System.out.println("cancel()------------------");
                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 12, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }
                                    }
                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }

                                        for (int h = 0; h < 2; h++) {
                                            Calendar calendarr = Calendar.getInstance();
                                            calendarr.setTime(current_time);
                                            calendarr.add(Calendar.HOUR, 12 * h);

                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(strt_date);
                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, calendarr.get(Calendar.HOUR_OF_DAY));
                                            setcalendar.set(Calendar.MINUTE, calendarr.get(Calendar.MINUTE));
                                            setcalendar.set(Calendar.SECOND, 1);
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();

                                            System.out.println("$$$$$$$$$$$ request_code : " + request_code);
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent16);
                                                System.out.println("cancel()------------------");
                                            }
                                        }
                                    }

                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 12, pendingIntent4);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent4);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                                if (Objects.equals(p.getInterval_time(), "Every 8 hours")) {
                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        System.out.println("getStrt_date()------------------" + p.getStrt_date());
                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int reqstcode = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                        if (Objects.equals(p.getSkipped_date1(), "false")) {

                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 8, pendingIntent1);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent1);
                                            System.out.println("cancel()------------------");
                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {


                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 8, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }

                                    }
                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }

                                        for (int h = 0; h < 3; h++) {
                                            Calendar calendarr = Calendar.getInstance();
                                            calendarr.setTime(current_time);
                                            calendarr.add(Calendar.HOUR, 8 * h);

                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(strt_date);
                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, calendarr.get(Calendar.HOUR_OF_DAY));
                                            setcalendar.set(Calendar.MINUTE, calendarr.get(Calendar.MINUTE));
                                            setcalendar.set(Calendar.SECOND, 1);
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                            System.out.println("$$$$$$$$$$$ request_code : " + request_code);
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent16);
                                                System.out.println("cancel()------------------");
                                            }
                                        }
                                    }

                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 8, pendingIntent4);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent4);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }

                                        }
                                    }


                                }
                                if (Objects.equals(p.getInterval_time(), "Every 6 hours")) {
                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        System.out.println("getStrt_date()------------------" + p.getStrt_date());
                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int reqstcode = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                        if (Objects.equals(p.getSkipped_date1(), "false")) {

                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 6, pendingIntent1);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent1);
                                            System.out.println("cancel()------------------");
                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {


                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 6, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }

                                    }
                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }

                                        for (int h = 0; h < 4; h++) {
                                            Calendar calendarr = Calendar.getInstance();
                                            calendarr.setTime(current_time);
                                            calendarr.add(Calendar.HOUR, 6 * h);

                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(strt_date);
                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, calendarr.get(Calendar.HOUR_OF_DAY));
                                            setcalendar.set(Calendar.MINUTE, calendarr.get(Calendar.MINUTE));
                                            setcalendar.set(Calendar.SECOND, 1);
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                            System.out.println("$$$$$$$$$$$ request_code : " + request_code);
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent16);
                                                System.out.println("cancel()------------------");
                                            }
                                        }
                                    }

                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 6, pendingIntent4);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent4);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }

                                        }
                                    }


                                }
                                if (Objects.equals(p.getInterval_time(), "Every 4 hours")) {
                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        System.out.println("getStrt_date()------------------" + p.getStrt_date());
                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int reqstcode = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                        if (Objects.equals(p.getSkipped_date1(), "false")) {

                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 4, pendingIntent1);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent1);
                                            System.out.println("cancel()------------------");
                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {


                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 4, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }

                                    }
                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }

                                        for (int h = 0; h < 6; h++) {
                                            Calendar calendarr = Calendar.getInstance();
                                            calendarr.setTime(current_time);
                                            calendarr.add(Calendar.HOUR_OF_DAY, 4 * h);

                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(strt_date);
                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, calendarr.get(Calendar.HOUR_OF_DAY));
                                            setcalendar.set(Calendar.MINUTE, calendarr.get(Calendar.MINUTE));
                                            setcalendar.set(Calendar.SECOND, 1);
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                            System.out.println("$$$$$$$$$$$ request_code : " + request_code);
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent16);
                                                System.out.println("cancel()------------------");
                                            }
                                        }

                                    }

                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {


                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 4, pendingIntent4);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent4);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }

                                        }


                                    }

                                } else {

                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        System.out.println("rabin----------------" + p.getdate1());
                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("current_time.getMinutes()----------------" + current_time.getMinutes());
                                        System.out.println("current_time.getHours()----------------" + current_time.getHours());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                        if (Objects.equals(p.getSkipped_date1(), "false")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);


                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent18);
                                            System.out.println("cancel()------------------");
                                        }
                                    }


                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }


                                    }
                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }

                                                        first_date = calnew.getTime();


                                                    }
                                                }
                                            }
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (first_date));
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(first_date);
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                            System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                            System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                            setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                            setcalendar.set(Calendar.SECOND, 1);
                                            /*if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);*/

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                            }
                                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent20);
                                                System.out.println("cancel()------------------");
                                            }

                                        }

                                    }

                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate1());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date1(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date1(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent21);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                                if (p.getdate2() != null) {
                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        System.out.println("rabin----------------" + p.getdate2());
                                        Date current_time = simpleDateFormat.parse(p.getdate2());
                                        System.out.println("current_time.getMinutes()----------------" + current_time.getMinutes());
                                        System.out.println("current_time.getHours()----------------" + current_time.getHours());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                        if (Objects.equals(p.getSkipped_date2(), "false")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);


                                        }
                                        if (Objects.equals(p.getSkipped_date2(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent18);
                                            System.out.println("cancel()------------------");
                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate2());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date2(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date2(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }

                                    }

                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        Date current_time = simpleDateFormat.parse(p.getdate2());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }

                                                        first_date = calnew.getTime();


                                                    }
                                                }
                                            }
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (first_date));
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(first_date);
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                            System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                            System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                            setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                            setcalendar.set(Calendar.SECOND, 1);
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date2(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                            }
                                            if (Objects.equals(p.getSkipped_date2(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent20);
                                                System.out.println("cancel()------------------");
                                            }

                                        }
                                    }

                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate2());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date2(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date2(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent21);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }
                                        }
                                    }


                                }


                                if (p.getdate3() != null) {
                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        System.out.println("rabin----------------" + p.getdate3());
                                        Date current_time = simpleDateFormat.parse(p.getdate3());
                                        System.out.println("current_time.getMinutes()----------------" + current_time.getMinutes());
                                        System.out.println("current_time.getHours()----------------" + current_time.getHours());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                        if (Objects.equals(p.getSkipped_date3(), "false")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);


                                        }
                                        if (Objects.equals(p.getSkipped_date3(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent18);
                                            System.out.println("cancel()------------------");
                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        Date current_time = simpleDateFormat.parse(p.getdate3());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date3(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date3(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }


                                    }

                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        Date current_time = simpleDateFormat.parse(p.getdate3());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }

                                                        first_date = calnew.getTime();


                                                    }
                                                }
                                            }
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (first_date));
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(first_date);
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                            System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                            System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                            setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                            setcalendar.set(Calendar.SECOND, 1);
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date3(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                            }
                                            if (Objects.equals(p.getSkipped_date3(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent20);
                                                System.out.println("cancel()------------------");
                                            }

                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {


                                        Date current_time = simpleDateFormat.parse(p.getdate3());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date3(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date3(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent21);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }
                                        }
                                    }


                                }


                                if (p.getdate4() != null) {
                                    if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        System.out.println("rabin----------------" + p.getdate4());
                                        Date current_time = simpleDateFormat.parse(p.getdate4());
                                        System.out.println("current_time.getMinutes()----------------" + current_time.getMinutes());
                                        System.out.println("current_time.getHours()----------------" + current_time.getHours());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);
                                        if (setcalendar.before(calendar))
                                            setcalendar.add(Calendar.DATE, 1);
                                        int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                        if (Objects.equals(p.getSkipped_date4(), "false")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);


                                        }
                                        if (Objects.equals(p.getSkipped_date4(), "true")) {
                                            Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent18);
                                            System.out.println("cancel()------------------");
                                        }
                                    }
                                    if (p.getContinues_day() != 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        Date current_time = simpleDateFormat.parse(p.getdate4());
                                        Calendar calendar = Calendar.getInstance();
                                        Calendar setcalendar = Calendar.getInstance();

                                        System.out.println("########## ading_cal.get(Calendar.YEAR) " + call.get(Calendar.YEAR));
                                        System.out.println("########## ading_cal.get(Calendar.MONTH) " + call.get(Calendar.MONTH));
                                        System.out.println("########## ading_cal.get(Calendar.DATE) " + call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.YEAR, call.get(Calendar.YEAR));
                                        setcalendar.set(Calendar.MONTH, (call.get(Calendar.MONTH)));
                                        setcalendar.set(Calendar.DAY_OF_MONTH, call.get(Calendar.DATE));
                                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                        setcalendar.set(Calendar.SECOND, 1);


                                        for (int y = 1; y <= p.getContinues_day(); y++) {
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);
                                            int request_code = current_time.getHours() + current_time.getMinutes() + call.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date4(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent19);

                                            }
                                            if (Objects.equals(p.getSkipped_date4(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent19);
                                                System.out.println("cancel()------------------");
                                            }
                                        }
                                    }

                                    if (p.getContinues_day() == 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {
                                        Date current_time = simpleDateFormat.parse(p.getdate4());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }

                                                        first_date = calnew.getTime();


                                                    }
                                                }
                                            }
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (first_date));
                                            Calendar ccc = Calendar.getInstance();
                                            ccc.setTime(first_date);
                                            System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                            System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                            System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                            Calendar calendar = Calendar.getInstance();
                                            Calendar setcalendar = Calendar.getInstance();
                                            setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                            setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                            setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                            setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                            setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                            setcalendar.set(Calendar.SECOND, 1);
                                            if (setcalendar.before(calendar))
                                                setcalendar.add(Calendar.DATE, 1);

                                            int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                            if (Objects.equals(p.getSkipped_date4(), "false")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                if(p.getDosage_no()!=0){
                                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                }
                                                else {
                                                    myIntent.putExtra("medname", p.getName());
                                                }
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                            }
                                            if (Objects.equals(p.getSkipped_date4(), "true")) {
                                                Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                alarmManager.cancel(pendingIntent20);
                                                System.out.println("cancel()------------------");
                                            }

                                        }
                                    }


                                    if (p.getContinues_day() != 1000 && !Objects.equals(p.getEvry_day_week(), "every_day")) {

                                        Date current_time = simpleDateFormat.parse(p.getdate4());
                                        System.out.println("%%%%%%%%%% p.getEvry_day_week() : " + p.getEvry_day_week());
                                        String[] days_of_week = p.getEvry_day_week().split(",");
                                        for (int k = 0; k < days_of_week.length; k++) {
                                            System.out.println("%%%%%%%%%% days_of_week[" + k + "] : " + days_of_week[k]);

                                        }
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                        Date current_date = null;
                                        Date strt_date = null;

                                        String[] days = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};

                                        Date today_dt = new Date();

                                        SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                                        String selected_day = "";
                                        String tody_day = simpleDateformatnew.format(today_dt).toString();
                                        Date first_date = null;

                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                        String todays_date = format1.format(cal.getTime());
                                        for (int i = 0; i < days_of_week.length; i++) {
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
                                                        if (days[u].equalsIgnoreCase("sun")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("mon")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("tue")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("wed")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("thu")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("fri")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        } else if (days[u].equalsIgnoreCase("sat")) {
                                                            while (calnew.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                                                                calnew.add(Calendar.DATE, 1);
                                                            }
                                                        }
                                                        first_date = calnew.getTime();
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            String founded_date = formatter.format(first_date);
                                            strt_date = formatter.parse(founded_date);
                                            current_date = formatter.parse(todays_date);
                                            System.out.println("%%%%%%%%%% startdate : " + strt_date);
                                            System.out.println("%%%%%%%%%% current_date : " + current_date);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }

                                        Date[] nxt_dates = new Date[p.getContinues_day()];

                                        Date array_added_date = strt_date;

                                        Calendar ading_cal = Calendar.getInstance();
                                        for (int k = 0; k < p.getContinues_day(); k++) {
                                            ading_cal.setTime(array_added_date);
                                            nxt_dates[k] = ading_cal.getTime();
                                            ading_cal.add(Calendar.DATE, 1);
                                            array_added_date = ading_cal.getTime();
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                                        Calendar ading_cal_new = Calendar.getInstance();
                                        int lim = (p.getContinues_day() / 7) + 1;
                                        Date[] repeating_dates = new Date[lim];
                                        Date array_added_date_new = strt_date;

                                        for (int k = 0; k < lim; k++) {
                                            System.out.println("%%%%%%%%%% setting time : " + array_added_date_new);
                                            ading_cal_new.setTime(array_added_date_new);
                                            repeating_dates[k] = ading_cal_new.getTime();
                                            ading_cal_new.add(Calendar.DATE, 7);
                                            array_added_date_new = ading_cal_new.getTime();
                                            System.out.println("%%%%%%%%%% repeating_dates[" + k + "] : " + repeating_dates[k]);
                                        }

                                        ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                                        System.out.println("%%%%%%%%%% nxt_dates are ");
                                        for (int k = 0; k < nxt_dates.length; k++) {
                                            System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                            for (int m = 0; m < repeating_dates.length; m++) {
                                                if (nxt_dates[k].equals(repeating_dates[m])) {

                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (strt_date));
                                                    Calendar ccc = Calendar.getInstance();
                                                    ccc.setTime(repeating_dates[m]);
                                                    System.out.println("output_MONTH@@@@@@@@@@@@: " + (ccc.get(Calendar.MONTH)));
                                                    System.out.println("output_Date@@@@@@@@@@@@: " + ccc.get(Calendar.DATE));
                                                    System.out.println("output_YEAR@@@@@@@@@@@@: " + ccc.get(Calendar.YEAR));

                                                    Calendar calendar = Calendar.getInstance();
                                                    Calendar setcalendar = Calendar.getInstance();
                                                    setcalendar.set(Calendar.YEAR, ccc.get(Calendar.YEAR));
                                                    setcalendar.set(Calendar.MONTH, (ccc.get(Calendar.MONTH)));
                                                    setcalendar.set(Calendar.DAY_OF_MONTH, ccc.get(Calendar.DATE));
                                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                                    setcalendar.set(Calendar.SECOND, 1);
                                                    if (setcalendar.before(calendar))
                                                        setcalendar.add(Calendar.DATE, 1);
                                                    int request_code = current_time.getHours() + current_time.getMinutes() + ccc.get(Calendar.DATE) + p.getId();
                                                    if (Objects.equals(p.getSkipped_date4(), "false")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        if(p.getDosage_no()!=0){
                                                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                                        }
                                                        else {
                                                            myIntent.putExtra("medname", p.getName());
                                                        }
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                                    }
                                                    if (Objects.equals(p.getSkipped_date4(), "true")) {
                                                        Intent myIntent = new Intent(Update_medicine.this, NotifyService1.class);
                                                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                                        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                                        alarmManager.cancel(pendingIntent21);
                                                        System.out.println("cancel()------------------");
                                                    }
                                                }
                                            }
                                        }
                                    }


                                }

                            System.out.println("p.getRemind_no() : " + p.getRemind_no());
                        } catch (ParseException ee) {
                            ee.printStackTrace();
                        }
                        if (p.getAmnt_meds() <= p.getRemind_no()) {

                            try {
                                if (p.getRemind_time() != null) {
                                    SimpleDateFormat DateFormat1 = new SimpleDateFormat("hh.mm aaa");
                                    Date current_time = DateFormat1.parse(p.getRemind_time());
                                    Calendar calendar = Calendar.getInstance();
                                    Calendar setcalendar = Calendar.getInstance();
                                    setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                    setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                    setcalendar.set(Calendar.SECOND, 1);
                                    if (setcalendar.before(calendar))
                                        setcalendar.add(Calendar.DATE, 1);
                                    int requstcode = current_time.getHours() + current_time.getMinutes() + p.getId() + current_time.getMinutes();
                                    Intent myIntent = new Intent(Update_medicine.this, NotifyService3.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                    PendingIntent pending = PendingIntent.getBroadcast(context, requstcode, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pending);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    Intent k = new Intent(Update_medicine.this, Medication_Activity.class);
                    startActivity(k);
                    finish();
                }
            }
        });




    }

    protected void showTimePicker1() {
        DialogFragment newFragment = new TimePickerFragment1();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    protected void showTimePicker2() {
        DialogFragment newFragment = new TimePickerFragment2();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    protected void showTimePicker3() {
        DialogFragment newFragment = new TimePickerFragment3();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    protected void showTimePicker4() {
        DialogFragment newFragment = new TimePickerFragment4();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    protected void showTimePicker5() {
        DialogFragment newFragment = new TimePickerFragment5();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    protected void showTimePicker6() {
        DialogFragment newFragment = new TimePickerFragment6();
        newFragment.show(getFragmentManager(), "timePicker");
    }
    @SuppressLint("ValidFragment")
    public class TimePickerFragment6 extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(context, this, hour, minute, DateFormat.is24HourFormat(context));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user

            String AM_PM = "";
            Calendar calendar = Calendar.getInstance();
            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            datetime.set(Calendar.SECOND, 0);
            if(datetime.before(calendar))
                datetime.add(Calendar.DATE,1);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                AM_PM = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                AM_PM = "PM";

            String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                    .get(Calendar.HOUR) + "";

            int hours1= Integer.parseInt(hours);
            String minute1;
            if(hours1>=0&&hours1<10){
                hours=  String.format("%02d",hours1);
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_remind_time=(hours + "." + minute1 + " " + AM_PM);
                    Set_time.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {
                    str_remind_time=(hours + "." + minute + " " + AM_PM);
                    Set_time.setText(hours + "." + minute + " " + AM_PM);
                }
            }
            else {
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_remind_time=(hours + "." + minute1 + " " + AM_PM);
                    Set_time.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {
                    str_remind_time=(hours + "." + minute + " " + AM_PM);
                    Set_time.setText(hours + "." + minute + " " + AM_PM);
                }

            }



        }
    }

    @SuppressLint("ValidFragment")
    public class TimePickerFragment1 extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(context, this, hour, minute,DateFormat.is24HourFormat(context));
        }

        public void onTimeSet(TimePicker view, int hourOfDay,int  minute) {
            // Do something with the time chosen by the user
            displaypopup_take();
            Select_text1.setText("Select number of medicine you want to take at this time");
            Okbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Txt_tak1.setText("Take"+number1[0]);
                    str_take1= number1[0];
                    take_popup.dismiss();
                }
            });
            String AM_PM = "";
            Calendar calendar = Calendar.getInstance();
            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            datetime.set(Calendar.SECOND, 0);
            if(datetime.before(calendar))
                datetime.add(Calendar.DATE,1);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                AM_PM = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                AM_PM = "PM";

            String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                    .get(Calendar.HOUR) + "";
            int hours1= Integer.parseInt(hours);
            String minute1;
            if(hours1>=0&&hours1<10){
                hours=  String.format("%02d",hours1);
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date1=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt1.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date1=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt1.setText(hours + "." + minute + " " + AM_PM);
                }
            }
            else {
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date1=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt1.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date1=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt1.setText(hours + "." + minute + " " + AM_PM);
                }

            }
            localData.set_hour(hourOfDay);
            localData.set_min(minute);




        }
    }
    @SuppressLint("ValidFragment")
    public class TimePickerFragment2 extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(context, this, hour, minute,DateFormat.is24HourFormat(context));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            displaypopup_take();
            Select_text1.setText("Select number of medicine you want to take at this time");
            Okbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Txt_tak2.setText("Take"+number1[0]);
                    str_take2= number1[0];
                    take_popup.dismiss();
                }
            });
            String AM_PM = "";
            Calendar calendar = Calendar.getInstance();
            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            datetime.set(Calendar.SECOND, 0);
            if(datetime.before(calendar))
                datetime.add(Calendar.DATE,1);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                AM_PM = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                AM_PM = "PM";

            String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                    .get(Calendar.HOUR) + "";
            int hours1= Integer.parseInt(hours);

            String minute1;
            if(hours1>=0&&hours1<10){
                hours=  String.format("%02d",hours1);
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date2=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt2.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date2=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt2.setText(hours + "." + minute + " " + AM_PM);
                }
            }
            else {
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date2=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt2.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date2=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt2.setText(hours + "." + minute + " " + AM_PM);
                }

            }
            localData.set_hour(hourOfDay);
            localData.set_min(minute);




        }
    }

    @SuppressLint("ValidFragment")
    public class TimePickerFragment3 extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(context, this, hour, minute,DateFormat.is24HourFormat(context));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            displaypopup_take();
            Select_text1.setText("Select number of medicine you want to take at this time");
            Okbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Txt_tak3.setText("Take"+number1[0]);
                    str_take3= number1[0];
                    take_popup.dismiss();
                }
            });
            String AM_PM = "";
            Calendar calendar = Calendar.getInstance();
            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            datetime.set(Calendar.SECOND, 0);
            if(datetime.before(calendar))
                datetime.add(Calendar.DATE,1);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                AM_PM = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                AM_PM = "PM";

            String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                    .get(Calendar.HOUR) + "";
            int hours1= Integer.parseInt(hours);

            String minute1;
            if(hours1>=0&&hours1<10){
                hours=  String.format("%02d",hours1);
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date3=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt3.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date3=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt3.setText(hours + "." + minute + " " + AM_PM);
                }
            }
            else {
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date3=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt3.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date3=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt3.setText(hours + "." + minute + " " + AM_PM);
                }

            }
            localData.set_hour(hourOfDay);
            localData.set_min(minute);




        }
    }

    @SuppressLint("ValidFragment")
    public class TimePickerFragment4 extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(context, this, hour, minute,DateFormat.is24HourFormat(context));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            displaypopup_take();
            Select_text1.setText("Select number of medicine you want to take at this time");
            Okbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Txt_tak4.setText("Take"+number1[0]);
                    str_take4= number1[0];
                    take_popup.dismiss();
                }
            });
            String AM_PM = "";
            Calendar calendar = Calendar.getInstance();
            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            datetime.set(Calendar.SECOND, 0);
            if(datetime.before(calendar))
                datetime.add(Calendar.DATE,1);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                AM_PM = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                AM_PM = "PM";

            String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                    .get(Calendar.HOUR) + "";
            int hours1= Integer.parseInt(hours);

            String minute1;
            if(hours1>=0&&hours1<10){
                hours=  String.format("%02d",hours1);
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date4=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt4.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date4=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt4.setText(hours + "." + minute + " " + AM_PM);
                }
            }
            else {
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date4=hours + "." + minute1 + " " + AM_PM;
                    Rmndr_timetxt4.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date4=hours + "." + minute + " " + AM_PM;
                    Rmndr_timetxt4.setText(hours + "." + minute + " " + AM_PM);
                }

            }
            localData.set_hour(hourOfDay);
            localData.set_min(minute);




        }
    }

    @SuppressLint("ValidFragment")
    public class TimePickerFragment5 extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(context, this, hour, minute,
                    DateFormat.is24HourFormat(context));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            displaypopup_take();
            Select_text1.setText("Select number of medicine you want to take at this time");
            Okbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Txt_tak5.setText("Take"+number1[0]);
                    str_take1= number1[0];
                    take_popup.dismiss();
                }
            });
            String AM_PM = "";
            Calendar calendar = Calendar.getInstance();
            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            datetime.set(Calendar.SECOND, 0);
            if (datetime.before(calendar))
                datetime.add(Calendar.DATE, 1);
            if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                AM_PM = "AM";
            else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                AM_PM = "PM";

            String hours = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime
                    .get(Calendar.HOUR) + "";

            int hours1= Integer.parseInt(hours);

            String minute1;
            if(hours1>=0&&hours1<10){
                hours=  String.format("%02d",hours1);
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date1=hours + "." + minute1 + " " + AM_PM;
                    Strt_Rmndr_timetxt.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date1=hours + "." + minute + " " + AM_PM;
                    Strt_Rmndr_timetxt.setText(hours + "." + minute + " " + AM_PM);
                }
            }
            else {
                if(minute>=0&&minute<10){

                    minute1= (String.format("%02d",minute));
                    str_date1=hours + "." + minute1 + " " + AM_PM;
                    Strt_Rmndr_timetxt.setText(hours + "." + minute1 + " " + AM_PM);
                }
                else {

                    str_date1=hours + "." + minute + " " + AM_PM;
                    Strt_Rmndr_timetxt.setText(hours + "." + minute + " " + AM_PM);
                }

            }
            localData.set_hour(hourOfDay);
            localData.set_min(minute);



        }
    }
    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }
    public void displaypopup_refill() {
        try {
            refill_popup =new PopupWindow(custompopup_view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                refill_popup.setElevation(5.0f);
            }
            refill_popup.setFocusable(true);
            refill_popup.setAnimationStyle(R.style.AppTheme);
            refill_popup.showAtLocation(inflate_layout, Gravity.CENTER,0,0);

        }
        catch (Exception e){
            e.printStackTrace();
        }}
    public void displaypopup_take() {
        try {
            take_popup =new PopupWindow(custompopup_view1, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                take_popup.setElevation(5.0f);
            }
            take_popup.setFocusable(true);
            take_popup.setAnimationStyle(R.style.AppTheme);
            take_popup.showAtLocation(inflate_layout, Gravity.CENTER,0,0);

        }
        catch (Exception e){
            e.printStackTrace();
        }}
    public void displaypopup_schedule_day() {
        try {
            schedule_day_popup =new PopupWindow(custompopup_view2, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                schedule_day_popup.setElevation(5.0f);
            }
            schedule_day_popup.setFocusable(true);
            schedule_day_popup.setAnimationStyle(R.style.AppTheme);
            schedule_day_popup.showAtLocation(inflate_layout, Gravity.CENTER,0,0);

        }
        catch (Exception e){
            e.printStackTrace();
        }}
    public void displaypopup_schedule_week() {
        try {
            schedule_week_popup =new PopupWindow(custompopup_view3, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            if (Build.VERSION.SDK_INT >= 21) {
                schedule_week_popup.setElevation(5.0f);
            }
            schedule_week_popup.setFocusable(true);
            schedule_week_popup.setAnimationStyle(R.style.AppTheme);
            schedule_week_popup.showAtLocation(inflate_layout, Gravity.CENTER,0,0);

        }
        catch (Exception e){
            e.printStackTrace();
        }}

}
