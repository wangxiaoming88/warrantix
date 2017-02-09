package com.warrantix.main.modules.intercom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

import com.noveogroup.android.log.Log;
import com.warrantix.main.WarrantixApplication;

//
// This class is for plugin side intercommunication manager
//

//
// Usage : not checked yet
//
//       initialize connection : WarrantixIntercomMessenger.getInstance().initialize();
//       send message : WarrantixIntercomMessenger.getInstance().sendMessage(string message);
//       finalize connection : WarrantixIntercomMessenger.getInstance().finalize();

public class WarrantixIntercomMessenger
{
    //
    // same constants with WarrantixIntercomService class
    //

    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_SET_VALUE = 3;

    private static WarrantixIntercomMessenger instance = null;
    public static WarrantixIntercomMessenger getInstance() {
        if (instance == null) {
            instance = new WarrantixIntercomMessenger();
        }

        return instance;
    }

    public void initialize() {
        doBindService();
    }

    public void finalize() {
        doUnbindService();
    }

    public boolean sendMessage(String message) {
        return true;
    }


    Messenger mService = null;

    /** Flag indicating whether we have called bind on the service. */
    boolean mIsBound;

    /**
     * Handler of incoming messages from service.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SET_VALUE:
                    //
                    // handle message from server service
                    //
                    // mCallbackText.setText("Received from service: " + msg.arg1);
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    /**
     * Class for interacting with the main interface of the service.
     */
    private ServiceConnection mConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = new Messenger(service);
            Log.d("connected to warrantix intercommunication service");

            // We want to monitor the service for as long as we are
            // connected to it.
            try {
                Message msg = Message.obtain(null, MSG_REGISTER_CLIENT);
                msg.replyTo = mMessenger;
                mService.send(msg);

                // Give it some value as an example.

                //
                // how to send text message to server side.
                //
                // Bundle b = new Bundle();
                // b.putString("type", "TestClass");
                // b.putString("message", "testMEssage");
                // msg.setData(b);
                // msg.replyTo = mMessenger;
                // mService.send(msg);

                //
                // how to send int value to server side
                //
                // msg = Message.obtain(null, MSG_SET_VALUE, this.hashCode(), 0);
                // mService.send(msg);
            }
            catch (RemoteException e) {
                // In this case the service has crashed before we could even
                // do anything with it; we can count on soon being
                // disconnected (and then reconnected if it can be restarted)
                // so there is no need to do anything here.
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
            Log.d("disconnected from warrantix intercommunication service");
        }
    };

    private Context getAppContext() {
        return WarrantixApplication.getAppContext();
    }

    void doBindService() {
        //Bind to the remote service
        Intent intent = new Intent();
        intent.setClassName("com.warrantix.main", "com.warrantix.main.modules.intercom.WarrantixIntercomService");
        getAppContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if (mIsBound) {
            // If we have received the service, and hence registered with
            // it, then now is the time to unregister.
            if (mService != null) {
                try {
                    Message msg = Message.obtain(null, MSG_UNREGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    mService.send(msg);
                }
                catch (RemoteException e) {
                    // There is nothing special we need to do if the service
                    // has crashed.
                }
            }

            // Detach our existing connection.
            getAppContext().unbindService(mConnection);

            mIsBound = false;
        }
    }
}