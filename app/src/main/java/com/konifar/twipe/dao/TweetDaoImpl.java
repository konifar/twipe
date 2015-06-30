package com.konifar.twipe.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import com.konifar.twipe.dao.datastore.TweetDataStore;
import com.konifar.twipe.dao.datastore.TweetDataStoreFactory;
import com.konifar.twipe.dao.executer.JobExecuter;
import com.konifar.twipe.dao.executer.ThreadExecuter;
import com.konifar.twipe.model.pojo.TweetModel;
import de.greenrobot.event.EventBus;
import java.util.Collection;

public class TweetDaoImpl implements TweetDao {

  private static final int GET_TWEET_COUNT = 20;
  private static TweetDaoImpl instance;
  private final ThreadExecuter threadExecutor;
  private final TweetDataStoreFactory tweetDataStoreFactory;

  private TweetDaoImpl(@NonNull Context context) {
    this.tweetDataStoreFactory = TweetDataStoreFactory.getInstance(context);
    this.threadExecutor = JobExecuter.getInstance();
  }

  public static TweetDaoImpl getInstance(@NonNull Context context) {
    if (instance == null) {
      instance = new TweetDaoImpl(context);
    }
    return instance;
  }

  @Override public void getHomeTweetList(final Long lastTweetId, final String tag) {
    final TweetDataStore tweetDataStore = this.tweetDataStoreFactory.create();
    threadExecutor.execute(new Runnable() {
      @Override public void run() {
        tweetDataStore.getHomeTweetList(lastTweetId, GET_TWEET_COUNT,
            new TweetDataStore.TweetListCallback() {
              @Override public void onTweetListLoaded(Collection<TweetModel> tweets) {
                EventBus.getDefault().post(new OnLoadedEvent(tweets, tag));
              }

              @Override public void onError(Exception exception) {
                EventBus.getDefault().post(new OnErrorEvent(exception, tag));
              }
            });
      }
    });
  }

  @Override
  public void getUserTweetList(final long userId, final Long lastTweetId, final String tag) {
    final TweetDataStore tweetDataStore = this.tweetDataStoreFactory.create();

    threadExecutor.execute(new Runnable() {
      @Override public void run() {
        tweetDataStore.getUserTweetList(userId, lastTweetId, GET_TWEET_COUNT,
            new TweetDataStore.TweetListCallback() {
              @Override public void onTweetListLoaded(Collection<TweetModel> tweets) {
                EventBus.getDefault().post(new OnLoadedEvent(tweets, tag));
              }

              @Override public void onError(Exception exception) {
                EventBus.getDefault().post(new OnErrorEvent(exception, tag));
              }
            });
      }
    });
  }
}
