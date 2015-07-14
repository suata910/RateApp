package com.sua.tavita.rateapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sua.tavita.rateapp.tables.App;

import java.util.ArrayList;

/**
 * Created by Tavita on 28/06/2015.
 */
public class AppRepo {
    private DBHelper dbHelper;
    private Cursor cursor;

    public AppRepo(Context context){
        dbHelper = new DBHelper(context);
    }

    public App getAppByName(String name){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT *" + " FROM " + App.TABLE
                + " WHERE " + App.NAME + "=?";

        App app = new App();
        int iCount = 0;
        cursor = db.rawQuery(selectQuery, new String[]{name});
        if(cursor.moveToFirst()){
            do{
                app.setName(cursor.getString(cursor.getColumnIndex(App.NAME)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return app;
    }

    public App getAppByID(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT *" + " FROM " + App.TABLE
                + " WHERE " + App.ID + "=?";

        App app = new App();
        int iCount = 0;
        cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            do{
                app.setName(cursor.getString(cursor.getColumnIndex(App.NAME)));
                app.setId(cursor.getInt(cursor.getColumnIndex(App.ID)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return app;
    }

    public ArrayList<App> getAppList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT *" + " FROM " + App.TABLE;
        ArrayList<App> apps = new ArrayList<>();
        cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                App app = new App();
                app.setName(cursor.getString(cursor.getColumnIndex(App.NAME)));
//                Log.d("vika", app.toString());
                apps.add(app);
//                System.out.println(apps.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return apps;
    }
}
