package com.chenyong.testdemo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.chenyong.testdemo.adapter.RecycleViewAdapter;
import com.example.chenyong.testdemo.R;
import com.chenyong.testdemo.adapter.OnItemClickListener;
import com.example.chenyong.testdemo.databinding.ActivityRxJavaBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaActivity extends AppCompatActivity {
    private ActivityRxJavaBinding binding;
    private List<String> dataList = new ArrayList<>();
    private RecycleViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java);
        dataList.add("组合操作符");
        dataList.add("concat");
        dataList.add("concatArray");
        dataList.add("merge");
        dataList.add("concatArrayDelayError");
        dataList.add("zip");
        dataList.add("combineLatest");
        dataList.add("reduce");
        dataList.add("collect");
        dataList.add("startWith");
        dataList.add("count");
        dataList.add("功能操作符");
        dataList.add("delay");
        dataList.add("doOnEach");
        dataList.add("doOnNext");
        dataList.add("doAfterNext");
        dataList.add("doOnDispose");
        dataList.add("doOnLifecycle");
        dataList.add("doOnTerminate");
        dataList.add("doFinally");
        adapter = new RecycleViewAdapter(this, dataList, onItemClickListener);
        binding.ry.setLayoutManager(new LinearLayoutManager(this));
        binding.ry.setAdapter(adapter);

    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int tag) {
            switch (dataList.get(tag).toString()) {
                case "concat":
                    concat();
                    break;
                case "concatArray":
                    concatArray();
                    break;
                case "merge":
                    merge();
                    break;
                case "concatArrayDelayError":
                    concatArrayDelayError();
                    break;
                case "zip":
                    zip();
                    break;
                case "combineLatest":
                    combineLatest();
                    break;
                case "reduce":
                    reduce();
                    break;
                case "count":
                    count();
                    break;
                case "startWith":
                    startWith();
                    break;
                case "collect":
                    collect();
                    break;
                case "delay":
                    delay();
                    break;
                case "doOnEach":
                    doOnEach();
                    break;
                case "doOnNext":
                    doOnNext();
                    break;
                case "doAfterNext":
                    doAfterNext();
                    break;
                case "doOnDispose":
                    doOnDispose();
                    break;
                case "doOnLifecycle":
                    doOnLifecycle();
                    break;
                case "doOnTerminate":
                    doOnTerminate();
                    break;
                case "doFinally":
                    doFinally();
                    break;


            }
        }
    };

    /**
     * 在所有事件发送完毕之后回调该方法。
     */
    private void doFinally() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doFinally(new Action() {
            @Override
            public void run() throws Exception {
                Log.d("","doFinally");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "==================onNext ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d("", "==================onComplete ");
            }
        });
    }

    /**
     * doOnTerminate 是在 onError 或者 onComplete 发送之前回调，而 doAfterTerminate 则是 onError 或者 onComplete 发送之后回调。
     */
    private void doOnTerminate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnTerminate(new Action() {
            @Override
            public void run() throws Exception {
                Log.d("","doOnTerminate");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "==================onNext ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "==================onError ");

            }

            @Override
            public void onComplete() {
                Log.d("", "==================onComplete ");

            }
        });
    }

    /**
     * 在回调 onSubscribe 之前回调该方法的第一个参数的回调方法，可以使用该回调方法决定是否取消订阅。
     */
    private void doOnLifecycle() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnLifecycle(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                disposable.dispose();
                Log.d("", "doOnLifecycle accept");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d("", "doOnLifecycle action");
            }
        }).subscribe(new Observer<Integer>() {
            Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "==================onSubscribe ");
                this.d = d;

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "==================onNext " + integer);
//                d.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d("", "==================onComplete ");

            }
        });
    }

    /**
     * 当调用 Disposable 的 dispose() 之后回调该方法。
     */
    private void doOnDispose() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Log.d("", "doOnDipose");
            }
        }).subscribe(new Observer<Integer>() {
            Disposable d;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "onSubercribe");
                this.d = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "onNext " + integer);
                d.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "onError");
            }

            @Override
            public void onComplete() {
                Log.d("", "onComplete");
            }
        });
    }

    /**
     * Observable 每发送 onNext() 之后都会回调这个方法。
     */
    private void doAfterNext() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("你");
                emitter.onNext("好");
                emitter.onNext("!");
                emitter.onComplete();
            }
        }).doAfterNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("", "doAfterNext:" + s);
            }
        }).subscribe(new Observer<String>() {
                         @Override
                         public void onSubscribe(Disposable d) {
                             Log.d("", "onSubscribe");
                         }

                         @Override
                         public void onNext(String s) {
                             Log.d("", "onNext:" + s);
                         }

                         @Override
                         public void onError(Throwable e) {
                             Log.d("", "onError");
                         }

                         @Override
                         public void onComplete() {
                             Log.d("", "onComplete");

                         }
                     }
        );
    }

    /**
     * Observable 每发送 onNext() 之前都会先回调这个方法。
     */
    private void doOnNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("", "doOnNext:" + integer);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "onNext,integer" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d("", "==================onComplete ");
            }
        });
    }

    /**
     * Observable 每发送一件事件之前都会先回调这个方法。
     */
    private void doOnEach() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).doOnEach(new Consumer<Notification<Integer>>() {
            @Override
            public void accept(Notification<Integer> integerNotification) throws Exception {
                Log.d("", "===========doOnEach" + integerNotification.getValue());
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "==================onSubscribe ");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "==================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "==================onError ");
            }

            @Override
            public void onComplete() {
                Log.d("", "==================onComplete ");
            }
        });
    }

    /**
     * 延迟一段事件发送事件。
     */
    private void delay() {
        Observable.just(1, 2, 3).delay(2, TimeUnit.SECONDS).subscribe(
                new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("", "=======================onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("", "integer " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("", "=======================onComplete");
                    }
                }
        );

    }

    /**
     * 返回被观察者发送事件的数量。
     */
    private void count() {
        Observable.just(1, 2, 3).count().subscribe(
                new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("", "aLong " + aLong);
                    }
                }
        );
    }

    /**
     * 在发送事件之前追加事件，startWith() 追加一个事件，startWithArray() 可以追加多个事件。追加的事件会先发出。
     */
    private void startWith() {
        Observable.just(5, 6, 7).startWith(4).startWithArray(1, 2, 3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("", "integer " + integer);
            }
        });
    }

    /**
     * 将数据收集到数据结构当中。
     */
    private void collect() {
        Observable.just(1, 2, 3, 4).collect(new Callable<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> call() {
                return new ArrayList<>();
            }
        }, new BiConsumer<ArrayList<Integer>, Integer>() {
            @Override
            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                integers.add(integer);
            }
        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                Log.d("", "accept " + integers);
            }
        });

    }

    /**
     * 与 scan() 操作符的作用也是将发送数据以一定逻辑聚合起来，这两个的区别在于 scan() 每处理一次数据就会将事件发送给观察者，
     * 而 reduce() 会将所有数据聚合在一起才会发送事件给观察者。
     */
    private void reduce() {
        Observable.just(0, 1, 2, 3).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                int res = integer + integer2;
                Log.d("", "integer" + integer);
                Log.d("", "integer2" + integer2);
                Log.d("", "res" + res);
                return res;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("", "accept" + integer);
            }
        });
    }

    /**
     * combineLatest() 的作用与 zip() 类似，但是 combineLatest() 发送事件的序列是与发送的时间线有关的，
     * 当 combineLatest() 中所有的 Observable 都发送了事件，
     * 只要其中有一个 Observable 发送事件，这个事件就会和其他 Observable 最近发送的事件结合起来发送，
     */
    private void combineLatest() {
        Observable.combineLatest(Observable.intervalRange(1, 4, 1, 1, TimeUnit.SECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) {
                        String s1 = "A" + aLong;
                        Log.d("", "====================A发送的事件" + s1);
                        return s1;
                    }
                }), Observable.intervalRange(1, 5, 2, 2, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) {
                String s2 = "B" + aLong;
                Log.d("", "==================B发送的事件" + s2);
                return s2;
            }
        }), new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + s2;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "===================onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.d("", "===================最终接收到的事件 " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "===================onError ");
            }

            @Override
            public void onComplete() {
                Log.d("", "===================onComplete ");
            }
        });
    }

    /**
     * 会将多个被观察者合并，根据各个被观察者发送事件的顺序一个个结合起来，最终发送的事件数量会与源 Observable 中最少事件的数量一样。
     */
    private void zip() {
        Observable.zip(Observable.intervalRange(1, 5, 1, 1, TimeUnit.SECONDS)
                        .map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) throws Exception {
                                String s1 = "A" + aLong;
                                Log.d("", "================A 发送的事件" + s1);
                                return s1;
                            }
                        }),
                Observable.intervalRange(1, 6, 1, 1, TimeUnit.SECONDS).map(
                        new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) {
                                String s2 = "B" + aLong;
                                Log.d("", "===============B 发送的事件" + s2);
                                return s2;
                            }
                        }
                ),
                new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) {
                        String res = s + s2;
                        return res;
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("", "===================onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.d("", "===================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "===================onError ");
            }

            @Override
            public void onComplete() {
                Log.d("", "===================onComplete ");
            }
        });

    }

    /**
     * 在 concatArray() 和 mergeArray() 两个方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件，
     * 如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，就可以使用 concatArrayDelayError() 和 mergeArrayDelayError()
     */
    private void concatArrayDelayError() {
        Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                emitter.onNext(1);
                emitter.onError(new NumberFormatException());
            }
        }), Observable.just(1, 2, 3)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "===================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("", "===================onError ");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 这个方法用 concat() 作用基本一样，知识 concat() 是串行发送事件，而 merge() 并行发送事件。
     */
    private void merge() {
        Observable.merge(Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                return "A" + aLong;
            }
        }), Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                return "B" + aLong;
            }
        })).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d("", "=====================onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    /**
     * 将多个观察者组合在一起，然后按照之前发送顺序发送事件。需要注意的是，concat() 最多只可以发送4个事件。
     */
    private void concat() {
        Observable.concat(Observable.just(1, 2), Observable.just(3, 4), Observable.just(5, 6), Observable.just(7, 8)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 与 concat() 作用一样，不过 concatArray() 可以发送多于 4 个被观察者。
     */
    private void concatArray() {
        Observable.concatArray(Observable.just(1, 2), Observable.just(3, 4)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("", "================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
