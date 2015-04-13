package com.konifar.twipe.data.repository.datasource;

import android.content.Context;
import com.konifar.twipe.data.entity.mapper.TweetMapper;
import com.twitter.sdk.android.core.services.StatusesService;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TweetDataStoreFactory {
  private final Context context;
  private final StatusesService statusesService;

  @Inject public TweetDataStoreFactory(Context context, StatusesService statusesService) {
    if (context == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null.");
    }
    this.context = context.getApplicationContext();
    this.statusesService = statusesService;
  }

  public TweetDataStore create() {
    return createApiDataStore();
  }

  public TweetDataStore createApiDataStore() {
    return new ApiTweetDataStore(new TweetMapper(), statusesService);
  }
}
