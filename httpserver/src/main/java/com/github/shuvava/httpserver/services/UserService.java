package com.github.shuvava.httpserver.services;

import com.github.shuvava.httpserver.models.NewUser;
import com.github.shuvava.httpserver.repositories.IUserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {

  private final IUserRepository userRepository;

  public String create(NewUser user) {
    return userRepository.create(user);
  }
}
