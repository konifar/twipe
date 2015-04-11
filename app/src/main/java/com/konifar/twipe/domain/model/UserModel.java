package com.konifar.twipe.domain.model;

import java.util.Date;

public class UserModel {
  private final long id;
  private String name;
  private String screenName;
  private String description;
  private String timeZone;
  private Date createdAt;
  private String url;
  private boolean followRequestSent;
  private boolean defaultProfile;
  private boolean defaultProfileImage;
  // Images
  private String profileBackgroundImageUrl;
  private String profileBannerUrl;
  private String profileImageUrl;
  // Counts
  private int listedCount;
  private int favouritesCount;
  private int followersCount;
  private int statusesCount;
  private int friendsCount;
  // Settings
  private boolean profileBackgroundTile;
  private boolean profileUseBackgroundImage;
  private boolean protectedUser;
  private boolean showAllInlineMedia;
  // Colors
  private String profileBackgroundColor;
  private String profileSidebarFillColor;
  private String profileSidebarBorderColor;
  private String profileLinkColor;
  private String profileTextColor;

  public UserModel(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isFollowRequestSent() {
    return followRequestSent;
  }

  public void setFollowRequestSent(boolean followRequestSent) {
    this.followRequestSent = followRequestSent;
  }

  public boolean isDefaultProfile() {
    return defaultProfile;
  }

  public void setDefaultProfile(boolean defaultProfile) {
    this.defaultProfile = defaultProfile;
  }

  public boolean isDefaultProfileImage() {
    return defaultProfileImage;
  }

  public void setDefaultProfileImage(boolean defaultProfileImage) {
    this.defaultProfileImage = defaultProfileImage;
  }

  public String getProfileBackgroundImageUrl() {
    return profileBackgroundImageUrl;
  }

  public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
    this.profileBackgroundImageUrl = profileBackgroundImageUrl;
  }

  public String getProfileBannerUrl() {
    return profileBannerUrl;
  }

  public void setProfileBannerUrl(String profileBannerUrl) {
    this.profileBannerUrl = profileBannerUrl;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public int getListedCount() {
    return listedCount;
  }

  public void setListedCount(int listedCount) {
    this.listedCount = listedCount;
  }

  public int getFavouritesCount() {
    return favouritesCount;
  }

  public void setFavouritesCount(int favouritesCount) {
    this.favouritesCount = favouritesCount;
  }

  public int getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(int followersCount) {
    this.followersCount = followersCount;
  }

  public int getStatusesCount() {
    return statusesCount;
  }

  public void setStatusesCount(int statusesCount) {
    this.statusesCount = statusesCount;
  }

  public int getFriendsCount() {
    return friendsCount;
  }

  public void setFriendsCount(int friendsCount) {
    this.friendsCount = friendsCount;
  }

  public boolean isProfileBackgroundTile() {
    return profileBackgroundTile;
  }

  public void setProfileBackgroundTile(boolean profileBackgroundTile) {
    this.profileBackgroundTile = profileBackgroundTile;
  }

  public boolean isProfileUseBackgroundImage() {
    return profileUseBackgroundImage;
  }

  public void setProfileUseBackgroundImage(boolean profileUseBackgroundImage) {
    this.profileUseBackgroundImage = profileUseBackgroundImage;
  }

  public boolean isProtectedUser() {
    return protectedUser;
  }

  public void setProtectedUser(boolean protectedUser) {
    this.protectedUser = protectedUser;
  }

  public boolean isShowAllInlineMedia() {
    return showAllInlineMedia;
  }

  public void setShowAllInlineMedia(boolean showAllInlineMedia) {
    this.showAllInlineMedia = showAllInlineMedia;
  }

  public String getProfileBackgroundColor() {
    return profileBackgroundColor;
  }

  public void setProfileBackgroundColor(String profileBackgroundColor) {
    this.profileBackgroundColor = profileBackgroundColor;
  }

  public String getProfileSidebarFillColor() {
    return profileSidebarFillColor;
  }

  public void setProfileSidebarFillColor(String profileSidebarFillColor) {
    this.profileSidebarFillColor = profileSidebarFillColor;
  }

  public String getProfileSidebarBorderColor() {
    return profileSidebarBorderColor;
  }

  public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
    this.profileSidebarBorderColor = profileSidebarBorderColor;
  }

  public String getProfileLinkColor() {
    return profileLinkColor;
  }

  public void setProfileLinkColor(String profileLinkColor) {
    this.profileLinkColor = profileLinkColor;
  }

  public String getProfileTextColor() {
    return profileTextColor;
  }

  public void setProfileTextColor(String profileTextColor) {
    this.profileTextColor = profileTextColor;
  }
}
