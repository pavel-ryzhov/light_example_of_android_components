package com.omisoft.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SecondActivity extends AppCompatActivity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.second_activity);

    final AppCompatButton thirdActivityButton = findViewById(R.id.btn_third_activity);

    thirdActivityButton.setOnClickListener(view -> Toast.makeText(SecondActivity.this, "Hey, that's enough for you! " +
            ":)", Toast.LENGTH_LONG).show());
  }
}
