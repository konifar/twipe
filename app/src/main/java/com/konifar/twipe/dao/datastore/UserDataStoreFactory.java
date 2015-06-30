package com.konifar.twipe.dao.datastore;

import android.content.Context;
import android.support.annotation.NonNull;

public class UserDataStoreFactory {

  private static UserDataStoreFactory instance;
  private final Context context;

  public UserDataStoreFactory(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }

  public static UserDataStoreFactory getInstance(@NonNull Context context) {
    if (instance == null) {
      instance = new UserDataStoreFactory(context);
    }
    return instance;
  }

  public UserDataStore create() {
    // TODO switch api or database
    return ApiUserDataStore.getInstance();
  }
}
