package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Ansal on 10/16/2017.
 */
public class NotifyService3 extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String s =intent.getStringExtra("medname");

        Intent service1 = new Intent(context, MyAlarmService1.class);
        service1.putExtra("medname1",s);
        context.startService(service1);

    }
}