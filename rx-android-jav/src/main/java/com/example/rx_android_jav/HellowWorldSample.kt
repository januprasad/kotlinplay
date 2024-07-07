package com.example.rx_android_jav

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    val observable: Observable<String> = Observable.just("Hello, RxJava!")
    observable.subscribe(
        { item -> println(item) },
        { error -> println(error) },
        { println("Completed") },
    )

    observable
        .map { it.uppercase() }
        .filter { it.startsWith("H") }
        .subscribe { item -> println(item) }

    observable
        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { item -> println(item) }

    val compositeDisposable = CompositeDisposable()

    val disposable =
        observable
            .subscribe { item -> println(item) }

    compositeDisposable.add(disposable)

// Dispose all subscriptions when no longer needed
    compositeDisposable.clear()
}
