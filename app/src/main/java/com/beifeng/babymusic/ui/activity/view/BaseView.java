package com.beifeng.babymusic.ui.activity.view;

/**
 * Created by beifeng on 2017/5/9.
 * view interface,所有View(此项目中的View主要是Fragment和自定义的ViewGroup)必须实现此接口
 */

public interface BaseView<T> {
  void setPresenter(T presenter);

  void showLoading();// loading页面

  void showContent();//  显示内容

  void showError();//  错误页面
}
