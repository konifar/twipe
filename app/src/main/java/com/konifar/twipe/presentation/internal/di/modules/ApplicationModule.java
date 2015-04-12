package com.konifar.twipe.presentation.internal.di.modules;

import android.content.Context;
import com.konifar.twipe.TwipeApplication;
import com.konifar.twipe.data.repository.TweetRepositoryImpl;
import com.konifar.twipe.domain.repository.TweetRepository;
import com.konifar.twipe.presentation.navigation.ActivityNavigator;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.services.StatusesService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final TwipeApplication app;

  public ApplicationModule(TwipeApplication app) {
    this.app = app;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.app;
  }

  @Provides @Singleton ActivityNavigator provideActivityNavigator() {
    return new ActivityNavigator();
  }

  @Provides @Singleton TweetRepository provideTweetRepository(TweetRepositoryImpl tweetRepository) {
    return tweetRepository;
  }

  @Provides @Singleton StatusesService provideStatusesService() {
    TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
    return twitterApiClient.getStatusesService();
  }
}
