package com.sua.tavita.rateapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.sql.SQLException;

import tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_POSITION = "position";
    private static final int PAGE_COUNT = 2;
    private static final String TAG = "Vika";
    private DBHelper dbHelper;
    private Toolbar toolbar;
    private SlidingTabLayout mTabs;
    private ViewPager mPager;
    private AppReviewRepo repoAppReview;
    Fragment fragment = null;

//    private RippleView rippleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        dbHelper.createDataBase();
        try {
            dbHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbHelper.close();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("RateApp");
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);

        drawerFragment.setUp(R.id.navigation_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
//        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
//        mPager = (ViewPager) findViewById(R.id.pager);
//        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
//        mTabs.setDistributeEvenly(true);
//        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
//        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
//        mTabs.setViewPager(mPager);
        FragmentManager fm = getSupportFragmentManager();
//        fragment = fm.findFragmentByTag("FragmentA");
        fragment = new FragmentA();
        if(fragment == null){
            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.frag_a, fragment, "FragmentA");
            ft.commit();
        }




    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] tabs = getResources().getStringArray(R.array.tabs);

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new FragmentA(); //outer fragment
            } else {
                fragment = new FragmentB();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
