package com.konifar.twipe;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class TwipeApplication extends Application {

  private static final String TWITTER_KEY = "VCuRKICQcA4CVuN44oNXiS7Fh";
  private static final String TWITTER_SECRET = "ZDnnmsWblWOzIB09meDDNDdAeHgGvIlsxDHeqRsW2SvB8LYbNE";

  @Override public void onCreate() {
    super.onCreate();
    initFabric();
  }

  private void initFabric() {
    TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
    Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
  }
}
