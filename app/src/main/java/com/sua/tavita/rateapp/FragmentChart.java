package com.sua.tavita.rateapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by Tavita on 03/07/2015.
 */
public class FragmentChart extends Fragment {
    private BarChart chart;
    private View rootView;

    public FragmentChart() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_a, container, false);
        chart = (BarChart)rootView.findViewById(R.id.chart);
//        chart.setVisibility(View.VISIBLE);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        chart.setVisibility(View.VISIBLE);
    }
}
