package com.konifar.twipe.presentation.internal.di.components;

import com.konifar.twipe.presentation.internal.di.PerActivity;
import com.konifar.twipe.presentation.internal.di.modules.ActivityModule;
import com.konifar.twipe.presentation.view.fragment.LoginFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface LoginComponent extends ActivityComponent {
  void inject(LoginFragment userListFragment);
}
