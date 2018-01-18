package com.kotlin.sample

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.observability.ui.VMUser
import com.example.android.observability.ui.VMFactory
import com.kotlin.sample.application.MyApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.os.Build
import android.view.View
import com.kotlin.sample.ui.Injection
import com.kotlin.sample.service.LocationService
import com.kotlin.sample.ui.VMAddress
import com.kotlin.sample.ui.VMBook

class MainActivity : AppCompatActivity() {

    private val mDisposable = CompositeDisposable()

    private lateinit var mVMUser: VMUser
    private lateinit var mVMAddress: VMAddress
    private lateinit var mVMBook: VMBook
    private lateinit var VMFactory: VMFactory

    private fun updateUserName() {
        val userName = et_name.text.toString()
        bt_save.isEnabled = false
        mDisposable.add(mVMUser.updateUserName(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ bt_save.isEnabled = true },
                        { throwable -> Log.e(TAG, "Unable to update username", throwable) }))
    }

    private fun getUser() {
        bt_save.isEnabled = false
        mDisposable.add(mVMUser.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    bt_save.isEnabled = true
                    Log.e(TAG, "user=" + user.toString())
                }, { throwable -> Log.e(TAG, "Unable to get user", throwable) }))
    }

    private fun getAddress() {
        bt_save.isEnabled = false
        mDisposable.add(mVMAddress.getAllAddress()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ address ->
                    bt_save.isEnabled = true
                    Log.e(TAG, "address=" + address.toString())
                }, { throwable -> Log.e(TAG, "Unable to get address", throwable) }))
    }

    private fun insertAddress() {
        bt_save.isEnabled = false
        mDisposable.add(mVMAddress.insertAddress()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    bt_save.isEnabled = true
                    Log.e(TAG, "success=")
                }, { throwable -> Log.e(TAG, "Unable to insert address", throwable) }))
    }

    private fun insertBook() {
        bt_save.isEnabled = false
        mDisposable.add(mVMBook.insertBook()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    bt_save.isEnabled = true
                    Log.e(TAG, "insert book success")
                }, { throwable -> Log.e(TAG, "Unable to insert book", throwable) }))
    }

    private fun getAllBook() {
        bt_save.isEnabled = false
        mDisposable.add(mVMBook.getAllBook()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ books ->
                    bt_save.isEnabled = true
                    Log.e(TAG, "size=" + books.size)
                    Log.e(TAG, "books=" + books.toString())
                }, { throwable -> Log.e(TAG, "Unable to get book", throwable) }))
    }

    private fun loadUserAndBook() {
        bt_save.isEnabled = false
        mDisposable.add(mVMUser.loadUserAndBook()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ books ->
                    bt_save.isEnabled = true
                    Log.e(TAG, "size=" + books.size)
                    Log.e(TAG, "books=" + books.toString())
                    Log.e(TAG, "books=" + books.get(0).books.toString())
                }, { throwable -> Log.e(TAG, "Unable to get book", throwable) }))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_name.text = "abc"

        bt_save.setOnClickListener {
            //            updateUserName()
//            getUser()
//            getAddress()
//            insertAddress()
//            insertBook()
            loadUserAndBook()
        }

        VMFactory = Injection.provideViewModelFactory(this)
        mVMUser = ViewModelProviders.of(this, VMFactory).get(VMUser::class.java)
        mVMAddress = ViewModelProviders.of(this, VMFactory).get(VMAddress::class.java)
        mVMBook = ViewModelProviders.of(this, VMFactory).get(VMBook::class.java)

//        SensorLiveData.getInstance().observe(this, Observer {
//            for (item: Float in it!!.asIterable())
//                Log.d("debug", "${item}")
//        })

//        val animation = ValueAnimator.ofFloat(0f, 100f)
//        animation.duration = 3000
//        animation.start()

//        animation.addUpdateListener { updatedAnimation ->
//            // You can use the animated value in a property that uses the
//            // same type as the animation. In this case, you can use the
//            // float value in the translationX property.
//            val animatedValue = updatedAnimation.animatedValue as Float
//            tv_name.text = ""+animatedValue
//        }

//        val animation = ObjectAnimator.ofFloat(tv_name, "rotation", 0f, 360f)
//        animation.duration = 3000
//        animation.start()

//        val animX = ObjectAnimator.ofFloat(tv_name, "x", 50f)
//        val animY = ObjectAnimator.ofFloat(tv_name, "y", 100f)
//        val animSetXY = AnimatorSet()
//        animSetXY.playTogether(animX, animY)
//        animSetXY.start()

//        val pvhX = PropertyValuesHolder.ofFloat("x", 50f)
//        val pvhY = PropertyValuesHolder.ofFloat("y", 100f)
//        ObjectAnimator.ofPropertyValuesHolder(tv_name, pvhX, pvhY).start()

//        hideBottomUIMenu()

//        PermissionUtils.requestPermission(this, object : PermissionUtils.PermissionsCallback {
//            override fun onSuccess() {
////                LocationLiveData.getInstance().observe(this@MainActivity, Observer {
////                    Log.d("debug", "it=" + "${it.toString()}")
////                })
//
//                startLocationService()
//            }
//        }, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION)

//        var xmlManager = XMLManager()
//        var xmlData = xmlManager.getData(this)
//        for (data in xmlData) {
//            for (key in data.keys) {
//                Log.d("debug", "value=" + data.get(key))
//            }
//        }

    }

    override fun onDestroy() {
        stopLocationService()
        super.onDestroy()
    }

    private var myService: Intent? = null

    fun startLocationService() {
        myService = Intent(MyApplication.context, LocationService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            MyApplication.context.startForegroundService(myService)
        } else {
            MyApplication.context.startService(myService)
        }
    }

    private fun stopLocationService() {
        if (null != myService) {
            MyApplication.context.stopService(myService)
        }
    }

    fun hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }

    override fun onStart() {
        super.onStart()
        mDisposable.add(mVMUser.getUserName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.tv_name.text = it },
                        { error -> Log.e(TAG, "Unable to get username", error) }))
    }

    override fun onStop() {
        super.onStop()
        mDisposable.clear()
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
