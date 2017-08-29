package com.example.ezsmuel.scavengerhunt;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.Result;


import java.util.ArrayList;
import java.util.List;

import static android.R.drawable.edit_text;



public class ItemCollections extends Activity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    TextView t1;
    List<String> scans = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_collections);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scans);
        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v,int position, long id)
            {
                Toast.makeText(getBaseContext(), "You have found "+ scans.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onListItemClick(
            ListView parent, View v,
            int position, long id)
    {
        Toast.makeText(this,
                "You have selected " + scans.get(position),
                Toast.LENGTH_SHORT).show();
    }

    public void scannerButton(View v){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

    }

    public void handleResult(Result result){

        Log.v("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        scans.add("the result is "+result);

        mScannerView.resumeCameraPreview(this);
        Intent intent = new Intent(this, ItemCollections.class);
        startActivity(intent);
        t1.setText("the result is "+result.getText().toString());
        this.finish();
    }
}