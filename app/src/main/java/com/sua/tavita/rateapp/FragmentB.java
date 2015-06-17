package com.sua.tavita.rateapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teuila on 12/06/15.
 */
class AppFeature {
    String title;
    int img;

    public AppFeature() {
    }

    public AppFeature(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }
}

public class FragmentB extends Fragment implements AdapterView.OnItemClickListener{
    private ListView listView;
    private TextView txt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_b, container, false);
        listView = (ListView) layout.findViewById(R.id.listView);
        listView.setAdapter(new ListViewAdapter(getActivity(), getData()));
        listView.setOnItemClickListener(this);
//        listView.setClickable(true);

        return layout;
        
    }

    public static List<AppFeature> getData() {
        List<AppFeature> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_battery, R.drawable.ic_data,
                R.drawable.ic_distance, R.drawable.ic_gps, R.drawable.ic_location, R.drawable.ic_maps,
                R.drawable.ic_signal, R.drawable.ic_support, R.drawable.ic_time, R.drawable.ic_version};

        String[] titles = {"Battery", "Data", "Distance", "GPS", "Location", "Maps", "Signal",
                "Support", "Time", "Version"};

        for (int i = 0; i < titles.length && i < icons.length; i++) {
            AppFeature current = new AppFeature();
            current.img = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int atPosition = (int) listView.getItemIdAtPosition(i);
        Object o = listView.getItemAtPosition(i);
        AppFeature a = (AppFeature) o;
        Message.message(getActivity(), a.getTitle() + ""+ atPosition);
    }
}
