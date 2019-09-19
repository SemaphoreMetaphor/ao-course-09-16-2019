package com.appnio.playground.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appnio.playground.R
import com.appnio.playground.adapter.FunAdpater


class SecondActivity : AppCompatActivity() {

    companion object {
        const val PASSWORD_KEY = "PASSWORD_KEY"
        const val USERNAME_KEY = "USERNAME_KEY"
    }

    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        recycler = findViewById(R.id.recycler)
        recycler.apply {
            layoutManager = LinearLayoutManager(
                this@SecondActivity,
                LinearLayoutManager.VERTICAL,
                false)
            adapter = FunAdpater()
        }
    }
}