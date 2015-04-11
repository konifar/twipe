package com.konifar.twipe.domain.model;

public class MentionModel {
  private final long id;
  private final String name;
  private final String screenName;

  public MentionModel(long id, String name, String screenName) {
    this.id = id;
    this.name = name;
    this.screenName = screenName;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getScreenName() {
    return screenName;
  }
}
