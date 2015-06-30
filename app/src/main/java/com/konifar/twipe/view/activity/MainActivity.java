package com.konifar.twipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.konifar.twipe.R;
import com.konifar.twipe.view.fragment.TweetListFragment;
import com.twitter.sdk.android.Twitter;

public class MainActivity extends BaseActivity {

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @InjectView(R.id.nav_view) NavigationView navigationView;

  private ActionBarDrawerToggle toggle;

  public static void start(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    initDrawer();
    initActionBar();

    if (Twitter.getSessionManager().getActiveSession() == null) {
      LoginActivity.start(this);
      finish();
    } else {
      if (savedInstanceState == null) {
        addFragment(R.id.container, new TweetListFragment());
      }
    }
  }

  private void initActionBar() {
    setSupportActionBar(toolbar);
    ActionBar bar = getSupportActionBar();
    if (bar != null) bar.setDisplayHomeAsUpEnabled(true);
  }

  private void initDrawer() {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
            drawerLayout.closeDrawers();
            return true;
          }
        });

    toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
    toggle.setDrawerIndicatorEnabled(true);
    drawerLayout.setDrawerListener(toggle);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (toggle.onOptionsItemSelected(item)) return true;

    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    toggle.syncState();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    toggle.onConfigurationChanged(newConfig);
  }
}
