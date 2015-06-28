package com.konifar.twipe.dao.datastore;

import android.content.Context;
import android.support.annotation.NonNull;

public class TweetDataStoreFactory {

  private static TweetDataStoreFactory instance;
  private final Context context;

  public TweetDataStoreFactory(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  public static TweetDataStoreFactory getInstance(@NonNull Context context) {
    if (instance == null) {
      instance = new TweetDataStoreFactory(context);
    }
    return instance;
  }

  public TweetDataStore create() {
    // TODO switch api or database
    return ApiTweetDataStore.getInstance();
  }
}
