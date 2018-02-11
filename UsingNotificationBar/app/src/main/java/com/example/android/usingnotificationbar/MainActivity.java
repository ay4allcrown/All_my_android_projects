package com.example.android.usingnotificationbar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private int notificationID = 100;
    private int numberOfMessages = 0;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.notification_editText);
        Button btnStart = (Button) findViewById(R.id.start_button);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

        Button btnUpdate = (Button) findViewById(R.id.update_button);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });
    }

    protected void cancelNotification() {
        Log.i("Cancel", "Notification");
        notificationManager.cancel(notificationID);
    }

    protected void updateNotification() {
        Log.i("Update", "Notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("My app Updated message");
        builder.setContentText("Update");
        builder.setSmallIcon(R.drawable.futa);


        builder.setNumber(++numberOfMessages);

        Intent resultIntent = new Intent(this, NotificationView.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(NotificationView.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, builder.build());
    }

    protected void displayNotification() {
        Log.i("Start", "Notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("My app Notificatiion");
        builder.setContentText("Start");
        builder.setTicker("New Message Alert!");
        builder.setSmallIcon(R.drawable.futa);

        builder.setNumber(++numberOfMessages);

        Intent resultIntent = new Intent(this, NotificationView.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //stackBuilder.addParentStack(NotificationView.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, builder.build());
    }
}
