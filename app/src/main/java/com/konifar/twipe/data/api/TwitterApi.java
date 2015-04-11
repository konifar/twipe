package com.konifar.twipe.data.api;

import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

/**
 * RestApi for retrieving data from the network.
 */
public interface TwitterApi {

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

    void onError(Exception exception);
  }
}
