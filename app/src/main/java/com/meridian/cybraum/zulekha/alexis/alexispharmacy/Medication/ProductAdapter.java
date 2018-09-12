package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

/**
 * Created by Ansal on 10/20/2017.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.meridian.cybraum.zulekha.alexis.alexispharmacy.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.ALARM_SERVICE;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private Context context;
    private List<MedicinModel> listProducts;
    private SqliteDatabase mDatabase;
    ArrayList<Model> medi_list=new ArrayList<Model>();
    Model model;
    private ArrayList<String> added_date1,added_date2,added_date3,added_date4;
    private String date1_Id,date2_Id,date3_Id,date4_Id;
    private String date1_med_names="",date2_med_names="",date3_med_names="",date4_med_names="";
    MedModel2 mm2;
    ArrayList<MedModel2>newMedArraylist;
    ArrayList<Integer>listProductsIDss;
    private boolean[] checkboxStatus;
    private  MedModel2 singleProduct;
    private String[] holder_dates;

    public ProductAdapter(Context context, List<MedicinModel> listProducts) {
        this.context = context;
        mm2=new MedModel2();

        mDatabase = new SqliteDatabase(context);

        this.listProducts = listProducts;
        added_date1=new ArrayList<>();
        added_date2=new ArrayList<>();
        added_date3=new ArrayList<>();
        added_date4=new ArrayList<>();

        newMedArraylist=new ArrayList<>();
        listProductsIDss=new ArrayList<>();
        System.out.println("listProducts.size() : "+listProducts.size());
        System.out.println("--------------- INSIDE PRODUCT ADAPTER -----------------");
        for(MedicinModel m:listProducts){
            System.out.println("-------------------------------");
            System.out.println("m.getId() : "+m.getId());
            System.out.println("m.getDate1() : "+m.getDate1());
            System.out.println("m.getDate2() : "+m.getDate2());
            System.out.println("m.getDate3() : "+m.getDate3());
            System.out.println("m.getDate4() : "+m.getDate4());
            System.out.println("m.getMedine_name() : "+m.getMedine_name().toString());
            listProductsIDss.add(m.getId());
            System.out.println("-------------------------------");
        }

/* for deleting repeating row in  arrylist  --start     */
        for(int i=0;i<listProducts.size();i++){
            int flag=0;
            for(int k=0;k<listProducts.size();k++){
                if(listProducts.get(i).getId()==listProducts.get(k).getId()){
                    flag++;
                }
            }
            if(flag>1) {
                listProducts.remove(i);
            }
        }
/* for deleting repeating row in  arrylist  --end     */



        for(MedicinModel m1:listProducts) {
            if (!contains_date(added_date1, m1.getDate1())) {//to prevent twon same dates being added to arraylist
                for (MedicinModel m2 : listProducts) {
                    if (m1.getDate1() != null && m2.getDate1() != null) {
                        if (m1.getDate1().equalsIgnoreCase(m2.getDate1())) {
                            if(!added_date1.contains(m2.getDate1())) {
                                added_date1.add(m2.getDate1().toString());
                            }
                        }
                    }
                }
            }
        }
        for(MedicinModel m1:listProducts) {
            if (!contains_date(added_date2, m1.getDate2())) {//to prevent twon same dates being added to arraylist
                for (MedicinModel m2 : listProducts) {
                    if (m1.getDate2() != null && m2.getDate2() != null) {
                        if (m1.getDate2().equalsIgnoreCase(m2.getDate2())) {
                            if(!added_date2.contains(m2.getDate2())) {
                                added_date2.add(m2.getDate2().toString());


                            }
                        }
                    }
                }
            }
        }
        for(MedicinModel m1:listProducts) {
            if (!contains_date(added_date3, m1.getDate3())) {//to prevent twon same dates being added to arraylist
                for (MedicinModel m2 : listProducts) {
                    if (m1.getDate3() != null && m2.getDate3() != null) {
                        if (m1.getDate3().equalsIgnoreCase(m2.getDate3())) {
                            if(!added_date3.contains(m2.getDate3())) {
                                added_date3.add(m2.getDate3().toString());

                            }
                        }
                    }
                }
            }
        }
        for(MedicinModel m1:listProducts) {
            if (!contains_date(added_date4, m1.getDate4())) {//to prevent twon same dates being added to arraylist
                for (MedicinModel m2 : listProducts) {
                    if (m1.getDate4() != null && m2.getDate4() != null) {
                        if (m1.getDate4().equalsIgnoreCase(m2.getDate4())) {
                            if(!added_date4.contains(m2.getDate4())) {
                                added_date4.add(m2.getDate4().toString());
                            }
                        }
                    }
                }
            }
        }
