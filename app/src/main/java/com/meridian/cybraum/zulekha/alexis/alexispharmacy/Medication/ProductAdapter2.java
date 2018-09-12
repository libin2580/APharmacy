package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meridian.cybraum.zulekha.alexis.alexispharmacy.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ansal on 10/21/2017.
 */

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ProductViewHolder>{
    private Context context;
    private String[] listProducts;
    private SqliteDatabase mDatabase;
    private String[] med_ids;
    private String dates;
    Date current_time;
    SharedPreferences preference_days;
    Date today_date=null;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    Calendar call = Calendar.getInstance();
    public ProductAdapter2(Context context, String[] listProducts, String[] idss,String dts) {
        this.context = context;
        this.listProducts = listProducts;
        this.med_ids=idss;
        this.dates=dts;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm aaa");
        preference_days = context.getSharedPreferences("day_sectcion", MODE_PRIVATE);
        String pref_tdy_date = preference_days.getString("todays_date", null);



        try {
             current_time = simpleDateFormat.parse(dates);
            today_date=format.parse(pref_tdy_date);

            call.setTime(today_date);
            System.out.println("today_date in adapter : "+today_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("dates are : "+dates);

        mDatabase = new SqliteDatabase(context);

    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_adapter_one, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {





        holder.name.setText(listProducts[position]);
        String dosage=mDatabase.getMedDosageById(med_ids[position]);
        holder.medi_dosage.setText(dosage);

        holder.linr_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("~~~~~~~~~~~~~~~~~~ name : "+listProducts[position]);
                System.out.println("~~~~~~~~~~~~~~~~~~ id : "+med_ids[position]);

            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~~~~~~~~~~~~~~~~~ name : "+listProducts[position]);
                System.out.println("~~~~~~~~~~~~~~~~~~ id : "+med_ids[position]);
                System.out.println("dates are : "+dates);
                holder.Extraa.setVisibility(View.VISIBLE);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.Extraa.setVisibility(View.GONE);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Update_medicine.class);
                i.putExtra("selected_id",med_ids[position]);
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String intervel_time=mDatabase.getIntervelTime(med_ids[position]);
                System.out.println("intervel_time : "+intervel_time);

                mDatabase.deleteDate(dates,listProducts[position],med_ids[position]);
              if(Objects.equals(intervel_time, "Every 4 hours")){
                    for(int h=0;h<6;h++) {
                        Calendar calendarr = Calendar.getInstance();
                        calendarr.setTime(current_time);
                        calendarr.add(Calendar.HOUR_OF_DAY, 4*h);
                        int request_code=calendarr.get(Calendar.HOUR_OF_DAY)+calendarr.get(Calendar.MINUTE)+call.get(Calendar.DATE)+Integer.parseInt(med_ids[position]);
                        reminder(request_code);
                    }
                }
                else if(Objects.equals(intervel_time, "Every 6 hours")){
                    for(int h=0;h<4;h++) {
                        Calendar calendarr = Calendar.getInstance();
                        calendarr.setTime(current_time);
                        calendarr.add(Calendar.HOUR_OF_DAY, 6*h);
                        int request_code=calendarr.get(Calendar.HOUR_OF_DAY)+calendarr.get(Calendar.MINUTE)+call.get(Calendar.DATE)+Integer.parseInt(med_ids[position]);
                        reminder(request_code);
                    }
                }
                else  if(Objects.equals(intervel_time, "Every 8 hours")){
                    for(int h=0;h<3;h++) {
                        Calendar calendarr = Calendar.getInstance();
                        calendarr.setTime(current_time);
                        calendarr.add(Calendar.HOUR_OF_DAY, 8*h);
                        int request_code=calendarr.get(Calendar.HOUR_OF_DAY)+calendarr.get(Calendar.MINUTE)+call.get(Calendar.DATE)+Integer.parseInt(med_ids[position]);
                        reminder(request_code);
                    }
                }
                else if(Objects.equals(intervel_time, "Every 12 hours")){
                    for(int h=0;h<2;h++) {
                        Calendar calendarr = Calendar.getInstance();
                        calendarr.setTime(current_time);
                        calendarr.add(Calendar.HOUR_OF_DAY, 12*h);
                        int request_code=calendarr.get(Calendar.HOUR_OF_DAY)+calendarr.get(Calendar.MINUTE)+call.get(Calendar.DATE)+Integer.parseInt(med_ids[position]);
                        reminder(request_code);
                    }
                }
                else {
                  int request_code=current_time.getHours()+current_time.getMinutes()+call.get(Calendar.DATE)+Integer.parseInt(med_ids[position]);
                    reminder(request_code);
                }
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

             }
        });


    }
    private void reminder( int request_code) {

        Intent myIntent = new Intent(context, NotifyService1.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent21);
        System.out.println("request_code()------------------"+request_code );

    }

    @Override
    public int getItemCount() {
        return listProducts.length;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        RelativeLayout linr_one;
        LinearLayout more,edit,delete,cancel,Extraa;
        TextView medi_dosage;

        public ProductViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.medi_name);
            linr_one=(RelativeLayout)itemView.findViewById(R.id.linr_one);
            Extraa = (LinearLayout)itemView.findViewById(R.id.extraa);
            more = (LinearLayout)itemView.findViewById(R.id.more);
            edit = (LinearLayout)itemView.findViewById(R.id.edit);
            delete = (LinearLayout)itemView.findViewById(R.id.delete);
            cancel = (LinearLayout)itemView.findViewById(R.id.cancel);
            medi_dosage=(TextView)itemView.findViewById(R.id.medi_dosage);

        }
    }
}