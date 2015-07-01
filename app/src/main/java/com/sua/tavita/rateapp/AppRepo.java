package com.sua.tavita.rateapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public ArrayList<App> getAppList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT *" + " FROM " + App.TABLE;
        ArrayList<App> apps = new ArrayList<>();
        cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                App app = new App();
                app.setName(cursor.getString(cursor.getColumnIndex(App.NAME)));
                Log.d("vika", app.toString());
                apps.add(app);
                System.out.println(apps.toString());
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return apps;
    }
}
