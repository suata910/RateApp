package com.sua.tavita.rateapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.sua.tavita.rateapp.tables.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teuila on 12/06/15.
 */
public class FragmentA extends Fragment {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private View rootView;
    private AppRepo repo;
    private AppReviewRepo appReviewRepo;
    private CommunicatorInterface listener;
    HorizontalBarChart chart;

    public FragmentA() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (CommunicatorInterface) activity;
        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
            Log.d("vika", " " + castException);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_a, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.AppList);
        chart = (HorizontalBarChart) rootView.findViewById(R.id.barChart);
        adapter = new RecycleViewAdapter(getActivity(), getData());
        listener.getDefaultActionBar();
        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (savedInstanceState == null) {

                    Fragment a = new FragmentA2();
                    listener.replaceFragment(a);
                    String s = getData().get(position).title;
                    listener.setSelectedApp(s);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d("vika", "you clicked an item");
            }
        }));
//        listener.getDefaultActionBar();
        createBarGraph(0);
    }

    public void createBarGraph(int aid) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        appReviewRepo = new AppReviewRepo(getActivity());
        ArrayList<Integer> s = appReviewRepo.getNumberStars(aid);

        int i = 0;
        for (Integer a : s) {
            entries.add(new BarEntry(a, i));
            System.out.println("a is " + String.valueOf(a));
            i++;
        }

        BarDataSet dataset = new BarDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");

        chart.setDescription("Number of Stars");

//        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setColors(new int[]{R.color.oneStar, R.color.twoStars, R.color.threeStars,R.color.fourStars ,R.color.fiveStars}, getActivity());
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getLegend().setEnabled(false);
//        chart.animateY(1500);
    }


    public List<Information> getData() {
        repo = new AppRepo(getActivity());
        List<App> apps = repo.getAppList();
        List<Information> data = new ArrayList<>();

//        int[] icons = {R.drawable.ic_facebook, R.drawable.ic_mytracks};
        int[] icons = {R.drawable.ic_mytracks};

        for (int i = 0; i < apps.size() && i < icons.length; i++) {
            Information current = new Information();
            current.iconID = icons[i];
            current.title = apps.get(i).getName();
            Log.d("vika", current.title);
            data.add(current);
        }
        return data;
    }
}
