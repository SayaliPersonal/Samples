package com.example.rxjava

import com.sun.corba.se.impl.orbutil.closure.Future
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.functions.Functions
import io.reactivex.schedulers.TestScheduler
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


public fun main(args: Array<String>) {
    println("0.FromArray Operator")
    println("1.Filter Operator")
    println("2.Distinct Operator")
    println("3.Take & Takewhile")
    println("4.Buffer Operator")
    println("5.Debounce Operator")
    println("6.Map Operator")
    println("7.FlatMap Operator")
    println("8.Concat Operator")
    println("9.SwitchMap Operator")
    println("10.FromFuture ")
    println("11.Flowable ")
    println("Enter choice:")
    val (a) = readLine()!!.split('\n')
    val x: Int? = a.toInt()
    when (x) {
        0 -> {
            OperatorTypes().fromArrayOperater()
        }
        1 -> {
            OperatorTypes().filterOperator()
        }
        2 -> {
            OperatorTypes().distinctOperator();
        }
        3 -> {
            OperatorTypes().take_takewhileOperator();
        }
        4 -> {
            OperatorTypes().bufferOperator();
        }
        5 -> {
            OperatorTypes().debounceOperator();
        }
        6 -> {
            OperatorTypes().map();
        }
        7 -> {
            OperatorTypes().flatMap();
        }
        8 -> {
        OperatorTypes().concatMap();
    }  9 -> {
        OperatorTypes().switchMap();
    } 11 -> {
        OperatorTypes().flowable();
    }
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }


}

class OperatorTypes {
    val list= listOf(1,2,3,4);
    fun fromArrayOperater()
    {
        val numberObservable= Observable.fromArray(list)
        numberObservable.subscribe(observerCreationList())


    }
    fun filterOperator() {
            val numberObservable = Observable.just(1, 2, 3, 4, 6, 7, 5)
            numberObservable.filter { item: Int -> item % 2 == 0 }
                    .subscribe(observerCreation())
    }

    fun distinctOperator() {
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
        numberObservable.subscribe(observerCreation())
        println();
        numberObservable.distinct()
                .subscribe(observerCreation())
    }

    fun take_takewhileOperator() {

        println("Take");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
        numberObservable.subscribe(observerCreation())
        println();
        numberObservable.take(3)
                .subscribe(observerCreation())

        println("\nTakeWhile");
        val numberObservable1 = Observable.just(1, 2, 3, 4, 5, 7, 5)
        numberObservable1.subscribe(observerCreation())
        println();
        numberObservable1.takeWhile { item: Int -> item <= 5 }
                .subscribe(observerCreation())
    }


    private fun observerCreation(): Observer<Int> {
        return object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(inetger: Int) {
                print("\t" + inetger)
            }

            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }

    fun bufferOperator() {
        println("Buffer");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
                .buffer(3)
        numberObservable.subscribe(observerCreationList())
    }

    fun debounceOperator() {
        println("Debounce");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
                .debounce(10, TimeUnit.MICROSECONDS)
        numberObservable.subscribe(observerCreation())
    }

    fun map() {
        println("MAP");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 6)
                .map { item: Int -> item * 2 }
                .subscribe(observerCreation())
    }

    fun flatMap() {
        println("FlatMAP");
        val numberObservable1 = Observable.just(1)
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
                .flatMap { item -> Observable.just<Int>(item + 10) }
                .subscribe(observerCreation())
    }

    fun concatMap() {
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 6 )
                .concatMap{  item -> Observable.just<Int>(item + 10) }
        numberObservable   .subscribe(observerCreation())
    }
    fun switchMap() {
        var list= listOf("a", "b", "c", "d", "e", "f");


        val scheduler = TestScheduler()
        var numberObservable=Observable.fromIterable(list)
                .switchMap({ item ->
                    val delay = Random().nextInt(10)
                    Observable.just<String>(item + "x")
                            .delay(delay.toLong(), TimeUnit.SECONDS, scheduler)
                })

        .subscribe { item: String -> println("Observer 1: $item") }
        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)

    }

//    @JvmStatic
//    fun main(args: Array<String>) {
//        // it prints from start up to (start + count - 1)
//        val observable = Observable.defer {
//            println("New Observable is created with start = " + Main.start + " and count = " + Main.count)
//            Observable.range(Main.start, Main.count)
//        }
//        observable.subscribe { item: Int -> println("Observer 1: $item") }
//        Main.count = 3
//        observable.subscribe { item: Int -> println("Observer 2: $item") }
//    }
//@Throws(Exception::class)
//open fun switchMap(): Unit {
//    val items: List<String> = Lists.newArrayList("a", "b", "c", "d", "e", "f")
//    val scheduler = TestScheduler()
//    Observable.from(items)
//            .switchMap({ s ->
//                val delay = Random().nextInt(10)
//                Observable.just<String>(s.toString() + "x")
//                        .delay(delay.toLong(), TimeUnit.SECONDS, scheduler)
//            })
//            .toList()
//            .doOnNext(System.out::println)
//            .subscribe()
//    scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
//}

    private fun observerCreationList(): Observer<List<Int>> {
        return object : Observer<List<Int>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: List<Int>) {
                println(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        }
    }

    private fun observerCreationListString(): Observer<List<String>> {
        return object : Observer<List<String>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: List<String>) {
                println(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        }
    }

//    fun fromFutureObservable() {
//        val f: FutureTask<Any> = FutureTask<Any>(Functions.EMPTY_RUNNABLE, "Hi")
//        Observable.fromFuture<Any>(f, null)
//        val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
//
//        val future: Future<> = executor.schedule({ "Hello world!" }, 1, TimeUnit.SECONDS)
// var future1:Future<String>=ex
//        val observable = Observable.fromFuture<String>(future)
//
//        observable.subscribe(
//                { item: String? -> println(item) },
//                { error: Throwable -> error.printStackTrace() }
//        ) { println("Done") }
//
//        executor.shutdown()
//
//        Observable.fromFuture(future, 1, TimeUnit.SECONDS);
//    }


    fun flowable() {
        val observable = Observable
                .just(1, 2, 3, 4, 5)

        val flowable = observable.toFlowable(BackpressureStrategy.BUFFER)

        val backToObservable = flowable.toObservable()
        backToObservable.subscribe(observerCreation())
    }
    private fun getNumber(): Int {
        println("Generating Value")
        return 3*2
    }


}
