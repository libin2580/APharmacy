package com.meridian.cybraum.zulekha.alexis.alexispharmacy;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kotlin.Pair;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.register)
    LinearLayout register;
    ArrayList<Pair<String, String>> params;
    @BindView(R.id.menu)
    LinearLayout menu;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.register, R.id.menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register:
                if (TextUtils.isEmpty(user.getText().toString())) {

                    SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
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
                if (email.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {

                    SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
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


                if (TextUtils.isEmpty(password.getText().toString())) {

                    SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter password!")
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
                if (password.getText().toString().length() < 6) {

                    SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Password too short, enter minimum 6 characters!")
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
                if (mobile.getText().toString().isEmpty() || !Patterns.PHONE.matcher(mobile.getText().toString()).matches() || mobile.getText().toString().length() < 6) {
                    SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter valid phone number!")
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
                if (TextUtils.isEmpty(address.getText().toString())) {

                    SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
                    dialog.setTitleText("Enter address")
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

                MD5(password.getText().toString());
                httpPost();
                break;
        }
    }

    public String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
                params = new ArrayList<Pair<String, String>>() {{
                    add(new Pair<String, String>("username", user.getText().toString()));
                    add(new Pair<String, String>("name", user.getText().toString()));
                    add(new Pair<String, String>("email", email.getText().toString()));
                    add(new Pair<String, String>("mobile", mobile.getText().toString()));
                    add(new Pair<String, String>("password", sb.toString()));
                    add(new Pair<String, String>("address", address.getText().toString()));
                }};
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    private void httpPost() {
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/registration.php", params).responseString(new Handler<String>() {
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
                        SweetAlertDialog dialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE);
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
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();


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

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
