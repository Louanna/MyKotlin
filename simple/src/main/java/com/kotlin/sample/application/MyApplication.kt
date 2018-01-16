package com.kotlin.sample.application

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.kotlin.sample.helper.SPHelper
import com.kotlin.sample.location.LocationLiveData
import com.kotlin.sample.sensor.SensorLiveData
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.MemoryCookieStore
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * Created by anna on 2017/12/21.
 */
class MyApplication : MultiDexApplication() {

    companion object {
        lateinit var instance : Context
        private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = applicationContext

        SPHelper.initialization(applicationContext)
        SensorLiveData.initialization(applicationContext)
        LocationLiveData.initialization(applicationContext)

        integrateOKGO()
    }

    private fun integrateOKGO() {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.INFO)
        builder.addInterceptor(loggingInterceptor)

        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)

        builder.cookieJar(CookieJarImpl(MemoryCookieStore()))

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount = 3
    }

}