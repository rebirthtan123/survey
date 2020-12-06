package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DBName = "database.db";

    public MyDBHelper(Context context) {
        super(context, "database.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users (username TEXT primary key, password TEXT)");
        db.execSQL("create Table info (name TEXT primary key, gender TEXT, age TEXT )");
        db.execSQL("create Table survey (id Integer primary key autoincrement, Q1 TEXT, Q2 TEXT, Q3 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists info");
        db.execSQL("drop Table if exists survey");
    }

    public Cursor view(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from survey", null);
        return cursor;
    }

    public Boolean inputSurvey(String Q1, String Q2, String Q3){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Q1", Q1);
        contentValues.put("Q2", Q2);
        contentValues.put("Q3", Q3);
        long result = database.insert("survey", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean inputInfo(String name, String gender, String age){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("age", age);
        long result = database.insert("info", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = database.insert("users", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from users where username = ?", new String[]{username});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from users where username = ? and password = ? ", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<String> q1YData(){
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<String> array = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select Q1 FROM survey Where Q1 IS NOT NULL ", null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            array.add(cursor.getString(0));
        }
        return array;
    }

    public ArrayList<String> q2YData(){
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<String> array = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select Q2 FROM survey Where Q2 IS NOT NULL ", null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            array.add(cursor.getString(0));
        }
        return array;
    }

    public ArrayList<String> q3YData(){
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<String> array = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select Q3 FROM survey Where Q3 IS NOT NULL ", null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            array.add(cursor.getString(0));
        }
        return array;
    }
}
