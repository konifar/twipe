package com.konifar.twipe.data.repository.datasource;

import com.konifar.twipe.data.api.TwitterApi;
import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

/**
 * {@link TweetDataStore} implementation based on connections to the api (Cloud).
 */
class ApiTweetDataStore implements TweetDataStore {

  private final TwitterApi twitterApi;

  /**
   * Construct a {@link TweetDataStore} based on connections to the api (Cloud).
   *
   * @param twitterApi The {@link TwitterApi} implementation to use.
   */
  public ApiTweetDataStore(TwitterApi twitterApi) {
    this.twitterApi = twitterApi;
  }

  /**
   * {@inheritDoc}
   *
   * @param tweetListCallback A {@link TweetListCallback} used for notifying clients.
   */
  @Override public void getTweetsList(final TweetListCallback tweetListCallback) {
    this.twitterApi.getTweetList(new TwitterApi.TweetListCallback() {
      @Override public void onTweetListLoaded(Collection<TweetModel> tweetsCollection) {
        tweetListCallback.onTweetListLoaded(tweetsCollection);
      }

      @Override public void onError(Exception exception) {
        tweetListCallback.onError(exception);
      }
    });
  }
}
