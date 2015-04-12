package com.konifar.twipe.data.entity.mapper;

import com.konifar.twipe.domain.model.HashtagModel;
import com.konifar.twipe.domain.model.MediaModel;
import com.konifar.twipe.domain.model.MentionModel;
import com.konifar.twipe.domain.model.TweetAttachmentModel;
import com.konifar.twipe.domain.model.UrlModel;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.MentionEntity;
import com.twitter.sdk.android.core.models.TweetEntities;
import com.twitter.sdk.android.core.models.UrlEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class TweetEntitiesMapper {

  @Inject public TweetEntitiesMapper() {
  }

  public TweetAttachmentModel transform(TweetEntities tweetEntities) {
    TweetAttachmentModel tweetAttachmentModel = null;
    if (tweetEntities != null) {
      tweetAttachmentModel = new TweetAttachmentModel();
      tweetAttachmentModel.setHashtags(transformHashtags(tweetEntities.hashtags));
      tweetAttachmentModel.setMedias(transformMedias(tweetEntities.media));
      tweetAttachmentModel.setUrls(transformUrls(tweetEntities.urls));
      tweetAttachmentModel.setUserMentions(transformMentions(tweetEntities.userMentions));
    }

    return tweetAttachmentModel;
  }

  private HashtagModel transform(HashtagEntity hashtagEntity) {
    HashtagModel hashtagModel = null;
    if (hashtagEntity != null) {
      hashtagModel = new HashtagModel(hashtagEntity.text);
    }

    return hashtagModel;
  }

  private Collection<HashtagModel> transformHashtags(List<HashtagEntity> hashtagEntityCollection) {
    List<HashtagModel> hashtagModelList = new ArrayList<>();
    HashtagModel hashtagModel;
    for (HashtagEntity tweetEntity : hashtagEntityCollection) {
      hashtagModel = transform(tweetEntity);
      if (hashtagModel != null) {
        hashtagModelList.add(hashtagModel);
      }
    }

    return hashtagModelList;
  }

  private MediaModel transform(MediaEntity mediaEntity) {
    MediaModel mediaModel = null;
    if (mediaEntity != null) {
      mediaModel = new MediaModel(mediaEntity.id, mediaEntity.mediaUrl, mediaEntity.sourceStatusId,
          mediaEntity.type);
    }

    return mediaModel;
  }

  private Collection<MediaModel> transformMedias(List<MediaEntity> mediaEntityCollection) {
    List<MediaModel> mediaModelList = new ArrayList<>();
    MediaModel mediaModel;
    for (MediaEntity tweetEntity : mediaEntityCollection) {
      mediaModel = transform(tweetEntity);
      if (mediaModel != null) {
        mediaModelList.add(mediaModel);
      }
    }

    return mediaModelList;
  }

  private UrlModel transform(UrlEntity urlEntity) {
    UrlModel urlModel = null;
    if (urlEntity != null) {
      urlModel = new UrlModel(urlEntity.url, urlEntity.expandedUrl, urlEntity.displayUrl);
    }

    return urlModel;
  }

  private Collection<UrlModel> transformUrls(List<UrlEntity> urlEntityCollection) {
    List<UrlModel> urlModelList = new ArrayList<>();
    UrlModel urlModel;
    for (UrlEntity urlEntity : urlEntityCollection) {
      urlModel = transform(urlEntity);
      if (urlModel != null) {
        urlModelList.add(urlModel);
      }
    }

    return urlModelList;
  }

  private MentionModel transform(MentionEntity mentionEntity) {
    MentionModel mentionModel = null;
    if (mentionEntity != null) {
      mentionModel =
          new MentionModel(mentionEntity.id, mentionEntity.name, mentionEntity.screenName);
    }

    return mentionModel;
  }

  private Collection<MentionModel> transformMentions(List<MentionEntity> mentionEntityCollection) {
    List<MentionModel> mentionModelList = new ArrayList<>();
    MentionModel mentionModel;
    for (MentionEntity mentionEntity : mentionEntityCollection) {
      mentionModel = transform(mentionEntity);
      if (mentionModel != null) {
        mentionModelList.add(mentionModel);
      }
    }

    return mentionModelList;
  }
}
