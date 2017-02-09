package com.warrantix.main.adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class TokenListAdapter extends ArrayAdapter<String> implements AdapterView.OnItemClickListener {

    private final Activity context;
    private ArrayList<String> tokens;
    private OnTokenItemSelectListener itemSelectListener = null;

    public TokenListAdapter(Activity context, String[] tokens) {
        super(context, R.layout.listview_token, tokens);

        this.context = context;
        if (tokens == null)
            this.tokens = new ArrayList<>();
        else
            this.tokens = new ArrayList<String>(Arrays.asList(tokens));
    }

    public void setTokenItemSelectListener(OnTokenItemSelectListener listener) {
        this.itemSelectListener = listener;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;
        if ((tokens != null) && (position < tokens.size())) {
            rowView = inflater.inflate(R.layout.listview_token, null, true);
            TextView nameText = (TextView) rowView.findViewById(R.id.productNameView);
            nameText.setText(tokens.get(position));
        }
        else {
            rowView = new View(context);
        }
        return rowView;
    }

    public void addToken(String token) {
        if (this.tokens == null)
            this.tokens = new ArrayList<>();

        this.tokens.add(token);
    }

    public void removeToken(String token) {
//        if (this.tokens == null)
//            return;
//
//        this.tokens.remove(token);
    }

    public void removeToken(int position) {
        // this.tokens.remove(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (tokens == null)
            return;

        if (position >= tokens.size())
            return;

        String value = tokens.get(position);
        removeToken(position);
        TokenListAdapter.this.notifyDataSetChanged();

        if (itemSelectListener != null)
            itemSelectListener.onTokenItemSelected(value);
    }

    //
    // public interface
    //

    public interface OnTokenItemSelectListener {
        public void onTokenItemSelected(String token);
    }
}