/* for merging medicin nemes of same date  --start     */



        for(int i=0;i<added_date1.size();i++){
            date1_med_names="";
            date1_Id="";
            mm2=new MedModel2();
            for(int k=0;k<listProducts.size();k++){
                if(added_date1.get(i).equalsIgnoreCase(listProducts.get(k).getDate1())){
                    date1_med_names+=listProducts.get(k).getMedine_name()+",";
                    date1_Id+=listProducts.get(k).getId()+",";
                }
            }
            mm2.setDate(added_date1.get(i));
            mm2.setList_of_meds(date1_med_names);
            mm2.setId(date1_Id);
            newMedArraylist.add(mm2);
        }
        for(int i=0;i<added_date2.size();i++){
            date2_med_names="";
            date2_Id="";
            mm2=new MedModel2();
            for(int k=0;k<listProducts.size();k++){
                if(added_date2.get(i).equalsIgnoreCase(listProducts.get(k).getDate2())){
                    date2_med_names+=listProducts.get(k).getMedine_name()+",";
                    date2_Id+=listProducts.get(k).getId()+",";
                }
            }
            mm2.setDate(added_date2.get(i));
            mm2.setList_of_meds(date2_med_names);
            mm2.setId(date2_Id);
            newMedArraylist.add(mm2);
        }
        for(int i=0;i<added_date3.size();i++){
            date3_med_names="";
            date3_Id="";
            mm2=new MedModel2();
            for(int k=0;k<listProducts.size();k++){
                if(added_date3.get(i).equalsIgnoreCase(listProducts.get(k).getDate3())){
                    date3_med_names+=listProducts.get(k).getMedine_name()+",";
                    date3_Id+=listProducts.get(k).getId()+",";
                }
            }
            mm2.setDate(added_date3.get(i));
            mm2.setList_of_meds(date3_med_names);
            mm2.setId(date3_Id);
            newMedArraylist.add(mm2);
        }
        for(int i=0;i<added_date4.size();i++){
            date4_med_names="";
            date4_Id="";
            mm2=new MedModel2();
            for(int k=0;k<listProducts.size();k++){
                if(added_date4.get(i).equalsIgnoreCase(listProducts.get(k).getDate4())){
                    date4_med_names+=listProducts.get(k).getMedine_name()+",";
                    date4_Id+=listProducts.get(k).getId()+",";
                }
            }
            mm2.setDate(added_date4.get(i));
            mm2.setList_of_meds(date4_med_names);
            mm2.setId(date4_Id);
            newMedArraylist.add(mm2);
        }
/* for merging medicin nemes of same date  --start     */



/* for sorting the final arrylist to join same date parts --start     */
        ArrayList<String> tempArraylist=new ArrayList<>();
        for(int i=0;i<newMedArraylist.size();i++) {
            if (!contains_date(tempArraylist, newMedArraylist.get(i).getDate())) {//to prevent twon same dates being added to arraylist
                for (int k=0;k<newMedArraylist.size();k++) {
                    if (newMedArraylist.get(i).getDate().equalsIgnoreCase(newMedArraylist.get(k).getDate())) {
                        if(!tempArraylist.contains(newMedArraylist.get(k).getDate())) {
                            tempArraylist.add(newMedArraylist.get(k).getDate());
                        }
                    }
                }
            }
        }

        String med_names="";
        String med_ids="";
        ArrayList<MedModel2> tempnewMedArraylist=new ArrayList<>();
        for(int i=0;i<tempArraylist.size();i++){
            med_names="";
            med_ids="";
            mm2=new MedModel2();
            for(int k=0;k<newMedArraylist.size();k++){
                if(tempArraylist.get(i).equalsIgnoreCase(newMedArraylist.get(k).getDate())){
                    med_names+=newMedArraylist.get(k).getList_of_meds();
                    med_ids+=newMedArraylist.get(k).getId();
                }
            }
            mm2.setDate(tempArraylist.get(i));
            mm2.setList_of_meds(med_names);
            mm2.setId(med_ids);
            tempnewMedArraylist.add(mm2);
        }

        newMedArraylist.clear();
        newMedArraylist=tempnewMedArraylist;
