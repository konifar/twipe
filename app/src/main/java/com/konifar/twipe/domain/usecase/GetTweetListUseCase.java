package com.konifar.twipe.domain.usecase;

import com.konifar.twipe.domain.exception.ErrorBundle;
import com.konifar.twipe.domain.model.TweetModel;
import com.twitter.sdk.android.core.models.User;
import java.util.Collection;

/**
 * This interface represents a execution unit for a use case to
 * get a collection of {@link TweetModel}.
 * By convention this use case (UseCase) implementation will return the result using a Callback.
 * That callback should be executed in the UI thread.
 */
interface GetTweetListUseCase extends UseCase {
  /**
   * Executes this user case.
   *
   * @param callback A {@link GetTweetListUseCase.Callback} used to notify the client.
   */
  void execute(Callback callback);

  /**
   * Callback used to be notified when either a users collection has been loaded or an error
   * happened.
   */
  interface Callback {
    void onUserListLoaded(Collection<User> usersCollection);

    void onError(ErrorBundle errorBundle);
  }
}
