package com.da_learn.backend.level.one.exception.handler;

public class CharacterNotFoundException extends RuntimeException {
  public CharacterNotFoundException(String name) {
    super("Character with name '" + name + "' not found.");
  }
}
