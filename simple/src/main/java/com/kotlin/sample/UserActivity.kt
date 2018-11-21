package com.kotlin.sample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlin.sample.helper.SPHelper
import com.kotlin.sample.viewmodel.Injection
import com.kotlin.sample.viewmodel.VMApp

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        var intent = getIntent()
        Log.d("debug", intent.getStringExtra("first_key"))


        var mVMFactory = Injection.provideViewModelFactory(this)
        var mVMApp = ViewModelProviders.of(this, mVMFactory).get(VMApp::class.java)

        mVMApp.isShowNightView().observe(this, Observer { isShow ->
            Log.d("debug", "UisShow=" + isShow)
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        VMApp.showNightView!!.value = false
        SPHelper.setBoolean("showNightView", VMApp.showNightView!!.value)
    }
}
