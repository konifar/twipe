package com.konifar.twipe.presentation.presenter;

import android.support.annotation.NonNull;
import com.konifar.twipe.domain.exception.ErrorBundle;
import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.domain.usecase.GetHomeTweetListUseCase;
import com.konifar.twipe.presentation.internal.di.PerActivity;
import com.konifar.twipe.presentation.view.TweetListView;
import de.greenrobot.event.EventBus;
import java.util.Collection;
import javax.inject.Inject;

@PerActivity
public class TweetListPresenter implements Presenter {

  private final GetHomeTweetListUseCase getHomeTweetListUseCase;
  private TweetListView tweetListView;

  @Inject
  public TweetListPresenter(GetHomeTweetListUseCase getHomeTweetListUseCase) {
    this.getHomeTweetListUseCase = getHomeTweetListUseCase;
  }

  public void setView(@NonNull TweetListView view) {
    this.tweetListView = view;
  }

  @Override public void resume() {
    EventBus.getDefault().register(this);
  }

  @Override public void pause() {
    EventBus.getDefault().unregister(this);
  }

  public void loadTweetList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getTweetList();
  }

  public void onTweetClicked(TweetModel tweetModel) {
    this.tweetListView.showTweet(tweetModel);
  }

  private void showViewLoading() {
    this.tweetListView.showLoading();
  }

  private void hideViewLoading() {
    this.tweetListView.hideLoading();
  }

  private void showViewRetry() {
    this.tweetListView.showRetry();
  }

  private void hideViewRetry() {
    this.tweetListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    this.tweetListView.showError("");
  }

  private void showUsersCollectionInView(Collection<TweetModel> tweets) {
    this.tweetListView.renderTweetList(tweets);
  }

  private void getTweetList() {
    this.getHomeTweetListUseCase.execute(null);
  }

  public void onEventMainThread(GetHomeTweetListUseCase.OnLoadedEvent event) {
    showUsersCollectionInView(event.tweetModels);
    hideViewLoading();
  }

  public void onEventMainThread(GetHomeTweetListUseCase.OnErrorEvent event) {
    hideViewLoading();
    showErrorMessage(event.errorBundle);
    showViewRetry();
  }
}
