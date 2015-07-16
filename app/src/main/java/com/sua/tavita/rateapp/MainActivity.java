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
import android.view.View;

import com.sua.tavita.rateapp.Fragments.FacebookFragment;

import java.sql.SQLException;
import java.util.ArrayList;

import tabs.SlidingTabLayout;


public class MainActivity extends AppCompatActivity implements CommunicatorInterface {
    private static final int TAB_FACEBOOK = 0;
    private static final int TAB_MYTRACKS = 1;

    private static final int PAGE_COUNT = 2;
    private static final String TAG = "Vika";
    private DBHelper dbHelper;
    private Toolbar toolbar;
    private SlidingTabLayout mTabs;
    private ViewPager mPager;
    private AppReviewRepo repoAppReview;
    Fragment fragment = null;
    private FragmentTransaction ft;
    private String appName = null;
    private ArrayList<String> features = new ArrayList<>();
    private ArrayList<String> issues = new ArrayList<>();
    private NavigationDrawerFragment drawerFragment;
//    private RippleView rippleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        dbHelper.createDataBase();
        try {
            dbHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbHelper.close();
        setContentView(R.layout.activity_main);

        getDefaultActionBar();
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mTabs.setDistributeEvenly(true);
        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
        mTabs.setViewPager(mPager);
        fragment = new FacebookFragment();
//        fragment = fm.findFragmentByTag("FacebookFragment");
//        if (savedInstanceState == null) {
////        replaceFragment(fragment);
//            ft = getSupportFragmentManager().beginTransaction();
////            ft.add(R.id.frag_main, fragment);
//            ft.commit();
//        }
    }


    @Override
    public void replaceFragment(Fragment fragment) {
        ft = getSupportFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_in_bottom);
//        ft.replace(R.id.frag_main, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
        mPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    @Override
    public void setActionBar(String title) {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
//        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                getDefaultActionBar();
                replaceFragment(fragment);
            }
        });
    }

    @Override
    public void setSelectedApp(String s) {
        appName = s;
    }

    @Override
    public String getSelectedApp() {
        return appName;
    }

    public void onDrawerItemClicked(int index) {
        mPager.setCurrentItem(index);
    }

    @Override
    public void getDefaultActionBar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("RateApp");
        drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        drawerFragment.setUp(R.id.navigation_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }

    @Override
    public void setFeatures(ArrayList<String> s) {
        features = s;
    }

    @Override
    public void setIssues(ArrayList<String> s) {
        issues = s;
    }

    @Override
    public ArrayList<String> getFeatures() {
        return features;
    }

    @Override
    public ArrayList<String> getIssues() {
        return issues;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        String[] tabs = getResources().getStringArray(R.array.tabs);
        FragmentManager fragmentManager;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case (TAB_FACEBOOK):
                    fragment = new FacebookFragment();
                    break;
                case (TAB_MYTRACKS):
                    fragment = new MyTracksFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }
}
