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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.fragments.BaseFragment;
import com.warrantix.main.loader.plugin.PluginManager;

public class WarrantixUsedProductFragment extends BaseFragment {

    private MarketPlaceFragment parentFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_warrantix_usedproduct, container, false);

        initialize(v);
        return v;
    }

    private void initialize(View v) {
        ImageButton hamburgerButton = (ImageButton) v.findViewById(R.id.popupMenu);
        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog dialog = new MessageDialog(mActivity);
                dialog.setTitle("Releasing Shortly");
                dialog.setMessage("");
                dialog.show();
            }
        });

        LinearLayout bestElectronic1 = (LinearLayout) v.findViewById(R.id.bestElectronics1);
        LinearLayout bestElectronic2 = (LinearLayout) v.findViewById(R.id.bestElectronic2);
        LinearLayout bestElectronic3 = (LinearLayout) v.findViewById(R.id.bestElectronics3);
        LinearLayout bestAppliance1 = (LinearLayout) v.findViewById(R.id.bestAppliances1);
        LinearLayout bestAppliance2 = (LinearLayout) v.findViewById(R.id.bestAppliance2);
        LinearLayout bestAppliance3 = (LinearLayout) v.findViewById(R.id.bestAppliance3);
        LinearLayout bestVehicle1 = (LinearLayout) v.findViewById(R.id.bestVehicle1);
        LinearLayout bestVehicle2 = (LinearLayout) v.findViewById(R.id.bestVehicle2);
        LinearLayout bestVehicle3 = (LinearLayout) v.findViewById(R.id.bestVehicle3);

        LinearLayout products_appliances = (LinearLayout) v.findViewById(R.id.products_appliances);
        LinearLayout products_vehicles = (LinearLayout) v.findViewById(R.id.products_vehicles);
        LinearLayout products_electronics = (LinearLayout) v.findViewById(R.id.products_electronics);
        LinearLayout products_assorted = (LinearLayout) v.findViewById(R.id.products_assorted);

        products_appliances.setOnClickListener(productClickListener);
        products_vehicles.setOnClickListener(productClickListener);
        products_electronics.setOnClickListener(productClickListener);
        products_assorted.setOnClickListener(productClickListener);

        bestElectronic1.setOnClickListener(productClickListener);
        bestElectronic2.setOnClickListener(productClickListener);
        bestElectronic3.setOnClickListener(productClickListener);
        bestAppliance1.setOnClickListener(productClickListener);
        bestAppliance2.setOnClickListener(productClickListener);
        bestAppliance3.setOnClickListener(productClickListener);
        bestVehicle1.setOnClickListener(productClickListener);
        bestVehicle2.setOnClickListener(productClickListener);
        bestVehicle3.setOnClickListener(productClickListener);

        ImageButton backButton = (ImageButton) v.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity instanceof MainActivity)
                    ((MainActivity)mActivity).setMarketplaceFragment();
            }
        });
    }

    private final View.OnClickListener productClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
            i.putExtra("title", "USED PRODUCT LIST");
            if (mActivity instanceof BaseActivity)
                ((BaseActivity) mActivity).startActivity(i, true);
            else
                mActivity.startActivity(i);
        }
    };

}