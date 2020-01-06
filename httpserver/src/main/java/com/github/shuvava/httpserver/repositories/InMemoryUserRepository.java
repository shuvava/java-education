package com.github.shuvava.httpserver.repositories;

import com.github.shuvava.httpserver.models.NewUser;
import com.github.shuvava.httpserver.models.User;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements IUserRepository {
  private static final Map USERS_STORE = new ConcurrentHashMap();

  @Override
  public String create(NewUser newUser) {
    String id = UUID.randomUUID().toString();
    User user = User.builder()
        .id(id)
        .login(newUser.getLogin())
        .password(newUser.getPassword())
        .build();
    USERS_STORE.put(newUser.getLogin(), user);

    return id;
  }
}
