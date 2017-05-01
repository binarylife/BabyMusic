package com.beifeng.babymusic.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.internal.schedulers.NewThreadScheduler;

/**
 * Created by beifeng on 2017/5/1.
 * rxjava实现了倒计时功能的工具类
 */

public class CountDownUtils {
  public static Observable<Integer> countDown(int time) {
    if (time < 0) {
      time = 0;
    }
    final int countTime = time;
    //   倒计时的observeable
    return Observable.interval(0, 1, TimeUnit.SECONDS)
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Func1<Long, Integer>() {
          @Override public Integer call(Long increaseTime) {
            return countTime - increaseTime.intValue();
          }
        }).take(countTime + 1);
  }
}
