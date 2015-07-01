package com.sua.tavita.rateapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sua.tavita.rateapp.tables.AppReview;
import com.sua.tavita.rateapp.tables.Defect;

/**
 * Created by Tavita on 29/06/2015.
 */
public class DefectRepo {
    private DBHelper dbHelper;
    private Cursor cursor = null;

    public DefectRepo(Context c){
        dbHelper = new DBHelper(c);
    }

    public int insert(Defect defect) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Defect.ID, defect.id);
        values.put(Defect.AID, defect.aid);
        values.put(Defect.FID, defect.fid);
        values.put(Defect.IID, defect.iid);

        // Inserting Row
        long review_id = db.insert(AppReview.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) review_id;
    }
}
