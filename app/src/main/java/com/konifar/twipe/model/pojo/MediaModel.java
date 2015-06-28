package com.konifar.twipe.model.pojo;

public class MediaModel {
  private final long id;
  private final String mediaUrl;
  private final long sourceStatusId;
  private final String type;

  public MediaModel(long id, String mediaUrl, long sourceStatusId, String type) {
    this.id = id;
    this.mediaUrl = mediaUrl;
    this.sourceStatusId = sourceStatusId;
    this.type = type;
  }

  public long getId() {
    return id;
  }

  public String getMediaUrl() {
    return mediaUrl;
  }

  public long getSourceStatusId() {
    return sourceStatusId;
  }

  public String getType() {
    return type;
  }
}
