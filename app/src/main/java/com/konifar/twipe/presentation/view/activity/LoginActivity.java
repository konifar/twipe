package com.konifar.twipe.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.konifar.twipe.R;
import com.konifar.twipe.presentation.internal.di.HasComponent;
import com.konifar.twipe.presentation.internal.di.components.DaggerLoginComponent;
import com.konifar.twipe.presentation.internal.di.components.LoginComponent;
import com.konifar.twipe.presentation.view.fragment.LoginFragment;

public class LoginActivity extends BaseActivity
    implements HasComponent<LoginComponent>, LoginFragment.LoginListener {

  private LoginComponent loginComponent;

  public static void start(Context context) {
    Intent intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    initInjector();
    addFragment(R.id.container_login, new LoginFragment());
  }

  private void initInjector() {
    this.loginComponent = DaggerLoginComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public LoginComponent getComponent() {
    return this.loginComponent;
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_login);
    if (fragment != null) {
      fragment.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override public void onLoginSuccessed() {
    this.activityNavigator.navigateToTweetList(this);
  }
}
