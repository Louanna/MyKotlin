package com.kotlin.sample.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import com.kotlin.sample.location.LocationLiveData

/**
 * Created by anna on 16/01/2018.
 */
class LocationService : Service() {

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LocationLiveData.getInstance().registeredListener()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = Notification()
            startForeground(20180111, notification)
        }
        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        LocationLiveData.getInstance().removeListener()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true)
        }
        super.onDestroy()
    }

}