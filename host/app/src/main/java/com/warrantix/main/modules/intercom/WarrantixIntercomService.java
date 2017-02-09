package com.warrantix.main.modules.intercom;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

import java.util.ArrayList;

import com.noveogroup.android.log.Log;

/**
 * Reference : https://github.com/android/platform_development/blob/master/samples/ApiDemos/src/com/example/android/apis/app/MessengerService.java
 *
 */

//
// This class is for main side intercommunication service
//


public class WarrantixIntercomService extends Service
{
    /** Keeps track of all current registered clients. */
    ArrayList<Messenger> mClients = new ArrayList<Messenger>();

    /** Holds last value set by a client. */
    int mValue = 0;

    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_SET_VALUE = 3;

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;

                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;

                case MSG_SET_VALUE:
                    //
                    // how to get text message from client side
                    //
                    // String typeMessage = msg.getData().getString("type");
                    // String jsonMessage = msg.getData().getString("message");

                    //
                    // how to get int value from client side
                    // mValue = msg.arg1;


                    //
                    // replies to all connected clients
                    //
//                    for (int i=mClients.size()-1; i>=0; i--)
//                    {
//                        try {
//                            mClients.get(i).send(Message.obtain(null, MSG_SET_VALUE, mValue, 0));
//                        }
//                        catch (RemoteException e) {
//                            // The client is dead.  Remove it from the list;
//                            // we are going through the list from back to front
//                            // so this is safe to do inside the loop.
//                            mClients.remove(i);
//                        }
//                    }

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

    @Override
    public void onCreate() {
        Log.d("WarrantixIntercomService is created");
    }

    @Override
    public void onDestroy() {
        Log.d("WarrantixIntercomService is destroyed");
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

}

//END_INCLUDE(service)