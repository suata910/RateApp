package com.sua.tavita.rateapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.sql.SQLException;


public class AppReview_a extends AppCompatActivity {
    private DBHelper dbHelper;
    private Toolbar toolbar;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_review);
        dbHelper = new DBHelper(this);
        dbHelper.createDataBase();
        try {
            dbHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbHelper.close();
        toolbar = (Toolbar) findViewById(R.id.app_bar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("SelectedItem");
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_fragment1);
        drawerFragment.setUp(R.id.navigation_fragment1, (DrawerLayout) findViewById(R.id.drawer_layout1), toolbar);

    }

}
