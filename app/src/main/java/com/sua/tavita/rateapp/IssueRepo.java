package com.sua.tavita.rateapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sua.tavita.rateapp.tables.Defect;
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
    public ArrayList<Issue> getIssuesByID(int id){
        ArrayList<Issue> issues = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String selectQuery = "SELECT " + Issue.ISSUE_DESCRIPTION + " FROM " + Issue.TABLE
                + " a INNER JOIN "
                + Defect.TABLE + " b ON "+ "a._id = b.did" + " where "+
                Defect.FID + "=?"; //select the issues related to a particular feature

        int iCount =0;

        cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()){
            do {
                Issue iss = new Issue();
                iss.setIssue_description(cursor.getString(cursor.getColumnIndex(Issue.ISSUE_DESCRIPTION)));
                iss.toString();
                issues.add(iss);
            }while (cursor.moveToNext());
        }
        Log.d("Vika", issues.toString());
        cursor.close();
        db.close();
        return issues;
    }
}
