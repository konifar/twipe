package com.konifar.twipe.presentation.internal.di.modules;

import com.konifar.twipe.domain.usecase.GetHomeTweetListUseCase;
import com.konifar.twipe.domain.usecase.GetHomeTweetListUseCaseImpl;
import com.konifar.twipe.domain.usecase.GetUserTweetListUseCase;
import com.konifar.twipe.domain.usecase.GetUserTweetListUseCaseImpl;
import com.konifar.twipe.presentation.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class TweetModule {
  @Provides @PerActivity GetHomeTweetListUseCase provideGetHomeTweetListUseCase(
      GetHomeTweetListUseCaseImpl getHomeTweetListUseCase) {
    return getHomeTweetListUseCase;
  }

  @Provides @PerActivity GetUserTweetListUseCase provideGetUserTweetListUseCase(
      GetUserTweetListUseCaseImpl getUserTweetListUseCase) {
    return getUserTweetListUseCase;
  }
}
