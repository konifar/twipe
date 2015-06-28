package com.konifar.twipe.model.mapper;

import com.konifar.twipe.model.pojo.TweetModel;
import com.konifar.twipe.util.DateUtil;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TweetMapper {

  private static TweetMapper instance;
  private final UserMapper userMapper;
  private final TweetEntitiesMapper tweetEntitiesMapper;

  private TweetMapper() {
    this.userMapper = UserMapper.getInstance();
    this.tweetEntitiesMapper = TweetEntitiesMapper.getInstance();
  }

  public static TweetMapper getInstance() {
    if (instance == null) {
      instance = new TweetMapper();
    }
    return instance;
  }

  public TweetModel transform(Tweet tweet) {
    TweetModel tweetModel = null;
    if (tweet != null) {
      tweetModel = new TweetModel(tweet.id);
      tweetModel.setText(tweet.text);
      tweetModel.setSource(tweet.source);
      tweetModel.setCreatedAt(DateUtil.twitterStringToDate(tweet.createdAt));
      tweetModel.setInReplyToScreenName(tweet.inReplyToScreenName);
      tweetModel.setInReplyToUserId(tweet.inReplyToUserId);
      tweetModel.setInReplyToStatusId(tweet.inReplyToStatusId);
      tweetModel.setFavoriteCount(tweet.favoriteCount);
      tweetModel.setRetweetCount(tweet.retweetCount);
      tweetModel.setFavorited(tweet.favorited);
      tweetModel.setTruncated(tweet.truncated);
      tweetModel.setRetweeted(tweet.retweeted);
      tweetModel.setPossiblySensitive(tweet.possiblySensitive);
      tweetModel.setUser(userMapper.transform(tweet.user));
      tweetModel.setRetweetedStatus(transform(tweet.retweetedStatus));
      tweetModel.setAttachment(tweetEntitiesMapper.transform(tweet.entities));
    }

    return tweetModel;
  }

  public Collection<TweetModel> transform(Collection<Tweet> tweets) {
    List<TweetModel> tweetModelList = new ArrayList<>(20);
    TweetModel tweetModel;
    for (Tweet tweet : tweets) {
      tweetModel = transform(tweet);
      if (tweetModel != null) {
        tweetModelList.add(tweetModel);
      }
    }

    return tweetModelList;
  }
}
