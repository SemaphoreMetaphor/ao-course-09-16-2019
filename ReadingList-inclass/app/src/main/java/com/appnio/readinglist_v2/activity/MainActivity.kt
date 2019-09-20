package com.appnio.readinglist_v2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appnio.readinglist_v2.R
import com.appnio.readinglist_v2.fragment.BookListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(BookListFragment.newInstance(), false)
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager
            .beginTransaction()

        if (addToBackStack) {
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit()
    }
}
