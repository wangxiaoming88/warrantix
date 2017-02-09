package com.warrantix.main.modules.gcm;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;

/**********************************************************
 *
 * Reference URL : https://github.com/codepath/android_guides/wiki/Google-Cloud-Messaging
 *
 **********************************************************/

public class WarrantixIDListenerService extends InstanceIDListenerService {

    private static final String TAG = "WarrantixIDListenerService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, WarrantixRegIntentService.class);
        startService(intent);
    }
    // [END refresh_token]
}