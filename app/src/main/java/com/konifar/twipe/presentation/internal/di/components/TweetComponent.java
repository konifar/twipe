package com.konifar.twipe.presentation.internal.di.components;

import com.konifar.twipe.presentation.internal.di.PerActivity;
import com.konifar.twipe.presentation.internal.di.modules.ActivityModule;
import com.konifar.twipe.presentation.internal.di.modules.TweetModule;
import com.konifar.twipe.presentation.view.fragment.TweetListFragment;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class,
    modules = { ActivityModule.class, TweetModule.class })
public interface TweetComponent extends ActivityComponent {
  void inject(TweetListFragment tweetListFragment);
}
