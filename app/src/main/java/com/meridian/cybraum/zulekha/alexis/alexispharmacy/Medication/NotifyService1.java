package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



/**
 * Created by Ansal on 10/16/2017.
 */
public class NotifyService1 extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {

        String s=null;
        try {
             s = intent.getStringExtra("medname");
        } catch (Exception e) {
            e.printStackTrace();
        }
            Intent service1 = new Intent(context, MyAlarmService.class);
            service1.putExtra("medname1", s);
            context.startService(service1);


    }
}