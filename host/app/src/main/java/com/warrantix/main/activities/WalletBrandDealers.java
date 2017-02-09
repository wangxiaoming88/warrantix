package com.warrantix.main.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;

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
import android.widget.TextView;
import android.widget.Toast;

import com.warrantix.main.R;
import com.warrantix.main.adapter.WalletBrandDealersList;

import java.util.List;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandDealers extends BaseActivity implements LocationListener {
    private ListView list;
    private TextView dealersplaceTEXT;
    private TextView dealerschangeTEXT;
    private TextView title, explain;

    private LocationManager locationManager;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_dealers);
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
        dealersplaceTEXT = (TextView) findViewById(R.id.dealersplace);
        dealerschangeTEXT = (TextView) findViewById(R.id.dealerschange);
        title = (TextView) findViewById(R.id.title);
        explain = (TextView) findViewById(R.id.explain);

        dealersplaceTEXT.setPaintFlags(dealersplaceTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        dealerschangeTEXT.setPaintFlags(dealerschangeTEXT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(null);

        //String[] name = new String[3],name1 = new String[3],days = new String[3],imageID = new String[3];
        String[] name = {"Zero Motocycles", "Indian Motocycles", "CF Motor"};
        String[] distance = {"0.6", "0.8", "1.2"};
        String[] place = {"stadium corssroads", "shyamal corssroads", "stadium corssroads"};
        String[] place1 = {"navrangpura", "navrangpura", "navrangpura"};
        String[] imageID = {"wallet_products_products1", "wallet_products_products2", "wallet_products_products2"};

        WalletBrandDealersList adapter = new WalletBrandDealersList(this, name, distance, place, place1, imageID);
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
        Toast.makeText(getBaseContext(), "Enabled GPS location service.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please enable GPS location service", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent, true);
    }
}
