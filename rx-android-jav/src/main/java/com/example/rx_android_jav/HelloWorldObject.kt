package com.example.rx_android_jav

import io.reactivex.rxjava3.core.Flowable

object HelloWorldObject {
    @JvmStatic
    fun main(args: Array<String>) {
        Flowable.just("Hello world").subscribe { x: String? -> println(x) }
    }
}
