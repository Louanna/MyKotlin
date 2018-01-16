package com.ac.demo;

import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by anna on 2017/12/5.
 */

public class LocationLiveData extends LiveData<Location> {
    private static final String TAG = "LiveData";
    private LocationManager locationManager;

    private Context mContext;
    private static LocationLiveData sIns;

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "location:" + location.toString());
            setValue(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    public static LocationLiveData getInstance(Context context) {
        if (null == sIns) {
            sIns = new LocationLiveData(context.getApplicationContext());
        }
        return sIns;
    }

    private LocationLiveData(Context context) {
        mContext = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
//        minTime = SPHelper.getLong(SPHelper.SP_LOCATION_TIME, TrackConstants.LOCATION_MIN_TIME_HIGH);
//        minDistance = SPHelper.getFloat(SPHelper.SP_LOCATION_DISTANCE, TrackConstants.LOCATION_MIN_DISTANCE_HIGH);

        long minTime= 1000;
        float minDistance = 0f;

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetWorkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetWorkEnabled) {
            // no network provider is enabled
            Log.d(TAG, "false");
        } else {
            if (isNetWorkEnabled) {
                Log.d(TAG, "isNetWorkEnabled");
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, listener);
            }

            if (isGPSEnabled) {
                Log.d(TAG, "isGPSEnabled");
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, listener);
            }
        }
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
        locationManager.removeUpdates(listener);
    }
}
