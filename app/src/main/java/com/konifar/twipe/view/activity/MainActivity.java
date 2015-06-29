package com.konifar.twipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.konifar.twipe.R;
import com.konifar.twipe.view.fragment.TweetListFragment;
import com.twitter.sdk.android.Twitter;

public class MainActivity extends BaseActivity {

  @InjectView(R.id.toolbar) Toolbar toolbar;

  public static void start(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    setSupportActionBar(toolbar);

    if (Twitter.getSessionManager().getActiveSession() == null) {
      LoginActivity.start(this);
      finish();
    } else {
      if (savedInstanceState == null) {
        addFragment(R.id.container, new TweetListFragment());
      }
    }
  }
}
