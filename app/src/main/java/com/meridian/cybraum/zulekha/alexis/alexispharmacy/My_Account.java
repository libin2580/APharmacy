package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kotlin.Pair;

/**
 * Created by Ansal on 12/21/2017.
 */

public class My_Account extends AppCompatActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.password)
    ImageView password;
    @BindView(R.id.write)
    ImageView write;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.email_txt)
    TextView emailTxt;
    @BindView(R.id.mobile_txt)
    TextView mobileTxt;
    @BindView(R.id.linr_txt)
    LinearLayout linrTxt;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.linr_edit)
    LinearLayout linrEdit;
    @BindView(R.id.update)
    LinearLayout update;
    @BindView(R.id.logout)
    LinearLayout logout;
    @BindView(R.id.linr)
    RelativeLayout linr;
    String user_id,login;
    List<Pair<String, String>> params;
    List<Pair<String, String>> params1;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.user_edit)
    EditText userEdit;
    @BindView(R.id.address_edit)
    EditText addressEdit;
    @BindView(R.id.txt_user)
    LinearLayout txtUser;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        ButterKnife.bind(this);
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        SharedPreferences sopre2 = getSharedPreferences("login_method", MODE_PRIVATE);
        user_id = sopre2.getString("user_id", null);
        login = sopre2.getString("login", null);
        params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<String, String>("user_id", user_id));

        }};
        httpget();
        if(Objects.equals(login, "fb") || Objects.equals(login, "google")){
            password.setVisibility(View.GONE);
        }
        else {
            password.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.back, R.id.password, R.id.write, R.id.close, R.id.update, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent intent = new Intent(My_Account.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.password:
                Intent cp = new Intent(My_Account.this, Change_password.class);
                startActivity(cp);
                finish();
                break;
            case R.id.close:
                logout.setVisibility(View.VISIBLE);
                update.setVisibility(View.GONE);
                linrEdit.setVisibility(View.GONE);
                linrTxt.setVisibility(View.VISIBLE);
                txtUser.setVisibility(View.VISIBLE);
                close.setVisibility(View.GONE);
                write.setVisibility(View.VISIBLE);
                break;
            case R.id.write:
                close.setVisibility(View.VISIBLE);
                write.setVisibility(View.GONE);
                logout.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);
                linrEdit.setVisibility(View.VISIBLE);
                linrTxt.setVisibility(View.GONE);
                txtUser.setVisibility(View.GONE);
                break;
            case R.id.update:
                params1 = new ArrayList<Pair<String, String>>() {{
                    add(new Pair<String, String>("user_id", user_id));
                    add(new Pair<String, String>("name", userEdit.getText().toString()));
                    add(new Pair<String, String>("contact", mobile.getText().toString()));
                    add(new Pair<String, String>("address", addressEdit.getText().toString()));

                }};
                httpPost();
                break;
            case R.id.logout:
                SweetAlertDialog dialog = new SweetAlertDialog(My_Account.this, SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("Do you want to Logout")
                        .setCancelText("cancel")
                        .setConfirmText("Ok")
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
                                SharedPreferences preferences = getApplicationContext().getSharedPreferences("login_method", MODE_PRIVATE);
                                preferences.edit().remove("login").apply();
                                Intent qw = new Intent(My_Account.this, MainActivity.class);
                                startActivity(qw);
                                finish();
                            }
                        })
                        .show();
                dialog.findViewById(R.id.cancel_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBCC2BD")));
                dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                break;
        }
    }

    private void httpPost() {
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/update_profile.php", params1).responseString(new Handler<String>() {
            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {
                progressWheel.setVisibility(View.GONE);
            }

            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {
                progressWheel.setVisibility(View.GONE);

                try {
                    JSONObject object = new JSONObject(data);
                    String status = object.getString("status");
                    String msg = object.getString("message");
                    if (!status.equals("true")) {
                        SweetAlertDialog dialog = new SweetAlertDialog(My_Account.this, SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText(msg)
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();

                                    }
                                })
                                .show();
                        dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                    } else {
                        SweetAlertDialog dialog = new SweetAlertDialog(My_Account.this, SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText(msg)
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                        httpget();
                                        logout.setVisibility(View.VISIBLE);
                                        update.setVisibility(View.GONE);
                                        linrEdit.setVisibility(View.GONE);
                                        linrTxt.setVisibility(View.VISIBLE);
                                        txtUser.setVisibility(View.VISIBLE);
                                        close.setVisibility(View.GONE);
                                        write.setVisibility(View.VISIBLE);
                                    }
                                })
                                .show();
                        dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    progressWheel.setVisibility(View.GONE);
                }

            }
        });
    }

    private void httpget() {
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/view_profile.php", params).responseString(new Handler<String>() {
            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {
                progressWheel.setVisibility(View.GONE);
            }

            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {
                progressWheel.setVisibility(View.GONE);

                try {
                    JSONObject object = new JSONObject(data);
                    String status = object.getString("status");
                    String dataa = object.getString("data");
                    JSONArray array = new JSONArray(dataa);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject j = array.getJSONObject(i);
                        String name = j.getString("name");
                        String mail = j.getString("email");
                        String contact = j.getString("contact");
                        String addres = j.getString("address");

                        username.setText(name);
                        userEdit.setText(name);
                        mobile.setText(contact);
                        emailTxt.setText(mail);
                        mobileTxt.setText(contact);
                        address.setText(addres);
                        addressEdit.setText(addres);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    progressWheel.setVisibility(View.GONE);
                }

            }
        });
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(My_Account.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
