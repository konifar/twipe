package com.konifar.twipe.domain.usecase;

/**
 * Common interface for an UseCase {@link java.lang.Runnable} declared in the application.
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a Callback that should
 * be executed in the UI thread.
 */
public interface UseCase extends Runnable {
  /**
   * Everything inside this method will be executed asynchronously.
   */
  void run();
}
