package com.example.ezsmuel.scavengerhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class FirstItem extends AppCompatActivity {
    String webUrl2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_item);

        webUrl2 = getIntent().getExtras().getString("website2");
        WebView webView2 = (WebView) findViewById(R.id.webview2);
        webView2.loadUrl(webUrl2);
    }
    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,NewStartPage.class);
        startActivity(setIntent);
        return;
    }
}
