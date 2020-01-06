package org.shuvava.syntax;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class EnumDemoTest {
  private final EnumDemo instance = new EnumDemo();
  @Test
  public void getEnumFromString() {
    var result = instance.getEnumFromString(null);
    assertEquals(Size.SMALL, result);
  }

  @Test
  public void enumerateEnum() {
    var result = instance.enumerateEnum();
    assertEquals(4, result.size());
  }
}
