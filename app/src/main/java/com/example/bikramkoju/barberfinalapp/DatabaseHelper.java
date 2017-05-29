package com.example.bikramkoju.barberfinalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 5/22/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    ArrayList<InfoModule> data = new ArrayList<>();
    private static final String DATABASENAME = "newdatabase", TABLENAME = "expenditure", TABLENAME2 = "income", TABLENAME3 = "info";

    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME + "(mid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "expense INTEGER)"
        );
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME2 + "(mid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "income INTEGER)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLENAME3 + "(mid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TITLE TEXT, PRICE TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME3);

        onCreate(db);
    }

    public void addExpense(String expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("expense", expense);
        db.insert(TABLENAME, null, cv);
        db.close();
    }

    public void addIncome(String income) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("income", income);
        db.insert(TABLENAME2, null, cv);
        db.close();
    }

    public void addInfo(InfoModule infoModule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("TITLE", infoModule.getUtitle());
        cv.put("PRICE", infoModule.getTitle());
        db.insert(TABLENAME3, null, cv);
        db.close();
    }

    public void updateData(InfoModule infoModule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TITLE", infoModule.getUtitle());
        cv.put("PRICE", infoModule.getTitle());
        db.update(TABLENAME3, cv, "TITLE=" + infoModule.getUtitle(), null);
        db.update(TABLENAME3, cv, "PRICE="+infoModule.getTitle(), null);

        db.close();
    }

    public void removeData(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLENAME3 + " where TITLE=" + title);
        db.close();
    }


    public String getExpense() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLENAME, null);

        String res = null;
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    String result = cursor.getString(cursor.getColumnIndex("expense"));
                    res = result;
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return res;
    }

    public int getIncome() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLENAME2, null);
        int res = 0;
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    int result = cursor.getInt(cursor.getColumnIndex("income"));
                    res = result;
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return res;
    }

    public ArrayList<InfoModule> getInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLENAME3, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    InfoModule im = new InfoModule();
                    im.setUtitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                    im.setTitle(cursor.getString(cursor.getColumnIndex("PRICE")));
                    data.add(im);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return data;


    }

}
