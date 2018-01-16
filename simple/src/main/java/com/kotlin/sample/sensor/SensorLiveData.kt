package com.kotlin.sample.sensor

import android.arch.lifecycle.LiveData
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

/**
 * Created by anna on 15/01/2018.
 */
class SensorLiveData : LiveData<FloatArray>() {
    private var accelerometerValues = FloatArray(3)
    private var magneticFieldValues = FloatArray(3)
    private var gyroscopeValues = FloatArray(3)

    companion object {
        private lateinit var mSensorManager: SensorManager
        private lateinit var mAccelerometerSensor: Sensor
        private lateinit var mMagneticFieldSensor: Sensor
        private lateinit var mGyroscopeSensor: Sensor
        private lateinit var mBarometricSensor: Sensor

        fun initialization(context: Context) {
            mSensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            mMagneticFieldSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            mGyroscopeSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            mBarometricSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
        }

        private var sensorLiveData: SensorLiveData? = null
        fun getInstance(): SensorLiveData {
            if (null == sensorLiveData) {
                sensorLiveData = SensorLiveData()
            }
            return sensorLiveData!!
        }
    }

    private val myListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {

            when (sensorEvent.sensor.type) {
                Sensor.TYPE_MAGNETIC_FIELD -> {
                    magneticFieldValues = sensorEvent.values
                    setValue(magneticFieldValues)
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    accelerometerValues = sensorEvent.values
                    setValue(accelerometerValues)
                }
                Sensor.TYPE_GYROSCOPE -> gyroscopeValues = sensorEvent.values
                Sensor.TYPE_PRESSURE -> setValue(sensorEvent.values)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    override fun onActive() {
        mSensorManager.registerListener(myListener, mAccelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(myListener, mMagneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(myListener, mBarometricSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onInactive() {
        mSensorManager.unregisterListener(myListener)
    }
}