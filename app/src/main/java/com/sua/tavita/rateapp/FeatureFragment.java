package com.sua.tavita.rateapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


class Feature {
    int iconID; //represents resourceID
    String featureTitle;
}
public class FeatureFragment extends Fragment {
    private RecyclerView recyclerView;
    private VikaAdapter adapter;

    public FeatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_feature, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.featureList);
        adapter = new VikaAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<Feature> getData() {
        List<Feature> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_battery, R.drawable.ic_data,
                R.drawable.ic_distance, R.drawable.ic_gps, R.drawable.ic_location, R.drawable.ic_maps,
                R.drawable.ic_signal, R.drawable.ic_support, R.drawable.ic_time, R.drawable.ic_version};

        String[] titles = {"Battery", "Data", "Distance", "GPS", "Location", "Maps", "Signal",
                "Support", "Time", "Version"};

        for (int i = 0; i < titles.length && i < icons.length; i++) {
            Feature current = new Feature();
            current.iconID = icons[i];
            current.featureTitle = titles[i];
            data.add(current);
        }
        return data;
    }


}
