package com.warrantix.main.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;

public class SearchMarketPlaceGridAdapter extends BaseAdapter {

    private Context context;
    private final int[] pluginResourceIDs;

    public SearchMarketPlaceGridAdapter(Context context, int[] pluginResourceIDs) {
        this.context = context;
        this.pluginResourceIDs = pluginResourceIDs;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView != null) {
            gridView = convertView;
        }
        else {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.gridcell_marketplace_plugin, null);
        }

        int row = position / 3;

        // set value into textview
        TextView textNumberView = (TextView) gridView.findViewById(R.id.textNumberView);
        TextView defaultTextView = (TextView) gridView.findViewById(R.id.defaultTextView);
        ImageView pluginImageView = (ImageView) gridView.findViewById(R.id.pluginImageView);

        pluginImageView.setVisibility(View.VISIBLE);
        textNumberView.setVisibility(View.GONE);
        defaultTextView.setVisibility(View.GONE);

        pluginImageView.setImageResource(pluginResourceIDs[position]);
        gridView.setBackgroundResource(R.drawable.default_layout_selector);

        return gridView;
    }

    @Override
    public int getCount() {
        return pluginResourceIDs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}