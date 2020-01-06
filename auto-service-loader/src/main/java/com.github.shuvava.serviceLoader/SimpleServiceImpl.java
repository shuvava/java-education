package com.github.shuvava.serviceLoader;

import com.google.auto.service.AutoService;

@AutoService(SimpleService.class)
public class SimpleServiceImpl implements SimpleService {
  public String echo(final String value) {
    return value;
  }
}
