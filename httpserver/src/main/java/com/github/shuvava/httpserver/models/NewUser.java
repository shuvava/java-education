package com.github.shuvava.httpserver.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewUser {

  String login;
  String password;
}
