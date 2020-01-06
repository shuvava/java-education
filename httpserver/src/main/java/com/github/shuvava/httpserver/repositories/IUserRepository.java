package com.github.shuvava.httpserver.repositories;

import com.github.shuvava.httpserver.models.NewUser;

public interface IUserRepository {
  String create(NewUser user);
}
