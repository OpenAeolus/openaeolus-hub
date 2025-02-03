package com.openaeolus.hub.exceptions;

public class AlreadyRegisterException extends RuntimeException {
  public AlreadyRegisterException(){
    super("The user already registered");
  }
}
