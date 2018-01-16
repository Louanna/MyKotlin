package com.kotlin.sample.utils

import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout

/**
 * Created by anna on 2017/12/14.
 */
object AppUtils {

    fun showSnackbar(view: View, message: String, action: String?, listener: View.OnClickListener?) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val mView = snackbar.view
        mView.setPadding(10, 20, 10, 20)
        val mParams = mView.layoutParams as FrameLayout.LayoutParams
        mParams.width = AppBarLayout.LayoutParams.MATCH_PARENT
        if (!TextUtils.isEmpty(action)) {
            snackbar.setAction(action, listener)
        }
        snackbar.show()
    }
}