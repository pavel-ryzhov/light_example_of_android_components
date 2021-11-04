package com.omisoft.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final AppCompatButton secondActivityButton = findViewById(R.id.btn_second_activity);
    final AppCompatButton openGoogleButton = findViewById(R.id.btn_open_browser);
    final WebView webView = findViewById(R.id.web_view);

    secondActivityButton.setOnClickListener(view -> {
      final Intent intent = new Intent(MainActivity.this,SecondActivity.class);
      startActivity(intent);
    });

    openGoogleButton.setOnClickListener(view -> {
      webView.getSettings().setJavaScriptEnabled(true);
      webView.getSettings().setLoadWithOverviewMode(true);
      webView.getSettings().setUseWideViewPort(true);
      webView.setWebViewClient(new WebViewClient(){

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
          view.loadUrl(url);

          return true;
        }
        @Override
        public void onPageFinished(WebView view, final String url) {
        }
      });
      webView.loadUrl("https://www.google.com/");
    });
  }
}