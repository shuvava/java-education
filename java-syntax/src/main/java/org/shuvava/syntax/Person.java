package org.shuvava.syntax;

import lombok.Data;

public @Data class Person {
  private final String first;
  private final String middle;
  private final String last;

  public Person(String first, String last) {
    this.first = first;
    this.last = last;
    this.middle = "";
  }

  public String getName() {
    if (middle == null) {
      return first + " " + last;
    }
    else {
      return first + " " + middle + " " + last;
    }
  }

  public String toString() {
    return getName();
  }
}
