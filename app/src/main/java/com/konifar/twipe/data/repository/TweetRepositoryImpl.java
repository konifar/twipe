package com.konifar.twipe.data.repository;

import com.konifar.twipe.data.exception.RepositoryErrorBundle;
import com.konifar.twipe.data.repository.datasource.TweetDataStore;
import com.konifar.twipe.data.repository.datasource.TweetDataStoreFactory;
import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.domain.repository.TweetRepository;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link TweetRepository} for retrieving user data.
 */
@Singleton
public class TweetRepositoryImpl implements TweetRepository {

  private TweetDataStoreFactory tweetDataStoreFactory;

  /**
   * Constructs a {@link TweetRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   */
  @Inject
  public TweetRepositoryImpl(TweetDataStoreFactory dataStoreFactory) {
    this.tweetDataStoreFactory = dataStoreFactory;
  }

  /**
   * {@inheritDoc}
   *
   * @param tweetListCallback A {@link TweetListCallback} used for notifying clients.
   */
  @Override public void getTweetList(final TweetListCallback tweetListCallback) {
    final TweetDataStore tweetDataStore = this.tweetDataStoreFactory.createApiDataStore();
    tweetDataStore.getTweetsList(new TweetDataStore.TweetListCallback() {
      @Override public void onTweetListLoaded(Collection<TweetModel> tweetsCollection) {
        tweetListCallback.onTweetListLoaded(tweetsCollection);
      }

      @Override public void onError(Exception exception) {
        tweetListCallback.onError(new RepositoryErrorBundle(exception));
      }
    });
  }
}
