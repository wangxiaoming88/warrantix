package com.warrantix.main.activities.brandlist;

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
import com.warrantix.main.adapter.WalletBrandListLocalDealList;

import java.util.List;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListLocalDeals extends BaseActivity  implements LocationListener {
    ListView list;
    private LocationManager locationManager;
    private Location myLocation;

    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_localdeals);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(null);

        String[] name = { "Karizma ZMR", "Hunk", "Karizma ZMR","Hunk" , "Hunk", "Karizma ZMR","Hunk" , "Hunk", "Karizma ZMR","Hunk" };
        String[] name1 = { "Hero Motocop", "Hero Motocop", "Hero Motocop","Hero Motocop", "Hero Motocop", "Hero Motocop","Hero Motocop", "Hero Motocop", "Hero Motocop","Hero Motocop"};
        String[] name2 = { "Zero Motorcycles", "CF Moto", "Zero Motorcycles", "CF Moto", "CF Moto", "Zero Motorcycles", "CF Moto", "CF Moto", "Zero Motorcycles", "CF Moto" };
        String[] name3 = { "Stadium Crossroads,Navrangpura", "Shyamal Crossroads,Satellite", "Stadium Crossroads,Navrangpura", "Shyamal Crossroads,Satellite"
                , "Shyamal Crossroads,Satellite", "Stadium Crossroads,Navrangpura", "Shyamal Crossroads,Satellite"
                , "Shyamal Crossroads,Satellite", "Stadium Crossroads,Navrangpura", "Shyamal Crossroads,Satellite" };
        String[] distance = { "0.6 km", "1.2 km", "0.6 km", "1.2 km" , "1.2 km", "0.6 km", "1.2 km" , "1.2 km", "0.6 km", "1.2 km" };
        String[] percent = { "15% off", "20% off", "15% off", "20% off" , "20% off", "15% off", "20% off" , "20% off", "15% off", "20% off" };
        String[] price = { "Rs. 53,000", "Rs. 60,000", "Rs. 53,000", "Rs. 60,000" , "Rs. 60,000", "Rs. 53,000", "Rs. 60,000" , "Rs. 60,000", "Rs. 53,000", "Rs. 60,000" };
        String[] imageID = { "brand_product_image1","brand_product_image2", "brand_product_image3","brand_product_image1"
                ,"brand_product_image2", "brand_product_image3","brand_product_image1","brand_product_image2", "brand_product_image3","brand_product_image1"};

        WalletBrandListLocalDealList adapter = new WalletBrandListLocalDealList(this, name, name1, name2,name3, distance, percent, price,imageID);
        list.setAdapter(adapter);

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
    public void onLocationChanged(Location location) {
        myLocation = location;
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
