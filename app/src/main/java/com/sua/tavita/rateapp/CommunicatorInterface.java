package com.sua.tavita.rateapp;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Teuila on 19/06/15.
 */
public interface CommunicatorInterface {
    public void replaceFragment(Fragment fragment);
    public void setActionBar(String title);
    public void setSelectedApp(String s);
    public String getSelectedApp();
    public void getDefaultActionBar();
    public void setFeatures(ArrayList<String> s);
    public void setIssues(ArrayList<String> s);
    public ArrayList<String> getFeatures();
    public ArrayList<String> getIssues();
}
