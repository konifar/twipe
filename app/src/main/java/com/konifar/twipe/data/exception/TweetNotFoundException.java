package com.konifar.twipe.data.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */
public class TweetNotFoundException extends Exception {

  public TweetNotFoundException() {
    super();
  }

  public TweetNotFoundException(final String message) {
    super(message);
  }

  public TweetNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public TweetNotFoundException(final Throwable cause) {
    super(cause);
  }
}
