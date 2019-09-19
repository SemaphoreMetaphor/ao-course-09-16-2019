package com.appnio.readinglist.db


interface DataCallback<T> {
    fun onDataLoaded(data: T)
}