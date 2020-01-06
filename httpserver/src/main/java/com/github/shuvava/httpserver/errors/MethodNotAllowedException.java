package com.github.shuvava.httpserver.errors;

class MethodNotAllowedException extends ApplicationException {

  MethodNotAllowedException(int code, String message) {
    super(code, message);
  }
}
