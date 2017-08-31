package com.example.ezsmuel.scavengerhunt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    CheckBox agree;
    Button startB;
    public static final String[] sitePg={"http://techdayireland.com/helmet","http://techdayireland.com/spraycan","http://techdayireland.com/scotty-hat","http://techdayireland.com/vodaphone-man","http://techdayireland.com/baby-plant","http://techdayireland.com/big-tree","http://techdayireland.com/comfy-red-chair","http://techdayireland.com/pool-table","http://techdayireland.com/vending-machines","http://techdayireland.com/water-fountain"};
//https://developer.android.com/reference/java/util/Random.html","https://en.wikipedia.org/wiki/Android_Studio","https://developer.android.com/studio/publish/preparing.html
    private static final int requestCode = 100;

    public void rulesButton(View view) {
        Intent intent = new Intent(this, RulesPage.class);
        startActivity(intent);
    }

    public void prizesButton(View view) {
        Intent intent = new Intent(this, PrizesPage.class);
        startActivity(intent);
    }

    //user permissions
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, requestCode);
        }

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
    //This makes a pop-up which prompts the user to allow use of their camera, if they select no the camera wont work.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == requestCode) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            }
            else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
