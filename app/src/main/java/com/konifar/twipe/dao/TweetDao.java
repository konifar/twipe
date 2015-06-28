package com.konifar.twipe.dao;

import com.konifar.twipe.model.pojo.TweetModel;
import java.util.Collection;

public interface TweetDao {
  void getHomeTweetList(final Long lastTweetId);

  void getUserTweetList(final long userId, final Long lastTweetId);

  public class OnLoadedEvent {
    public final Collection<TweetModel> tweetModels;

    public OnLoadedEvent(Collection<TweetModel> tweetModels) {
      this.tweetModels = tweetModels;
    }
  }

  public class OnErrorEvent {
    public final Exception exception;

    public OnErrorEvent(Exception exception) {
      this.exception = exception;
    }
  }
}
