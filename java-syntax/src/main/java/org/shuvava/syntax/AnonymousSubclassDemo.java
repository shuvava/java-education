package org.shuvava.syntax;

import java.util.ArrayList;

public class AnonymousSubclassDemo {

  public ArrayList<String> InitAnonymousSubclass() {
    ArrayList<String> names = new ArrayList<String>(100) {
      public void add(int index, String element) {
        super.add(index, element);
        System.out.printf("Adding %s at %d\n", element, index);
      }
    };

    names.add(0, "Peter");
    names.add(1, "Paul");
    names.add(0, "Mary");
    return names;
  }

  public ArrayList<String> InlineArrayInitialization() {
    var array = new ArrayList<String>() {{ add("Harry"); add("Sally"); }};
    invite(array);
    return array;
  }

  public static void invite(ArrayList<String> friends) {
    System.out.println("Guest list: " + friends);
  }
}
