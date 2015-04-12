package com.konifar.twipe.data.entity.mapper;

import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.util.DateUtil;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class TweetMapper {

  private final UserMapper userMapper;
  private final TweetEntitiesMapper tweetEntitiesMapper;

  @Inject public TweetMapper() {
    this.userMapper = new UserMapper();
    this.tweetEntitiesMapper = new TweetEntitiesMapper();
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
