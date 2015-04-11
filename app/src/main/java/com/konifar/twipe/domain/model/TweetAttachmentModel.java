package com.konifar.twipe.domain.model;

import java.util.Collection;

public class TweetAttachmentModel {
  private Collection<UrlModel> urls;
  private Collection<MentionModel> userMentions;
  private Collection<MediaModel> medias;
  private Collection<HashtagModel> hashtags;

  public Collection<UrlModel> getUrls() {
    return urls;
  }

  public void setUrls(Collection<UrlModel> urls) {
    this.urls = urls;
  }

  public Collection<MentionModel> getUserMentions() {
    return userMentions;
  }

  public void setUserMentions(Collection<MentionModel> userMentions) {
    this.userMentions = userMentions;
  }

  public Collection<MediaModel> getMedias() {
    return medias;
  }

  public void setMedias(Collection<MediaModel> medias) {
    this.medias = medias;
  }

  public Collection<HashtagModel> getHashtags() {
    return hashtags;
  }

  public void setHashtags(Collection<HashtagModel> hashtags) {
    this.hashtags = hashtags;
  }
}
