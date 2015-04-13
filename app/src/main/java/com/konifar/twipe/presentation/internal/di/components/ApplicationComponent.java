package com.konifar.twipe.presentation.internal.di.components;

import android.content.Context;
import com.konifar.twipe.TwipeApplication;
import com.konifar.twipe.domain.executor.ThreadExecutor;
import com.konifar.twipe.domain.repository.TweetRepository;
import com.konifar.twipe.presentation.internal.di.modules.ApplicationModule;
import com.konifar.twipe.presentation.view.activity.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(TwipeApplication androidApplication);

  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();

  TweetRepository tweetRepository();

  ThreadExecutor threadExecutor();
}
