package org.shuvava.syntax;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class AnonymousSubclassDemoTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void InlineArrayInitialization() {
    var instance = new AnonymousSubclassDemo();
    var result = instance.InlineArrayInitialization();
    assertFalse(result.isEmpty());
  }

  @Test
  public void InitAnonymousSubclass() {
    var instance = new AnonymousSubclassDemo();
    var result = instance.InitAnonymousSubclass();
    String out = outContent.toString();

    assertFalse(out.isBlank());
  }
}
