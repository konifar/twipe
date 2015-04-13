package com.konifar.twipe.data.repository.datasource;

import com.konifar.twipe.data.entity.mapper.TweetMapper;
import com.konifar.twipe.domain.model.TweetModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import java.util.Collection;
import java.util.List;

class ApiTweetDataStore implements TweetDataStore {

  private final TweetMapper tweetMapper;
  private final StatusesService statusesService;

  public ApiTweetDataStore(TweetMapper tweetMapper, StatusesService statusesService) {
    this.tweetMapper = tweetMapper;
    this.statusesService = statusesService;
  }

  @Override public void getHomeTweetList(Long lastTweetId, int count,
      final TweetListCallback callback) {
    statusesService.homeTimeline(count, null, lastTweetId, false, false, true, true,
        new Callback<List<Tweet>>() {
          @Override
          public void success(Result<List<Tweet>> listResult) {
            Collection<TweetModel> tweetModels = tweetMapper.transform(listResult.data);
            callback.onTweetListLoaded(tweetModels);
          }

          @Override
          public void failure(TwitterException e) {
            callback.onError(e);
          }
        });
  }

  @Override public void getUserTweetList(long userId, Long lastTweetId, int count,
      final TweetListCallback callback) {
    statusesService.userTimeline(userId, null, count, lastTweetId, null, false, false, false, false,
        new Callback<List<Tweet>>() {
          @Override
          public void success(Result<List<Tweet>> listResult) {
            Collection<TweetModel> tweetModels = tweetMapper.transform(listResult.data);
            callback.onTweetListLoaded(tweetModels);
          }

          @Override
          public void failure(TwitterException e) {
            callback.onError(e);
          }
        });
  }
}
