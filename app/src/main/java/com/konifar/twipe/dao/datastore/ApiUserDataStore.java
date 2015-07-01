package com.konifar.twipe.dao.datastore;

import com.konifar.twipe.model.mapper.UserMapper;
import com.konifar.twipe.model.pojo.UserModel;
import com.konifar.twipe.network.CustomTwitterApiClient;
import com.konifar.twipe.network.UserService;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

class ApiUserDataStore implements UserDataStore {

  private static ApiUserDataStore instance;
  private final UserMapper userMapper;
  private final UserService userService;

  private ApiUserDataStore() {
    this.userMapper = UserMapper.getInstance();
    TwitterSession session = Twitter.getSessionManager().getActiveSession();
    this.userService = new CustomTwitterApiClient(session).getUserService();
  }

  public static ApiUserDataStore getInstance() {
    if (instance == null) {
      instance = new ApiUserDataStore();
    }
    return instance;
  }

  @Override public void getUser(Long userId, final UserCallback callback) {
    userService.show(userId, null, true, new Callback<User>() {
      @Override public void success(Result<User> result) {
        UserModel userModel = userMapper.transform(result.data);
        callback.onUserLoaded(userModel);
      }

      @Override public void failure(TwitterException e) {
        callback.onError(e);
      }
    });
  }
}
