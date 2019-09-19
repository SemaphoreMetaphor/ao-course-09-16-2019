package com.appnio.readinglist_v2.extension

import androidx.fragment.app.Fragment
import com.appnio.readinglist_v2.activity.MainActivity


fun Fragment.replaceFragment(fragment: Fragment, addToBackstack: Boolean) {
    activity?.let {
        (it as MainActivity).replaceFragment(fragment, addToBackstack)
    }
}