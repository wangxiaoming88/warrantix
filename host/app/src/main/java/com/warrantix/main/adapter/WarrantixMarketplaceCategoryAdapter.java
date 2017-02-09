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

public class WarrantixMarketplaceCategoryAdapter extends ArrayAdapter<WarrantixMarketplaceCategoryAdapter.SubCategory> implements AdapterView.OnItemClickListener {

    public static class SubCategory {
        public String name;
        public int imageResource;   // temporary

        public SubCategory() {
        }
    }

    private final Activity context;
    private ArrayList<SubCategory> subCategories;
    private OnCategoryItemSelectListener itemSelectListener = null;

    public WarrantixMarketplaceCategoryAdapter(Activity context, SubCategory[] subCategories) {
        super(context, R.layout.listview_warrantix_category, subCategories);

        this.context = context;
        if (subCategories == null)
            this.subCategories = new ArrayList<>();
        else {
            this.subCategories = new ArrayList<>();
            for (int i=0; i<subCategories.length; i++)
                this.subCategories.add(subCategories[i]);
        }
    }

    public void setCategoryItemSelectListener(OnCategoryItemSelectListener listener) {
        this.itemSelectListener = listener;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;
        if ((subCategories != null) && (position < subCategories.size())) {
            if (view == null)
                rowView = inflater.inflate(R.layout.listview_warrantix_category, null, true);
            else
                rowView = view;

            TextView nameText = (TextView) rowView.findViewById(R.id.categoryNameView);
            nameText.setText(subCategories.get(position).name);

            ImageView categoryImageView = (ImageView) rowView.findViewById(R.id.categoryImageView);
            Picasso.with(this.context.getApplicationContext())
                    .load(subCategories.get(position).imageResource)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(categoryImageView);
        }
        else {
            rowView = new View(context);
        }
        return rowView;
    }

    public void addCategory(SubCategory subCategory) {
        if (this.subCategories == null)
            this.subCategories = new ArrayList<>();

        this.subCategories.add(subCategory);
    }

    public void removeCategory(int position) {
        this.subCategories.remove(position);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (subCategories == null)
            return;

        if (position >= subCategories.size())
            return;

        SubCategory subCategory = subCategories.get(position);
        removeCategory(position);
        WarrantixMarketplaceCategoryAdapter.this.notifyDataSetChanged();

        if (itemSelectListener != null)
            itemSelectListener.OnCategoryItemSelected(subCategory);
    }

    //
    // public interface
    //

    public interface OnCategoryItemSelectListener {
        public void OnCategoryItemSelected(SubCategory subCategory);
    }
}