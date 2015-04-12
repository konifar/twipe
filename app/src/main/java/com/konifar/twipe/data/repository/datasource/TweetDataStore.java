package com.konifar.twipe.data.repository.datasource;

import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

public interface TweetDataStore {
  public void getHomeTweetList(long lastTweetId, int count, TweetListCallback callback);

  public void getUserTweetList(long userId, long lastTweetId, int count,
      TweetListCallback callback);

  interface TweetListCallback {
    void onTweetListLoaded(Collection<TweetModel> tweets);

    void onError(Exception exception);
  }
}
