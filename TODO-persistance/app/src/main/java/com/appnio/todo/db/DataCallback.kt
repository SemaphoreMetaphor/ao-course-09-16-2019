package com.appnio.todo.db


interface DataCallback<T> {
    fun onData(data: T)
}