package com.example.ezsmuel.scavengerhunt;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.text.util.Linkify;
import android.util.Log;
import android.content.Intent;
import android.app.Activity;
import android.view.View;
import com.google.zxing.Result;
import android.widget.Chronometer;
import android.widget.Button;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.provider.AlarmClock.EXTRA_MESSAGE;



public class ScannerPage extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    DatabaseHelper mDatabaseHelper;
    public static TextView tvresult;
    public static Button currentItm;
    int pStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_page);

        tvresult = (TextView) findViewById(R.id.outputRes);
        currentItm = (Button) findViewById(R.id.currentItem);

        mDatabaseHelper = new DatabaseHelper(this);
    }

    public void onClick(View v){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    public void handleResult(Result result){

        String str = result.getText();
        String res = str.substring(str.lastIndexOf("/")+1);

        NewStartPage.tvresult.setText("Current Item: "+res);

        Intent intent = new Intent(this, CurrentItem.class);
        intent.putExtra("website", result.getText());
        startActivity(intent);

        AddData(result.getText());
        //onBackPressed();
    }

    public void AddData(String newEntry){

        //check to see if its a valid scan
        boolean data = mDatabaseHelper.duplicate(newEntry);

        if(newEntry.contains("http://techdayireland.com/")){
            if(data==true){
            boolean insertData2 = mDatabaseHelper.addData(newEntry);

            toastMessage("Scan Successful!");   //Data Successfully Inserted!
        }
        else {
                toastMessage("Invalid Scan, Try again");
            }
        }
        else{
            toastMessage("Invalid Scan, Try again");
            Intent intent = new Intent(this, ScannerPage.class);
            startActivity(intent);
        }
    }
    public void Exist(String newEntry){
        boolean yes = mDatabaseHelper.exits(newEntry);
        if(yes==true){
            toastMessage("Scan Already Exists");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
