package com.example.diagnoseme;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

//import static com.example.diagnoseme.NotificationHelper.getManager;

public class AlertReceiver extends BroadcastReceiver {
    private String title;
    private String description;

    public AlertReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(title, description);
        notificationHelper.getManager().notify(1, nb.build());

    }
}
