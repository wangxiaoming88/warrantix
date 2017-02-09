package com.plugin.testplugin;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    protected void initialize() {
        String pluginUri = "com.warrantix.consumer";
        boolean installed = checkInstallation(pluginUri);
        if(installed)
        {
            ActivityManager activityManager = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
            List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
            boolean isRunningHost = false;
            for(int i = 0; i < procInfos.size(); i++)
            {
                String processName = procInfos.get(i).processName;
                if(processName.equals(pluginUri))
                {
                    // if host app is running, start me
                    isRunningHost = true;
                }
            }

            if (isRunningHost == false) {
                //This intent will help you to launch if the package is already installed
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(pluginUri);
                startActivity(LaunchIntent);
                finish();
            }

        } else {
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pluginUri));
            marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET|Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(marketIntent);
            finish();
        }

    }

    private boolean checkInstallation(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
