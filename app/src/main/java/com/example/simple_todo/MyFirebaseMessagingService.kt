package com.example.simple_todo

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, remoteMessage.from!!)
        Log.d(TAG, remoteMessage.notification?.body.toString())
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.d(TAG,"the refreshed token is: "+ s);
    }
}