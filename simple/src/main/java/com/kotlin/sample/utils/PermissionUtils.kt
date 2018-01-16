package com.kotlin.sample.utils

import android.app.Activity
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by anna on 2017/12/14.
 */
object PermissionUtils {

    fun requestPermission(activity: Activity, vararg permission: String,
                          callback: PermissionsCallback) {
        RxPermissions(activity)
                .request(*permission)
                .subscribe({ aBoolean ->
                    if (aBoolean!!) {
                        callback.onSuccess()
                    }
                }, {
                    //onError
                }, {
                    //OnComplete
                })
    }

    fun requestPermission(activity: Activity, view: View, vararg permission: String,
                          callback: PermissionsCallback) {
        RxView.clicks(view)
                .compose(RxPermissions(activity).ensureEach(*permission))
                .subscribe({ permission ->
                    if (permission.granted) {

                        callback.onSuccess()

                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                        AppUtils.showSnackbar(view, "Denied permission without ask never again",
                                null, null)
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                        AppUtils.showSnackbar(view, "Need to go to the settings",
                                null, null)
                    }
                }, {
                    //onError
                }
                ) {
                    //OnComplete
                }
    }

    interface PermissionsCallback {
        fun onSuccess()
    }
}