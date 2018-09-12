package com.meridian.cybraum.zulekha.alexis.alexispharmacy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.firebase.app.Config;
import com.meridian.cybraum.zulekha.alexis.alexispharmacy.firebase.util.NotificationUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.ContentValues.TAG;


public class SplashActivty extends Activity{
    private static int SPLASH_TIME_OUT = 1500;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    String refreshedToken;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activty);
        FirebaseApp.initializeApp(SplashActivty.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
            try {
            PackageInfo info1 = getPackageManager().getPackageInfo(
                    "com.meridian.cybraum.zulekha.alexis.alexispharmacy",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info1.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("YourKey:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                System.out.println("YourKeyHash: "+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        System.out.println("<<<<<<<<<<<<<<<<<<refreshedToken>>>>>>>>>>>>>>>>>>>>>>>>>"+refreshedToken);
        mRegistrationBroadcastReceiver =
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                        // checking for type intent filter
                        if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                            // gcm successfully registered
                            // now subscribe to `global` topic to receive app wide notifications
                            FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                            displayFirebaseRegId();

                        } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                            // new push notification is received

                            String message = intent.getStringExtra("message");

                            Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                            //tv.setText(message);
                        }
                    }
                };
        displayFirebaseRegId();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ir=new Intent(SplashActivty.this,MainActivity.class);
                startActivity(ir);
                finish();


            }
        }, SPLASH_TIME_OUT);

    }
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);
    }
    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
