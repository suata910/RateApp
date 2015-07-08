package com.sua.tavita.rateapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sua.tavita.rateapp.tables.AppReview;

/**
 * Created by Tavita on 29/06/2015.
 */
public class AppReviewRepo {
    private DBHelper dbHelper;
    private Cursor cursor;

    public AppReviewRepo(Context context){
        dbHelper = new DBHelper(context);
    }

    public long insertAppReview(AppReview appReview) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppReview.STARS, appReview.stars);
        values.put(AppReview.TITLE, appReview.title);
        values.put(AppReview.DESCRIPTION, appReview.description);
        values.put(AppReview.APPLICATION_ID, appReview.aid);
        values.put(AppReview.DEVICE_ID, appReview.deviceId);
        values.put(AppReview.TIMESTAMP, appReview.timeStamp);

        // Inserting Row
        long review_id = db.insert(AppReview.TABLE, null, values);
        db.close(); // Closing database connection
        return review_id;
    }

}
