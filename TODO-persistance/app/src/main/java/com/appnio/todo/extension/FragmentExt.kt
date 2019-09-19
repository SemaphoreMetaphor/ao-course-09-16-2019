package com.appnio.todo.extension

import androidx.fragment.app.Fragment
import com.appnio.todo.TodoApplication
import com.appnio.todo.activity.MainActivity
import com.appnio.todo.db.Repository


fun Fragment.getRepository(): Repository {
    return (activity?.application as TodoApplication).repository
}

fun Fragment.addFragment(fragment: Fragment, addToBackStack: Boolean) {
    (activity as MainActivity).addFragment(fragment, addToBackStack)
}

fun Fragment.popBackStack() {
    (activity as MainActivity).supportFragmentManager.popBackStack()
}