package com.omisoft.myapplication.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.omisoft.myapplication.R;

import static com.omisoft.myapplication.service.FirstBroadcastReceiver.RECEIVER_MESSAGE_KEY;
import static com.omisoft.myapplication.service.ForegroundServiceActivity.ACTION_STOP_FOREGROUND;
import static com.omisoft.myapplication.service.ForegroundServiceActivity.RECEIVER_FILTER;

public class FirstForegroundService extends Service {

  private static final int NOTIFICATION_ID = 777;
  private static final String FIRST_FOREGROUND_CHANNEL_ID = "FIRST_FOREGROUND_CHANNEL_ID";
  private static final String CHANNEL_NAME = "CHANNEL_NAME";
  private static final String CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION";

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    final String action = intent.getAction();
    if (action != null && action.equals(ACTION_STOP_FOREGROUND)) {
      stopSelf();
    }
    generateForegroundNotification();
    sendMessageToReceiver();

    return START_NOT_STICKY;
  }

  private void sendMessageToReceiver() {
    final Intent intent = new Intent();
    intent.setAction(RECEIVER_FILTER);
    intent.putExtra(RECEIVER_MESSAGE_KEY, "Message from service");
    sendBroadcast(intent);
  }

  private void generateForegroundNotification() {
    final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      int importance = NotificationManager.IMPORTANCE_HIGH;
      final NotificationChannel channel = new NotificationChannel(FIRST_FOREGROUND_CHANNEL_ID, CHANNEL_NAME,
              importance);
      channel.setDescription(CHANNEL_DESCRIPTION);
      notificationManager.createNotificationChannel(channel);
    }

    final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, FIRST_FOREGROUND_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Foreground Service Notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_HIGH);
    final Notification notification = builder.build();
    startForeground(NOTIFICATION_ID, notification);
  }
}
