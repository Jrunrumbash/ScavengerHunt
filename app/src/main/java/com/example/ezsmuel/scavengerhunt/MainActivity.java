package com.example.ezsmuel.scavengerhunt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    CheckBox agree;
    Button startB;
    public static final String[] sitePg={"http://techdayireland.com/helmet","http://techdayireland.com/spraycan","http://techdayireland.com/scotty-hat","http://techdayireland.com/vodaphone-man","http://techdayireland.com/baby-plant","http://techdayireland.com/big-tree","http://techdayireland.com/comfy-red-chair","http://techdayireland.com/pool-table","http://techdayireland.com/vending-machines","http://techdayireland.com/water-fountain"};
//https://developer.android.com/reference/java/util/Random.html","https://en.wikipedia.org/wiki/Android_Studio","https://developer.android.com/studio/publish/preparing.html


    public void rulesButton(View view) {
        Intent intent = new Intent(this, RulesPage.class);
        startActivity(intent);
    }

    public void prizesButton(View view) {
        Intent intent = new Intent(this, PrizesPage.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, FirstItem.class);

        startB = (Button) findViewById(R.id.getStartButton);
    agree = (CheckBox) findViewById(R.id.checkBox);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agree.isChecked()){
                    startB.setEnabled(true);
                }
                else{
                    startB.setEnabled(false);
                }
            }
        });

        startB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s = sitePg[(int) (int) Math.round(Math.random() * (sitePg.length - 1))];

                intent.putExtra("website2", s);
                startActivity(intent);
            }
        });
    }

}
