package com.sua.tavita.rateapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tavita on 03/06/2015.
 */
public class VikaHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rateappdatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FEATURE = "feature";
    private static final String TABLE_ISSUE = "issue";
    private static final String FID = "fid";
    private static final String FEATURE_NAME = "feature_name";
    private static final String UID = "_id";
    private static final String ISSUE_DESCRIPTION = "issue_description";
    private Context context;

    private static final String CREATE_TABLE_0 = "CREATE TABLE " + TABLE_FEATURE + "(" + FID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FEATURE_NAME + " VARCHAR(255));";
    private static final String DROP_TABLE_0 = "DROP TABLE IF EXISTS " + TABLE_FEATURE;

    private static final String CREATE_TABLE_1 = "CREATE TABLE " + TABLE_ISSUE + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ISSUE_DESCRIPTION + " VARCHAR(255)); ";

    private static final String DROP_TABLE_1 = "DROP TABLE IF EXISTS " + TABLE_ISSUE;


    public VikaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Message.message(context, "VikaHelper constructor called");

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            Log.v("vikahelper", "Before table_0 was created");
            sqLiteDatabase.execSQL(CREATE_TABLE_0);
            Log.v("vikahelper", "Before table_1 was created");
            sqLiteDatabase.execSQL(CREATE_TABLE_1);
            Message.message(context, "onCreate called");
        } catch (SQLException e) {
            Message.message(context, ""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE_0);
            sqLiteDatabase.execSQL(DROP_TABLE_1);
            onCreate(sqLiteDatabase);
            Message.message(context, "onUpgrade called");
        } catch (SQLException e) {
            Message.message(context, ""+e);
        }
    }
}
