package com.sua.tavita.rateapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.ListView;

import com.sua.tavita.rateapp.tables.Issue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Teuila on 19/06/15.
 */
public class IssuesDialogFragment extends DialogFragment {
    IssueRepo repo;
    ArrayList<Issue> is;
    ArrayList<String> dialogItems;
    private String s, issueList;
    private List<Integer> mSelectedItems;
    private ArrayList<String> innerList = new ArrayList<>();
    private ArrayList<ArrayList<String>> outerList = new ArrayList<>();
    private CharSequence[] items = null;

    public IssuesDialogFragment(String s, String issueList, CharSequence[] items) {
        this.s = s;
        this.issueList = issueList;
        this.items = items;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialogItems.toArray(items);

        mSelectedItems = new ArrayList<>();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        Log.d("vika", "the related issues are " + Arrays.toString(items));
        builder.setTitle(issueList)
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
        }).setCancelable(false);
        return builder.create();
    }
}
