package com.konifar.twipe.model.mapper;

import com.konifar.twipe.model.pojo.UserModel;
import com.konifar.twipe.util.DateUtil;
import com.twitter.sdk.android.core.models.User;

public class UserMapper {

  private static UserMapper instance;

  private UserMapper() {
  }

  public static UserMapper getInstance() {
    if (instance == null) {
      instance = new UserMapper();
    }
    return instance;
  }

  public UserModel transform(User user) {
    UserModel userModel = null;
    if (user != null) {
      userModel = new UserModel(user.id);
      userModel.setName(user.name);
      userModel.setScreenName(user.screenName);
      userModel.setDescription(user.description);
      userModel.setTimeZone(user.timeZone);
      userModel.setCreatedAt(DateUtil.twitterStringToDate(user.createdAt));
      userModel.setUrl(user.url);
      userModel.setFollowRequestSent(user.followRequestSent);
      userModel.setDefaultProfile(user.defaultProfile);
      userModel.setDefaultProfileImage(user.defaultProfileImage);
      userModel.setProfileBackgroundImageUrl(user.profileBackgroundImageUrl);
      userModel.setProfileBannerUrl(user.profileBannerUrl);
      userModel.setProfileImageUrl(user.profileImageUrl);
      userModel.setListedCount(user.listedCount);
      userModel.setFavouritesCount(user.favouritesCount);
      userModel.setFollowersCount(user.followersCount);
      userModel.setStatusesCount(user.statusesCount);
      userModel.setFriendsCount(user.friendsCount);
      userModel.setProfileBackgroundTile(user.profileBackgroundTile);
      userModel.setProfileUseBackgroundImage(user.profileUseBackgroundImage);
      userModel.setProtectedUser(user.protectedUser);
      userModel.setShowAllInlineMedia(user.showAllInlineMedia);
      userModel.setProfileBackgroundColor(user.profileBackgroundColor);
      userModel.setProfileSidebarFillColor(user.profileSidebarFillColor);
      userModel.setProfileSidebarBorderColor(user.profileSidebarBorderColor);
      userModel.setProfileLinkColor(user.profileLinkColor);
      userModel.setProfileTextColor(user.profileTextColor);
    }
    return userModel;
  }
}
