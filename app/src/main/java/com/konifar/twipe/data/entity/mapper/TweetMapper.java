package com.konifar.twipe.data.entity.mapper;

import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.util.DateUtil;
import com.twitter.sdk.android.core.models.Tweet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link Tweet} (in the data layer) to {@link TweetModel}
 * in the domain layer.
 */
@Singleton class TweetMapper {

  private UserMapper userEntityDataMapper;
  private TweetEntitiesMapper tweetEntitiesMapper;

  @Inject
  public TweetMapper(UserMapper userEntityDataMapper, TweetEntitiesMapper tweetEntitiesMapper) {
    this.userEntityDataMapper = userEntityDataMapper;
    this.tweetEntitiesMapper = tweetEntitiesMapper;
  }

  /**
   * Transform a {@link Tweet} into an {@link TweetModel}.
   *
   * @param tweetEntity Object to be transformed.
   * @return {@link TweetModel} if valid {@link Tweet} otherwise null.
   */
  public TweetModel transform(Tweet tweetEntity) {
    TweetModel tweetModel = null;
    if (tweetEntity != null) {
      tweetModel = new TweetModel(tweetEntity.id);
      tweetModel.setText(tweetEntity.text);
      tweetModel.setSource(tweetEntity.source);
      tweetModel.setCreatedAt(DateUtil.twitterStringToDate(tweetEntity.createdAt));
      tweetModel.setInReplyToScreenName(tweetEntity.inReplyToScreenName);
      tweetModel.setInReplyToUserId(tweetEntity.inReplyToUserId);
      tweetModel.setInReplyToStatusId(tweetEntity.inReplyToStatusId);
      tweetModel.setFavoriteCount(tweetEntity.favoriteCount);
      tweetModel.setRetweetCount(tweetEntity.retweetCount);
      tweetModel.setFavorited(tweetEntity.favorited);
      tweetModel.setTruncated(tweetEntity.truncated);
      tweetModel.setRetweeted(tweetEntity.retweeted);
      tweetModel.setPossiblySensitive(tweetEntity.possiblySensitive);
      tweetModel.setUser(userEntityDataMapper.transform(tweetEntity.user));
      tweetModel.setRetweetedStatus(transform(tweetEntity.retweetedStatus));
      tweetModel.setAttachment(tweetEntitiesMapper.transform(tweetEntity.entities));
    }

    return tweetModel;
  }

  /**
   * Transform a Collection of {@link Tweet} into a Collection of {@link TweetModel}.
   *
   * @param tweetEntityCollection Object Collection to be transformed.
   * @return {@link Tweet} if valid {@link Tweet} otherwise null.
   */
  public Collection<TweetModel> transform(Collection<Tweet> tweetEntityCollection) {
    List<TweetModel> tweetModelList = new ArrayList<>(20);
    TweetModel tweetModel;
    for (Tweet tweetEntity : tweetEntityCollection) {
      tweetModel = transform(tweetEntity);
      if (tweetModel != null) {
        tweetModelList.add(tweetModel);
      }
    }

    return tweetModelList;
  }
}
