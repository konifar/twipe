package com.konifar.twipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.konifar.twipe.R;
import com.konifar.twipe.model.pojo.TweetModel;
import com.konifar.twipe.view.fragment.TweetListFragment;

public class TweetListActivity extends BaseActivity implements TweetListFragment.TweetListListener {

  public static void start(Context context) {
    Intent intent = new Intent(context, TweetListActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tweet_list);
    addFragment(R.id.container_tweet_list, new TweetListFragment());
  }

  @Override public void onTweetClicked(TweetModel tweetModel) {
    //
  }
}
