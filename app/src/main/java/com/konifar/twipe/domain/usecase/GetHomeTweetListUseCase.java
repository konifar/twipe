package com.konifar.twipe.domain.usecase;

import com.konifar.twipe.domain.exception.ErrorBundle;
import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

interface GetHomeTweetListUseCase extends UseCase {
  void execute(long lastTweetId);

  public class OnLoadedEvent {
    public final Collection<TweetModel> tweetModels;

    public OnLoadedEvent(Collection<TweetModel> tweetModels) {
      this.tweetModels = tweetModels;
    }
  }

  public class OnErrorEvent {
    public final ErrorBundle errorBundle;

    public OnErrorEvent(ErrorBundle errorBundle) {
      this.errorBundle = errorBundle;
    }
  }
}
