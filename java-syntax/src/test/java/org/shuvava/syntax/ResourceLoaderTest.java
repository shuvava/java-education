package org.shuvava.syntax;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class ResourceLoaderTest {
  private ResourceLoader instance;

  @BeforeEach
  public void Setup() {
    instance = new ResourceLoader();
  }

  @Test
  public void loadResource() throws IOException {
    String result = instance.loadResource("");
    System.out.println("Result data:" + result);
    assertFalse(result.isEmpty());
  }

  @Test
  public void loadResourceV2() throws IOException, URISyntaxException {
    String result = instance.loadResourceV2();
    System.out.println("Result data:" + result);
    assertFalse(result.isEmpty());
  }
}
