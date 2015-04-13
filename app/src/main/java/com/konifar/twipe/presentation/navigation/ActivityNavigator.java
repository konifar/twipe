package com.konifar.twipe.presentation.navigation;

import android.content.Context;
import com.konifar.twipe.presentation.view.activity.TweetListActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class ActivityNavigator {

  @Inject public ActivityNavigator() {
  }

  public void navigateToTweetList(Context context) {
    if (context != null) {
      TweetListActivity.start(context);
    }
  }

  public void navigateToTweet(Context context, long tweetId) {
    if (context != null) {
      // TODO
    }
  }
}
