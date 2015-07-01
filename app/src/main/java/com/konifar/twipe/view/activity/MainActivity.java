package com.konifar.twipe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.konifar.twipe.R;
import com.konifar.twipe.dao.UserDao;
import com.konifar.twipe.dao.UserDaoImpl;
import com.konifar.twipe.model.pojo.UserModel;
import com.konifar.twipe.view.adapter.TweetListPagerAdappter;
import com.konifar.twipe.view.fragment.TweetListFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @InjectView(R.id.nav_view) NavigationView navigationView;
  @InjectView(R.id.img_user_cover) SimpleDraweeView imgUserCover;
  @InjectView(R.id.img_user) SimpleDraweeView imgUser;
  @InjectView(R.id.txt_user_name) TextView txtUserName;
  @InjectView(R.id.txt_user_account) TextView txtUserAccount;
  @InjectView(R.id.tab_layout) TabLayout tabLayout;
  @InjectView(R.id.view_pager) ViewPager viewPager;

  private ActionBarDrawerToggle toggle;
  private UserDao userDao;

  public static void start(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    userDao = UserDaoImpl.getInstance(this);
    initActionBar();

    if (Twitter.getSessionManager().getActiveSession() == null) {
      LoginActivity.start(this);
      finish();
    } else {
      if (savedInstanceState == null) {
        initDrawer();
        initTabLayout();
      }
    }
  }

  @Override protected void onResume() {
    super.onResume();
    EventBus.getDefault().register(this);
  }

  @Override protected void onPause() {
    super.onPause();
    EventBus.getDefault().unregister(this);
  }

  private void initTabLayout() {
    TweetListPagerAdappter adapter = new TweetListPagerAdappter(getSupportFragmentManager());
    adapter.addFragment(TweetListFragment.create("0"), "Home");
    adapter.addFragment(TweetListFragment.create("1"), "Profile");
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void initActionBar() {
    setSupportActionBar(toolbar);
    ActionBar bar = getSupportActionBar();
    if (bar != null) bar.setDisplayHomeAsUpEnabled(true);
  }

  private void initDrawer() {
    TwitterSession session = Twitter.getSessionManager().getActiveSession();
    userDao.getUser(session.getUserId(), null);
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

  public void onEventMainThread(UserDao.OnUserLoadedEvent event) {
    UserModel userModel = event.userModel;
    if (userModel != null) {
      imgUser.setImageURI(Uri.parse(userModel.getProfileImageUrl()));
      imgUserCover.setImageURI(Uri.parse(userModel.getProfileBannerUrl()));
      txtUserName.setText(userModel.getName());
      txtUserAccount.setText(userModel.getScreenName());
    }
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
