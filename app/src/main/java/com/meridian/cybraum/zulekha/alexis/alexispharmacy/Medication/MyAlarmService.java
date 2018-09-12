package com.meridian.cybraum.zulekha.alexis.alexispharmacy.Medication;

/**
 * Created by Ansal on 10/13/2017.
 */


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.meridian.cybraum.zulekha.alexis.alexispharmacy.R;


public class MyAlarmService extends Service
{



    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.ic_launcher);
        Intent activityIntent = new Intent(this, Medication_Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_ONE_SHOT);
       /*
        NotificationCompat.Builder notificationBuilder = new
                NotificationCompat.Builder(this)
                .setLargeIcon(icon)
                .setContentTitle("Pink Knight")
                .setContentText("take your medicine")
                .setContentIntent(pendingIntent)
                .setVibrate(new long[] { 1000, 1000})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setAutoCancel(true);


        notificationBuilder.setSmallIcon(R.drawable.pink_icon);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = notificationBuilder.getNotification();
        notificationManager.notify(100, notification);*/

        String s=null;
       try {
            s=intent.getStringExtra("medname1");

       }
       catch (Exception e){
           e.printStackTrace();
       }
       if(s!=null){
           NotificationCompat.Builder notificationBuilder = new
                   NotificationCompat.Builder(this)
                   .setContentTitle("Alexis Pharmacy")
                   .setContentText("Take "+s)
                   .setColor(Color.parseColor("#23a6f8"))
                   .setLargeIcon(icon)
                   .setContentIntent(pendingIntent)
                   .setVibrate(new long[] { 1000,1000})
                   .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                   .setAutoCancel(true);

           notificationBuilder.setSmallIcon(R.drawable.alaxis);
  /*         NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            Notification notification = notificationBuilder.getNotification();
            notificationManager.notify(100, notification);*/
           NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
           notificationManager.cancel(startId);
           notificationManager.notify(startId,notificationBuilder.build());


       }




    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}