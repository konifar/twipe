package com.konifar.twipe.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.konifar.twipe.R;
import com.konifar.twipe.presentation.internal.di.components.LoginComponent;
import com.konifar.twipe.presentation.presenter.LoginPresenter;
import com.konifar.twipe.presentation.view.LoginView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import javax.inject.Inject;

public class LoginFragment extends BaseFragment implements LoginView {
  private static final String TAG = LoginFragment.class.getSimpleName();

  @Inject LoginPresenter loginPresenter;
  @InjectView(R.id.btn_twitter_login) TwitterLoginButton btnTwitterLogin;

  private LoginListener loginListener;

  public LoginFragment() {
    super();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login, container, false);
    ButterKnife.inject(this, view);

    return view;
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof LoginListener) {
      this.loginListener = (LoginListener) activity;
    }
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    this.init();
  }

  @Override public void onResume() {
    super.onResume();
    this.loginPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.loginPresenter.pause();
  }

  private void init() {
    this.getComponent(LoginComponent.class).inject(this);
    this.loginPresenter.setView(this);
    this.loginPresenter.init();
  }

  @Override public void initLoginButton() {
    btnTwitterLogin.setCallback(new Callback<TwitterSession>() {
      @Override
      public void success(Result<TwitterSession> result) {
        loginPresenter.loginSuccess();
      }

      @Override
      public void failure(TwitterException e) {
        loginPresenter.loginFailure(e);
      }
    });
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    btnTwitterLogin.onActivityResult(requestCode, resultCode, data);
  }

  @Override public void loginSuccessed() {
    loginListener.onLoginSuccessed();
  }

  @Override public void showError() {
    Log.e("hogehoge", "showError");
  }

  @Override public Context getContext() {
    return getActivity().getApplicationContext();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }

  public interface LoginListener {
    void onLoginSuccessed();
  }
}
