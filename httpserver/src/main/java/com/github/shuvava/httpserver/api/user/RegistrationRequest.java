package com.github.shuvava.httpserver.api.user;

import lombok.Data;

@Data
class RegistrationRequest {

  String login;
  String password;
}
