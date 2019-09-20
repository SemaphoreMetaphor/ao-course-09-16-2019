package com.appnio.readinglist.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appnio.readinglist.R
import com.appnio.readinglist.ReadingApplication
import com.appnio.readinglist.service.SharedPreferenceService
import com.appnio.readinglist_v2.InitialData
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    var timerJob: Job? = null
    lateinit var sharedPreferenceService: SharedPreferenceService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferenceService = SharedPreferenceService(this)
    }

    override fun onStart() {
        super.onStart()
        if (sharedPreferenceService.isFirstLoad()) {
            sharedPreferenceService.setIsFirstLoad(false)
            (application as ReadingApplication).repository.insertAll(InitialData.data)
        }
        timerJob = MainScope().launch {
            delay(3000)
            startMainActivity()
        }
    }

    override fun onPause() {
        super.onPause()
        timerJob?.cancel()
        startMainActivity()
    }

    fun startMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }
}