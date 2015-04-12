package com.konifar.twipe.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.konifar.twipe.TwipeApplication;
import com.konifar.twipe.presentation.internal.di.components.ApplicationComponent;
import com.konifar.twipe.presentation.internal.di.modules.ActivityModule;
import com.konifar.twipe.presentation.navigation.ActivityNavigator;
import javax.inject.Inject;

public class BaseActivity extends FragmentActivity {

  @Inject ActivityNavigator activityNavigator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  protected void addFragment(int containerViewId, Fragment fragment) {
    FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
    ft.add(containerViewId, fragment);
    ft.commit();
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link com.konifar.twipe.presentation.internal.di.components.ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((TwipeApplication) getApplication()).getApplicationComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link com.konifar.twipe.presentation.internal.di.modules.ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
