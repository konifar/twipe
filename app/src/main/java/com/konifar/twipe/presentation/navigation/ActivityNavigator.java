package com.konifar.twipe.presentation.navigation;

import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class ActivityNavigator {

  @Inject
  public void ActivityNavigator() {
    // Do nothing
  }

  /**
   * Goes to the twitter list screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToTwitterList(Context context) {
    if (context != null) {
      // TODO
    }
  }
}
