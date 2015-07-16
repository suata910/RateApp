package com.sua.tavita.rateapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sua.tavita.rateapp.Fragments.FacebookFragment;
import com.sua.tavita.rateapp.tables.AppReview;
import com.sua.tavita.rateapp.tables.Issue;
import com.sua.tavita.rateapp.tables.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private IssueRepo issueRepo;
    private String android_id;
    private String ts;
    private int counter;
    private User user;
    private CommunicatorInterface comm;
    private FragmentTransaction ft;
    private List<Integer> mSelectedItems;
    private CharSequence[] items = null;
    private String[] features;
    private String[] issues;
    private ArrayList<String> summary = new ArrayList<>();
    private Fragment fA = new FacebookFragment();


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
//        mPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
//        mTabs.setDistributeEvenly(true);
//        mTabs.setBackgroundColor(getResources().getColor(R.color.primaryColor));
//        mTabs.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
//        mTabs.setViewPager(mPager);
        android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        rating = (RatingBar) rootView.findViewById(R.id.ratingBar);
        rating.setRating((float)1.0);
        titleTxt = (EditText) rootView.findViewById(R.id.title);
        descTxt = (EditText) rootView.findViewById(R.id.description);
        ratingValue = (TextView) rootView.findViewById(R.id.ratingValue);
        submitBtn = (Button) rootView.findViewById(R.id.submitBtn);
        reportIssue = (Button)rootView.findViewById(R.id.reportIssue);
        mSelectedItems = new ArrayList<>();
        features = null;
        issues = null;

        counter = 0;
        setListeners();
        comm.setActionBar("Review");

        return rootView;
    }

    public void setListeners() {
        Long tsLong = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(tsLong);
        titleTxt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        descTxt.setImeOptions(EditorInfo.IME_ACTION_DONE);
        ts = resultdate.toString();
        ratingValue.setText("Hated It");
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
                features = comm.getFeatures().toArray(new String[comm.getFeatures().size()]);
                issues = comm.getIssues().toArray(new String[comm.getFeatures().size()]);
                summary.add("Rating: " + String.valueOf(rating.getNumStars()));
                summary.add("Title: " + titleTxt.getText().toString());
                summary.add("Description: " + descTxt.getText().toString());
                summary.add("Features: " + Arrays.toString(features));
                summary.add("Issues: " + Arrays.toString(issues));
                items = summary.toArray(new String[summary.size()]);
                createDialog("Submit Review?", items).show();
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

    public void addIssues(){
        issueRepo = new IssueRepo(getActivity());
        Issue issue = new Issue();
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
                fragment = new FacebookFragment(); //outer fragment
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

    public Dialog createDialog(final String title, CharSequence[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        createUser();
                        dialog.dismiss();
                        comm.replaceFragment(fA);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                summary = new ArrayList<String>();
                dialog.dismiss();
            }
        }).setCancelable(false);
        return builder.create();
    }
}
