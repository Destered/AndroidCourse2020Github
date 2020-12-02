package com.dester.androidcourse2020github.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationActionService:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(context != null && intent != null){
            context.sendBroadcast(Intent("TRACKS_TRACK").putExtra("actioname",intent.action))
        } else{
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
        }

    }

}