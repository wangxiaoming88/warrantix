package com.warrantix.main.activities.productdetail;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.activities.WalletBrandAddTechnician;
import com.warrantix.main.adapter.WalletBrandProductsDetailServiceContactsList;

import java.util.List;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class ProductsDetailServiceContacts extends BaseActivity implements LocationListener {
    ListView list;

    private LocationManager locationManager;
    private Location myLocation;

    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_products_detail_servicecontacts);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize() {

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(null);

        String[] name = {"Srinivasan M.K", "Ajay Patel", "Rahul Kumar"};
        String[] name1 = {"Sales Manager", "Sales Manager", "Sales Manager"};
        String[] name2 = {"Hero Motocop", "Godrej", "Sales India"};
        String[] distance = {"0.6km", "0.6km", "0.6km"};
        String[] price = {"Stadium Corssroads, Navrangpura", "Stadium Corssroads, Navrangpura", "Stadium Corssroads, Navrangpura"};
        String[] imageID = {"brand_product_image1", "brand_product_image2", "brand_product_image3", "brand_product_image1"};

        WalletBrandProductsDetailServiceContactsList adapter = new WalletBrandProductsDetailServiceContactsList(this, name, name1, name2, distance, price, imageID);
        list.setAdapter(adapter);

        ImageButton fabButton = (ImageButton) findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductsDetailServiceContacts.this, WalletBrandAddTechnician.class);
                startActivity(i, true);
            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation = location;
    }

    public Location getMyLocation() {
        if (myLocation == null) {
            List<String> providers = locationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return null;
                }

                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }
            myLocation = bestLocation;
        }
        return myLocation;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
//        Toast.makeText(getBaseContext(), "Enabled GPS location service.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
//        Toast.makeText(this, "Please enable GPS location service", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent, true);
    }
}
