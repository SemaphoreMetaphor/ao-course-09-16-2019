package com.appnio.readinglist.service

import android.content.Context


class SharedPreferenceService(context: Context) {

    companion object {
        const val IS_FIRST_LOAD = "IS_FIRST_LOAD"
    }

    val prefs = context.getSharedPreferences("book_prefs", Context.MODE_PRIVATE)

    fun isFirstLoad(): Boolean {
        return prefs.getBoolean(IS_FIRST_LOAD, true)
    }

    fun setIsFirstLoad(isFirstLoad: Boolean) {
        prefs.edit().putBoolean(IS_FIRST_LOAD, isFirstLoad).apply()
    }

}