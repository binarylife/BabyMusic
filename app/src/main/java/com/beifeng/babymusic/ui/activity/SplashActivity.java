package com.beifeng.babymusic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import com.beifeng.babymusic.*;
import com.beifeng.babymusic.base.BaseActivity;
import com.beifeng.babymusic.util.CountDownUtils;
import com.beifeng.babymusic.util.LogUtils;
import java.util.Calendar;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * 欢迎界面
 */
public class SplashActivity extends BaseActivity {

  public static String TAG = "splashActivity";
  @BindView(R.id.tv_copyright) TextView tv_copyright;
  @BindView(R.id.count_down) TextView count_down;

  @Override protected int getLayoutId() {
    return R.layout.activity_splash;
  }

  @Override protected void afterCreate(Bundle savedInstanceState) {
    int year = Calendar.getInstance().get(Calendar.YEAR);
    tv_copyright.setText(getString(R.string.copyright, year));

    //  开启广告倒计时
    CountDownUtils.countDown(5).doOnSubscribe(new Action0() {
      @Override public void call() {
        // 初始化工作
        LogUtils.e(TAG, "倒计时开始");
      }
    }).subscribe(new Subscriber<Integer>() {
      @Override public void onCompleted() {
        LogUtils.e(TAG, "完成");
        startMainActivity();
      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(Integer integer) {
        LogUtils.e(TAG, "当前时间" + integer);
        count_down.setText(integer+"秒");
      }
    });
  }

  /**
   * 调到mainactivity
   */
  private void startMainActivity() {
    Intent intent = new Intent();
    intent.setClass(this, MainActivity.class);
    intent.putExtras(getIntent());
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    startActivity(intent);
  }
}
