package com.konifar.twipe.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import com.konifar.twipe.presentation.internal.di.PerActivity;
import com.konifar.twipe.presentation.view.LoginView;
import javax.inject.Inject;

@PerActivity
public class LoginPresenter implements Presenter {

  private static final String TAG = LoginPresenter.class.getSimpleName();

  private LoginView loginView;

  @Inject
  public LoginPresenter() {
  }

  public void setView(@NonNull LoginView view) {
    this.loginView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  public void init() {
    this.loginView.initLoginButton();
  }

  public void loginSuccess() {
    this.loginView.loginSuccessed();
  }

  public void loginFailure(Exception e) {
    Log.e(TAG, e.getMessage());
    this.loginView.showError();
  }
}
