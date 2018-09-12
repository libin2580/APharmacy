package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.kaopiz.kprogresshud.KProgressHUD;


import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kotlin.Pair;

/**
 * Created by Ansal on 12/21/2017.
 */

public class Forgot_password extends AppCompatActivity {

    @BindView(R.id.menu)
    LinearLayout menu;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.submit)
    LinearLayout submit;
    String str_forgtemail;
    List<Pair<String, String>> params1;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.menu, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:
                Intent intent = new Intent(Forgot_password.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.submit:
                str_forgtemail=email.getText().toString();
                if (str_forgtemail.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(str_forgtemail).matches()) {

                    SweetAlertDialog dialog = new SweetAlertDialog(Forgot_password.this, SweetAlertDialog.ERROR_TYPE);
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
                }
                else {
                    params1 = new ArrayList<Pair<String, String>>() {{
                        add(new Pair<String, String>("email",str_forgtemail));

                    }};
                    update_pass();
                }
                break;
        }
    }
    private void update_pass() {
        final KProgressHUD hud1= KProgressHUD.create(Forgot_password.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f)
                .show();
        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/forgot_password.php", params1).responseString(new Handler<String>() {
            @Override
            public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {
                hud1.dismiss();
            }

            @Override
            public void success(@NotNull Request request, @NotNull Response response, String data) {
                hud1.dismiss();

                try {
                    JSONObject object=new JSONObject(data);
                    String status=object.getString("status");
                    String msg=object.getString("message");
                    if (!status.equals("true")){
                        SweetAlertDialog dialog = new SweetAlertDialog(Forgot_password.this, SweetAlertDialog.ERROR_TYPE);
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

                        SweetAlertDialog dialog = new SweetAlertDialog(Forgot_password.this, SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText("Check your mail")
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismiss();
                                        Intent intent = new Intent(Forgot_password.this, LoginActivity.class);
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
                    hud1.dismiss();
                }

            }
        });
    }

}
