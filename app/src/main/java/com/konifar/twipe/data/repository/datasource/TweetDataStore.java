package com.konifar.twipe.data.repository.datasource;

import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface TweetDataStore {
  /**
   * Get a collection of {@link TweetModel}.
   *
   * @param tweetListCallback A {@link TweetListCallback} used for notifying clients.
   */
  public void getTweetsList(TweetListCallback tweetListCallback);

  /**
   * Callback used for clients to be notified when either a user list has been loaded or any error
   * occurred.
   */
  interface TweetListCallback {
    void onTweetListLoaded(Collection<TweetModel> tweetsCollection);

    void onError(Exception exception);
  }
}
