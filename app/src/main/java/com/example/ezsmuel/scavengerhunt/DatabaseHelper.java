package com.example.ezsmuel.scavengerhunt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ezsmuel on 16/08/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "people_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
String createTable = "CREATE TABLE " +TABLE_NAME+" ("+COL1+" INTEGER PRIMARY KEY, "+ COL2 + " TEXT NOT NULL, UNIQUE ("+COL2+"))"; //(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL2 + " TEXT)";

        //String createT = "CREATE TABLE "+TABLE_NAME+" ("+COL1+" integer primary key, "+COL2+" text not null, unique
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP IF TABLE EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean duplicate(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        //following 2 lines!!
        String query ="DELETE FROM " + TABLE_NAME + " WHERE "+COL2+" = '" + item + "'";
        db.execSQL(query);
        return true;
    }

    public boolean exits(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        String query ="SELECT FROM " + TABLE_NAME + " WHERE "+COL2+" = '" + item + "'";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }


    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        //following 2 lines!!
        String query ="DELETE FROM " + TABLE_NAME + " WHERE "+COL2+" = '" + item + "'";
        db.execSQL(query);

        Log.d(TAG, "addData: Adding "+item+ " to "+TABLE_NAME);
        long result = db.insert(TABLE_NAME, null,contentValues);

        if(result ==1){
            return false;

        }
        else{
            return true;
        }
    }
    public Cursor getDate(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COL1+" FROM "+TABLE_NAME+" WHERE "+COL2+" = '"+name+"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME+" SET "+COL2+" = '"+newName+"' WHERE "+COL1+" = '"+id+"'"+" AND "+COL2+" = '"+oldName+"'";
        Log.d(TAG, "updateName: query: "+query);
        Log.d(TAG, "updateName: Setting name to "+newName);
        db.execSQL(query);
    }

    public void deleteName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query2 = ("DELETE FROM "+TABLE_NAME);
        Log.d(TAG, "deleteName: Deleting "+name+" from database.");
        Log.d(TAG, "deleteName: query: "+query2);
        db.execSQL(query2);
    }

    public Cursor countItems(Object o){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("select * from " + TABLE_NAME,null);
        return mCursor;
    }
}
