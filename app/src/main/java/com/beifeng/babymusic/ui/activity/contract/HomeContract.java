package com.beifeng.babymusic.ui.activity.contract;

import com.beifeng.babymusic.base.BasePresenter;
import com.beifeng.babymusic.ui.activity.view.BaseView;

/**
 * Created by beifeng on 2017/5/9.
 * main页面 统一管理view和Presenter 的类
 */

public interface HomeContract {
  interface HomeView extends BaseView<Presenter> {
    void displayData();
  }

  interface Presenter extends BasePresenter {
    void loadData();
  }
}