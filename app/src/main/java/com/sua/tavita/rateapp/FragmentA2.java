package com.sua.tavita.rateapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import tabs.SlidingTabLayout;

/**
 * Created by Teuila on 18/06/15.
 */
public class FragmentA2 extends Fragment {
    BarChart chart;
    RatingBar stars;
    EditText txt;
    TextView ratingValue;
    private SlidingTabLayout mTabs;
    private ViewPager mPager;
    private AppReviewRepo repoAppReview;
    private static final int PAGE_COUNT = 2;


    public FragmentA2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_a2, container, false);
//        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
//        mPager = (ViewPager) findViewById(R.id.pager);
//        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
//        mTabs.setDistributeEvenly(true);
//        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
//        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
//        mTabs.setViewPager(mPager);
        chart = (BarChart) layout.findViewById(R.id.chart);
        stars = (RatingBar) layout.findViewById(R.id.ratingBar);
        txt = (EditText) layout.findViewById(R.id.comment);
        ratingValue = (TextView) layout.findViewById(R.id.ratingValue);
        setListeners();
        return layout;
    }

    public void setListeners() {
        stars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String ratedValue = String.valueOf(rating);

                // Get Number of stars
                int numStars = ratingBar.getNumStars();

                // Now set the rated Value and numstars on textView
                ratingValue.setText("Rating value is : "
                        + ratedValue + "/" + numStars);
            }
        });
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
