package com.appnio.bullseye

import android.content.Context

class PreferenceService(private val context: Context) {

    private val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"

    val sharedPreferences = context.getSharedPreferences("bullseye", Context.MODE_PRIVATE)

    fun getHighScore() = sharedPreferences.getInt(HIGH_SCORE_KEY, 0)

    fun saveHighScore(score: Int) {
        sharedPreferences.edit().putInt(HIGH_SCORE_KEY, score).apply()
    }
}