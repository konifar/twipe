package com.konifar.twipe.dao.datastore;

import com.konifar.twipe.model.mapper.TweetMapper;
import com.konifar.twipe.model.pojo.TweetModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import java.util.Collection;
import java.util.List;

class ApiTweetDataStore implements TweetDataStore {

  private static ApiTweetDataStore instance;
  private final TweetMapper tweetMapper;
  private final StatusesService statusesService;

  private ApiTweetDataStore() {
    this.tweetMapper = TweetMapper.getInstance();
    this.statusesService = TwitterCore.getInstance().getApiClient().getStatusesService();
  }

  public static ApiTweetDataStore getInstance() {
    if (instance == null) {
      instance = new ApiTweetDataStore();
    }
    return instance;
  }

  @Override
  public void getHomeTweetList(Long lastTweetId, int count, final TweetListCallback callback) {
    statusesService.homeTimeline(count, null, lastTweetId, false, false, true, true,
        new Callback<List<Tweet>>() {
          @Override public void success(Result<List<Tweet>> listResult) {
            Collection<TweetModel> tweetModels = tweetMapper.transform(listResult.data);
            callback.onTweetListLoaded(tweetModels);
          }

          @Override public void failure(TwitterException e) {
            callback.onError(e);
          }
        });
  }

  @Override public void getUserTweetList(long userId, Long lastTweetId, int count,
      final TweetListCallback callback) {
    statusesService.userTimeline(userId, null, count, lastTweetId, null, false, false, false, false,
        new Callback<List<Tweet>>() {
          @Override public void success(Result<List<Tweet>> listResult) {
            Collection<TweetModel> tweetModels = tweetMapper.transform(listResult.data);
            callback.onTweetListLoaded(tweetModels);
          }

          @Override public void failure(TwitterException e) {
            callback.onError(e);
          }
        });
  }
}
