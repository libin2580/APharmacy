package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.kaopiz.kprogresshud.KProgressHUD;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kotlin.Pair;

/**
 * Created by Ansal on 12/19/2017.
 */

public class Feedback extends AppCompatActivity {

    @BindView(R.id.menu)
    LinearLayout menu;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.submit)
    LinearLayout submit;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;

    String user_id;
    List<Pair<String, String>> params;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        ButterKnife.bind(this);
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        SharedPreferences sopre2 = getSharedPreferences("login_method", MODE_PRIVATE);
        user_id=sopre2.getString("user_id",null);
        params = new ArrayList<Pair<String, String>>() {{
            add(new Pair<String, String>("user_id", user_id));

        }};
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
                        String nam = j.getString("name");
                        String mail = j.getString("email");
                        email.setText(mail);
                        name.setText(nam);



                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    progressWheel.setVisibility(View.GONE);
                }

            }
        });

    }

    @OnClick({R.id.menu, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:
                Intent intent = new Intent(Feedback.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.submit:
                if (TextUtils.isEmpty(name.getText().toString())) {

                    SweetAlertDialog dialog = new SweetAlertDialog(Feedback.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter name!")
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
                if (email.getText().toString().isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {

                    SweetAlertDialog dialog = new SweetAlertDialog(Feedback.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter a valid email!")
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
                if (TextUtils.isEmpty(description.getText().toString())) {

                    SweetAlertDialog dialog = new SweetAlertDialog(Feedback.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter description!")
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
                params = new ArrayList<Pair<String, String>>() {{
                    add(new Pair<String, String>("email", email.getText().toString()));
                    add(new Pair<String, String>("name",name.getText().toString()));
                    add(new Pair<String, String>("description",description.getText().toString()));
                    add(new Pair<String, String>("user_id",user_id));
                }};

                httpPost();
                break;
        }
    }
    private void httpPost() {
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/feedback.php", params).responseString(new Handler<String>() {
            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {
                progressWheel.setVisibility(View.GONE);
            }

            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {
                progressWheel.setVisibility(View.GONE);

                try {
                    JSONObject object=new JSONObject(data);
                    String status=object.getString("status");
                    String msg=object.getString("message");
                    if (!status.equals("true")){
                        SweetAlertDialog dialog = new SweetAlertDialog(Feedback.this, SweetAlertDialog.ERROR_TYPE);
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

                    }
                    else {
                        SweetAlertDialog dialog = new SweetAlertDialog(Feedback.this, SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText(msg)
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                        Intent intent = new Intent(Feedback.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .show();
                        dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));

                    }


                }
                catch (Exception e){
                    e.printStackTrace();
                    progressWheel.setVisibility(View.GONE);
                }

            }
        });
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Feedback.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}


