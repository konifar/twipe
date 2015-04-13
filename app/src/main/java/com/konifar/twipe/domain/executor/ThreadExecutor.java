package com.konifar.twipe.domain.executor;

import com.konifar.twipe.domain.usecase.UseCase;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the {@link UseCase} out of the UI thread.
 *
 * Use this class to execute an {@link UseCase}.
 */
public interface ThreadExecutor {
  /**
   * Executes a {@link Runnable}.
   *
   * @param runnable The class that implements {@link Runnable} interface.
   */
  void execute(final Runnable runnable);
}
