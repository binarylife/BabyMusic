package com.beifeng.babymusic;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.beifeng.babymusic.adapter.FragmentAdapter;
import com.beifeng.babymusic.base.BaseActivity;
import com.beifeng.babymusic.entity.GankEntity;
import com.beifeng.babymusic.http.HttpFactory;
import com.beifeng.babymusic.http.HttpResult;
import com.beifeng.babymusic.http.HttpTransformer;
import com.beifeng.babymusic.ui.LocalMusicFragment;
import com.beifeng.babymusic.ui.fragment.OnlineMusicFragment;
import com.beifeng.babymusic.ui.widget.CirclePercentView;
import com.beifeng.babymusic.util.LogUtils;
import com.beifeng.babymusic.util.ToastUtils;
import java.util.List;
import rx.Subscriber;
import rx.functions.Action0;

public class MainActivity extends BaseActivity {

  private static final String TAG = "main";
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.navigation_view) NavigationView navigation_view;
  @BindView(R.id.iv_menu) ImageView ivMenu;
  @BindView(R.id.iv_search) ImageView ivSearch;
  @BindView(R.id.tv_local_music) TextView tvLocalMusic;
  @BindView(R.id.tv_online_music) TextView tvOnlineMusic;
  @BindView(R.id.viewpager) ViewPager mViewPager;
  private LocalMusicFragment localMusicFragment;
  private OnlineMusicFragment onlineMusicFragment;

  @Override protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override protected void afterCreate(Bundle savedInstanceState) {
    FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
    localMusicFragment = new LocalMusicFragment();
    onlineMusicFragment = new OnlineMusicFragment();
    adapter.addFragment(localMusicFragment);
    adapter.addFragment(onlineMusicFragment);
    mViewPager.setAdapter(adapter);
    mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override public void onPageSelected(int position) {
        if (position == 0) {
          tvLocalMusic.setSelected(true);
          tvOnlineMusic.setSelected(false);
        } else {
          tvLocalMusic.setSelected(false);
          tvOnlineMusic.setSelected(true);
        }
      }

      @Override public void onPageScrollStateChanged(int state) {

      }
    });
    tvLocalMusic.setSelected(true);

  }

  @OnClick({ R.id.iv_menu }) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.iv_menu:
        drawerLayout.openDrawer(GravityCompat.START);
        break;
    }
  }
}

//HttpFactory.getHttpApiSingleton()
//    .getCategoryData("Android", 10, 1)
//    .compose(new HttpTransformer<HttpResult<List<GankEntity>>, List<GankEntity>>())
//    .doOnSubscribe(new Action0() {
//      @Override public void call() {
//        showLoadingView();
//      }
//    })
//    .subscribe(new Subscriber<List<GankEntity>>() {
//      @Override public void onCompleted() {
//        LogUtils.d(TAG, "Completed");
//        showContentView();
//      }
//
//      @Override public void onError(Throwable e) {
//        LogUtils.d(TAG, "OnError, Error is " + e.toString());
//        showErrorView();
//      }
//
//      @Override public void onNext(List<GankEntity> gankEntities) {
//
//      }
//    });
