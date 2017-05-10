package com.beifeng.babymusic;

import android.content.Context;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by beifeng on 2017/5/9.
 * 全局application 依赖的moulds
 */
@Singleton @Component(modules = { ApplicationModule.class }) public interface ApplicationComponent {
  //   获取全局app
  BabyApplication getApplication();

  //   获取全局content
  Context getContent();
}
