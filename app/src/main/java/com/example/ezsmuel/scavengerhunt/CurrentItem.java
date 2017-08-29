package com.example.ezsmuel.scavengerhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

//import static com.example.ezsmuel.scavengerhunt.R.id.outputRes;

public class CurrentItem extends AppCompatActivity {

    public static TextView tvresult;
    String webUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_item);
        tvresult = (TextView) findViewById(R.id.outputRes);

        webUrl = getIntent().getExtras().getString("website");
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(webUrl);


    }
    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,ListDataActivity.class);
        startActivity(setIntent);
        return;
    }

}
