package com.konifar.twipe.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.konifar.twipe.R;
import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.presentation.internal.di.HasComponent;
import com.konifar.twipe.presentation.internal.di.components.DaggerTweetComponent;
import com.konifar.twipe.presentation.internal.di.components.TweetComponent;
import com.konifar.twipe.presentation.internal.di.modules.TweetModule;
import com.konifar.twipe.presentation.view.fragment.TweetListFragment;

public class TweetListActivity extends BaseActivity
    implements HasComponent<TweetComponent>, TweetListFragment.TweetListListener {

  private TweetComponent tweetComponent;

  public static void start(Context context) {
    Intent intent = new Intent(context, TweetListActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tweet_list);

    initInjector();
    addFragment(R.id.container_tweet_list, new TweetListFragment());
  }

  private void initInjector() {
    this.tweetComponent = DaggerTweetComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .tweetModule(new TweetModule())
        .build();
  }

  @Override public TweetComponent getComponent() {
    return tweetComponent;
  }

  @Override public void onTweetClicked(TweetModel tweetModel) {
    this.activityNavigator.navigateToTweet(this, tweetModel.getId());
  }
}
