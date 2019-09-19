package com.appnio.readinglist.extension

import android.widget.EditText


fun EditText.textString(): String {
    return text.toString()
}