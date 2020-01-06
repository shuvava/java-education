package com.github.shuvava.DependencyInjection;

import org.springframework.stereotype.Component;

@Component
public class SimpleServiceUpperImpl implements SimpleService {
  public String echo(final String value) {
    return value.toUpperCase();
  }
}
