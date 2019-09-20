package com.appnio.readinglist_v2.service

import android.content.Context
import android.content.SharedPreferences


class SharedPreferenceService(context: Context) {

    private val IS_FIRST_LAUNCH_KEY = "IS_FIRST_LAUNCH_KEY"
    val prefs: SharedPreferences = context.getSharedPreferences("book_prefs", Context.MODE_PRIVATE)

    fun setIsFirstAppLaunch(isFirst: Boolean) {
        prefs.edit().putBoolean(IS_FIRST_LAUNCH_KEY, isFirst).apply()
    }

    fun isFirstAppLaunch(): Boolean {
        return prefs.getBoolean(IS_FIRST_LAUNCH_KEY, true)
    }
}