package com.sua.tavita.rateapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sua.tavita.rateapp.tables.Issue;

import java.util.ArrayList;
import java.util.List;


class Feature {
    int iconID; //represents resourceID
    String featureTitle;
}

public class FragmentB extends Fragment {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private DatabaseAdapter vikaHelper;
    private IssuesDialogFragment fr;
    private List<Integer> mSelectedItems;
    IssueRepo repo;
    ArrayList<Issue> is;
    ArrayList<String> dialogItems;


    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_b, container, false);
        vikaHelper = new DatabaseAdapter(getActivity());
        recyclerView = (RecyclerView) layout.findViewById(R.id.featureList);
        adapter = new RecycleViewAdapter(getActivity(), getData());
        dialogItems = new ArrayList<>();
        fr = new IssuesDialogFragment();
        repo = new IssueRepo(getActivity());
        is = repo.getIssuesByID(7);
        String s;

        for(Issue i: is){
            s = i.getIssue_description();
            dialogItems.add(s);
        }

        final CharSequence[] items = new String[dialogItems.size()];
        dialogItems.toArray(items);
        mSelectedItems = new ArrayList<>();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener1() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("Vika", "item " + position + " was clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_message)
                        .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                                if (isChecked) {
                                    mSelectedItems.add(i);
                                } else if (mSelectedItems.contains(i)) {
                                    mSelectedItems.remove(Integer.valueOf(i));
                                }
                            }
                        })
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create().show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Message.message(getActivity(), "that was a long click");
            }
        }));
        return layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void databaseSetup() {
        String[] features = {"GPS", "Time", "Map", "Signal", "Data", "Distance", "Battery",
                "Support", "Location", "Version"};
        for (String feature : features) {
            long id = vikaHelper.insertFeature(feature);
            if (id < 0) {
                Log.d("Vika", "successfully inserted feature");
            } else {
                Log.d("Vika", "Unsuccessful");

            }
        }

    }

    public static List<Feature> getData() {
        List<Feature> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_gps, R.drawable.ic_time,
                R.drawable.ic_maps, R.drawable.ic_signal, R.drawable.ic_data, R.drawable.ic_distance,
                R.drawable.ic_battery, R.drawable.ic_support, R.drawable.ic_location, R.drawable.ic_version};

        String[] titles = {"GPS", "Time", "Map", "Signal", "Data", "Distance", "Battery",
                "Support", "Location", "Version"};

        for (int i = 0; i < titles.length && i < icons.length; i++) {
            Feature current = new Feature();
            current.iconID = icons[i];
            current.featureTitle = titles[i];
            data.add(current);
        }
        return data;
    }

}
