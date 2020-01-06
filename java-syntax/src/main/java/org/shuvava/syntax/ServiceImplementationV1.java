package org.shuvava.syntax;

public class ServiceImplementationV1 implements IExampleOfServiceLoader {

  @Override
  public String getApplicationVersion() {
    return "version 1";
  }
}
