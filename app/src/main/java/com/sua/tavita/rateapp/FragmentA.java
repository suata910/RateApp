package com.sua.tavita.rateapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teuila on 12/06/15.
 */
public class FragmentA extends Fragment {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private View rootView;

    public FragmentA() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_a, container, false);
        Log.d("vika", "FragmentA OnCreateView called");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.AppList);
        adapter = new RecycleViewAdapter(getActivity(), getData());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("vika", "FragmentA onViewCreated called");
        recyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener1() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Vika", "item " + position + " was clicked");
                if (savedInstanceState == null) {
                    Fragment fragment = new FragmentA2();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentA2, fragment).commit();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d("vika", "you clicked an item");
            }
        }));

    }

    public static List<Feature> getData() {
        List<Feature> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_facebook, R.drawable.ic_mytracks};

        String[] titles = {"Facebook", "MyTracks"};

        for (int i = 0; i < titles.length && i < icons.length; i++) {
            Feature current = new Feature();
            current.iconID = icons[i];
            current.featureTitle = titles[i];
            data.add(current);
        }
        return data;
    }
}
