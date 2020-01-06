package org.shuvava.syntax;

import java.util.ArrayList;

public class EnumDemo {

  public Size getEnumFromString(String val) {
    if (val == null || val.isEmpty()) {
      val = "SMALL";
    }
    Size result = Size.valueOf(val);
    return result;
  }

  public ArrayList<Size> enumerateEnum() {
    var arr = new ArrayList<Size>();
    for (Size s: Size.values()) {
      arr.add(s);
    }
    return arr;
  }
}
