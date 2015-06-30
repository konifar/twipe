package com.konifar.twipe.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.konifar.twipe.R;
import com.konifar.twipe.dao.TweetDao;
import com.konifar.twipe.dao.TweetDaoImpl;
import com.konifar.twipe.model.pojo.TweetModel;
import com.konifar.twipe.view.adapter.TweetAdapter;
import com.konifar.twipe.view.listener.EndlessScrollListener;
import de.greenrobot.event.EventBus;
import java.util.Collection;

public class TweetListFragment extends BaseFragment {

  private static final String ARG_TAG = "arg_tag";

  @InjectView(R.id.recyclerView) RecyclerView recyclerView;
  @InjectView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefresh;
  @InjectView(R.id.txt_error) TextView txtError;

  private String tag;
  private TweetDao tweetDao;
  private TweetAdapter tweetsAdapter;
  private TweetListListener tweetListListener;

  public static TweetListFragment create(String tag) {
    TweetListFragment fragment = new TweetListFragment();
    Bundle args = new Bundle();
    args.putString(ARG_TAG, tag);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onResume() {
    super.onResume();
    EventBus.getDefault().register(this);
  }

  @Override public void onPause() {
    super.onResume();
    EventBus.getDefault().unregister(this);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof TweetListListener) {
      this.tweetListListener = (TweetListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      this.tag = getArguments().getString(ARG_TAG);
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

  private void init() {
    initRecyclerView();
    tweetDao = TweetDaoImpl.getInstance(getActivity());
  }

  private void initRecyclerView() {
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    tweetsAdapter = new TweetAdapter(getActivity());
    recyclerView.setAdapter(tweetsAdapter);
    recyclerView.addOnScrollListener(
        new EndlessScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
          @Override public void onLoadMore(int page) {
            tweetDao.getHomeTweetList(tweetsAdapter.getLastItem().getId() - 1, tag);
          }
        });
  }

  private void showLoading() {
    //this.rl_progress.setVisibility(View.VISIBLE);
    getActivity().setProgressBarIndeterminateVisibility(true);
  }

  private void hideLoading() {
    //this.rl_progress.setVisibility(View.GONE);
    getActivity().setProgressBarIndeterminateVisibility(false);
  }

  private void showRetry() {
    //this.rl_retry.setVisibility(View.VISIBLE);
  }

  private void hideRetry() {
    //this.rl_retry.setVisibility(View.GONE);
  }

  private void renderTweetList(Collection<TweetModel> tweetModels) {
    if (tweetModels != null) {
      tweetsAdapter.addAll(tweetModels);
    }
  }

  private void showTweet(TweetModel tweetModel) {
    if (this.tweetListListener != null) {
      this.tweetListListener.onTweetClicked(tweetModel);
    }
  }

  private void showError(String message) {
    //
  }

  private void loadTweetList() {
    hideRetry();
    showLoading();
    tweetDao.getHomeTweetList(null, tag);
  }

  public void onEventMainThread(TweetDao.OnLoadedEvent event) {
    if (tag != null && tag.equals(event.tag)) {
      hideLoading();
      renderTweetList(event.tweetModels);
    }
  }

  public void onEventMainThread(TweetDao.OnErrorEvent event) {
    if (tag != null && tag.equals(event.tag)) {
      hideLoading();
      showError(event.exception.getMessage());
      showRetry();
    }
  }

  public interface TweetListListener {
    void onTweetClicked(final TweetModel tweetModel);
  }
}
