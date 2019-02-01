package com.kotlin.sample.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import java.io.Serializable

inline fun <reified T> AppCompatActivity?.startActivityWithData(vararg pairs: Pair<String, Any?>) {
    this ?: return
    this.baseContext.startActivityWithData<T>(*pairs)
}

inline fun <reified T> Fragment?.startActivityWithData(vararg pairs: Pair<String, Any?>) {
    this ?: return
    this.activity.startActivityWithData<T>(*pairs)
}

inline fun <reified T> Context?.startActivityWithData(vararg pairs: Pair<String, Any?>) {
    this ?: return
    val intent = Intent(this, T::class.java)
    fillTheIntent(intent, pairs)
    this.startActivity(intent)
}

fun fillTheIntent(intent: Intent, pairs: Array<out Pair<String, Any?>>) {
    pairs.forEach {
        val value = it.second
        when (value) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> {   // ignore
            }
        }
    }
}