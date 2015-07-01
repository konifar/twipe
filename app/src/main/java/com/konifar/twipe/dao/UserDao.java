package com.konifar.twipe.dao;

import com.konifar.twipe.model.pojo.UserModel;

public interface UserDao {
  void getUser(final Long userId, final String tag);

  public class OnUserLoadedEvent {
    public final UserModel userModel;
    public final String tag;

    public OnUserLoadedEvent(UserModel userModel, String tag) {
      this.userModel = userModel;
      this.tag = tag;
    }
  }

  public class OnUserErrorEvent {
    public final Exception exception;
    public final String tag;

    public OnUserErrorEvent(Exception exception, String tag) {
      this.exception = exception;
      this.tag = tag;
    }
  }
}
