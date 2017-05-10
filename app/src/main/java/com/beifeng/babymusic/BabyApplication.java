package com.beifeng.babymusic;

import android.app.Application;
import android.os.Handler;
import android.os.StrictMode;
import com.beifeng.babymusic.util.LogUtils;

/**
 * Created by bei on 2017/4/17.
 * 自定义Application
 */

public class BabyApplication extends Application {

  private static BabyApplication mContext;

  private static Handler mMainThreadHandler;
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();

    mContext = this;
    mMainThreadHandler = new Handler();

    //设置是否打印日志
    LogUtils.setIsLog(BuildConfig.LOG_DEBUG);
    //  debug严苛模式
    if (android.support.design.BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(
          new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    //  初始化dragger2 applicationComponent
    applicationComponent =DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
  }

  public static BabyApplication getApplication() {
    return mContext;
  }

  public static Handler getMainThreadHandler() {
    return mMainThreadHandler;
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
