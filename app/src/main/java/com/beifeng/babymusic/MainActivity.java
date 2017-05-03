package com.beifeng.babymusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.beifeng.babymusic.base.BaseActivity;
import com.beifeng.babymusic.entity.GankEntity;
import com.beifeng.babymusic.http.HttpFactory;
import com.beifeng.babymusic.http.HttpResult;
import com.beifeng.babymusic.http.HttpTransformer;
import com.beifeng.babymusic.ui.widget.CirclePercentView;
import com.beifeng.babymusic.util.LogUtils;
import com.beifeng.babymusic.util.ToastUtils;
import java.util.List;
import rx.Subscriber;
import rx.functions.Action0;

public class MainActivity extends BaseActivity {

  private static final String TAG = "main";
  @BindView(R.id.circleView) CirclePercentView circleView;
  //@BindView(R.id.bt_test) Button bt_test;

  @Override protected int getLayoutId() {
    return R.layout.activity_main_include;
  }

  @Override protected void afterCreate(Bundle savedInstanceState) {
    circleView.dodo(80);
  }

  //@OnClick({ R.id.bt_test }) public void onViewClicked(View view) {
  //  switch (view.getId()) {
      //case R.id.bt_loading:
      //  HttpFactory.getHttpApiSingleton()
      //      .getCategoryData("Android", 10, 1)
      //      .compose(new HttpTransformer<HttpResult<List<GankEntity>>, List<GankEntity>>())
      //      .doOnSubscribe(new Action0() {
      //        @Override public void call() {
      //          showLoadingView();
      //        }
      //      })
      //      .subscribe(new Subscriber<List<GankEntity>>() {
      //        @Override public void onCompleted() {
      //          LogUtils.d(TAG, "Completed");
      //          showContentView();
      //        }
      //
      //        @Override public void onError(Throwable e) {
      //          LogUtils.d(TAG, "OnError, Error is " + e.toString());
      //          showErrorView();
      //        }
      //
      //        @Override public void onNext(List<GankEntity> gankEntities) {
      //
      //        }
      //      });
      //  break;
    //  case R.id.bt_test:
    //    circleView.dodo(90);
    //}
  //}
}
