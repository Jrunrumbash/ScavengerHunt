package com.example.ezsmuel.scavengerhunt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class NewStartPage extends AppCompatActivity {
    public static TextView tvresult;
    public static Button btnView, currentItm;
    public static ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_start_page);
        tvresult = (TextView) findViewById(R.id.outputRes);
        currentItm = (Button) findViewById(R.id.currentItem);
        btnView = (Button) findViewById(R.id.itemsButton);

        Linkify.addLinks(tvresult, Linkify.WEB_URLS);
        btnView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(NewStartPage.this, ListDataActivity.class);
                startActivity(intent);
            }

        });
    }

    public void rulesButton(View view) {
        Intent intent = new Intent(this, RulesPage.class);
        startActivity(intent);
    }

    public void prizesButton(View view) {
        Intent intent = new Intent(this, PrizesPage.class);
        startActivity(intent);
    }

    public void currentPage(View view) {

            goToUrl ( "http://techdayireland.com");
    }

    public void scannerButton(View view) {
        Intent intent = new Intent(this, ScannerPage.class);
        startActivity(intent);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
