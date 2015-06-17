package com.sua.tavita.rateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Teuila on 11/06/15.
 */


public class ListViewAdapter extends BaseAdapter {
    Context context;
//    private LayoutInflater inflater;
    List<AppFeature> list;

    public ListViewAdapter(Context context, List<AppFeature> data) {
        this.list = data;
        this.context = context;
//        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.custom_row, viewGroup, false);
        TextView title = (TextView) row.findViewById(R.id.listText);
        ImageView icon = (ImageView) row.findViewById(R.id.listIcon);

        AppFeature temp = list.get(i);
        title.setText(temp.title);
        icon.setImageResource(temp.img);
        return row;
    }

}
