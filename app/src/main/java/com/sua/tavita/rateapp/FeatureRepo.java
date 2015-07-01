package com.sua.tavita.rateapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sua.tavita.rateapp.tables.Defect;
import com.sua.tavita.rateapp.tables.Feature;

import java.util.ArrayList;

/**
 * Created by Tavita on 28/06/2015.
 */
public class FeatureRepo {
    private DBHelper dbHelper;
    private Cursor cursor = null;

    public FeatureRepo(Context c){
        dbHelper = new DBHelper(c);
    }

    //get all the available features
    public ArrayList<Feature> getFeatureList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT " + Feature.FEATURE_NAME + ", " + Defect.COUNT
                + " FROM " + Feature.TABLE + " a INNER JOIN "
                + Defect.TABLE + " b ON "
                + "a._id = b.did "
                + "ORDER BY " + Defect.COUNT + " DESC;";

        ArrayList<Feature> features = new ArrayList<>();
        cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Feature feature = new Feature();
//                feature.setId(cursor.getInt(cursor.getColumnIndex(Feature.ID)));
                feature.setFeature_name(cursor.getString(cursor.getColumnIndex(Feature.FEATURE_NAME)));
                features.add(feature);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return features;
    }

    public Feature getFeatureByID(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + Feature.FEATURE_NAME
                + " FROM " + Feature.TABLE
                + " WHERE " + Feature.ID + " = ?";
        Feature feature = new Feature();
        int iCount = 0;
        cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            do{
                feature.setFeature_name(cursor.getString(cursor.getColumnIndex(Feature.FEATURE_NAME)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return feature;
    }
}
