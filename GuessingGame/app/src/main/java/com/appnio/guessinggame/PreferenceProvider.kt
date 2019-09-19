package com.appnio.guessinggame

import android.content.Context
import android.content.SharedPreferences


class PreferenceProvider(private val context: Context) {

    private val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("guessing_game", Context.MODE_PRIVATE)


    public fun saveHighScore(highScore: Int) {
        sharedPreferences.edit().putInt(HIGH_SCORE_KEY, highScore).apply()
    }

    public fun getHighScore(): Int {
        return sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
    }
}