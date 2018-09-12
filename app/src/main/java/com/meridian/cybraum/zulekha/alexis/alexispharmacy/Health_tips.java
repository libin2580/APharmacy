package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 20/12/2017.
 */

public class Health_tips extends AppCompatActivity {
    @BindView(R.id.menu)
    LinearLayout menu;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;

    RecyclerView recyclerView;
    Health_tips_Adapter healthTipsAdapter;
    Health_tips_Model healthTipsModel;
    ArrayList<Health_tips_Model> health_tips_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthtips);
        ButterKnife.bind(this);
        banner();
        recyclerView=(RecyclerView)findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        health_tips_list=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));

    }

    @OnClick(R.id.menu)
    public void onViewClicked() {
        Intent intent = new Intent(Health_tips.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void banner() {
        progressWheel.setVisibility(View.VISIBLE);
        Fuel.get("http://www.alexishospital.com/pharmacyapp/json/healthtips.php").responseString(new Handler<String>() {
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
                        String tip_title = j.getString("tip_title");
                        String tip_content = j.getString("tip_content");
                        String image = j.getString("tip_image");
                        healthTipsModel=new Health_tips_Model();
                        healthTipsModel.settip_title(tip_title);
                        healthTipsModel.setimage(image);
                        healthTipsModel.settip_content(tip_content);
                        health_tips_list.add(healthTipsModel);
                        healthTipsAdapter = new Health_tips_Adapter(health_tips_list, getApplicationContext());
                        recyclerView.setAdapter(healthTipsAdapter);

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

        Intent intent = new Intent(Health_tips.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
