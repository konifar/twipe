package com.konifar.twipe.presentation.view;

import com.konifar.twipe.domain.model.TweetModel;
import java.util.Collection;

public interface TweetListView extends LoadDataView {
  void renderTweetList(Collection<TweetModel> tweetModelCollection);

  void showTweet(TweetModel tweetModel);
}
