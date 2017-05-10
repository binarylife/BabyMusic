package com.beifeng.babymusic.ui.activity.component;

import android.app.Activity;
import com.beifeng.babymusic.ApplicationComponent;
import com.beifeng.babymusic.ApplicationModule;
import com.beifeng.babymusic.MainActivity;
import com.beifeng.babymusic.ui.activity.module.HomeModule;
import com.beifeng.babymusic.util.ActivityScoped;
import dagger.Component;

/**
 * Created by beifeng on 2017/5/9.
 */
@ActivityScoped
@Component(modules = HomeModule.class, dependencies = ApplicationComponent.class)
public interface HomeComponent {
  void inject(MainActivity activity);
}
