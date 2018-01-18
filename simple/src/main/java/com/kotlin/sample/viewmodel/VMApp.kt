package com.kotlin.sample.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import com.kotlin.sample.helper.SPHelper

/**
 * Created by anna on 18/01/2018.
 */
class VMApp : ViewModel() {

    companion object {
        var showNightView: MutableLiveData<Boolean> = MutableLiveData()
    }

    fun isShowNightView(): MutableLiveData<Boolean> {
        showNightView.value = SPHelper.getBoolean("showNightView", false)
        return showNightView!!
    }

}