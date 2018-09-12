package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication.Medication_Activity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.biubiubiu.justifytext.library.JustifyTextView;

/**
 * Created by Ansal on 12/20/2017.
 */

public class About_zulekha extends AppCompatActivity {
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.web)
    JustifyTextView web;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;
    String url="http://www.alexishospital.com/pharmacyapp/json/about.php";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_zhulekha);
        ButterKnife.bind(this);
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        web.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Regular.ttf"));
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.get(url).responseString(new Handler<String>() {
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
                        String about_hospital = j.getJSONObject("about_zulekha").getString("content");
                        System.out.println("___________aboutus___________" + about_hospital);
                        web.setText(about_hospital);
                        Glide.with(getApplicationContext())
                                .load( j.getJSONObject("about_zulekha").getString("banner"))
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }
                                })

                                .into(img);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    progressWheel.setVisibility(View.GONE);
                }

            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        Intent q = new Intent(About_zulekha.this, MainActivity.class);
        startActivity(q);
        finish();
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(About_zulekha.this,Medication_Activity.class);
        startActivity(intent);
        finish();
    }

}

