package com.sua.tavita.rateapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;

import com.sua.tavita.rateapp.tables.User;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Tavita on 03/07/2015.
 */
public class UserRepo {
    private DBHelper dbHelper;
    private Cursor cursor = null;

    public UserRepo(Context c){
        dbHelper = new DBHelper(c);
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    public long insertUser(User user) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("vika", "timestamp is " + user.getTimeStamp());
        values.put(User.DEVICE_ID,user.device_id);
        values.put(User.TIMESTAMP, user.timeStamp);

        // Inserting Row
        long user_id = db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
        return user_id;
    }
}
