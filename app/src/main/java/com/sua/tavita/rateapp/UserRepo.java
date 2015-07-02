package com.sua.tavita.rateapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sua.tavita.rateapp.tables.User;

/**
 * Created by Tavita on 03/07/2015.
 */
public class UserRepo {
    private DBHelper dbHelper;
    private Cursor cursor = null;

    public UserRepo(Context c){
        dbHelper = new DBHelper(c);
    }

    public long insertUser(User user) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.DEVICE_ID,user.device_id);
        values.put(User.TIMESTAMP, user.timeStamp);

        // Inserting Row
        long user_id = db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
        return user_id;
    }
}
