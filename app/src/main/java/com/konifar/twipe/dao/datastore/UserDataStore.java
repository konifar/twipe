package com.konifar.twipe.dao.datastore;

import com.konifar.twipe.model.pojo.UserModel;

public interface UserDataStore {
  void getUser(Long userId, UserCallback callback);

  interface UserCallback {
    void onUserLoaded(UserModel userModel);

    void onError(Exception exception);
  }
}
