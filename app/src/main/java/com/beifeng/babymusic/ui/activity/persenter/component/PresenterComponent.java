package com.beifeng.babymusic.ui.activity.persenter.component;

import com.beifeng.babymusic.ApplicationModule;
import com.beifeng.babymusic.MainActivity;
import com.beifeng.babymusic.ui.activity.persenter.HomePagePresenter;
import com.beifeng.babymusic.ui.activity.persenter.HomePresenter;
import dagger.Component;

/**
 *
 *         2016/12/2
 */
@Component(modules = ApplicationModule.class)
public interface PresenterComponent {

    void inject(HomePagePresenter presenter);

    //void inject(MainActivity activity);
    //
    //void inject(CityManagerPresenter presenter);
}
 