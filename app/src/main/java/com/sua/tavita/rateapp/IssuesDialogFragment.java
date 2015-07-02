package com.sua.tavita.rateapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.sua.tavita.rateapp.tables.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teuila on 19/06/15.
 */
public class IssuesDialogFragment extends DialogFragment {
    List<Integer> mSelectedItems;
    IssueRepo repo;
    ArrayList<Issue> is;
    ArrayList<String> dialogItems;
    Issue a;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        repo = new IssueRepo(getActivity());
        is = repo.getIssuesByID("GPS");
        a = new Issue();
        String s = "";
        for(Issue i: is){
            s = i.getIssue_description();
            dialogItems.add(s);
        }


        CharSequence[] items = new String[dialogItems.size()];
        dialogItems.toArray(items);

        mSelectedItems = new ArrayList<>();

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
                        return;
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                return;
            }
        });

        return builder.create();
    }
}
