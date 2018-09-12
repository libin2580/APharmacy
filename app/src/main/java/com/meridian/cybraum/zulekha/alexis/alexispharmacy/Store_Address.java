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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
 * Created by Ansal on 12/20/2017.
 */

public class Store_Address extends AppCompatActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.store_image)
    ImageView storeImage;
    @BindView(R.id.store_title)
    TextView storeTitle;
    @BindView(R.id.store_address)
    TextView storeAddress;
    @BindView(R.id.store_number)
    TextView storeNumber;
    @BindView(R.id.linr)
    RelativeLayout linr;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;
    String store_phone;
    @BindView(R.id.store_title1)
    TextView storeTitle1;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stored_address);
        ButterKnife.bind(this);
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Bold.ttf"));
        get_data();

    }

    @OnClick({R.id.back, R.id.store_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                Intent q = new Intent(Store_Address.this, MainActivity.class);
                startActivity(q);
                finish();
                break;
            case R.id.store_number:
                SweetAlertDialog dialog = new SweetAlertDialog(Store_Address.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                                intent.setData(Uri.parse("tel:" + store_phone));
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

    private void get_data() {
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
                        String store_image = j.getString("store_image");
                        String store_title = j.getString("store_title");
                        String store_title1 = j.getString("store_title");
                        String store_address = j.getString("store_address");
                        store_phone = j.getString("store_phone");
                        Glide.with(Store_Address.this)
                                .load(store_image)
                                .into(storeImage);
                        store_title=store_title.substring(0,6);
                        store_title1=store_title1.substring(6,15);
                        storeTitle.setText(store_title);
                        storeTitle1.setText(store_title1);
                        storeAddress.setText(store_address);
                        storeNumber.setText(store_phone);

                        System.out.println("__________store_image_________" + store_image);


                    }

                } catch (JSONException ew) {
                    ew.printStackTrace();
                }


            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Store_Address.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

