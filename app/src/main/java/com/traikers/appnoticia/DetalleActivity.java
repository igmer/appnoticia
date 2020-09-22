package com.traikers.appnoticia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        WebView webView = findViewById(R.id.detalle);
        String url = getIntent().getExtras().getString("url");
        webView.loadUrl(url);
    }
}