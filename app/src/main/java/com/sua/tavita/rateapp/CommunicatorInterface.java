package com.sua.tavita.rateapp;

import android.support.v4.app.Fragment;

/**
 * Created by Teuila on 19/06/15.
 */
public interface CommunicatorInterface {
    public void replaceFragment(Fragment fragment);
    public void setActionBarTitle(String title);
    public String getActionBarTitle();
}
