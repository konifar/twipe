package com.konifar.twipe.domain.model;

public class UrlModel {
  private final String url;
  private final String expandedUrl;
  private final String displayUrl;

  public UrlModel(String url, String expandedUrl, String displayUrl) {
    this.url = url;
    this.expandedUrl = expandedUrl;
    this.displayUrl = displayUrl;
  }

  public String getUrl() {
    return url;
  }

  public String getExpandedUrl() {
    return expandedUrl;
  }

  public String getDisplayUrl() {
    return displayUrl;
  }
}
