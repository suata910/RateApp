package com.sua.tavita.rateapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.sua.tavita.rateapp.AppRepo;
import com.sua.tavita.rateapp.AppReviewRepo;
import com.sua.tavita.rateapp.CommunicatorInterface;
import com.sua.tavita.rateapp.Information;
import com.sua.tavita.rateapp.R;
import com.sua.tavita.rateapp.tables.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teuila on 12/06/15.
 */
public class FacebookFragment extends Fragment {
//    private RecyclerView recyclerView;
//    private RecycleViewAdapter adapter;
    private View rootView;
    private AppRepo repo;
    private AppReviewRepo appReviewRepo;
    private CommunicatorInterface listener;
    private TextView avgRating;
    HorizontalBarChart chart;

    public FacebookFragment() {

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
//        recyclerView = (RecyclerView) rootView.findViewById(R.id.AppList);
        chart = (HorizontalBarChart) rootView.findViewById(R.id.barChart);
//        adapter = new RecycleViewAdapter(getActivity(), getData());
        avgRating = (TextView) rootView.findViewById(R.id.avgRating);
        listener.getDefaultActionBar();
        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        recyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(adapter);
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                if (savedInstanceState == null) {
//
//                    Fragment a = new FragmentA2();
//                    listener.replaceFragment(a);
//                    String s = getData().get(position).title;
//                    listener.setSelectedApp(s);
//                }
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Log.d("vika", "you clicked an item");
//            }
//        }));
////        listener.getDefaultActionBar();
        appReviewRepo = new AppReviewRepo(getActivity());
        avgRating.setText(String.valueOf(appReviewRepo.getAverageRating(0)+".0"));
//        avgRating.setText("3.0");
        createBarGraph(0);
    }

    public void createBarGraph(int aid) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<Integer> s = appReviewRepo.getNumberStars(aid);

        int i = 0;
        for (Integer a : s) {
            entries.add(new BarEntry(a, i));
            System.out.println("a is " + String.valueOf(a));
            i++;
        }

        BarDataSet dataset = new BarDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("*");
        labels.add("**");
        labels.add("***");
        labels.add("****");
        labels.add("*****");

        chart.setDescription("");

//        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setColors(new int[]{R.color.oneStar, R.color.twoStars, R.color.threeStars, R.color.fourStars, R.color.fiveStars}, getActivity());
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
        chart.setNoDataTextDescription("");
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getLegend().setEnabled(false);
        chart.setDrawValueAboveBar(true);

//        chart.animateY(1500);
    }


    public List<Information> getData() {
        repo = new AppRepo(getActivity());
        List<App> apps = repo.getAppList();
        List<Information> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_facebook,R.drawable.ic_mytracks};
//        int[] icons = {R.drawable.ic_mytracks};

        for (int i = 0; i < apps.size() && i < icons.length; i++) {
            Information current = new Information();
            App ape = repo.getAppByID(i);
            current.setIconID(icons[i]);
            current.setTitle(ape.getName());
//            Log.d("vika", " "+current.title);
            data.add(current);
        }
        return data;
    }
}
