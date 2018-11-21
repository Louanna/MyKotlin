package com.kotlin.sample.extensions

import android.util.Log
import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        Log.d("debug","$value has been assigned to '${property.name}' in $thisRef.")
    }
}