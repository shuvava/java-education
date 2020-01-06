package com.github.shuvava.httpserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shuvava.httpserver.errors.GlobalExceptionHandler;
import com.github.shuvava.httpserver.repositories.IUserRepository;
import com.github.shuvava.httpserver.repositories.InMemoryUserRepository;
import com.github.shuvava.httpserver.services.UserService;

public class Configuration {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final IUserRepository USER_REPOSITORY = new InMemoryUserRepository();
  private static final UserService USER_SERVICE = new UserService(USER_REPOSITORY);
  private static final GlobalExceptionHandler GLOBAL_ERROR_HANDLER = new GlobalExceptionHandler(OBJECT_MAPPER);

  static ObjectMapper getObjectMapper() {
    return OBJECT_MAPPER;
  }

  static UserService getUserService() {
    return USER_SERVICE;
  }

  static IUserRepository getUserRepository() {
    return USER_REPOSITORY;
  }

  public static GlobalExceptionHandler getErrorHandler() {
    return GLOBAL_ERROR_HANDLER;
  }
}
