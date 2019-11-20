# Java language specifics

## Variable
all variables should be initialized at first 
```java
int count;
count++; // Error—uses an uninitialized variable
```

It is legal to defer the initialization of a final variable, provided it is initialized
exactly once before it is used for the first time. For example, the following
is legal:
```java
final int DAYS_IN_FEBRUARY;
if (leapYear) {
DAYS_IN_FEBRUARY = 29;
} else {
DAYS_IN_FEBRUARY = 28;
}
```

That is the reason for calling them “final” variables. Once a value has been
assigned, it is final and can never be changed.

## Constant

`final int DAY_PER_WEEK = 7`
Any variable defined in an interface is automatically public static final.
For example, the SwingConstants interface defines constants for compass directions:
```java
public interface SwingConstants {
int NORTH = 1;
int NORTH_EAST = 2;
int EAST = 3;
...
}
```

## Interface

### Default method
You can supply a default implementation for any interface method. You must tag such a method with the default modifier.
```java
public interface IntSequence {
default boolean hasNext() { return true; }
// By default, sequences are infinite
int next();
}
```
A class implementing this interface can choose to override the hasNext method or to inherit the default implementation.
Default methods put an end to the classic pattern of providing an interface and a companion class that implements most or all of
its methods

### Resolving Default Method Conflicts
```java
public interface Person {
  String getName();
  default int getId() { return 0; }
}
public interface Identified {
  default int getId() { return Math.abs(hashCode()); }
}
public class Employee implements Person, Identified {
  public int getId() { return Identified.super.getId(); }
...
}
```

### Functional Interfaces
You can supply a lambda expression whenever an object of an interface with a single abstract method is expected. Such an interface is called a *functional
interface*.
```java
Arrays.sort(words,
  (first, second) -> first.length() - second.length());
```

#### Implementing Your Own Functional Interfaces

```java
@FunctionalInterface
public interface PixelFunction {
  Color apply(int x, int y);
}
BufferedImage createImage(int width, int height, PixelFunction f) {
    BufferedImage image = new BufferedImage(width, height,
    BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++)
    for (int y = 0; y < height; y++) {
        Color color = f.apply(x, y);
        image.setRGB(x, y, color.getRGB());
    }
    return image;
}
BufferedImage frenchFlag = createImage(150, 100,
  (x, y) -> x < 50 ? Color.BLUE : x < 100 ? Color.WHITE : Color.RED);
```

#### Method References
As you can see from these examples, the :: operator separates the method name from the name of a class or object. There are three variations:
1. Class::instanceMethod
1. Class::staticMethod
1. object::instanceMethod

```java
Arrays.sort(strings, (x, y) -> x.compareToIgnoreCase(y));
Arrays.sort(strings, String::compareToIgnoreCase);
```

#### Constructor References

```java
Stream<Employee> stream = names.stream().map(Employee::new);
```
### Anonymous Classes

class anonymous:
```java
public static IntSequence randomInts(int low, int high) {
    return new IntSequence() {
        public int next() { return low + generator.nextInt(high - low + 1); }
        public boolean hasNext() { return true; }
    }
}
```
The expression
`new Interface() { methods }`

### Examples of Interfaces

* The Comparable Interface
    ```java
    public interface Comparable<T> {
    int compareTo(T other);
    }
    ```
    When calling x.compareTo(y), the compareTo method returns an integer value to indicate whether x or y should come first. A positive return value (not necessarily
    1) indicates that x should come after y. A negative integer (not necessarily -1) is returned when x should come before y. If x and y are considered
    equal, the returned value is 0.
* The Comparator Interface
    To deal with this situation, there is a second version of the Arrays.sort method whose parameters are an array and a comparator—an instance of a class that
    implements the Comparator interface.
    ```java
    public interface Comparator<T> {
    int compare(T first, T second);
    }
    ```
* The Runnable Interface

At a time when just about every processor has multiple cores, you want to
keep those cores busy. You may want to run certain tasks in a separate thread,
or give them to a thread pool for execution. To define the task, you implement
the Runnable interface. This interface has just one method.
```java
class HelloTask implements Runnable {
public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.println("Hello, World!");
        }
    }
}
Runnable task = new HelloTask();
Thread thread = new Thread(task);
thread.start();
// OR with lambda
public static void repeat(int n, Runnable action) {
for (int i = 0; i < n; i++) action.run();
}
repeat(10, () -> System.out.println("Hello, World!"));
```

[Functional interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

