package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meridian.cybraum.zulekha.alexis.alexispharmacy.MainActivity;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ansal on 10/11/2017.
 */

public class Medication_Activity extends AppCompatActivity {

    LinearLayout Add_medication,Back;
    LinearLayout Morning_linr,Aftrnoon_linr,Evng_linr,Night_linr;
    TextView Zulekha_medication,Time;
    SqliteDatabase mDatabase;
    Context context;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication);
        context=this;
        mDatabase = new SqliteDatabase(getApplicationContext());


        Add_medication = (LinearLayout) findViewById(R.id.add_medication);


        Morning_linr = (LinearLayout) findViewById(R.id.morning_linr);
        Aftrnoon_linr = (LinearLayout) findViewById(R.id.aftrnoon_linr);
        Evng_linr = (LinearLayout) findViewById(R.id.evng_linr);
        Night_linr = (LinearLayout) findViewById(R.id.night_linr);
        Zulekha_medication= (TextView) findViewById(R.id.home_txt);
        Zulekha_medication.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "Mark Simonson - Proxima Nova Alt Regular-webfont.ttf"));
        Back=(LinearLayout)findViewById(R.id.menu);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Medication_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Time= (TextView) findViewById(R.id.time);
        SimpleDateFormat sdfor = new SimpleDateFormat("dd-MM-yyyy");//,hh.mm aaa
        String currentDateandTime = sdfor.format(new Date());
        Time.setText(currentDateandTime);
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c=Calendar.getInstance();
                mYear=c.get(Calendar.YEAR);
                mMonth=c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(Medication_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofMonth) {
                        SimpleDateFormat dfDate  = new SimpleDateFormat("dd-MM-yyyy");
                        String current_date=mYear+"-"+mMonth+"-"+mDay;
                        String selected_date=year+"-"+monthofYear+"-"+dayofMonth;
                        try {


                                Time.setText(dayofMonth+ "-" + (monthofYear + 1) + "-" +year
                                );

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });






        final SharedPreferences preference_days=getApplicationContext().getSharedPreferences("day_sectcion",MODE_PRIVATE);
        Morning_linr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 = preference_days.edit();
                editor1.putString("section", "Morning");
                editor1.putString("time", "4.00 AM-12.00 PM");
                editor1.putString("todays_date", Time.getText().toString());
                editor1.apply();
                Intent i=new Intent(Medication_Activity.this,Days_Activity.class);
                startActivity(i);

            }
        });
        Aftrnoon_linr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 = preference_days.edit();
                editor1.putString("section", "Afternoon");
                editor1.putString("time", "12.00 PM-4.00 PM");
                editor1.putString("todays_date", Time.getText().toString());
                editor1.apply();
                Intent i=new Intent(Medication_Activity.this,Days_Activity.class);
                startActivity(i);

            }
        });
        Evng_linr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 = preference_days.edit();
                editor1.putString("section", "Evening");
                editor1.putString("time", "4.00 PM-8.00 PM");
                editor1.putString("todays_date", Time.getText().toString());
                editor1.apply();
                Intent i=new Intent(Medication_Activity.this,Days_Activity.class);
                startActivity(i);

            }
        });
        Night_linr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor1 = preference_days.edit();
                editor1.putString("section", "Night");
                editor1.putString("time", "8.00 PM-04.00 AM");
                editor1.putString("todays_date", Time.getText().toString());
                editor1.apply();
                Intent i=new Intent(Medication_Activity.this,Days_Activity.class);
                startActivity(i);

            }
        });

        Add_medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Medication_Activity.this,Add_medicine.class);
                startActivity(i);
                finish();
            }
        });



    }

    @Override
    public void onBackPressed() {

        Intent k=new Intent(Medication_Activity.this,MainActivity.class);
        startActivity(k);
        finish();
    }
}
