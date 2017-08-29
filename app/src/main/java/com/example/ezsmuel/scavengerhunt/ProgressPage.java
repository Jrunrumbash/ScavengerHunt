package com.example.ezsmuel.scavengerhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressPage extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    public static TextView tvresult;
    public static TextView textRes1;
    ImageView im1, im2, im3, im4 , im5, im6, im7, im8, im9, im10, im11, im12, im13, im14, im15;
    Chronometer myChronometer;
    private Button increase, scanPress;
    int pStatus = 0;
    TextView tv, textBck, finished;
    Button emailBtn;
    ProgressBar mProgress;
    int value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_page);

        im1= (ImageView) findViewById(R.id.imageView1);
        im2= (ImageView) findViewById(R.id.imageView2);
        im3= (ImageView) findViewById(R.id.imageView3);
        im4= (ImageView) findViewById(R.id.imageView4);
        im5= (ImageView) findViewById(R.id.imageView5);
        im6= (ImageView) findViewById(R.id.imageView6);
        im7= (ImageView) findViewById(R.id.imageView7);
        im8= (ImageView) findViewById(R.id.imageView8);
        im9= (ImageView) findViewById(R.id.imageView9);
        im10= (ImageView) findViewById(R.id.imageView10);
        im11= (ImageView) findViewById(R.id.imageView11);
        im12= (ImageView) findViewById(R.id.imageView12);
        im13= (ImageView) findViewById(R.id.imageView13);
        im14= (ImageView) findViewById(R.id.imageView14);
        im15= (ImageView) findViewById(R.id.imageView15);

        //increase = (Button) findViewById(R.id.btnAdd);
        tvresult = (TextView) findViewById(R.id.outputRes);
        finished = (TextView) findViewById(R.id.finishedText);
        emailBtn = (Button) findViewById(R.id.emailBtn);
        //textRes1 = (TextView) findViewById(R.id.timeRes2);

        mProgress = (ProgressBar) findViewById(R.id.circularProgressbar2);
        mProgress.setProgress(0);   // Main Progress
        mProgress.setSecondaryProgress(100); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        //mProgress.setProgressDrawable(drawable);
        tv = (TextView) findViewById(R.id.tv2);

    /////making progress equal 9 items for testing. was 15.

        Bundle bundle=getIntent().getExtras();
        int value=bundle.getInt("no");

        for(int i=1; i<=value;i++){
            pStatus= (int) (i*11.111111111111111);//6.6666666666666667
            mProgress.setProgress(pStatus);
            tv.setText(pStatus+"%");
            if(i==1){
                im1.setVisibility(View.VISIBLE);
            }
            else if(i==2){
                im2.setVisibility(View.VISIBLE);
            }
            else if(i==3){
                im3.setVisibility(View.VISIBLE);
            }
            else if(i==4){
                im4.setVisibility(View.VISIBLE);
            }
            else if(i==5){
                im5.setVisibility(View.VISIBLE);
            }
            else if(i==6){
                im6.setVisibility(View.VISIBLE);
            }
            else if(i==7){
                im7.setVisibility(View.VISIBLE);
            }
            else if(i==8){
                im8.setVisibility(View.VISIBLE);
            }
            else{
                im9.setVisibility(View.VISIBLE);
            }
           /* else if(i==10){
                im10.setVisibility(View.VISIBLE);
            }
            else if(i==11){
                im11.setVisibility(View.VISIBLE);
            }
            else if(i==12){
                im12.setVisibility(View.VISIBLE);
            }
            else if(i==13){
                im13.setVisibility(View.VISIBLE);
            }
            else if(i==14){
                im14.setVisibility(View.VISIBLE);
            }
            else {
                im15.setVisibility(View.VISIBLE);
            }*/
           if(value==9){
               emailBtn.setEnabled(true);
               emailBtn.setVisibility(View.VISIBLE);
               finished.setVisibility(View.VISIBLE);
           }
        }
        mDatabaseHelper = new DatabaseHelper(this);
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, ScannerPage.class);
        startActivity(intent);
    }
    public void emailPg(View view){
        Intent intent = new Intent(this, GetStarted.class);
        startActivity(intent);
    }
}
