package com.example.rxjava;




import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaSample {
    public static void main(String[] args) {
        System.out.println("Hello");
//        new RxJavaSample().justObservable();
        new RxJavaSample().rangeRepeat();
//        new RxJavaSample().intervalOperater();

    }

    private void intervalOperater() {
        Observable<Integer> numberObservable= Observable.range(1,2);

        numberObservable.interval(10, TimeUnit.SECONDS);

//        try {
//            Thread.sleep(600000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        numberObservable.subscribe(oberverCreation());

    }

    private Observer<Integer> oberverCreation() {
        Observer<Integer> numberObserver=new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer inetger) {
                System.out.println("\n"+inetger+"");
//                Log.d(TAG, "onNext: interval: " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return numberObserver;
    }

    private void rangeRepeat() {
        Observable<Integer> numberObservable= Observable.range(6,2)
                                                         .repeat(3);
        numberObservable.subscribe(oberverCreation());
    }

    private  void justObservable() {
        Observable<Integer> numberObservable= Observable.just(1,2,3,4);
        numberObservable.subscribe(oberverCreation());
    }
}