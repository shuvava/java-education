package org.shuvava.syntax;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ComparatorDemoTest {
  private Person[] people;

  @BeforeEach
  public void setUpTest(){
    people = new Person[]{
        new Person("George", "Washington"),
        new Person("John", "Adams"),
        new Person("Thomas", "Jefferson"),
    };
    }

  @Test
  public void simpleSort() {
    Arrays.sort(people, Comparator.comparing(Person::getName));
    assertEquals("George  Washington", people[0].getName());
  }

  @Test
  public void complicatedSort() {
    Arrays.sort(people,
        Comparator.comparing(Person::getLast)
            .thenComparing(Person::getFirst));
    assertEquals("John  Adams", people[0].getName());
  }

  @Test
  public void lambdaSort() {
    Arrays.sort(people, Comparator.comparingInt(p -> p.getName().length()));
    assertEquals("John  Adams", people[0].getName());
  }
}
