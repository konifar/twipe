package com.konifar.twipe.domain.repository;

import com.konifar.twipe.domain.exception.ErrorBundle;
import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

public interface TweetRepository {
  void getHomeTweetList(int lastTweetId, TweetListCallback callback);

  void getUserTweetList(int userId, int lastTweetId, TweetListCallback callback);

  interface TweetListCallback {
    void onTweetListLoaded(Collection<TweetModel> tweets);

    void onError(ErrorBundle errorBundle);
  }
}