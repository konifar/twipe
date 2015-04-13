package com.konifar.twipe.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.konifar.twipe.R;
import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.presentation.internal.di.components.TweetComponent;
import com.konifar.twipe.presentation.presenter.TweetListPresenter;
import com.konifar.twipe.presentation.view.TweetListView;
import com.konifar.twipe.presentation.view.adapter.TweetAdapter;
import java.util.Collection;
import javax.inject.Inject;

public class TweetListFragment extends BaseFragment implements TweetListView {

  @Inject TweetListPresenter tweetListPresenter;
  @InjectView(R.id.listview) ListView listview;
  @InjectView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;
  @InjectView(R.id.txt_error) TextView txtError;

  private TweetAdapter tweetsAdapter;
  private TweetListListener tweetListListener;
  //private UsersAdapter.OnItemClickListener onItemClickListener =
  //    new UsersAdapter.OnItemClickListener() {
  //      @Override public void onUserItemClicked(UserModel userModel) {
  //
  //      }
  //    };

  public TweetListFragment() {
    super();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof TweetListListener) {
      this.tweetListListener = (TweetListListener) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_tweet_list, container, false);
    ButterKnife.inject(this, view);

    return view;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    init();
    loadTweetList();
  }

  @Override public void onResume() {
    super.onResume();
    this.tweetListPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.tweetListPresenter.pause();
  }

  private void init() {
    this.getComponent(TweetComponent.class).inject(this);
    this.tweetListPresenter.setView(this);
    this.tweetsAdapter = new TweetAdapter(getActivity());
    this.listview.setAdapter(tweetsAdapter);
    //this.tweetsAdapter.setOnItemClickListener(onItemClickListener);
  }

  @Override public void showLoading() {
    //this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    //this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    //this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    //this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void renderTweetList(Collection<TweetModel> tweetModels) {
    if (tweetModels != null) {
      tweetsAdapter.addAll(tweetModels);
    }
  }

  @Override public void showTweet(TweetModel tweetModel) {
    if (this.tweetListListener != null) {
      this.tweetListListener.onTweetClicked(tweetModel);
    }
  }

  @Override public void showError(String message) {
    //
  }

  @Override public Context getContext() {
    return this.getActivity().getApplicationContext();
  }

  private void loadTweetList() {
    this.tweetListPresenter.loadTweetList();
  }

  //@OnClick(R.id.bt_retry) void onButtonRetryClick() {
  //  //
  //}

  public interface TweetListListener {
    void onTweetClicked(final TweetModel tweetModel);
  }
}
