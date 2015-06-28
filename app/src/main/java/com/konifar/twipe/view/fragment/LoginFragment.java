package com.konifar.twipe.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.konifar.twipe.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginFragment extends BaseFragment {

  @InjectView(R.id.btn_twitter_login) TwitterLoginButton btnTwitterLogin;

  private LoginListener loginListener;

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
    initLoginButton();
  }

  private void initLoginButton() {
    btnTwitterLogin.setCallback(new Callback<TwitterSession>() {
      @Override public void success(Result<TwitterSession> result) {
        loginListener.onLoginSuccessed();
      }

      @Override public void failure(TwitterException e) {
        Log.e("hogehoge", "showError");
      }
    });
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    btnTwitterLogin.onActivityResult(requestCode, resultCode, data);
  }

  public interface LoginListener {
    void onLoginSuccessed();
  }
}
