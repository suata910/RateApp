package com.sua.tavita.rateapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sua.tavita.rateapp.tables.Feature;
import com.sua.tavita.rateapp.tables.Issue;

import java.util.ArrayList;
import java.util.List;

public class FragmentB extends Fragment {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    //    private IssuesDialogFragment fr;
    private List<Integer> mSelectedItems;

    private IssueRepo repoIss;
    private FeatureRepo repoFe;
    private ArrayList<Issue> is;
    private ArrayList<String> dialogItems;
    private CharSequence[] items = null;


    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_b, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.featureList);
        adapter = new RecycleViewAdapter(getActivity(), getFeatureList());
        dialogItems = new ArrayList<>();
        mSelectedItems = new ArrayList<>();
        return layout;
    }

    public void getRelatedIssues(int featureID) {
        repoIss = new IssueRepo(getActivity());
        is = repoIss.getIssuesByID(featureID);
        items = new String[dialogItems.size()];
        String s;
        for (Issue a : is) {
            s = a.getIssue_description();
            System.out.println(s);
            dialogItems.add(s);
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                getRelatedIssues(recyclerView.getChildAdapterPosition(view));
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_message)
                        .setMultiChoiceItems(dialogItems.toArray(items), null, new DialogInterface.OnMultiChoiceClickListener() {
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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public List<Information> getFeatureList() {
        repoFe = new FeatureRepo(getActivity());
        List<Feature> features = repoFe.getFeatureList();
        List<Information> data = new ArrayList<>();

        int[] icons = {R.drawable.ic_gps, R.drawable.ic_time,
                R.drawable.ic_maps, R.drawable.ic_signal, R.drawable.ic_data, R.drawable.ic_distance,
                R.drawable.ic_battery, R.drawable.ic_support, R.drawable.ic_location, R.drawable.ic_version};

        String s;

        for (int i = 0; i < features.size() && i < icons.length; i++) {
            Information current = new Information();
            current.iconID = icons[i];
            current.title = features.get(i).feature_name;
            data.add(current);
        }
        return data;
    }

}
