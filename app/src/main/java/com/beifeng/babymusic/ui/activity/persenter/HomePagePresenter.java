package com.beifeng.babymusic.ui.activity.persenter;

import android.content.Context;
import android.nfc.Tag;
import android.widget.Toast;
import com.beifeng.babymusic.ApplicationModule;
import com.beifeng.babymusic.ui.activity.contract.HomeContract;
import com.beifeng.babymusic.ui.activity.persenter.component.DaggerPresenterComponent;
import com.beifeng.babymusic.util.ActivityScoped;
import com.beifeng.babymusic.util.LogUtils;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com ==>> baronzhang.com)
 */
@ActivityScoped
public final class HomePagePresenter implements HomeContract.Presenter {

    private final Context context;
    private final HomeContract.HomeView weatherView;

    private CompositeSubscription subscriptions;


    @Inject HomePagePresenter(Context context, HomeContract.HomeView view) {

        this.context = context;
        this.weatherView = view;
        this.subscriptions = new CompositeSubscription();
        weatherView.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void subscribe() {
        //String cityId = PreferenceHelper.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getId(), "");
        loadData();
    }


    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    @Override public void loadData() {
        LogUtils.d("123","请求网络");
    }
}
