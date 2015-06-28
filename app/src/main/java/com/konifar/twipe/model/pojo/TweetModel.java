package com.konifar.twipe.model.pojo;

import java.util.Date;

public class TweetModel {
  private final long id;
  private String text;
  private String source;
  private Date createdAt;
  private String inReplyToScreenName;
  private long inReplyToUserId;
  private long inReplyToStatusId;
  private int favoriteCount;
  private int retweetCount;
  private boolean favorited;
  private boolean truncated;
  private boolean retweeted;
  private boolean possiblySensitive;
  private UserModel user;
  private TweetModel retweetedStatus;
  private TweetAttachmentModel attachment;

  public TweetModel(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getInReplyToScreenName() {
    return inReplyToScreenName;
  }

  public void setInReplyToScreenName(String inReplyToScreenName) {
    this.inReplyToScreenName = inReplyToScreenName;
  }

  public long getInReplyToUserId() {
    return inReplyToUserId;
  }

  public void setInReplyToUserId(long inReplyToUserId) {
    this.inReplyToUserId = inReplyToUserId;
  }

  public long getInReplyToStatusId() {
    return inReplyToStatusId;
  }

  public void setInReplyToStatusId(long inReplyToStatusId) {
    this.inReplyToStatusId = inReplyToStatusId;
  }

  public int getFavoriteCount() {
    return favoriteCount;
  }

  public void setFavoriteCount(int favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  public int getRetweetCount() {
    return retweetCount;
  }

  public void setRetweetCount(int retweetCount) {
    this.retweetCount = retweetCount;
  }

  public boolean isFavorited() {
    return favorited;
  }

  public void setFavorited(boolean favorited) {
    this.favorited = favorited;
  }

  public boolean isTruncated() {
    return truncated;
  }

  public void setTruncated(boolean truncated) {
    this.truncated = truncated;
  }

  public boolean isRetweeted() {
    return retweeted;
  }

  public void setRetweeted(boolean retweeted) {
    this.retweeted = retweeted;
  }

  public boolean isPossiblySensitive() {
    return possiblySensitive;
  }

  public void setPossiblySensitive(boolean possiblySensitive) {
    this.possiblySensitive = possiblySensitive;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public TweetModel getRetweetedStatus() {
    return retweetedStatus;
  }

  public void setRetweetedStatus(TweetModel retweetedStatus) {
    this.retweetedStatus = retweetedStatus;
  }

  public TweetAttachmentModel getAttachment() {
    return attachment;
  }

  public void setAttachment(TweetAttachmentModel attachment) {
    this.attachment = attachment;
  }
}
