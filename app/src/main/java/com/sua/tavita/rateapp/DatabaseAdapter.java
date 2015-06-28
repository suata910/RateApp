package com.sua.tavita.rateapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tavita on 03/06/2015.
 */
public class DatabaseAdapter {
    DatabaseHelper helper;

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
    }

    public long insertFeature(String feature){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FEATURE_NAME, feature);
        return db.insert(DatabaseHelper.FEATURE_TABLE, null, contentValues);
    }

    public void insertIssue(String issue, int relatedFeature){

    }

   static class DatabaseHelper extends SQLiteOpenHelper{

       private static final String DATABASE_NAME = "RATEAPPDATABASE";
       private static final int DATABASE_VERSION = 3;

       private static final String FEATURE_TABLE = "FeatureOne";
       private static final String FID = "FID";
       private static final String FEATURE_NAME = "FeatureName";

       private static final String ISSUE_TABLE = "Issue";
       private static final String IID = "_IID";
       private static final String ISSUE_DESCRIPTION = "IssueDescription";
       private static final String RELATED_FEATURE = "RelatedFeature";

       private static final String APPREVIEW_TABLE = "AppReview";
       private static final String RID = "_RID";
       private static final String STARS = "Stars";
       private static final String COMMENT = "Comment";

       private Context context;

       private static final String ENABLE_FOREIGN_KEYS = "PRAGMA foreign_keys = 1;";

       private static final String CREATE_TABLE0 = "CREATE TABLE " + FEATURE_TABLE + "(" + FID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
               + FEATURE_NAME + " VARCHAR(255));";
       private static final String DROP_TABLE0 = "DROP TABLE IF EXISTS " + FEATURE_TABLE;

       private static final String CREATE_TABLE1 = "CREATE TABLE " + ISSUE_TABLE + "(" + IID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
               + ISSUE_DESCRIPTION + " VARCHAR(255), "+ RELATED_FEATURE + " INTEGER, FOREIGN KEY("+RELATED_FEATURE+") REFERENCES  " +
               FEATURE_TABLE + "("+FID+"));";
       private static final String DROP_TABLE1 = "DROP TABLE IF EXISTS " + ISSUE_TABLE;



       private static final String CREATE_TABLE2 = "CREATE TABLE " + APPREVIEW_TABLE + "(" + RID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               STARS + " INTEGER, " + COMMENT + " VARCHAR(255));";

       private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS " + APPREVIEW_TABLE;



       public DatabaseHelper(Context context){
           super(context, DATABASE_NAME, null, DATABASE_VERSION);
           this.context = context;
           Message.message(context, "DatabaseAdapter constructor called");
       }


       @Override
       public void onCreate(SQLiteDatabase sqLiteDatabase) {

           try {
               sqLiteDatabase.execSQL(ENABLE_FOREIGN_KEYS);
               sqLiteDatabase.execSQL(CREATE_TABLE0);
               sqLiteDatabase.execSQL(CREATE_TABLE1);
               sqLiteDatabase.execSQL(CREATE_TABLE2);
               Message.message(context, "onCreate called");
           } catch (SQLException e) {
               Message.message(context, ""+e);
               Log.d("Vika", "" + e);
           }
       }

       @Override
       public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           try {
               sqLiteDatabase.execSQL(ENABLE_FOREIGN_KEYS);
               sqLiteDatabase.execSQL(DROP_TABLE0);
               sqLiteDatabase.execSQL(DROP_TABLE1);
               sqLiteDatabase.execSQL(DROP_TABLE2);
               onCreate(sqLiteDatabase);
               Message.message(context, "onUpgrade called");
           } catch (SQLException e) {
               Message.message(context, ""+e);
               Log.d("Vika", "" + e);
           }
       }
   }
}
