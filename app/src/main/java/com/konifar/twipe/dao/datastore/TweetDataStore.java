package com.konifar.twipe.dao.datastore;

import com.konifar.twipe.model.pojo.TweetModel;
import java.util.Collection;

public interface TweetDataStore {
  void getHomeTweetList(Long lastTweetId, int count, TweetListCallback callback);

  void getUserTweetList(long userId, Long lastTweetId, int count, TweetListCallback callback);

  interface TweetListCallback {
    void onTweetListLoaded(Collection<TweetModel> tweets);

    void onError(Exception exception);
  }
}
