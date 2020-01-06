package com.github.shuvava.httpserver.errors;

class InvalidRequestException extends ApplicationException {

  public InvalidRequestException(int code, String message) {
    super(code, message);
  }
}
