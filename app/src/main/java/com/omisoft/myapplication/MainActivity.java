package com.omisoft.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
  private static final String ASSET_FILE_NAME = "test.txt";
  private static final String GOOGLE_URL = "https://www.google.com/";
  private static final String CLEAR_WEB_VIEW = "about:blank";
  

  TextView title;
  WebView webView;

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(TAG, "onCreate");

    title = findViewById(R.id.txt_title);
    //  Стринга, которая генерируется в зависимости от собраного билда, статическая строчка с названием
    //  BASE_URL_SECOND
    title.setText("Build Type: " + BuildConfig.BUILD_TYPE + ", version code: " +
            BuildConfig.VERSION_CODE + ", is minified: " + BuildConfig.IS_MINIFIED);
    title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

    final AppCompatButton secondActivityButton = findViewById(R.id.btn_second_activity);
    final AppCompatButton openGoogleButton = findViewById(R.id.btn_open_browser);
    webView = findViewById(R.id.web_view);

    secondActivityButton.setOnClickListener(view -> {
      final Intent intent = new Intent(MainActivity.this, SecondActivity.class);
      startActivity(intent);
    });

    openGoogleButton.setOnClickListener(view -> {
      webView.setVisibility(View.VISIBLE);
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
      webView.loadUrl(GOOGLE_URL);
    });
  }

  @Override
  public void onBackPressed() {
    if (webView.getVisibility() == View.VISIBLE) {
      webView.setVisibility(View.GONE);
      webView.loadUrl(CLEAR_WEB_VIEW);
    } else {
      super.onBackPressed();
    }
  }

  void readTextAsset() {
    try {
      final AssetManager am = this.getAssets();
      final InputStream is = am.open(ASSET_FILE_NAME);

      final StringBuilder textBuilder = new StringBuilder();
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