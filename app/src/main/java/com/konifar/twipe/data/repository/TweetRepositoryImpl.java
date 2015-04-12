package com.konifar.twipe.data.repository;

import com.konifar.twipe.data.exception.RepositoryErrorBundle;
import com.konifar.twipe.data.repository.datasource.TweetDataStore;
import com.konifar.twipe.data.repository.datasource.TweetDataStoreFactory;
import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.domain.repository.TweetRepository;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TweetRepositoryImpl implements TweetRepository {

  private static final int GET_TWEET_COUNT = 20;
  private TweetDataStoreFactory tweetDataStoreFactory;

  @Inject public TweetRepositoryImpl(TweetDataStoreFactory dataStoreFactory) {
    this.tweetDataStoreFactory = dataStoreFactory;
  }

  @Override public void getHomeTweetList(int lastTweetId, final TweetListCallback callback) {
    final TweetDataStore tweetDataStore = this.tweetDataStoreFactory.createApiDataStore();
    tweetDataStore.getHomeTweetList(lastTweetId, GET_TWEET_COUNT,
        new TweetDataStore.TweetListCallback() {
          @Override public void onTweetListLoaded(Collection<TweetModel> tweets) {
            callback.onTweetListLoaded(tweets);
          }

          @Override public void onError(Exception exception) {
            callback.onError(new RepositoryErrorBundle(exception));
          }
        });
  }

  @Override public void getUserTweetList(int userId, int lastTweetId,
      final TweetListCallback callback) {
    final TweetDataStore tweetDataStore = this.tweetDataStoreFactory.createApiDataStore();
    tweetDataStore.getUserTweetList(userId, lastTweetId, GET_TWEET_COUNT,
        new TweetDataStore.TweetListCallback() {
          @Override public void onTweetListLoaded(Collection<TweetModel> tweets) {
            callback.onTweetListLoaded(tweets);
          }

          @Override public void onError(Exception exception) {
            callback.onError(new RepositoryErrorBundle(exception));
          }
        });
  }
}
