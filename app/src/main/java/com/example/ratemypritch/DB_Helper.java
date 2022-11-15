package com.example.ratemypritch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class DB_Helper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DB_Helper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table ratings(ratingid INTEGER primary key, rating FLOAT)");
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table reviews(reviewid INTEGER primary key, reviewhead TEXT, reviewbot TEXT, ratingid INTEGER)");
        MyDB.execSQL("create Table menu(date DATE primary key, pizza TEXT, chefTable TEXT, pasta TEXT, grill TEXT)");
        getMenu();

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists ratings");
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists reviews");
        MyDB.execSQL("drop Table if exists menu");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean insertReview(int reviewid, String reviewhead, String reviewbot, int ratingid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("reviewid", reviewid);
        contentValues.put("reviewhead", reviewhead);
        contentValues.put("reviewbot", reviewbot);
        contentValues.put("ratingid", ratingid);
        long result = MyDB.insert("reviews", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertRatings(int ratingid, Float rating){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ratingid", ratingid);
        contentValues.put("rating", rating);
        long result = MyDB.insert("ratings", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public void getMenu(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String[] date = {"2022-11-13", "2022-11-14", "2022-11-15", "2022-11-16", "2022-11-17", "2022-11-18", "2022-11-19"};
        String[] pizza = {"MUSHROOM BIANCA PIZZA,CHICKEN BIANCA", "MARGHERITA PIZZA,PEPPERONI PIZZA", "SIMPLE FLORENTINE DELIGHT,P-P-PORKIE'S P-P-PIZZA", "HIT THE ROAD JACKfruit,DELUXE", "ZESTY FIESTA PIZZA,Cajun chicken Pizza", "4 cheese pizza,SAVOURY COWBOY PIZZA", "NOT CHO PIZZA,CORD EN BLEU PIZZA"};
        String[] ct = {"CITRUS CHILI SNAPPER,CITRUS CHILI TOFU STEAKS", "TANDOORI CHICKEN,TOFU ALOO GOBI", "SHERRY BRAISED PORK CHEEKS,APPLE JACKS", "BEEF STROGANOFF,MUSHROOM STROGANOFF", "BUTTER CHICKEN,BUTTER CHICKPEAS", "BRAISED BEEF RAVIOLI,BABY SPINACH SALAD", "PEROGIES,LENTIL SHEPARDS PIE"};
        String[] pasta = {"PAD THAI WITH SOY CURLS,SHRIMP PAD THAI", "TTEOKBOKKI, SAMPLE", "PORK DAN DAN NOODLES,TOFU DAN DAN NOODLES", "CHICKEN YAKI UDON,YAKI UDON WITH SOY CURLS", "BIBIM NOODLES WITH EGG,BIBIM NOODLES WITH TOFU", "PORK SHANGHAI FRIED NOODLES,TOFU SHANGHAI NOODLES", "CRISPY SHRIMP ZARU SOBA,CRISPY TOFU ZARU SOBA"};
        String[] grill = {"GREEK CHILI BURGER,BEYOND GREEK CHILI BURGER", "BEEF DIPPIN,MUSHROOM DIPPIN", "SALMON POWER BOWL,TOFU POWER BOWL", "CHICKEN WINGS,CAULIFLOWER WINGS", "CHICKEN TENDERS,BEYOND TENDERS", "FISH & CHIPS,MUSHROOM CALAMARI", "CHICAGO DOG,BEYOND THE CHICAGO DOG"};
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", String.valueOf(date[0]));
        contentValues.put("pizza", pizza[0]);
        contentValues.put("chefTable", ct[0]);
        contentValues.put("pasta", pasta[0]);
        contentValues.put("grill", grill[0]);
        MyDB.insert("menu", null, contentValues);
        /*
        for (int i = 0; i < 6; i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("date", String.valueOf(date[i]));
            contentValues.put("pizza", pizza[i]);
            contentValues.put("chefTable", ct[i]);
            contentValues.put("pasta", pasta[i]);
            contentValues.put("grill", grill[i]);
            MyDB.insert("menu", null, contentValues);
        }

         */

    }

    public int ratingidassign() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from ratings", new String[]{});
        return cursor.getCount();
    }

    public int reviewidassign() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from reviews", new String[]{});
        return cursor.getCount();
    }

    public int getRatingidatreview(int reviewid) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String revid = String.valueOf(reviewid);
        Cursor cursor = MyDB.rawQuery("Select * from reviews where reviewid = ?", new String[]{revid});
        cursor.moveToFirst();
        return cursor.getInt(3);
    }

    public float getRating(int ratingid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String ratid = String.valueOf(ratingid);
        Cursor cursor = MyDB.rawQuery("Select * from ratings where ratingid = ?", new String[]{ratid});
        cursor.moveToFirst();
        Log.i("ratingfloat", String.valueOf(cursor.getFloat(1)));
        return cursor.getFloat(1);
    }

    public String getReviewtop(int reviewid) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String revid = String.valueOf(reviewid);
        Cursor cursor = MyDB.rawQuery("Select * from reviews where reviewid = ?", new String[]{revid});
        cursor.moveToFirst();
        Log.i("reviewtopat2", String.valueOf(cursor.getString(1)));
        return cursor.getString(1);
    }

    public String getReviewbot(int reviewid) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String revid = String.valueOf(reviewid);
        Cursor cursor = MyDB.rawQuery("Select * from reviews where reviewid = ?", new String[]{revid});
        cursor.moveToFirst();
        Log.i("reviewBOTat2", String.valueOf(cursor.getString(2)));
        return cursor.getString(2);
    }


    public float avgreview(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select AVG(rating) AS avgrating FROM ratings", null);
        cursor.moveToFirst();
        return cursor.getFloat(0);
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
