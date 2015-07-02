package com.sua.tavita.rateapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sua.tavita.rateapp.tables.Defect;
import com.sua.tavita.rateapp.tables.Feature;
import com.sua.tavita.rateapp.tables.Issue;

import java.util.ArrayList;

/**
 * Created by Tavita on 28/06/2015.
 */
public class IssueRepo {
    private Cursor cursor = null;
    private DBHelper helper;

    public IssueRepo(Context context) {
        helper = new DBHelper(context);
    }

    //get the issues related to a specific feature
    public ArrayList<Issue> getIssuesByID(String name){
        ArrayList<Issue> issues = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String selectQuery = "SELECT " + Issue.ISSUE_DESCRIPTION + " FROM " + Issue.TABLE
                + " a INNER JOIN "
                + Defect.TABLE + " b ON "+ "a._id = b.iid "
                + "INNER JOIN " + Feature.TABLE + " c ON "
                + "c._id = b.fid "
                + " where "+
                Feature.FEATURE_NAME + "=?"; //select the issues related to a particular feature

        int iCount =0;

        cursor = db.rawQuery(selectQuery, new String[]{name});

        if (cursor.moveToFirst()){
            do {
                Issue iss = new Issue();
                iss.setIssue_description(cursor.getString(cursor.getColumnIndex(Issue.ISSUE_DESCRIPTION)));
                iss.toString();

                issues.add(iss);
                Log.d("Vika", issues.get(iCount).getIssue_description());
                iCount++;
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return issues;
    }
}
