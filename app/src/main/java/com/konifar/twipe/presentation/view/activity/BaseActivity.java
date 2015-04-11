package com.konifar.twipe.presentation.view.activity;

import android.app.Activity;
import android.os.Bundle;
import com.konifar.twipe.R;
import com.konifar.twipe.TwipeApplication;
import com.konifar.twipe.presentation.internal.di.components.ApplicationComponent;
import com.konifar.twipe.presentation.internal.di.modules.ActivityModule;
import com.konifar.twipe.presentation.navigation.ActivityNavigator;
import javax.inject.Inject;

public class BaseActivity extends Activity {

  @Inject ActivityNavigator activityNavigator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
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
