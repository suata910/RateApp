package com.sua.tavita.rateapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sua.tavita.rateapp.tables.Feature;
import com.sua.tavita.rateapp.tables.Issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentB extends Fragment {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    //    private IssuesDialogFragment fr;
    private String s;
    private List<Integer> mSelectedItems;

    private IssueRepo repoIss;
    private FeatureRepo repoFe;
    private ArrayList<Issue> is;
    private ArrayList<String> dialogItems;
    private CharSequence[] items = null;
    private ArrayList<String> innerList = new ArrayList<>();
    private ArrayList<ArrayList<String>> outerList = new ArrayList<>();
    private List<Information> data;


    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_b, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.featureList);
        adapter = new RecycleViewAdapter(getActivity(), getFeatureList());
//        dialogItems = new ArrayList<>();
        mSelectedItems = new ArrayList<>();
        return layout;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                s = getFeatureList().get(position).getTitle();
                getRelatedIssues(s);

                Log.d("vika", "the related issues are " + Arrays.toString(items));
                builder.setTitle(data.get(recyclerView.getChildAdapterPosition(view)).getTitle())
                        .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                                if (isChecked) {

                                    mSelectedItems.add(i);
//                                    innerList.add((String)items[i]);
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
                                ListView lw = ((AlertDialog) dialog).getListView();
                                SparseBooleanArray checkedItems = lw.getCheckedItemPositions();
                                if (checkedItems != null) {
                                    for (int i = 0; i < checkedItems.size(); i++) {
                                        if (checkedItems.valueAt(i)) {
                                            String item = lw.getAdapter().getItem(
                                                    checkedItems.keyAt(i)).toString();
//                                            Log.i("vika",item + " was selected");
                                            innerList.add(item);
//                                            Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
//                                            Message.message(getActivity(), item);
                                        }
                                    }
                                }
                                outerList.add(innerList);
                                for (ArrayList<String> list_a : outerList) {
                                    for (String s : list_a)
                                        Log.d("vika", "selected issues " + s + "\n");
                                }


                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).setCancelable(false).create().show();
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

    public void getRelatedIssues(String name) {
        dialogItems = new ArrayList<>();
        repoIss = new IssueRepo(getActivity());
        is = repoIss.getIssuesByID(name);
        String w = null;

        for (Issue a : is) {
            w = a.getIssue_description();
            System.out.println(w);
            dialogItems.add(w);
        }
        items = dialogItems.toArray(new String[dialogItems.size()]);
    }

    public List<Information> getFeatureList() {
        repoFe = new FeatureRepo(getActivity());
        List<Feature> features = repoFe.getFeatureList();
        data = new ArrayList<>();

        int[] icons = {R.drawable.ic_gps, R.drawable.ic_time,
                R.drawable.ic_maps, R.drawable.ic_signal, R.drawable.ic_data, R.drawable.ic_distance,
                R.drawable.ic_battery, R.drawable.ic_support, R.drawable.ic_location, R.drawable.ic_version};

        String s;

        for (int i = 0; i < features.size() && i < icons.length; i++) {
            Information current = new Information();
            current.setIconID(icons[i]);
            current.setTitle(features.get(i).getFeature_name());
//            Log.d("vika", "the title of the current issue is " + current.getTitle());
            data.add(current);
        }
        return data;
    }

}
