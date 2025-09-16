package com.da_learn.backend.level.one.exception.handler;

public class TrackNotFoundException extends RuntimeException {
  public TrackNotFoundException(String name) {
    super("Track with name '" + name + "' not found.");
  }
}
