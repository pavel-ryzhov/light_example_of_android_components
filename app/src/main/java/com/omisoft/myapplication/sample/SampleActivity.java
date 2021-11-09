package com.omisoft.myapplication.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;

import com.omisoft.myapplication.R;

public class SampleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sample_activity_layout);

    final TextView sampleTextView = findViewById(R.id.sample_text);
    final AppCompatButton changeResourcesButton = findViewById(R.id.change_resources_button);

    final String testString = getResources().getString(R.string.test_string);
    final int redColor = getResources().getColor(R.color.red);
    final Drawable foreground = AppCompatResources.getDrawable(this, R.drawable.ic_launcher_foreground);


    // todo: если надо сделать виджет некликабельным
    // changeResourcesButton.setEnabled(false);

    changeResourcesButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        sampleTextView.setText(testString);
        sampleTextView.setTextColor(redColor);
      }
    });
  }
}
