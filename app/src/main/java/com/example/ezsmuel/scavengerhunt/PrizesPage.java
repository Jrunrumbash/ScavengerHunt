package com.example.ezsmuel.scavengerhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

public class PrizesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prizes_page);
    }
    //brings to new start page, was bringing to hone page
    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,NewStartPage.class);
        startActivity(setIntent);
        return;
    }
}
