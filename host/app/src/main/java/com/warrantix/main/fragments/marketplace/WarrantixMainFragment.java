package com.warrantix.main.fragments.marketplace;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.activities.WalletBrandAMC0;
import com.warrantix.main.activities.WalletBrandAccessoriesEshop;
import com.warrantix.main.activities.WalletBrandFinance0;
import com.warrantix.main.activities.WalletBrandFinanceCompany;
import com.warrantix.main.activities.WalletBrandInsurance0;
import com.warrantix.main.activities.WalletMarketplaceAMC;
import com.warrantix.main.activities.WalletMarketplaceSubCategory;
import com.warrantix.main.dialog.MessageDialog;
import com.warrantix.main.fragments.BaseFragment;

public class WarrantixMainFragment extends BaseFragment {

    private ImageButton hamburgerButton = null;
    private MarketPlaceFragment parentFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_warrantix_main, container, false);

        initialize(v);
        return v;
    }

    public void setParentFragment(MarketPlaceFragment fragment) {
        parentFragment = fragment;
    }


    private void initialize(View v) {
        final View appliancesOverlayView = (View) v.findViewById(R.id.applianceOverlayView);
        final View electronicsOverlayView = (View) v.findViewById(R.id.electronicsOverlayView);
        final View vehiclesOverlayView = (View) v.findViewById(R.id.vehiclesOverlayView);
        final View fitnessOverlayView = (View) v.findViewById(R.id.fitnessOverlayView);
        final View personalOverlayView = (View) v.findViewById(R.id.personalOverlayView);
        final View assortedOverlayView = (View) v.findViewById(R.id.assortedOverlayView);
        final View amcOverlayView = (View) v.findViewById(R.id.amcOverlayView);
        final View insuranceOverlayView = (View) v.findViewById(R.id.insuranceOverlayView);
        final View warrantyOverlayView = (View) v.findViewById(R.id.warrantyOverlayView);
        final View financeOverlayView = (View) v.findViewById(R.id.financeOverlayView);

        hamburgerButton = (ImageButton) v.findViewById(R.id.popupMenu);
        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMorePopupMenu();
            }
        });

        appliancesOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentFragment != null)
                    parentFragment.setWarrantixCategoryFragment(0);
            }
        });

        electronicsOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentFragment != null)
                    parentFragment.setWarrantixCategoryFragment(1);
            }
        });

        vehiclesOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentFragment != null)
                    parentFragment.setWarrantixCategoryFragment(2);
            }
        });

        fitnessOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentFragment != null)
                    parentFragment.setWarrantixCategoryFragment(3);
            }
        });

        personalOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentFragment != null)
                    parentFragment.setWarrantixCategoryFragment(4);
            }
        });

        assortedOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parentFragment != null)
                    parentFragment.setWarrantixCategoryFragment(5);
            }
        });

        amcOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, WalletMarketplaceAMC.class);
                if (mActivity instanceof BaseActivity)
                    ((BaseActivity)mActivity).startActivity(i, true);
                else
                    mActivity.startActivity(i);
            }
        });

        insuranceOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, WalletBrandInsurance0.class);
                if (mActivity instanceof BaseActivity)
                    ((BaseActivity)mActivity).startActivity(i, true);
                else
                    mActivity.startActivity(i);
            }
        });

        warrantyOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog dialog = new MessageDialog(mActivity);
                dialog.setTitle("Releasing Shortly");
                dialog.setMessage("");
                dialog.show();
            }
        });

        financeOverlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, WalletBrandFinanceCompany.class);
                if (mActivity instanceof BaseActivity)
                    ((BaseActivity)mActivity).startActivity(i, true);
                else
                    mActivity.startActivity(i);
            }
        });
    }

    public void loadMorePopupMenu() {
        PopupMenu popupMenu = new PopupMenu(mActivity, hamburgerButton) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.menu_warrantix_usedproduct:
                        if (parentFragment != null)
                            parentFragment.setWarrantixUsedProductFragment();
                        return true;

                    case R.id.menu_warrantix_accessories:
//                        Intent i = new Intent(mActivity, WalletBrandAccessoriesEshop.class);
//                        if (mActivity instanceof BaseActivity)
//                            ((BaseActivity)mActivity).startActivity(i, true);
//                        else
//                            mActivity.startActivity(i);

                        Intent i = new Intent(mActivity, WalletMarketplaceSubCategory.class);
                        i.putExtra("title", "eSHOP ACCESSORIES");
                        if (mActivity instanceof BaseActivity)
                            ((BaseActivity) mActivity).startActivity(i, true);
                        else
                            mActivity.startActivity(i);
                        return true;

                    case R.id.menu_warrantix_spareparts:
//                        MessageDialog dialog = new MessageDialog(mActivity);
//                        dialog.setTitle("Releasing Shortly");
//                        dialog.setMessage("");
//                        dialog.show();
                        return true;

                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };

        popupMenu.inflate(R.menu.menu_popup_warrantix);

        // disable spare parts menu item
        MenuItem settingsMenuItem = popupMenu.getMenu().findItem(R.id.menu_warrantix_spareparts);
        SpannableString s = new SpannableString(settingsMenuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.wx_third_color)), 0, s.length(), 0);
        settingsMenuItem.setTitle(s);

        popupMenu.show();
    }
}