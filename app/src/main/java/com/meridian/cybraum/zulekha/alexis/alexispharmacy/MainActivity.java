package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication.Medication_Activity;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication.Order_medicine;
import com.tapadoo.alerter.Alerter;
import com.williammora.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.meridian.cybraum.zulekha.alexis.alexispharmacy.LoginActivity.fAuth;
import static com.meridian.cybraum.zulekha.alexis.alexispharmacy.LoginActivity.mGoogleApiClient;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.menu)
    LinearLayout menu;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.order_txt)
    TextView orderTxt;
    @BindView(R.id.oreder_medicine)
    LinearLayout orederMedicine;
    @BindView(R.id.account_txt)
    TextView accountTxt;
    @BindView(R.id.my_account)
    LinearLayout myAccount;
    @BindView(R.id.feed_txt)
    TextView feedTxt;
    @BindView(R.id.feedback)
    LinearLayout feedback;
    @BindView(R.id.emergency_txt)
    TextView emergencyTxt;
    @BindView(R.id.emergency)
    LinearLayout emergency;
    @BindView(R.id.remindr_txt)
    TextView remindrTxt;
    @BindView(R.id.reminder)
    LinearLayout reminder;
    @BindView(R.id.health_txt)
    TextView healthTxt;
    @BindView(R.id.health_tips)
    LinearLayout healthTips;
    @BindView(R.id.stored_txt)
    TextView storedTxt;
    @BindView(R.id.store_address)
    LinearLayout storeAddress;
    @BindView(R.id.location_txt)
    TextView locationTxt;
    @BindView(R.id.location_map)
    LinearLayout locationMap;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.pharmacy)
    TextView pharmacy;
    @BindView(R.id.alexis)
    TextView alexis;
    @BindView(R.id.zhulekha)
    TextView zhulekha;
    @BindView(R.id.health)
    TextView health;
    @BindView(R.id.store_address_txt)
    TextView store_address_txt;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.fb)
    LinearLayout fb;
    @BindView(R.id.twitter)
    LinearLayout twitter;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    String curnt_latitude, curnt_longitude;
    GPSTracker1 gps;
    double mylongitude, mylatitude;
    static LatLng MY_LOCATION = new LatLng(0, 0);
    @BindView(R.id.slider)
    SliderLayout slider;
    String Reg_id, store_latitude, store_longitude;
    private static long back_pressed;
    private static final int TIME_DELAY = 2000;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences sopre = getSharedPreferences("login_method", MODE_PRIVATE);
        Reg_id = sopre.getString("login", null);
        slider = (SliderLayout) findViewById(R.id.slider);
        banner();


        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        orderTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        accountTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        feedTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        emergencyTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        remindrTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        healthTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        storedTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        locationTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        alexis.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        zhulekha.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        pharmacy.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        health.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        location.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        store_address_txt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));

        progressWheel.setVisibility(View.VISIBLE);

        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/store_address.php").responseString(new Handler<String>() {
            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {
                progressWheel.setVisibility(View.GONE);
            }

            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {

                progressWheel.setVisibility(View.GONE);
                try {
                    JSONObject jsonObj = new JSONObject(data);
                    String status = jsonObj.getString("status");
                    System.out.println("_________status_____________" + status);
                    String dataa = jsonObj.getString("data");
                    System.out.println("_______ddssss___________" + dataa);

                    JSONArray arrayy = new JSONArray(dataa);
                    for (int ii = 0; ii < arrayy.length(); ii++) {

                        JSONObject j = arrayy.getJSONObject(ii);
                        store_latitude = j.getString("store_latitude");
                        store_longitude = j.getString("store_longitude");

                    }

                } catch (JSONException ew) {
                    ew.printStackTrace();
                }


            }
        });


        if (DetectConnection.checkInternetConnection(getApplicationContext())) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                gps = new GPSTracker1(this);
                if (gps.canGetLocation()) {

                    mylatitude = gps.getLatitude();
                    mylongitude = gps.getLongitude();
                    MY_LOCATION = new LatLng(mylatitude, mylongitude);


                } else {
                    gps.showSettingsAlert();
                }
                curnt_latitude = String.valueOf(MY_LOCATION.latitude);
                curnt_longitude = String.valueOf(MY_LOCATION.longitude);
                System.out.println("url : " + curnt_latitude + "-------------------" + curnt_longitude);

            }

        } else {

            Alerter.create(MainActivity.this)
                    .setTitle("")
                    .setText("Oops Your Connection Seems Off..!")
                    .setDuration(1000)
                    .setBackgroundColor(R.color.error_stroke_color)
                    .setIcon(R.drawable.alerter_ic_notifications)
                    .show();

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                ActivityCompat.finishAffinity(MainActivity.this);
            } else {
                Snackbar.with(MainActivity.this)
                        .text("Press again to exit from this app")
                        .textColor(Color.parseColor("#ffffff"))
                        .color(Color.parseColor("#23a6f8"))
                        .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                        .show(MainActivity.this);
            }
            back_pressed = System.currentTimeMillis();
        }
    }

    @OnClick({R.id.menu, R.id.oreder_medicine, R.id.my_account, R.id.feedback, R.id.emergency, R.id.reminder, R.id.health_tips, R.id.store_address,
            R.id.location_map, R.id.pharmacy, R.id.alexis, R.id.zhulekha, R.id.health, R.id.store_address_txt, R.id.location, R.id.fb,R.id.twitter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.oreder_medicine:
                if (Reg_id != null) {
                    Intent q = new Intent(MainActivity.this, Order_medicine.class);
                    startActivity(q);
                    finish();
                } else {
                    SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                    dialog
                            .setTitleText("Please Login")
                            .setCancelText("Cancel")
                            .setConfirmText("Yes")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();

                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    Intent ir = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(ir);
                                    finish();
                                }
                            })
                            .show();
                    dialog.findViewById(R.id.cancel_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                }

                break;
            case R.id.my_account:

                if (Reg_id != null) {
                    Intent q = new Intent(MainActivity.this, My_Account.class);
                    startActivity(q);
                    finish();

                } else {
                    SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                    dialog
                            .setTitleText("Please Login")
                            .setCancelText("Cancel")
                            .setConfirmText("Yes")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();

                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    Intent ir = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(ir);
                                    finish();

                                }
                            })
                            .show();
                    dialog.findViewById(R.id.cancel_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                }
                break;
            case R.id.feedback:

                if (Reg_id != null) {
                    Intent i = new Intent(MainActivity.this, Feedback.class);
                    startActivity(i);
                    finish();
                } else {
                    SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                    dialog
                            .setTitleText("Please Login")
                            .setCancelText("Cancel")
                            .setConfirmText("Yes")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();

                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    Intent ir = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(ir);
                                    finish();
                                }
                            })
                            .show();
                    dialog.findViewById(R.id.cancel_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                    dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                }

                break;
            case R.id.emergency:
                Intent intent = new Intent(MainActivity.this, Emergency.class);
                startActivity(intent);
                finish();
                break;
            case R.id.reminder:
                Intent u = new Intent(MainActivity.this, Medication_Activity.class);
                startActivity(u);
                finish();
                break;
            case R.id.health_tips:
                Intent dd = new Intent(MainActivity.this, Health_tips.class);
                startActivity(dd);
                finish();
                break;
            case R.id.store_address:
                Intent y = new Intent(MainActivity.this, Store_Address.class);
                startActivity(y);
                finish();
                break;
            case R.id.location_map:

                Intent qt = new Intent(MainActivity.this, Location_map.class);
                qt.putExtra("store_latitude",store_latitude);
                qt.putExtra("store_longitude",store_longitude);
                startActivity(qt);
                finish();
                break;
            case R.id.pharmacy:
                Intent w = new Intent(MainActivity.this, About_pharmacy.class);
                startActivity(w);
                finish();
                break;
            case R.id.alexis:
                Intent e = new Intent(MainActivity.this, About_alexis.class);
                startActivity(e);
                finish();
                break;
            case R.id.zhulekha:
                Intent r = new Intent(MainActivity.this, About_zulekha.class);
                startActivity(r);
                finish();
                break;
            case R.id.health:
                Intent hh = new Intent(MainActivity.this, Health_tips.class);
                startActivity(hh);
                finish();
                break;
            case R.id.store_address_txt:
                Intent t = new Intent(MainActivity.this, Store_Address.class);
                startActivity(t);
                finish();
                break;
            case R.id.location:
                Intent tt = new Intent(MainActivity.this, Location_map.class);
                tt.putExtra("store_latitude",store_latitude);
                tt.putExtra("store_longitude",store_longitude);
                startActivity(tt);
                finish();
                break;

            case R.id.fb:

                gotourl("https://www.facebook.com/alexishospital");
                break;
            case R.id.twitter:

                gotourl("https://twitter.com/alexishospital");
                break;
        }

    }
    public void gotourl(String url) {
        Uri uriUrl= Uri.parse(url);
        Intent Launch=new Intent(Intent.ACTION_VIEW,uriUrl);
        startActivity(Launch);
    }

    private void banner() {

        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/home_banners.php").responseString(new Handler<String>() {
            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {

            }

            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {


                try {
                    JSONObject jsonObj = new JSONObject(data);
                    String status = jsonObj.getString("status");
                    System.out.println("_________status_____________" + status);
                    String dataa = jsonObj.getString("data");
                    System.out.println("___________dataddd___________" + dataa);

                    JSONArray arrayy = new JSONArray(dataa);
                    for (int ii = 0; ii < arrayy.length(); ii++) {

                        DefaultSliderView defaultSliderView = new DefaultSliderView(getApplicationContext());
                        JSONObject j = arrayy.getJSONObject(ii);

                        String banner = j.getString("banner");
                        System.out.println("___________banner__________" + banner);
                        defaultSliderView.image(banner);
                        slider.addSlider(defaultSliderView);


                    }

                } catch (JSONException ew) {
                    ew.printStackTrace();
                }
                slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                slider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
            }
        });
    }


}
