package com.github.shuvava.serviceLoader;

import java.util.ServiceLoader;

public class ServiceLoaderExample {
  public static void main(String [] args) {
    //final ServiceLoader<SimpleService> services = ServiceLoader.load(SimpleService.class);
    final ServiceLoader<SimpleService> services = getInstances(SimpleService.class);
    for (SimpleService service : services) {
      System.out.println("Echo: " + service.echo("test"));
    }
  }

  public static <T> ServiceLoader<T> getInstances(Class<T> service){
    return ServiceLoader.load(service);
  }
}
