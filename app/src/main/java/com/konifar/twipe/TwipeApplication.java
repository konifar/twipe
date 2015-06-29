package com.konifar.twipe;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class TwipeApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(getApplicationContext());
    initFabric();
  }

  private void initFabric() {
    TwitterAuthConfig authConfig =
        new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
    Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
  }
}
