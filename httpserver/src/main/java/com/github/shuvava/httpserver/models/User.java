package com.github.shuvava.httpserver.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

  String id;
  String login;
  String password;
}
