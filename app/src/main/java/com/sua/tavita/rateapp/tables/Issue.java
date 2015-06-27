package com.sua.tavita.rateapp.tables;

/**
 * Created by Tavita on 28/06/2015.
 */
public class Issue {
    public static final String TABLE = "issue";
    public static final String ID = "_id";
    public static final String ISSUE_DESCRIPTION = "issue_description";

    public int id;
    public String issue_description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssue_description() {
        return issue_description;
    }

    public void setIssue_description(String issue_description) {
        this.issue_description = issue_description;
    }
}
