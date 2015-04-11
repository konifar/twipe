package com.konifar.twipe.data.repository.datasource;

import android.content.Context;
import com.konifar.twipe.data.api.TwitterApi;
import com.konifar.twipe.data.api.TwitterApiImpl;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link TweetDataStore}.
 */
@Singleton
public class TweetDataStoreFactory {
  private final Context context;

  @Inject
  public TweetDataStoreFactory(Context context) {
    if (context == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null.");
    }
    this.context = context.getApplicationContext();
  }

  /**
   * Create {@link TweetDataStore} from a user id.
   */
  public TweetDataStore create() {
    return createApiDataStore();
  }

  /**
   * Create {@link TweetDataStore} to retrieve data from the Cloud.
   */
  public TweetDataStore createApiDataStore() {
    TwitterApi twitterApi = new TwitterApiImpl();
    return new ApiTweetDataStore(twitterApi);
  }
}
