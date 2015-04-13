package com.konifar.twipe.presentation.view;

import android.content.Context;

public interface LoginView {
  void initLoginButton();

  void loginSuccessed();

  void showError();

  Context getContext();
}
