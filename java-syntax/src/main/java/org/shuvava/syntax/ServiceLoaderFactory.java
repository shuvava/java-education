package org.shuvava.syntax;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceLoaderFactory {

  /***
   * Build-in service loader example
   * @return implementations defined in META-INF/services
   */
  public ServiceLoader<IExampleOfServiceLoader> loadImplementations() {
    var services = ServiceLoader.load(IExampleOfServiceLoader.class);
    return services;
  }
  public Iterator<IExampleOfServiceLoader> getProviders(boolean refresh) {
    var services = loadImplementations();
    if (refresh) {
      services.reload();
    }
    return services.iterator();
  }
}
