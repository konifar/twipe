package com.konifar.twipe.network;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

public class CustomTwitterApiClient extends TwitterApiClient {
  public CustomTwitterApiClient(TwitterSession session) {
    super(session);
  }

  public UserService getUserService() {
    return getService(UserService.class);
  }
}
