package com.konifar.twipe.domain.repository;

import com.konifar.twipe.domain.exception.ErrorBundle;
import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

/**
 * Interface that represents a Repository for getting {@link TweetModel} related data.
 */
public interface TweetRepository {
  /**
   * Get a collection of {@link TweetModel}.
   *
   * @param tweetListCallback A {@link TweetListCallback} used for notifying clients.
   */
  void getTweetList(TweetListCallback tweetListCallback);

  /**
   * Callback used to be notified when either a tweet list has been loaded or an error happened.
   */
  interface TweetListCallback {
    void onTweetListLoaded(Collection<TweetModel> tweetsCollection);

    void onError(ErrorBundle errorBundle);
  }
}