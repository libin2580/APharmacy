package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by user on 20/12/2017.
 */

public class Emergency extends AppCompatActivity {


    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.menu)
    LinearLayout menu;
    @BindView(R.id.txtcall)
    TextView txtCall;
    @BindView(R.id.call)
    LinearLayout call;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;
    String emr_contact;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        ButterKnife.bind(this);
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        slider = (SliderLayout) findViewById(R.id.slider);
        banner();
    }

    private void banner() {
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.post("http://www.alexishospital.com/pharmacyapp/json/emergency_contacts.php").responseString(new Handler<String>() {
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
                    System.out.println("___________dataddd___________" + dataa);

                    JSONArray arrayy = new JSONArray(dataa);
                    for (int ii = 0; ii < arrayy.length(); ii++) {

                        DefaultSliderView defaultSliderView = new DefaultSliderView(getApplicationContext());


                        JSONObject j = arrayy.getJSONObject(ii);

                        String banner = j.getString("emr_banner");
                         emr_contact = j.getString("emr_contact");
                        txtCall.setText(emr_contact);
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


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Emergency.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.menu, R.id.call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:
                Intent intent = new Intent(Emergency.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.call:
                SweetAlertDialog dialog = new SweetAlertDialog(Emergency.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                dialog.setCustomImage(R.drawable.alaxis_logo)
                        .setTitleText("Do you want to make a call")
                        .setCancelText("Cancel")
                        .setConfirmText("Call")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();

                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:"+emr_contact));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(intent);
                                sDialog.cancel();
                            }
                        })
                        .show();
                dialog.findViewById(R.id.cancel_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBCC2BD")));
                dialog.findViewById(R.id.confirm_button).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#23a6f8")));
                break;
        }
    }
}
