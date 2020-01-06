package org.shuvava.syntax;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.*;

public class ServiceLoaderFactoryTest {
  private static ServiceLoaderFactory instance;

  @BeforeAll
  static void setUp(){
    instance = new ServiceLoaderFactory();
  }

  @DisplayName("Test ServiceLoader")
  @Test
  public void loadImplementations() {
    var loaders = instance.loadImplementations();
    ArrayList<String> result = new ArrayList<>();
    for (IExampleOfServiceLoader item: loaders) {
      result.add(item.getApplicationVersion());
    }
    assertEquals(2, result.size());
  }
}
