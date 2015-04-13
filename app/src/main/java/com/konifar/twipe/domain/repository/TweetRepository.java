package com.konifar.twipe.domain.repository;

import com.konifar.twipe.domain.exception.ErrorBundle;
import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

public interface TweetRepository {
  void getHomeTweetList(Long lastTweetId, TweetListCallback callback);

  void getUserTweetList(long userId, Long lastTweetId, TweetListCallback callback);

  interface TweetListCallback {
    void onTweetListLoaded(Collection<TweetModel> tweets);

    void onError(ErrorBundle errorBundle);
  }
}