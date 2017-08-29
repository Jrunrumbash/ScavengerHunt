package com.example.ezsmuel.scavengerhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RulesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_page);
    }
    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,NewStartPage.class);
        startActivity(setIntent);
        return;
    }
}
