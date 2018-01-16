package com.kotlin.sample.helper

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by anna on 2017/12/21.
 */
object SPHelper {

    private val INAVX_SETTING = "inavx_setting"
    private lateinit var sp: SharedPreferences

    fun initialization(context: Context) {
        sp = context.getSharedPreferences(INAVX_SETTING, Context.MODE_PRIVATE)
    }

    fun getInteger(key: String, defValue: Int): Int {
        return sp.getInt(key, defValue)
    }

    fun setInteger(key: String, value: Int) {
        val editor = sp.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun setFloat(key: String, value: Float) {
        val editor = sp.edit()
        editor.putFloat(key, value)
        editor.commit()
    }

    fun getFloat(key: String, defValue: Float): Float {
        return sp.getFloat(key, defValue)
    }

    fun getBoolean(key: String, bool: Boolean): Boolean {
        return sp.getBoolean(key, bool)
    }

    fun setBoolean(key: String, value: Boolean?) {
        val editor = sp.edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }

    fun getString(key: String, defValue: String): String? {
        return sp.getString(key, defValue)
    }

    fun setString(key: String, value: String) {
        val editor = sp.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getStringSet(key: String, defValues: Set<String>): Set<String>? {
        return sp.getStringSet(key, defValues)
    }

    fun setStringSet(key: String, values: Set<String>) {
        val editor = sp.edit()
        editor.putStringSet(key, values)
        editor.commit()
    }

    fun getDouble(key: String, defValue: Double): Double {
        return sp.getFloat(key, defValue.toFloat()).toDouble()
    }

    fun setDouble(key: String, value: Double) {
        val editor = sp.edit()
        editor.putFloat(key, value.toFloat())
        editor.commit()
    }

    fun getLong(key: String, defValue: Long): Long {
        return sp.getLong(key, defValue)
    }

    fun setLong(key: String, value: Long) {
        val editor = sp.edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun remove(key: String) {
        val editor = sp.edit()
        editor.remove(key)
        editor.commit()
    }

    fun hasKey(key: String): Boolean {
        return sp.contains(key)
    }
}