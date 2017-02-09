package com.warrantix.main.fragments.marketplace;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.adapter.RecipientListAdapter;
import com.warrantix.main.adapter.TokenListAdapter;
import com.warrantix.main.adapter.WarrantixMarketplaceCategoryAdapter;
import com.warrantix.main.adapter.WarrantixMarketplaceSubCategoryAdapter;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.loader.plugin.PluginManager;

public class WarrantixCategoryFragment extends BaseFragment {

    protected ImageView categoryImageView;
    protected TextView titleText;
    protected RelativeLayout titleBackground;

    final public static int TYPE_APPLIANCE = 1;
    final public static int TYPE_ELECTRONICS = 2;
    final public static int TYPE_VEHICLES = 3;
    final public static int TYPE_FITNESS = 4;
    final public static int TYPE_PERSONAL = 5;
    final public static int TYPE_ASSORTED = 6;

    protected WarrantixMarketplaceCategoryAdapter subCategoryAdapter;
    protected int titleBackgroundResource;
    protected int categoryImageResource;
    protected String title;
    protected ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_warrantix_category, container, false);
        initialize(v);
        return v;
    }

    private void initialize(View v) {
        titleBackground = (RelativeLayout) v.findViewById(R.id.titleBackground);
        categoryImageView = (ImageView) v.findViewById(R.id.categoryImageView);
        titleText = (TextView) v.findViewById(R.id.titleText);
        listView = (ListView) v.findViewById(R.id.listView);

        // set title
        try {
            titleBackground.setBackgroundResource(titleBackgroundResource);
            categoryImageView.setImageResource(categoryImageResource);
        }
        catch (Exception e) {
            ;
        }
        titleText.setText(title);

        // set list view
        listView.setAdapter(subCategoryAdapter);
        listView.setDividerHeight(0);
        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
                if (mActivity instanceof BaseActivity)
                    ((BaseActivity) mActivity).startActivity(i, true);
                else
                    mActivity.startActivity(i);
            }
        });

        // notify data change
        if (subCategoryAdapter != null)
            subCategoryAdapter.notifyDataSetChanged();

        ImageButton backButton = (ImageButton) v.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity instanceof MainActivity)
                    ((MainActivity)mActivity).setMarketplaceFragment();
            }
        });
    }

    public void setType(int type) {
        if (type == TYPE_APPLIANCE) {
            configureApplianceFragment();
            refreshScreen();
        }
        else if (type == TYPE_ELECTRONICS) {
            configureElectronicsFragment();
            refreshScreen();
        }
        else if (type == TYPE_VEHICLES) {
            configureVehiclesFragment();
            refreshScreen();
        }
        else if (type == TYPE_FITNESS) {
            configureFitnessFragment();
            refreshScreen();
        }
        else if (type == TYPE_PERSONAL) {
            configurePersonalFragment();
            refreshScreen();
        }
        else if (type == TYPE_ASSORTED) {
            configureAssortedFragment();
            refreshScreen();
        }
    }

    private void configureApplianceFragment() {
        titleBackgroundResource = R.drawable.marketplace_bkg_category_appliances_large;
        categoryImageResource = R.drawable.marketplace_category_appliances;
        title = "Appliances";

        String subCategoryNames[] = {"Refrigerators", "Washing Machines", "Microwaves", "Dish Washers", "Vacuum Cleaners"};
        WarrantixMarketplaceCategoryAdapter.SubCategory subCategories[] = new WarrantixMarketplaceCategoryAdapter.SubCategory[5];
        subCategories[0] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[0].name = subCategoryNames[0];
        subCategories[0].imageResource = R.drawable.marketplace_subcategory_fridge;

        subCategories[1] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[1].name = subCategoryNames[1];
        subCategories[1].imageResource = R.drawable.marketplace_subcategory_laundry;

        subCategories[2] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[2].name = subCategoryNames[2];
        subCategories[2].imageResource = R.drawable.marketplace_subcategory_oven;

        subCategories[3] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[3].name = subCategoryNames[3];
        subCategories[3].imageResource = R.drawable.marketplace_subcategory_dishwasher;

        subCategories[4] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[4].name = subCategoryNames[4];
        subCategories[4].imageResource = R.drawable.marketplace_subcategory_vaccum;

        subCategoryAdapter = new WarrantixMarketplaceCategoryAdapter(mActivity, subCategories);
    }

    private void configureElectronicsFragment() {
        titleBackgroundResource = R.drawable.marketplace_bkg_category_electronics_large;
        categoryImageResource = R.drawable.marketplace_category_electronics;
        title = "Electronics";

        String subCategoryNames[] = {"Refrigerators", "Air conditioners", "Air Coolers", "Washing Machines", "Microwaves", "Dish Washers", "Vacuum Cleaners", "Water Purifiers"};
        WarrantixMarketplaceCategoryAdapter.SubCategory subCategories[] = new WarrantixMarketplaceCategoryAdapter.SubCategory[8];
        subCategories[0] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[0].name = subCategoryNames[0];
        subCategories[0].imageResource = R.drawable.marketplace_subcategory_fridge;

        subCategories[1] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[1].name = subCategoryNames[1];
        subCategories[1].imageResource = R.drawable.marketplace_subcategory_laundry;

        subCategories[2] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[2].name = subCategoryNames[2];
        subCategories[2].imageResource = R.drawable.marketplace_subcategory_oven;

        subCategories[3] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[3].name = subCategoryNames[3];
        subCategories[3].imageResource = R.drawable.marketplace_subcategory_dishwasher;

        subCategories[4] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[4].name = subCategoryNames[4];
        subCategories[4].imageResource = R.drawable.marketplace_subcategory_vaccum;

        subCategories[5] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[5].name = subCategoryNames[5];
        subCategories[5].imageResource = R.drawable.marketplace_subcategory_laundry;

        subCategories[6] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[6].name = subCategoryNames[6];
        subCategories[6].imageResource = R.drawable.marketplace_subcategory_oven;

        subCategories[7] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[7].name = subCategoryNames[7];
        subCategories[7].imageResource = R.drawable.marketplace_subcategory_dishwasher;

        subCategoryAdapter = new WarrantixMarketplaceCategoryAdapter(mActivity, subCategories);
    }

    private void configureVehiclesFragment() {
        titleBackgroundResource = R.drawable.marketplace_bkg_category_vehicles_large;
        categoryImageResource = R.drawable.marketplace_category_vehicles;
        title = "Vehicles";

        String subCategoryNames[] = {"Mobile Phones", "Televisions", "Laptops", "Printers", "Computers & Accessories"};
        WarrantixMarketplaceCategoryAdapter.SubCategory subCategories[] = new WarrantixMarketplaceCategoryAdapter.SubCategory[5];
        subCategories[0] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[0].name = subCategoryNames[0];
        subCategories[0].imageResource = R.drawable.marketplace_subcategory_fridge;

        subCategories[1] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[1].name = subCategoryNames[1];
        subCategories[1].imageResource = R.drawable.marketplace_subcategory_laundry;

        subCategories[2] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[2].name = subCategoryNames[2];
        subCategories[2].imageResource = R.drawable.marketplace_subcategory_oven;

        subCategories[3] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[3].name = subCategoryNames[3];
        subCategories[3].imageResource = R.drawable.marketplace_subcategory_dishwasher;

        subCategories[4] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[4].name = subCategoryNames[4];
        subCategories[4].imageResource = R.drawable.marketplace_subcategory_vaccum;

        subCategoryAdapter = new WarrantixMarketplaceCategoryAdapter(mActivity, subCategories);
    }

    private void configureFitnessFragment() {
        titleBackgroundResource = R.drawable.marketplace_bkg_category_fitness_large;
        categoryImageResource = R.drawable.marketplace_category_fitness;
        title = "Fitness";

        String subCategoryNames[] = {"Treadmill", "Exercise Bikes", "Elliptical Trainers", "Home Gym", "Rowers, Steppers & Twisters", "Dumbbells"};
        WarrantixMarketplaceCategoryAdapter.SubCategory subCategories[] = new WarrantixMarketplaceCategoryAdapter.SubCategory[6];
        subCategories[0] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[0].name = subCategoryNames[0];
        subCategories[0].imageResource = R.drawable.marketplace_subcategory_fridge;

        subCategories[1] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[1].name = subCategoryNames[1];
        subCategories[1].imageResource = R.drawable.marketplace_subcategory_laundry;

        subCategories[2] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[2].name = subCategoryNames[2];
        subCategories[2].imageResource = R.drawable.marketplace_subcategory_oven;

        subCategories[3] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[3].name = subCategoryNames[3];
        subCategories[3].imageResource = R.drawable.marketplace_subcategory_dishwasher;

        subCategories[4] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[4].name = subCategoryNames[4];
        subCategories[4].imageResource = R.drawable.marketplace_subcategory_vaccum;

        subCategories[5] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[5].name = subCategoryNames[5];
        subCategories[5].imageResource = R.drawable.marketplace_subcategory_vaccum;

        subCategoryAdapter = new WarrantixMarketplaceCategoryAdapter(mActivity, subCategories);
    }

    private void configurePersonalFragment() {
        titleBackgroundResource = R.drawable.marketplace_bkg_category_personal_large;
        categoryImageResource = R.drawable.marketplace_category_personal;
        title = "Personal";

        String subCategoryNames[] = {"Watches", "Jewelry", "Shavers", "Message and Relaxation", "Travel Products"};
        WarrantixMarketplaceCategoryAdapter.SubCategory subCategories[] = new WarrantixMarketplaceCategoryAdapter.SubCategory[5];
        subCategories[0] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[0].name = subCategoryNames[0];
        subCategories[0].imageResource = R.drawable.marketplace_subcategory_fridge;

        subCategories[1] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[1].name = subCategoryNames[1];
        subCategories[1].imageResource = R.drawable.marketplace_subcategory_laundry;

        subCategories[2] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[2].name = subCategoryNames[2];
        subCategories[2].imageResource = R.drawable.marketplace_subcategory_oven;

        subCategories[3] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[3].name = subCategoryNames[3];
        subCategories[3].imageResource = R.drawable.marketplace_subcategory_dishwasher;

        subCategories[4] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[4].name = subCategoryNames[4];
        subCategories[4].imageResource = R.drawable.marketplace_subcategory_vaccum;

        subCategoryAdapter = new WarrantixMarketplaceCategoryAdapter(mActivity, subCategories);
    }

    private void configureAssortedFragment() {
        titleBackgroundResource = R.drawable.marketplace_bkg_category_assorted_large;
        categoryImageResource = R.drawable.marketplace_category_assorted;
        title = "Assorted";

        String subCategoryNames[] = {"Home Products", "Kitchen Products", "Solar Products", "Garden Products"};
        WarrantixMarketplaceCategoryAdapter.SubCategory subCategories[] = new WarrantixMarketplaceCategoryAdapter.SubCategory[4];
        subCategories[0] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[0].name = subCategoryNames[0];
        subCategories[0].imageResource = R.drawable.marketplace_subcategory_fridge;

        subCategories[1] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[1].name = subCategoryNames[1];
        subCategories[1].imageResource = R.drawable.marketplace_subcategory_laundry;

        subCategories[2] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[2].name = subCategoryNames[2];
        subCategories[2].imageResource = R.drawable.marketplace_subcategory_oven;

        subCategories[3] = new WarrantixMarketplaceCategoryAdapter.SubCategory();
        subCategories[3].name = subCategoryNames[3];
        subCategories[3].imageResource = R.drawable.marketplace_subcategory_dishwasher;

        subCategoryAdapter = new WarrantixMarketplaceCategoryAdapter(mActivity, subCategories);
    }

    private void refreshScreen() {
        // set title
        try {
            if (titleBackground != null)
                titleBackground.setBackgroundResource(titleBackgroundResource);

            if (categoryImageView != null)
                categoryImageView.setImageResource(categoryImageResource);
        }
        catch (Exception e) {
            ;
        }

        if (titleText != null)
            titleText.setText(title);

        // set list view
        if (listView != null) {
            listView.setAdapter(subCategoryAdapter);
            listView.setDividerHeight(0);
            listView.setDivider(null);
        }

        // notify data change
        if (subCategoryAdapter != null)
            subCategoryAdapter.notifyDataSetChanged();
    }
}