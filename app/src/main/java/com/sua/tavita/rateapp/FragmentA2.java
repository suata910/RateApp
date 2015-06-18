package com.sua.tavita.rateapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by Teuila on 18/06/15.
 */
public class FragmentA2 extends Fragment {
    BarChart chart;
    RatingBar stars;
    EditText txt;
    TextView ratingValue;

    public FragmentA2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("vika", "FragmentA2 onCreateView called");
        View layout = inflater.inflate(R.layout.fragment_a2, container, false);
        chart = (BarChart) layout.findViewById(R.id.chart);
        stars = (RatingBar) layout.findViewById(R.id.ratingBar);
        txt = (EditText) layout.findViewById(R.id.editText);
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
}
