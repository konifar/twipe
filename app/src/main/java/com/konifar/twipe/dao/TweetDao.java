package com.konifar.twipe.dao;

import com.konifar.twipe.model.pojo.TweetModel;
import java.util.Collection;

public interface TweetDao {
  void getHomeTweetList(final Long lastTweetId, final String tag);

  void getUserTweetList(final long userId, final Long lastTweetId, final String tag);

  public class OnLoadedEvent {
    public final Collection<TweetModel> tweetModels;
    public final String tag;

    public OnLoadedEvent(Collection<TweetModel> tweetModels, String tag) {
      this.tweetModels = tweetModels;
      this.tag = tag;
    }
  }

  public class OnErrorEvent {
    public final Exception exception;
    public final String tag;

    public OnErrorEvent(Exception exception, String tag) {
      this.exception = exception;
      this.tag = tag;
    }
  }
}
