package com.appnio.todo_v2.extension

import androidx.fragment.app.Fragment
import com.appnio.todo_v2.TodoApplication
import com.appnio.todo_v2.activity.MainActivity
import com.appnio.todo_v2.db.Repository


fun Fragment.addFragment(fragment: Fragment, addToBackStack: Boolean) {
    (activity as MainActivity).addFragment(fragment, addToBackStack)
}

fun Fragment.getRepository(): Repository {
    return (activity?.application as TodoApplication).repository
}

fun Fragment.popBackstack() {
    activity?.supportFragmentManager?.popBackStack()
}