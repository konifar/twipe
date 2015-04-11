package com.konifar.twipe;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.konifar.twipe.presentation.internal.di.components.ApplicationComponent;
import com.konifar.twipe.presentation.internal.di.components.DaggerApplicationComponent;
import com.konifar.twipe.presentation.internal.di.modules.ApplicationModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class TwipeApplication extends Application {

  private static final String TWITTER_KEY = "VCuRKICQcA4CVuN44oNXiS7Fh";
  private static final String TWITTER_SECRET = "ZDnnmsWblWOzIB09meDDNDdAeHgGvIlsxDHeqRsW2SvB8LYbNE";

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initFabric();
    injectApplicationComponent();
  }

  private void injectApplicationComponent() {
    this.applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    this.applicationComponent.inject(this);
  }

  private void initFabric() {
    TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
    Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }
}
