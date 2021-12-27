package com.ahmed.terminal

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.d("myLog", "I am started")

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("myLog", "I am destroyed")
    }
}