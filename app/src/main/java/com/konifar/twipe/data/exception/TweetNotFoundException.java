package com.konifar.twipe.data.exception;

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
