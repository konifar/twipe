package com.konifar.twipe.domain.usecase;

import com.konifar.twipe.domain.exception.ErrorBundle;
import com.konifar.twipe.domain.executor.ThreadExecutor;
import com.konifar.twipe.domain.model.TweetModel;
import com.konifar.twipe.domain.repository.TweetRepository;
import de.greenrobot.event.EventBus;
import java.util.Collection;
import javax.inject.Inject;

public class GetHomeTweetListUseCaseImpl implements GetHomeTweetListUseCase {
  private final TweetRepository.TweetListCallback callback =
      new TweetRepository.TweetListCallback() {
        @Override public void onTweetListLoaded(Collection<TweetModel> tweets) {
          EventBus.getDefault().post(new OnLoadedEvent(tweets));
        }

        @Override public void onError(ErrorBundle errorBundle) {
          EventBus.getDefault().post(new OnErrorEvent(errorBundle));
        }
      };
  private final TweetRepository tweetRepository;
  private final ThreadExecutor threadExecutor;
  private Long lastTweetId;

  @Inject public GetHomeTweetListUseCaseImpl(TweetRepository tweetRepository,
      ThreadExecutor threadExecutor) {
    this.tweetRepository = tweetRepository;
    this.threadExecutor = threadExecutor;
  }

  @Override public void execute(Long lastTweetId) {
    this.lastTweetId = lastTweetId;
    this.threadExecutor.execute(this);
  }

  @Override public void run() {
    tweetRepository.getHomeTweetList(lastTweetId, callback);
  }
}
