package com.sua.tavita.rateapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public ArrayList<Issue> getIssuesByID(int id){
        ArrayList<Issue> issues = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String selectQuery = "SELECT " + Issue.ISSUE_DESCRIPTION + " FROM " + Issue.TABLE
                + " a INNER JOIN "
                + Defect.TABLE + " b ON "+ "a._id = b._id" + " where "+
                Defect.FID + "=?"; //select the issues related to a particular feature

        int iCount =0;
        Issue iss = new Issue();
        cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()){
            do {
                iss.setIssue_description(cursor.getString(cursor.getColumnIndex(Issue.ISSUE_DESCRIPTION)));
                issues.add(iss);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return issues;
    }
}
