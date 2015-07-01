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

    public FragmentA() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_a, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.AppList);
        adapter = new RecycleViewAdapter(getActivity(), getData());
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
                    Fragment fragment = new FragmentA2();
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_in_bottom);
                    ft.replace(R.id.fragmentA2, fragment).commit();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d("vika", "you clicked an item");
            }
        }));

    }

    public List<Information> getData() {
        repo = new AppRepo(getActivity());
        List<App> apps = repo.getAppList();
        List<Information> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_facebook, R.drawable.ic_mytracks};

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