/* for sorting the final arrylist to join same date parts --end     */



        checkboxStatus = new boolean[newMedArraylist.size()];//for showing only single expanded view
        for(int k=0;k<newMedArraylist.size();k++)
            checkboxStatus[k]=false;//for showing only single expanded view


        for(MedModel2 m2:newMedArraylist){
            System.out.println("########################");
            System.out.println("mm2.getDate() : "+m2.getDate());
            System.out.println("mm2.getList_of_meds() : "+m2.getList_of_meds());
            System.out.println("mm2.getId() : "+m2.getId());
            System.out.println("########################");
        }

        mDatabase = new SqliteDatabase(context);
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_adapter, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        // final SharedPreferences preference_days = context.getSharedPreferences("day_sectcion", MODE_PRIVATE);
        // String day_section = preference_days.getString("section", null);
        final String[] parts_new;
        final String[] med_ids_new;
        String[] parts;
        String[] med_ids;


        singleProduct = newMedArraylist.get(position);
        parts = singleProduct.getList_of_meds().split(",");
        med_ids=singleProduct.getId().split(",");

        System.out.println("@@@ singleProduct.getList_of_meds() : "+singleProduct.getList_of_meds());
        System.out.println("@@@ singleProduct.getId() : "+singleProduct.getId());

        System.out.println("----------------------------------------");
        System.out.println("in parts : ");
        for(int i=0;i<parts.length;i++)
        {
            System.out.println(parts[i]);
        }
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("in med_ids : ");
        for(int i=0;i<med_ids.length;i++)
        {
            System.out.println(med_ids[i]);
        }
        System.out.println("----------------------------------------");



        parts_new = printDistinctElements(parts);
        med_ids_new = printDistinctElements(med_ids);

        System.out.println("----------------------------------------");
        System.out.println("in parts_new : ");
        for(int i=0;i<parts_new.length;i++)
        {
            System.out.println(parts_new[i]);
        }
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("in med_ids_new : ");
        for(int i=0;i<med_ids_new.length;i++)
        {
            System.out.println(med_ids_new[i]);
        }
        System.out.println("----------------------------------------");




        String skipped_status=mDatabase.checkIfSkipped(newMedArraylist.get(position).getDate(),med_ids_new);
        System.out.println("skipped_status ("+position+") : "+skipped_status);
        if(skipped_status.equalsIgnoreCase("true")){
            holder.linear_skip.setVisibility(View.GONE);
            holder.linear_unskip.setVisibility(View.VISIBLE);
        }
        else {
            holder.linear_skip.setVisibility(View.VISIBLE);
            holder.linear_unskip.setVisibility(View.GONE);
        }

        /*if(checkboxStatus[position]==false){//for showing only single expanded view
            holder.Date1.setChecked(false);
            holder.Medi_details1.setVisibility(View.GONE);
        }
        else {
            holder.Date1.setChecked(true);
            holder.Medi_details1.setVisibility(View.VISIBLE);
        }*/



        try {
            //if(singleProduct.getDate1()!=null){

            holder.Card1.setVisibility(View.VISIBLE);
            holder.Date1.setText(singleProduct.getDate());

            holder.Date1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                                /*for(int k=0;k<newMedArraylist.size();k++) {
                                    if(k==position)
                                        checkboxStatus[k] = true;//for showing only single expanded view
                                    else
                                        checkboxStatus[k] = false;//for showing only single expanded view
                                }*/
                    // notifyItemRangeChanged(0,newMedArraylist.size());
                    if (holder.Date1.isChecked()){
                        holder.Date1.setChecked(false);
                        holder.Medi_details1.setVisibility(View.GONE);
                    }
                    else{
                        holder.Date1.setChecked(true);
                        holder.Medi_details1.setVisibility(View.VISIBLE);
                        medi_list.clear();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        holder.recyclerView1.setLayoutManager(linearLayoutManager);
                        holder.recyclerView1.setHasFixedSize(true);

                                  /*mDatabase = new SqliteDatabase(context);
                                    List<Product> allProducts = mDatabase.listProducts();
                                    model=new Model();
                                    model.setMedi_name(allProducts.get(position).getName());
                                    model.setDate1(allProducts.get(position).getdate1());
                                    model.setDate2(allProducts.get(position).getdate2());
                                    model.setDate3(allProducts.get(position).getdate3());
                                    model.setDate4(allProducts.get(position).getdate4());
                                    medi_list.add(model);

                                    String[] parts = singleProduct.getList_of_meds().split(",");
                                    String[] med_ids=singleProduct.getId().split(",");

                                    ProductAdapter2 mAdapter = new ProductAdapter2(context,parts,med_ids,medi_list);
                                    holder.recyclerView1.setAdapter(mAdapter);*/


                        ProductAdapter2 mAdapter = new ProductAdapter2(context,parts_new,med_ids_new,holder.Date1.getText().toString());
                        holder.recyclerView1.setAdapter(mAdapter);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



        holder.linear_reschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String[] med_ids=singleProduct.getId().split(",");
                //final String[] med_ids_new = printDistinctElements(med_ids);

                Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int  minut) {
                        String str_date1="";
                        String AM_PM = "";
                        Calendar calendar1 = Calendar.getInstance();
                        Calendar datetime = Calendar.getInstance();
                        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetime.set(Calendar.MINUTE, minut);
                        datetime.set(Calendar.SECOND, 0);
                        if(datetime.before(calendar1))
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
                            if(minut>=0&&minut<10){

                                minute1= (String.format("%02d",minut));
                                str_date1=hours + "." + minute1 + " " + AM_PM;
                            }
                            else {

                                str_date1=hours + "." + minut + " " + AM_PM;
                            }
                        }
                        else {
                            if(minut>=0&&minut<10){

                                minute1= (String.format("%02d",minut));
                                str_date1=hours + "." + minute1 + " " + AM_PM;
                            }
                            else {

                                str_date1=hours + "." + minut + " " + AM_PM;
                            }
                        }
                        mDatabase.rescheduleDate(holder.Date1.getText().toString(),med_ids_new,str_date1);
                        reminder();
                        ((Activity) context).finish();
                        context.startActivity(((Activity) context).getIntent());

                    }
                },mHour,mMinute,false);
                timePickerDialog.show();



            }
        });


        holder.linear_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String[] med_ids=singleProduct.getId().split(",");
                //String[] med_ids_new = printDistinctElements(med_ids);


                mDatabase.decermentMedCountOnTake(holder.Date1.getText().toString(),med_ids_new);
                Toast.makeText(context,"medicine taken",Toast.LENGTH_SHORT).show();
                reminder();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });


        holder.linear_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.setSkipStatus(holder.Date1.getText().toString(),med_ids_new,"true");
                holder.linear_skip.setVisibility(View.GONE);
                holder.linear_unskip.setVisibility(View.VISIBLE);
                reminder();
            }
        });

        holder.linear_unskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.setSkipStatus(holder.Date1.getText().toString(),med_ids_new,"false");
                holder.linear_skip.setVisibility(View.VISIBLE);
                holder.linear_unskip.setVisibility(View.GONE);
                reminder();
            }
        });
    }

    private void reminder() {
        List<Product> allProducts = mDatabase.listProducts();
        for(Product p:allProducts) {
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
                System.out.println("MONTH@@@@@@@@@@@@: "+(call.get(Calendar.MONTH)));
                System.out.println("Date@@@@@@@@@@@@: "+call.get(Calendar.DATE));
                System.out.println("YEAR@@@@@@@@@@@@: "+call.get(Calendar.YEAR));
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

                                Intent myIntent = new Intent(context, NotifyService1.class);
                                if(p.getDosage_no()!=0){
                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                }
                                else {
                                    myIntent.putExtra("medname", p.getName());
                                }
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 12, pendingIntent1);
                            }
                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 12, pendingIntent19);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 12, pendingIntent4);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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

                                Intent myIntent = new Intent(context, NotifyService1.class);
                                if(p.getDosage_no()!=0){
                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                }
                                else {
                                    myIntent.putExtra("medname", p.getName());
                                }
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 8, pendingIntent1);
                            }
                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 8, pendingIntent19);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 8, pendingIntent4);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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

                                Intent myIntent = new Intent(context, NotifyService1.class);
                                if(p.getDosage_no()!=0){
                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                }
                                else {
                                    myIntent.putExtra("medname", p.getName());
                                }
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 6, pendingIntent1);
                            }
                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 6, pendingIntent19);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 6, pendingIntent4);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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

                                Intent myIntent = new Intent(context, NotifyService1.class);
                                if(p.getDosage_no()!=0){
                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                }
                                else {
                                    myIntent.putExtra("medname", p.getName());
                                }
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, reqstcode, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 4, pendingIntent1);
                            }
                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 4, pendingIntent19);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent16 = PendingIntent.getBroadcast(context, request_code, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent16);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent4 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 4, pendingIntent4);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                if(p.getDosage_no()!=0){
                                    myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                }
                                else {
                                    myIntent.putExtra("medname", p.getName());
                                }
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);


                            }
                            if (Objects.equals(p.getSkipped_date1(), "true")) {
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent19);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                }
                                if (Objects.equals(p.getSkipped_date1(), "true")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                        }
                                        if (Objects.equals(p.getSkipped_date1(), "true")) {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
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
                            System.out.println("rabin----------------"+p.getdate2());
                            Date current_time = simpleDateFormat.parse(p.getdate2());
                            System.out.println("current_time.getMinutes()----------------"+current_time.getMinutes());
                            System.out.println("current_time.getHours()----------------"+current_time.getHours());
                            Calendar calendar = Calendar.getInstance();
                            Calendar setcalendar = Calendar.getInstance();
                            setcalendar.set(Calendar.YEAR,call.get(Calendar.YEAR));
                            setcalendar.set(Calendar.MONTH,(call.get(Calendar.MONTH)));
                            setcalendar.set(Calendar.DAY_OF_MONTH,call.get(Calendar.DATE));
                            setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                            setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                            setcalendar.set(Calendar.SECOND,1);
                            if (setcalendar.before(calendar))
                                setcalendar.add(Calendar.DATE, 1);
                            int request_code=current_time.getHours()+ current_time.getMinutes()+call.get(Calendar.DATE)+p.getId();
                            if(Objects.equals(p.getSkipped_date2(),"false")){
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                myIntent.putExtra("medname",p.getName()+"("+p.getDosage_no()+" "+p.getDosage_unit()+")");
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);



                            }
                            if(Objects.equals(p.getSkipped_date2(),"true")){
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.cancel(pendingIntent18);
                                System.out.println("cancel()------------------" );
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


                            for(int y=1;y<=p.getContinues_day();y++){
                                if (setcalendar.before(calendar))
                                    setcalendar.add(Calendar.DATE, 1);
                                int request_code=current_time.getHours()+ current_time.getMinutes()+call.get(Calendar.DATE)+p.getId();
                                if (Objects.equals(p.getSkipped_date2(), "false")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),1000*60*60*24, pendingIntent19);

                                }
                                if(Objects.equals(p.getSkipped_date2(),"true")){
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.cancel(pendingIntent19);
                                    System.out.println("cancel()------------------" );
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
                            Date current_date=null;
                            Date strt_date = null;

                            String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                            Date today_dt=new Date();

                            SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                            String selected_day="";
                            String tody_day=simpleDateformatnew.format(today_dt).toString();
                            Date first_date=null;

                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                            String todays_date = format1.format(cal.getTime());
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
                                System.out.println("output_MONTH@@@@@@@@@@@@: "+(first_date));
                                Calendar ccc = Calendar.getInstance();
                                ccc.setTime(first_date);
                                System.out.println("output_MONTH@@@@@@@@@@@@: "+(ccc.get(Calendar.MONTH)));
                                System.out.println("output_Date@@@@@@@@@@@@: "+ccc.get(Calendar.DATE));
                                System.out.println("output_YEAR@@@@@@@@@@@@: "+ccc.get(Calendar.YEAR));

                                Calendar calendar = Calendar.getInstance();
                                Calendar setcalendar = Calendar.getInstance();
                                setcalendar.set(Calendar.YEAR,ccc.get(Calendar.YEAR));
                                setcalendar.set(Calendar.MONTH,(ccc.get(Calendar.MONTH)));
                                setcalendar.set(Calendar.DAY_OF_MONTH,ccc.get(Calendar.DATE));
                                setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                setcalendar.set(Calendar.SECOND, 1);
                                if (setcalendar.before(calendar))
                                    setcalendar.add(Calendar.DATE, 1);

                                int request_code=current_time.getHours()+ current_time.getMinutes()+ccc.get(Calendar.DATE)+p.getId();
                                if(Objects.equals(p.getSkipped_date2(),"false")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    myIntent.putExtra("medname",p.getName()+"("+p.getDosage_no()+" "+p.getDosage_unit()+")");
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                }
                                if(Objects.equals(p.getSkipped_date2(),"true")){
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.cancel(pendingIntent20);
                                    System.out.println("cancel()------------------" );
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
                            Date current_date=null;
                            Date strt_date = null;

                            String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                            Date today_dt=new Date();

                            SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                            String selected_day="";
                            String tody_day=simpleDateformatnew.format(today_dt).toString();
                            Date first_date=null;

                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                            String todays_date = format1.format(cal.getTime());
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
                            }
                            try {
                                String founded_date=formatter.format(first_date);
                                strt_date=formatter.parse(founded_date);
                                current_date=formatter.parse(todays_date);
                                System.out.println("%%%%%%%%%% startdate : "+strt_date);
                                System.out.println("%%%%%%%%%% current_date : "+current_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Date[] nxt_dates=new Date[p.getContinues_day()];

                            Date array_added_date=strt_date;

                            Calendar ading_cal=Calendar.getInstance();
                            for (int k = 0; k < p.getContinues_day(); k++) {
                                ading_cal.setTime(array_added_date);
                                nxt_dates[k] = ading_cal.getTime();
                                ading_cal.add(Calendar.DATE,1);
                                array_added_date = ading_cal.getTime();
                            }

                            ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                            Calendar ading_cal_new=Calendar.getInstance();
                            int lim=(p.getContinues_day()/7)+1;
                            Date[] repeating_dates=new Date[lim];
                            Date array_added_date_new=strt_date;

                            for (int k = 0; k < lim; k++) {
                                System.out.println("%%%%%%%%%% setting time : "+array_added_date_new);
                                ading_cal_new.setTime(array_added_date_new);
                                repeating_dates[k] = ading_cal_new.getTime();
                                ading_cal_new.add(Calendar.DATE,7);
                                array_added_date_new = ading_cal_new.getTime();
                                System.out.println("%%%%%%%%%% repeating_dates["+k+"] : "+repeating_dates[k]);
                            }

                            ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                            System.out.println("%%%%%%%%%% nxt_dates are ");
                            for (int k = 0; k < nxt_dates.length; k++) {
                                System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                for(int m=0;m<repeating_dates.length;m++) {
                                    if(nxt_dates[k].equals(repeating_dates[m])){

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
                                        int request_code=current_time.getHours()+ current_time.getMinutes()+ccc.get(Calendar.DATE)+p.getId();
                                        if(Objects.equals(p.getSkipped_date2(),"false"))
                                        {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                        }
                                        if(Objects.equals(p.getSkipped_date2(),"true"))
                                        {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent21);
                                            System.out.println("cancel()------------------" );
                                        }
                                    }
                                }
                            }
                        }



                    }


                    if (p.getdate3() != null) {
                        if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                            System.out.println("rabin----------------"+p.getdate3());
                            Date current_time = simpleDateFormat.parse(p.getdate3());
                            System.out.println("current_time.getMinutes()----------------"+current_time.getMinutes());
                            System.out.println("current_time.getHours()----------------"+current_time.getHours());
                            Calendar calendar = Calendar.getInstance();
                            Calendar setcalendar = Calendar.getInstance();
                            setcalendar.set(Calendar.YEAR,call.get(Calendar.YEAR));
                            setcalendar.set(Calendar.MONTH,(call.get(Calendar.MONTH)));
                            setcalendar.set(Calendar.DAY_OF_MONTH,call.get(Calendar.DATE));
                            setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                            setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                            setcalendar.set(Calendar.SECOND,1);
                            if (setcalendar.before(calendar))
                                setcalendar.add(Calendar.DATE, 1);
                            int request_code=current_time.getHours()+ current_time.getMinutes()+call.get(Calendar.DATE)+p.getId();
                            if(Objects.equals(p.getSkipped_date3(),"false")){
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                myIntent.putExtra("medname",p.getName()+"("+p.getDosage_no()+" "+p.getDosage_unit()+")");
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);



                            }
                            if(Objects.equals(p.getSkipped_date3(),"true")){
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.cancel(pendingIntent18);
                                System.out.println("cancel()------------------" );
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


                            for(int y=1;y<=p.getContinues_day();y++){
                                if (setcalendar.before(calendar))
                                    setcalendar.add(Calendar.DATE, 1);
                                int request_code=current_time.getHours()+ current_time.getMinutes()+call.get(Calendar.DATE)+p.getId();
                                if (Objects.equals(p.getSkipped_date3(), "false")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),1000*60*60*24, pendingIntent19);

                                }
                                if(Objects.equals(p.getSkipped_date3(),"true")){
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.cancel(pendingIntent19);
                                    System.out.println("cancel()------------------" );
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
                            Date current_date=null;
                            Date strt_date = null;

                            String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                            Date today_dt=new Date();

                            SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                            String selected_day="";
                            String tody_day=simpleDateformatnew.format(today_dt).toString();
                            Date first_date=null;

                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                            String todays_date = format1.format(cal.getTime());
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
                                System.out.println("output_MONTH@@@@@@@@@@@@: "+(first_date));
                                Calendar ccc = Calendar.getInstance();
                                ccc.setTime(first_date);
                                System.out.println("output_MONTH@@@@@@@@@@@@: "+(ccc.get(Calendar.MONTH)));
                                System.out.println("output_Date@@@@@@@@@@@@: "+ccc.get(Calendar.DATE));
                                System.out.println("output_YEAR@@@@@@@@@@@@: "+ccc.get(Calendar.YEAR));

                                Calendar calendar = Calendar.getInstance();
                                Calendar setcalendar = Calendar.getInstance();
                                setcalendar.set(Calendar.YEAR,ccc.get(Calendar.YEAR));
                                setcalendar.set(Calendar.MONTH,(ccc.get(Calendar.MONTH)));
                                setcalendar.set(Calendar.DAY_OF_MONTH,ccc.get(Calendar.DATE));
                                setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                setcalendar.set(Calendar.SECOND, 1);
                                if (setcalendar.before(calendar))
                                    setcalendar.add(Calendar.DATE, 1);

                                int request_code=current_time.getHours()+ current_time.getMinutes()+ccc.get(Calendar.DATE)+p.getId();
                                if(Objects.equals(p.getSkipped_date3(),"false")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    myIntent.putExtra("medname",p.getName()+"("+p.getDosage_no()+" "+p.getDosage_unit()+")");
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                }
                                if(Objects.equals(p.getSkipped_date3(),"true")){
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.cancel(pendingIntent20);
                                    System.out.println("cancel()------------------" );
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
                            Date current_date=null;
                            Date strt_date = null;

                            String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                            Date today_dt=new Date();

                            SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                            String selected_day="";
                            String tody_day=simpleDateformatnew.format(today_dt).toString();
                            Date first_date=null;

                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                            String todays_date = format1.format(cal.getTime());
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
                            }
                            try {
                                String founded_date=formatter.format(first_date);
                                strt_date=formatter.parse(founded_date);
                                current_date=formatter.parse(todays_date);
                                System.out.println("%%%%%%%%%% startdate : "+strt_date);
                                System.out.println("%%%%%%%%%% current_date : "+current_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Date[] nxt_dates=new Date[p.getContinues_day()];

                            Date array_added_date=strt_date;

                            Calendar ading_cal=Calendar.getInstance();
                            for (int k = 0; k < p.getContinues_day(); k++) {
                                ading_cal.setTime(array_added_date);
                                nxt_dates[k] = ading_cal.getTime();
                                ading_cal.add(Calendar.DATE,1);
                                array_added_date = ading_cal.getTime();
                            }

                            ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                            Calendar ading_cal_new=Calendar.getInstance();
                            int lim=(p.getContinues_day()/7)+1;
                            Date[] repeating_dates=new Date[lim];
                            Date array_added_date_new=strt_date;

                            for (int k = 0; k < lim; k++) {
                                System.out.println("%%%%%%%%%% setting time : "+array_added_date_new);
                                ading_cal_new.setTime(array_added_date_new);
                                repeating_dates[k] = ading_cal_new.getTime();
                                ading_cal_new.add(Calendar.DATE,7);
                                array_added_date_new = ading_cal_new.getTime();
                                System.out.println("%%%%%%%%%% repeating_dates["+k+"] : "+repeating_dates[k]);
                            }

                            ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                            System.out.println("%%%%%%%%%% nxt_dates are ");
                            for (int k = 0; k < nxt_dates.length; k++) {
                                System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                for(int m=0;m<repeating_dates.length;m++) {
                                    if(nxt_dates[k].equals(repeating_dates[m])){

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
                                        int request_code=current_time.getHours()+ current_time.getMinutes()+ccc.get(Calendar.DATE)+p.getId();
                                        if(Objects.equals(p.getSkipped_date3(),"false"))
                                        {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                        }
                                        if(Objects.equals(p.getSkipped_date3(),"true"))
                                        {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent21);
                                            System.out.println("cancel()------------------" );
                                        }
                                    }
                                }
                            }
                        }



                    }


                    if (p.getdate4() != null) {
                        if (p.getContinues_day() == 1000 && Objects.equals(p.getEvry_day_week(), "every_day")) {
                            System.out.println("rabin----------------"+p.getdate4());
                            Date current_time = simpleDateFormat.parse(p.getdate4());
                            System.out.println("current_time.getMinutes()----------------"+current_time.getMinutes());
                            System.out.println("current_time.getHours()----------------"+current_time.getHours());
                            Calendar calendar = Calendar.getInstance();
                            Calendar setcalendar = Calendar.getInstance();
                            setcalendar.set(Calendar.YEAR,call.get(Calendar.YEAR));
                            setcalendar.set(Calendar.MONTH,(call.get(Calendar.MONTH)));
                            setcalendar.set(Calendar.DAY_OF_MONTH,call.get(Calendar.DATE));
                            setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                            setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                            setcalendar.set(Calendar.SECOND,1);
                            if (setcalendar.before(calendar))
                                setcalendar.add(Calendar.DATE, 1);
                            int request_code=current_time.getHours()+ current_time.getMinutes()+call.get(Calendar.DATE)+p.getId();
                            if(Objects.equals(p.getSkipped_date4(),"false")){
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                myIntent.putExtra("medname",p.getName()+"("+p.getDosage_no()+" "+p.getDosage_unit()+")");
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent18);



                            }
                            if(Objects.equals(p.getSkipped_date4(),"true")){
                                Intent myIntent = new Intent(context, NotifyService1.class);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                PendingIntent pendingIntent18 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                alarmManager.cancel(pendingIntent18);
                                System.out.println("cancel()------------------" );
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


                            for(int y=1;y<=p.getContinues_day();y++){
                                if (setcalendar.before(calendar))
                                    setcalendar.add(Calendar.DATE, 1);
                                int request_code=current_time.getHours()+ current_time.getMinutes()+call.get(Calendar.DATE)+p.getId();
                                if (Objects.equals(p.getSkipped_date4(), "false")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    if(p.getDosage_no()!=0){
                                        myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                    }
                                    else {
                                        myIntent.putExtra("medname", p.getName());
                                    }
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),1000*60*60*24, pendingIntent19);

                                }
                                if(Objects.equals(p.getSkipped_date4(),"true")){
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent19 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.cancel(pendingIntent19);
                                    System.out.println("cancel()------------------" );
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
                            Date current_date=null;
                            Date strt_date = null;

                            String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                            Date today_dt=new Date();

                            SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                            String selected_day="";
                            String tody_day=simpleDateformatnew.format(today_dt).toString();
                            Date first_date=null;

                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                            String todays_date = format1.format(cal.getTime());
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
                                System.out.println("output_MONTH@@@@@@@@@@@@: "+(first_date));
                                Calendar ccc = Calendar.getInstance();
                                ccc.setTime(first_date);
                                System.out.println("output_MONTH@@@@@@@@@@@@: "+(ccc.get(Calendar.MONTH)));
                                System.out.println("output_Date@@@@@@@@@@@@: "+ccc.get(Calendar.DATE));
                                System.out.println("output_YEAR@@@@@@@@@@@@: "+ccc.get(Calendar.YEAR));

                                Calendar calendar = Calendar.getInstance();
                                Calendar setcalendar = Calendar.getInstance();
                                setcalendar.set(Calendar.YEAR,ccc.get(Calendar.YEAR));
                                setcalendar.set(Calendar.MONTH,(ccc.get(Calendar.MONTH)));
                                setcalendar.set(Calendar.DAY_OF_MONTH,ccc.get(Calendar.DATE));
                                setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                                setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                                setcalendar.set(Calendar.SECOND, 1);
                                if (setcalendar.before(calendar))
                                    setcalendar.add(Calendar.DATE, 1);

                                int request_code=current_time.getHours()+ current_time.getMinutes()+ccc.get(Calendar.DATE)+p.getId();
                                if(Objects.equals(p.getSkipped_date4(),"false")) {
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    myIntent.putExtra("medname",p.getName()+"("+p.getDosage_no()+" "+p.getDosage_unit()+")");
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY * 7, pendingIntent20);

                                }
                                if(Objects.equals(p.getSkipped_date4(),"true")){
                                    Intent myIntent = new Intent(context, NotifyService1.class);
                                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                    PendingIntent pendingIntent20 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                    alarmManager.cancel(pendingIntent20);
                                    System.out.println("cancel()------------------" );
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
                            Date current_date=null;
                            Date strt_date = null;

                            String[] days=new String[]{"sun","mon","tue","wed","thu","fri","sat"};

                            Date today_dt=new Date();

                            SimpleDateFormat simpleDateformatnew = new SimpleDateFormat("E"); // the day of the week abbreviated
                            String selected_day="";
                            String tody_day=simpleDateformatnew.format(today_dt).toString();
                            Date first_date=null;

                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                            String todays_date = format1.format(cal.getTime());
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
                            }
                            try {
                                String founded_date=formatter.format(first_date);
                                strt_date=formatter.parse(founded_date);
                                current_date=formatter.parse(todays_date);
                                System.out.println("%%%%%%%%%% startdate : "+strt_date);
                                System.out.println("%%%%%%%%%% current_date : "+current_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Date[] nxt_dates=new Date[p.getContinues_day()];

                            Date array_added_date=strt_date;

                            Calendar ading_cal=Calendar.getInstance();
                            for (int k = 0; k < p.getContinues_day(); k++) {
                                ading_cal.setTime(array_added_date);
                                nxt_dates[k] = ading_cal.getTime();
                                ading_cal.add(Calendar.DATE,1);
                                array_added_date = ading_cal.getTime();
                            }

                            ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --start
                            Calendar ading_cal_new=Calendar.getInstance();
                            int lim=(p.getContinues_day()/7)+1;
                            Date[] repeating_dates=new Date[lim];
                            Date array_added_date_new=strt_date;

                            for (int k = 0; k < lim; k++) {
                                System.out.println("%%%%%%%%%% setting time : "+array_added_date_new);
                                ading_cal_new.setTime(array_added_date_new);
                                repeating_dates[k] = ading_cal_new.getTime();
                                ading_cal_new.add(Calendar.DATE,7);
                                array_added_date_new = ading_cal_new.getTime();
                                System.out.println("%%%%%%%%%% repeating_dates["+k+"] : "+repeating_dates[k]);
                            }

                            ///////////////////////getting all start date days within the number of weeks in 'p.getContinues_day()' value --end
                            System.out.println("%%%%%%%%%% nxt_dates are ");
                            for (int k = 0; k < nxt_dates.length; k++) {
                                System.out.println("%%%%%%%%%% nxt_dates[" + k + "] : " + nxt_dates[k]);
                                for(int m=0;m<repeating_dates.length;m++) {
                                    if(nxt_dates[k].equals(repeating_dates[m])){

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
                                        int request_code=current_time.getHours()+ current_time.getMinutes()+ccc.get(Calendar.DATE)+p.getId();
                                        if(Objects.equals(p.getSkipped_date4(),"false"))
                                        {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            if(p.getDosage_no()!=0){
                                                myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                                            }
                                            else {
                                                myIntent.putExtra("medname", p.getName());
                                            }
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context, request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pendingIntent21);
                                        }
                                        if(Objects.equals(p.getSkipped_date4(),"true"))
                                        {
                                            Intent myIntent = new Intent(context, NotifyService1.class);
                                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                                            PendingIntent pendingIntent21 = PendingIntent.getBroadcast(context,request_code, myIntent, FLAG_UPDATE_CURRENT);
                                            alarmManager.cancel(pendingIntent21);
                                            System.out.println("cancel()------------------" );
                                        }
                                    }
                                }
                            }
                        }


                    }


            }

            catch (ParseException ee) {
                ee.printStackTrace();
            }

            System.out.println("p.getRemind_no() : "+p.getRemind_no());
            System.out.println("p.getnjaaan : "+p.getAmnt_meds());
            double Remind_no=p.getRemind_no();
            System.out.println("p.kk() : "+Remind_no);
            if (p.getAmnt_meds() <= Remind_no) {
                System.out.println("mohan : "+p.getAmnt_meds());
                try {
                    if(p.getRemind_time()!=null) {

                        System.out.println("mammu : "+p.getRemind_no());
                        SimpleDateFormat DateFormat1 = new SimpleDateFormat("hh.mm aaa");
                        Date current_time = DateFormat1.parse(p.getRemind_time());
                        Calendar calendar = Calendar.getInstance();
                        Calendar setcalendar = Calendar.getInstance();
                        setcalendar.set(Calendar.HOUR_OF_DAY, current_time.getHours());
                        setcalendar.set(Calendar.MINUTE, current_time.getMinutes());
                        setcalendar.set(Calendar.SECOND, 1);
                        if (setcalendar.before(calendar))
                            setcalendar.add(Calendar.DATE, 1);
                        int requstcode= current_time.getHours()+ current_time.getMinutes()+p.getId()+current_time.getMinutes();
                        Intent myIntent = new Intent(context, NotifyService3.class);
                        if(p.getDosage_no()!=0){
                            myIntent.putExtra("medname", p.getName() + "(" + p.getDosage_no() + " " + p.getDosage_unit() + ")");
                        }
                        else {
                            myIntent.putExtra("medname", p.getName());
                        }
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                        PendingIntent pending = PendingIntent.getBroadcast(context, requstcode, myIntent, FLAG_UPDATE_CURRENT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, setcalendar.getTimeInMillis(), pending);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return newMedArraylist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public CheckedTextView Date1,Date2,Date3,Date4;
        CardView Card1,Card2,Card3,Card4;
        public RecyclerView recyclerView1,recyclerView2,recyclerView3,recyclerView4;
        LinearLayout Medi_details1,Medi_details2,Medi_details3,Medi_details4;
        LinearLayout linear_reschedule,linear_take,linear_skip,linear_unskip;
        public ProductViewHolder(View itemView) {
            super(itemView);
            Medi_details1 = (LinearLayout)itemView.findViewById(R.id.medi_details1);
            Date1 = (CheckedTextView)itemView.findViewById(R.id.time1);
            recyclerView1 = (RecyclerView)itemView.findViewById(R.id.recy1);

            Medi_details2 = (LinearLayout)itemView.findViewById(R.id.medi_details2);
            Date2 = (CheckedTextView)itemView.findViewById(R.id.time2);
            recyclerView2 = (RecyclerView)itemView.findViewById(R.id.recy2);

            Medi_details3 = (LinearLayout)itemView.findViewById(R.id.medi_details3);
            Date3 = (CheckedTextView)itemView.findViewById(R.id.time3);
            recyclerView3 = (RecyclerView)itemView.findViewById(R.id.recy3);

            Medi_details4 = (LinearLayout)itemView.findViewById(R.id.medi_details4);
            Date4 = (CheckedTextView)itemView.findViewById(R.id.time4);
            recyclerView4 = (RecyclerView)itemView.findViewById(R.id.recy4);

            Card1 = (CardView)itemView.findViewById(R.id.card1);
            Card2 = (CardView)itemView.findViewById(R.id.card2);
            Card3 = (CardView)itemView.findViewById(R.id.card3);
            Card4 = (CardView)itemView.findViewById(R.id.card4);

            linear_reschedule=(LinearLayout)itemView.findViewById(R.id.linear_reschedule);
            linear_take=(LinearLayout)itemView.findViewById(R.id.linear_take);
            linear_skip=(LinearLayout)itemView.findViewById(R.id.linear_skip);
            linear_unskip=(LinearLayout)itemView.findViewById(R.id.linear_unskip);
        }
    }
    boolean contains_date(ArrayList<String> list, String date) {
        for (String item : list) {
            if (item!= null) {
                if (item.equalsIgnoreCase(date)) {
                    System.out.println(">>>>>>>>> inside item.getDate1().equalsIgnoreCase(date)");
                    return true;
                }
            }
        }
        return false;
    }
    boolean contains(ArrayList<MedModel2> list, String date) {
        for (MedModel2 item : list) {
            if (item!= null) {
                if (item.getDate().equalsIgnoreCase(date)) {
                    System.out.println(">>>>>>>>> inside item.getDate1().equalsIgnoreCase(date)");
                    return true;
                }
            }
        }
        return false;
    }

    public String[]  printDistinctElements(String[] arr){
        ArrayList<String> temp=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            boolean isDistinct = false;
            for(int j=0;j<i;j++){
                if(arr[i].equals(arr[j])){
                    isDistinct = true;
                    break;
                }
            }
            if(!isDistinct){
                //System.out.println(arr[i]+" ");
                temp.add(arr[i]);
            }
        }
        String[] returnArray=new String[temp.size()];
        for(int i=0;i<temp.size();i++){
            returnArray[i]=temp.get(i);
        }
        return returnArray;

    }



}