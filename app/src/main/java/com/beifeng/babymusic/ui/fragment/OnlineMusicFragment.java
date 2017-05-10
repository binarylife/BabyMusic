package com.beifeng.babymusic.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.beifeng.babymusic.R;
import com.beifeng.babymusic.base.BaseFragment;
import com.beifeng.babymusic.ui.activity.contract.HomeContract;

/**
 * Created by beifeng on 2017/5/5.
 */

public class OnlineMusicFragment extends BaseFragment implements HomeContract.HomeView {

  @BindView(R.id.rv_list) RecyclerView rv_list;
  private HomeContract.Presenter presenter;

  @Override protected int getLayoutId() {
    return R.layout.fragment_localmusic;
  }

  @Override protected void afterCreate(Bundle savedInstanceState) {

  }

  @Override public void onResume() {
    super.onResume();
    presenter.subscribe();
  }

  @Override public void setPresenter(HomeContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override public void showLoading() {
    showLoadingView();
  }

  @Override public void showContent() {
    showContentView();
  }

  @Override public void showError() {
    showErrorView();
  }

  @Override public void displayData() {

  }
}
