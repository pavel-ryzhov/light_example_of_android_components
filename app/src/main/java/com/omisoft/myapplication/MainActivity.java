package com.omisoft.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivityTag";

  TextView title;

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    title = findViewById(R.id.txt_title);
    title.setText(BuildConfig.BASE_URL);

    final AppCompatButton secondActivityButton = findViewById(R.id.btn_second_activity);
    final AppCompatButton openGoogleButton = findViewById(R.id.btn_open_browser);
    final WebView webView = findViewById(R.id.web_view);

    secondActivityButton.setOnClickListener(view -> {
      final Intent intent = new Intent(MainActivity.this, SecondActivity.class);
      startActivity(intent);
    });

    openGoogleButton.setOnClickListener(view -> {
      webView.getSettings().setJavaScriptEnabled(true);
      webView.getSettings().setLoadWithOverviewMode(true);
      webView.getSettings().setUseWideViewPort(true);
      webView.setWebViewClient(new WebViewClient() {

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

  void readTextAsset() {
    try {
      AssetManager am = this.getAssets();
      InputStream is = am.open("test.txt");

      StringBuilder textBuilder = new StringBuilder();
      try (Reader reader = new BufferedReader(new InputStreamReader
              (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
        int c;
        while ((c = reader.read()) != -1) {
          textBuilder.append((char) c);
        }
      }

      title.setText(textBuilder.toString());

      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}