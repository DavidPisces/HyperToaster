package com.reiraku.hypertoaster.utils

import android.os.Build

class AndroidPlatform {
    val androidVersion: String = Build.VERSION.RELEASE
    val osVersion: String = Build.VERSION.INCREMENTAL
    val sdkVersion: String = Build.VERSION.SDK_INT.toString()
}

fun getPlatform() = AndroidPlatform()