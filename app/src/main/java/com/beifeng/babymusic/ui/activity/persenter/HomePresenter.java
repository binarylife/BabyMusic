package com.beifeng.babymusic.ui.activity.persenter;

import android.content.Context;
import com.beifeng.babymusic.ApplicationModule;
import com.beifeng.babymusic.entity.GankEntity;
import com.beifeng.babymusic.http.HttpFactory;
import com.beifeng.babymusic.http.HttpResult;
import com.beifeng.babymusic.http.HttpTransformer;
import com.beifeng.babymusic.ui.activity.contract.HomeContract;
import com.beifeng.babymusic.ui.activity.persenter.component.DaggerPresenterComponent;
import com.beifeng.babymusic.util.ActivityScoped;
import com.beifeng.babymusic.util.LogUtils;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by beifeng on 2017/5/10.
 * 首页p层
 */
@ActivityScoped public class HomePresenter implements HomeContract.Presenter {
  private CompositeSubscription subscription;
  private HomeContract.HomeView view;
  private final Context context;
  String TAG = "homePresenter";

  @Inject HomePresenter(Context context, HomeContract.HomeView view) {

    this.context = context;
    this.view = view;
    subscription = new CompositeSubscription();
    view.setPresenter(this);
    DaggerPresenterComponent.builder()
        .applicationModule(new ApplicationModule(context))
        .build()
        .inject(this);
  }

  @Override public void loadData() {
    HttpFactory.getHttpApiSingleton()
        .getCategoryData("Android", 10, 1)
        .compose(new HttpTransformer<HttpResult<List<GankEntity>>, List<GankEntity>>())
        .doOnSubscribe(new Action0() {
          @Override public void call() {
            view.showLoading();
          }
        })
        .subscribe(new Subscriber<List<GankEntity>>() {
          @Override public void onCompleted() {
            LogUtils.d(TAG, "Completed");
            view.showContent();
          }

          @Override public void onError(Throwable e) {
            LogUtils.d(TAG, "OnError, Error is " + e.toString());
            view.showError();
          }

          @Override public void onNext(List<GankEntity> gankEntities) {

          }
        });
  }

  @Override public void subscribe() {
    loadData();
  }

  @Override public void unSubscribe() {

  }
}
