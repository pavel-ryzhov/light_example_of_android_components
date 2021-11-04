package com.omisoft.myapplication.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FirstBroadcastReceiver extends BroadcastReceiver {
  static final String RECEIVER_MESSAGE_KEY = "MESSAGE_KEY";

  @Override
  public void onReceive(Context context, Intent intent) {
    final String message = intent.getStringExtra(RECEIVER_MESSAGE_KEY);
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }
}
