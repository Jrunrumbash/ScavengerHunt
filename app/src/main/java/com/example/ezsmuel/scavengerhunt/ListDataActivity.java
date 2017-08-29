package com.example.ezsmuel.scavengerhunt;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static android.R.id.list;

/**
 * Created by ezsmuel on 16/08/2017.
 */

public class ListDataActivity extends AppCompatActivity{

    private static final String TAG = "ListDataActivity";

    public static TextView timeText, count, found;
    DatabaseHelper mDatabaseHelper;
    ScannerPage myChronometer;
    private ListView mListView;
    private Button btnDelete, dbCount, scanItmBtn;
    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_collections);//list_layout
        mListView = (ListView) findViewById(list);
        mDatabaseHelper = new DatabaseHelper(this);
        //timeText = (TextView) findViewById(R.id.timeText);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        scanItmBtn = (Button) findViewById(R.id.scanItemButton) ;
        dbCount = (Button) findViewById(R.id.dbCount);
        //count = (TextView) findViewById(R.id.count);
        found = (TextView) findViewById(R.id.found);
        Intent receivedIntnet = getIntent();

        selectedID = receivedIntnet.getIntExtra("id", -1);

        selectedName = receivedIntnet.getStringExtra("name");
        populateListView();

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mDatabaseHelper.deleteName(selectedName);
                //edittable_item.setText("");

                toastMessage("removed from database");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        dbCount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Cursor c = mDatabaseHelper.countItems(null);
                int Counter=c.getCount();

                Intent intent=new Intent(ListDataActivity.this,ProgressPage.class);
                intent.putExtra("no",Counter);
                startActivity(intent);

                if(Counter <= 0){
                    found.setText("Treasures found: 0");
                    //found.setPaintFlags(found.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                    Toast.makeText(getBaseContext(), "You haven't found any items yet", Toast.LENGTH_LONG).show();
                }
                else if(Counter == 1){
                    found.setText("Treasures found: "+String.valueOf(Counter));
                    Toast.makeText(getBaseContext(), String.valueOf(Counter)+" item found!", Toast.LENGTH_LONG).show();
                }
                else if(Counter >= 2){
                    found.setText("Treasures found: "+String.valueOf(Counter));
                    Toast.makeText(getBaseContext(), String.valueOf(Counter)+" items found!", Toast.LENGTH_LONG).show();
                }
                else if(Counter>10){
                    found.setText("Treasures found: "+String.valueOf(Counter));
                    Toast.makeText(getBaseContext(), "All items found!", Toast.LENGTH_LONG).show();
                }
                mDatabaseHelper.close();
            }
        });
       // onBackPressed();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Display data in the ListView.");
        Cursor data = mDatabaseHelper.getDate();

        final ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);((BaseAdapter)adapter).notifyDataSetChanged();
        final ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = lv.getAdapter().getItem(position);
                Uri uri = Uri.parse(obj.toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,NewStartPage.class);
        startActivity(setIntent);
        return;
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void scannerButton(View view) {
        Intent intent = new Intent(this, ScannerPage.class);
        startActivity(intent);
    }
}
