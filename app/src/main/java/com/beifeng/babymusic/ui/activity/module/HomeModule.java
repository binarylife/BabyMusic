package com.beifeng.babymusic.ui.activity.module;

import com.beifeng.babymusic.ui.activity.contract.HomeContract;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by beifeng on 2017/5/9.
 * 此处用来初始化 @Inject   HomePresenter(Context context, HomePageContract.View view)中的view
 */
@Module public class HomeModule {

  private HomeContract.HomeView view;

  public HomeModule(HomeContract.HomeView view) {
    this.view = view;
  }

  @Provides HomeContract.HomeView provideHomeContractView() {
    return view;
  }
}
