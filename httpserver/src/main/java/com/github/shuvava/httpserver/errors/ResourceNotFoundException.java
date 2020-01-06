package com.github.shuvava.httpserver.errors;

class ResourceNotFoundException extends ApplicationException {

  ResourceNotFoundException(int code, String message) {
    super(code, message);
  }
}
