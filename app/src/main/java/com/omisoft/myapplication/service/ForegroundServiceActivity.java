package com.omisoft.myapplication.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.omisoft.myapplication.R;

public class ForegroundServiceActivity extends AppCompatActivity {

  static final String ACTION_STOP_FOREGROUND = "ACTION_STOP_FOREGROUND";
  static final String RECEIVER_FILTER = "RECEIVER_FILTER";

  AppCompatTextView messageText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.foreground_service_activity);

    final AppCompatButton startForegroundButton = findViewById(R.id.btn_start_foreground);
    final AppCompatButton stopForegroundButton = findViewById(R.id.btn_stop_foreground);

    messageText = findViewById(R.id.txt_message);

    startForegroundButton.setOnClickListener(view -> {
      final Intent intent = new Intent(ForegroundServiceActivity.this, FirstForegroundService.class);
      ForegroundServiceActivity.this.startService(intent);
    });

    stopForegroundButton.setOnClickListener(view -> {
      final Intent stopForegroundIntent = new Intent(ForegroundServiceActivity.this, FirstForegroundService.class);
      stopForegroundIntent.setAction(ACTION_STOP_FOREGROUND);
      startService(stopForegroundIntent);
    });

    final InnerFirstBroadcastReceiver innerFirstBroadcastReceiver = new InnerFirstBroadcastReceiver();
    registerReceiver(innerFirstBroadcastReceiver, new IntentFilter(RECEIVER_FILTER));
  }

  public class InnerFirstBroadcastReceiver extends BroadcastReceiver {
    static final String RECEIVER_MESSAGE_KEY = "MESSAGE_KEY";

    @Override
    public void onReceive(Context context, Intent intent) {
      final String message = intent.getStringExtra(RECEIVER_MESSAGE_KEY);
      Toast.makeText(context, message, Toast.LENGTH_LONG).show();
      ForegroundServiceActivity.this.messageText.setText(message);
    }
  }
}