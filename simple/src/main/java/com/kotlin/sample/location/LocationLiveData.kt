package com.kotlin.sample.location

import android.Manifest
import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log

/**
 * Created by anna on 16/01/2018.
 */
class LocationLiveData : LiveData<Location> {

    private var minTime = 1000L
    private var minDistance = 1f

    private val listener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (null != location) {
                Log.d("debug", "location=" + location.toString())
                value = location
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    companion object {

        private lateinit var mContext: Context
        private lateinit var mLocationManager: LocationManager

        fun initialization(context: Context) {
            mContext = context
            mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        private var locationLiveData: LocationLiveData? = null

        fun getInstance(): LocationLiveData {
            if (null == locationLiveData) {
                locationLiveData = LocationLiveData()
            }
            return locationLiveData!!
        }
    }

    private constructor()

    override fun onActive() {
        registeredListener()
    }

    override fun onInactive() {
        removeListener()
    }

    fun registeredListener() {
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetWorkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGPSEnabled && !isNetWorkEnabled) {
            // no network provider is enabled
        } else {
            if (isNetWorkEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, listener)
            }

            if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, listener)
            }
        }
    }

    fun removeListener() {
        mLocationManager.removeUpdates(listener)
    }

}