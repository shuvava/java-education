package com.github.shuvava.serviceLoader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.*;

public class ServiceLoaderExampleTest {
  private ServiceLoaderExample instance;

  @BeforeEach
  public void Setup() {
    instance = new ServiceLoaderExample();
  }

  @Test
  public void AutoServiceLoader(){
    var loaders = ServiceLoaderExample.getInstances(SimpleService.class);
    ArrayList<String> result = new ArrayList<>();
    for (var item: loaders) {
      result.add(item.echo("test"));
    }
    assertEquals(2, result.size());
  }
}
