package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tapadoo.alerter.Alerter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ansal on 12/26/2017.
 */

public class Location_map extends FragmentActivity implements OnMapReadyCallback {
    @BindView(R.id.menu)
    LinearLayout menu;
    @BindView(R.id.home_txt)
    TextView homeTxt;
    @BindView(R.id.navigation)
    Button navigation;
    @BindView(R.id.progressWheel)
    ProgressBar progressWheel;
    double store_latitude,store_longitude;
    String curnt_latitude, curnt_longitude;
    GPSTracker1 gps;
    double mylongitude, mylatitude;
    static LatLng MY_LOCATION = new LatLng(0, 0);
    private GoogleMap mMap;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationmap);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        store_latitude= Double.parseDouble(getIntent().getStringExtra("store_latitude"));
        store_longitude= Double.parseDouble(getIntent().getStringExtra("store_longitude"));
        homeTxt.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Raleway-Medium.ttf"));
        if (DetectConnection.checkInternetConnection(getApplicationContext())) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

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

            Alerter.create(Location_map.this)
                    .setTitle("")
                    .setText("Oops Your Connection Seems Off..!")
                    .setDuration(1000)
                    .setBackgroundColor(R.color.error_stroke_color)
                    .setIcon(R.drawable.alerter_ic_notifications)
                    .show();

        }
    }

    @OnClick({R.id.menu, R.id.navigation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:
                Intent intent = new Intent(Location_map.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.navigation:
                         try {
                    ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
                    String directionweburl = "http://maps.google.com/maps?daddr=" + store_latitude + "," + store_longitude + "&saddr=" + curnt_latitude + "," + curnt_longitude;
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(directionweburl));
                    // myIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    myIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.driveabout.app.NavigationActivity");
                    startActivity(myIntent);


                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "install google map", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(store_latitude, store_longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Alexis Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(store_latitude, store_longitude)).zoom(16).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Location_map.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

