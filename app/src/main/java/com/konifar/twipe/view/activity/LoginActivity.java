package com.konifar.twipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.konifar.twipe.R;
import com.konifar.twipe.view.fragment.LoginFragment;

public class LoginActivity extends BaseActivity implements LoginFragment.LoginListener {

  public static void start(Context context) {
    Intent intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    addFragment(R.id.container_login, new LoginFragment());
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_login);
    if (fragment != null) {
      fragment.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override public void onLoginSuccessed() {
    MainActivity.start(this);
  }
}
