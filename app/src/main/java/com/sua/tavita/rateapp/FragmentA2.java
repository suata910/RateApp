package com.sua.tavita.rateapp;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sua.tavita.rateapp.tables.AppReview;
import com.sua.tavita.rateapp.tables.User;

import tabs.SlidingTabLayout;

/**
 * Created by Teuila on 18/06/15.
 */
public class FragmentA2 extends Fragment {
    RatingBar rating;
    EditText titleTxt;
    EditText descTxt;
    TextView ratingValue;
    Button submitBtn;
    Button reportIssue;
    Fragment fragment = null;

    private SlidingTabLayout mTabs;
    private ViewPager mPager;
    private static final int PAGE_COUNT = 2;
    private int stars;
    private UserRepo userRepo;
    private AppRepo appRepo;
    private AppReviewRepo reviewRepo;
    private String android_id;
    private String ts;
    private int counter;
    private User user;
    private CommunicatorInterface comm;
    private FragmentTransaction ft;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            comm = (CommunicatorInterface) activity;
        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
            Log.d("vika", " " + castException);
        }
    }

    public FragmentA2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a2, container, false);
//        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
//        mPager = (ViewPager) findViewById(R.id.pager);
//        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
//        mTabs.setDistributeEvenly(true);
//        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
//        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
//        mTabs.setViewPager(mPager);
        android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        rating = (RatingBar) rootView.findViewById(R.id.ratingBar);
        titleTxt = (EditText) rootView.findViewById(R.id.title);
        descTxt = (EditText) rootView.findViewById(R.id.description);
        ratingValue = (TextView) rootView.findViewById(R.id.ratingValue);
        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);
        reportIssue = (Button)rootView.findViewById(R.id.reportIssue);
        counter = 0;
        setListeners();
        comm.setActionBar("Review");

        return rootView;
    }

    public void setListeners() {
        Long tsLong = System.currentTimeMillis() / 1000;
        ts = tsLong.toString();

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (rating == 5) {
                    ratingValue.setText("Loved It");
                } else if (rating == 4) {
                    ratingValue.setText("Liked It");
                } else if (rating == 3) {
                    ratingValue.setText("It's Ok");
                } else if (rating == 2) {
                    ratingValue.setText("Didn't Like It");
                } else if (rating == 1) {
                    ratingValue.setText("Hated It");
                } else {
                    ratingValue.setText("");
                }
                stars = (int) rating;
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }

        });
        reportIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new FragmentB();
                replaceFragment(fragment);
            }
        });
//        comm.backPressed();
    }

    public void replaceFragment(Fragment fragment) {
        ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_in_bottom);
        ft.replace(R.id.fragment_b, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void createAppReview(User user) {
        reviewRepo = new AppReviewRepo(getActivity());
        appRepo = new AppRepo(getActivity());
        AppReview appReview = new AppReview();
        appReview.setStars(stars);
        appReview.setTitle(titleTxt.getText().toString());
        appReview.setDescription(descTxt.getText().toString());
        appReview.setAid(appRepo.getAppByName(comm.getSelectedApp()).getId());
        appReview.setDeviceId(user.getDevice_id());
        appReview.setTimeStamp(user.getTimeStamp());
        long id = reviewRepo.insertAppReview(appReview);
        if (id > 0) {
            Message.message(getActivity(), "Successfully added a new AppReview");
        } else {
            Message.message(getActivity(), "Unsuccessful in adding a new AppReview");
        }
    }

    public void createUser() {
        userRepo = new UserRepo(getActivity());
        user = new User();
        user.device_id = android_id;
        user.timeStamp = ts;
        long id = userRepo.insertUser(user);
        if (id > 0) {
            Message.message(getActivity(), "Successfully added a new User");
        } else {
            Message.message(getActivity(), "Unsuccessful in adding a new user");
        }
        createAppReview(user);
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
