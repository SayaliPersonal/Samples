package com.example.rxjava

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

public fun main(args: Array<String>) {


//    var (a, b) = readLine()!!.split(' ') // !! this operator use for NPE(NullPointerException).
//

    println("1.Hot Observable")
    println("2.Cold Observable")
    println("3.Defer Observable")
    println("4.fromCallable Observable")
    println("Enter choice:")
    val (a) = readLine()!!.split('\n')
    val x: Int? = a.toInt()
//    println("Hello, ${args[0]}!")
    when (x) {
        1 -> {
            ObservableTypes().createHotObservable()


        }
        2 -> {
            ObservableTypes().createColdObservable();

        }
        3 -> {
            ObservableTypes().deferObservable();

        }  4 -> {
        ObservableTypes().fromCallableObservable();

    }
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }




}

class ObservableTypes {

    fun createColdObservable() {
        val numberObservable = Observable.just(1, 2, 3, 4)
        numberObservable.subscribe(observerCreation())
    }

    fun createHotObservable() {
        val numberObservable = Observable.just(1, 2, 3, 4).publish()
        println("Observer 1")
        numberObservable.subscribe(observerCreation())
        numberObservable.connect()
        println("Observer 2")

        numberObservable.subscribe(observerCreation())
    }

    private fun observerCreation(): Observer<Int> {
        return object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(inetger: Int) {
                println(inetger)
            }

            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }

    fun deferObservable() {
        var start: Int = 5;
        var count: Int = 2;
        Observable.range(start, count)
        val observable = Observable.defer {
            println("New Observable is created with start = $start and count = $count")
            Observable.range(start, count)
        }
        observable.subscribe(observerCreation())
        count = 3
        observable.subscribe(observerCreation())
//        observable.subscribe { item: Int -> println("Observer 2: $item") }
    }


    fun fromCallableObservable() {
        var observable = Observable.fromCallable {
            println("Calling Method")
            getNumber()
        }

        observable.subscribe(observerCreation())
    }

    private fun getNumber(): Int {
        println("Generating Value")
        return 3 * 2
    }
}

