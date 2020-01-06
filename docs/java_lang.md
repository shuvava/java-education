# Java language specifics

## Folder structure

[Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

| Path | Description |
|:-:|---|
|  `src/main/java` | Application/Library sources |
| `src/main/resources` | Application/Library resources |
| `src/main/webapp` | Web application sources |
| `src/test/java` | Test sources |
| `src/test/resources` | Test resources |
| `src/site` | Site |

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

## Using for auto closing resources

```java
  try (in; out) { // Effectively final variables
      while (in.hasNext())
          out.println(in.next().toLowerCase());
  }
```

## Null check

The Objects class has a method for convenient null checks of parameters.

```java
public void process(String direction) {
  this.direction = Objects.requireNonNull(direction);
  ...
}
```

## Enum

if you like, add constructors, methods, and fields to an enumerated type.

```java
public enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String abbreviation;

    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() { return abbreviation; }
}
```

### static initializer

```java
public enum Modifier {
  PUBLIC, PRIVATE, PROTECTED, STATIC, FINAL, ABSTRACT;
  private int mask;
  static {
    int maskBit = 1;
    for (Modifier m : Modifier.values()) {
      m.mask = maskBit;
      maskBit *= 2;
    }
  }
...
}
```

## Methods

### Arbitrary Number of Arguments

You can use a construct called [varargs](https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html#varargs) to pass an arbitrary number of values to a method. You use varargs when you don't know how many of a particular type of argument will be passed to the method. It's a shortcut to creating an array manually (the previous method could have used varargs rather than an array).

To use varargs, you follow the type of the last parameter by an ellipsis (three dots, ...), then a space, and the parameter name. The method can then be called with any number of that parameter, including none.

```java
public BulkRequest add(DocWriteRequest<?>... requests) {
  for (DocWriteRequest<?> request : requests) {
      add(request);
  }
  return this;
}
```

## Class

### The final Keyword

When you declare a method as `final`, no subclass can override it.

Occasionally, you may want to prevent someone from forming a subclass from one of your classes. Use the `final` modifier in the class definition to in- dicate this.

```java
public final class Executive extends Manager {
  ...
}
```

### Java Inheritance (Subclass and Superclass)

In Java, it is possible to inherit attributes and methods from one class to another. We group the "inheritance concept" into two categories:

* subclass (child) - the class that inherits from another class
* superclass (parent) - the class being inherited from

To inherit from a class, use the `extends` keyword.

### Method overriding

```java
public class Manager extends Employee {
  public Manager(String name, double salary) {
        // subclass construction
        super(name, salary);
        bonus = 0;
    }

  public double getSalary() {
    // Overrides superclass method
    return super.getSalary() + bonus;
  }
}
```

### Anonymous Subclasses

Just as you can have an anonymous class that implements an interface, you can have an anonymous class that extends a superclass. 

```java
ArrayList<String> names = new ArrayList<String>(100) { public void add(int index, String element) {
super.add(index, element);
System.out.printf("Adding %s at %d\n", element, index); }
};
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

### Predefined Interfaces

#### The Comparable Interface

When calling x.compareTo(y), the compareTo method returns an integer value to indicate whether x or y should come first. A positive return value (not necessarily
    1) indicates that x should come after y. A negative integer (not necessarily -1) is returned when x should come before y. If x and y are considered
    equal, the returned value is 0.
To compare to Objects

```java
public interface Comparator<T> { int compare(T first, T second);
}
class LengthComparator implements Comparator<String> { public int compare(String first, String second) {
return first.length() - second.length(); }
}
```

#### The Comparator Interface

To deal with this situation, there is a second version of the Arrays.sort method whose parameters are an array and a comparator—an instance of a class that
implements the Comparator interface.

```java
public interface Comparator<T> {
int compare(T first, T second);
}
```

#### Runnable Interface

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

#### Functional Interfaces

You can supply a lambda expression whenever an object of an interface with a single abstract method is expected. Such an interface is called a *functional interface*.

```java
Arrays.sort(words,
  (first, second) -> first.length() - second.length());
```

[FULL list of Common Functional interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

##### Implementing Your Own Functional Interfaces

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

### Lambda Expression

```java
(String first, String second) -> first.length()-second.lenght();
```

### Method References

As you can see from these examples, the :: operator separates the method name from the name of a class or object. There are three variations:

1. Class::instanceMethod
1. Class::staticMethod
1. object::instanceMethod

```java
Arrays.sort(strings, (x, y) -> x.compareToIgnoreCase(y));
Arrays.sort(strings, String::compareToIgnoreCase);
```

### Constructor References

Constructor references are just like method references, except that the name of the method is new. For example, `Employee::new` is a reference to an Employee constructor. If the class has more than one constructor, then it depends on the context which constructor is chosen.

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

## Proxy class

The Proxy class can create, at runtime, new classes that implement a given interface or set of interfaces. Such proxies are only necessary when you don’t yet know at compile time which interfaces you need to implement.

```java
return Proxy.newProxyInstance(
                null, value.getClass().getInterfaces(), 
                (Object proxy, Method m, Object[] margs) -> {
                    System.out.println(value + "." + m.getName() + Arrays.toString(margs));
                    return m.invoke(value, margs);
                });
```
A proxy class has all methods required by the specified interfaces, and all methods defined in the Object class (toString, equals, and so on). However, since you cannot define new code for these methods at runtime, you supply an invocation handler, an object of a class that implements the InvocationHandler interface. That interface has a single method:
`Object invoke(Object proxy, Method method, Object[] args)`

## Generics

### Generic methods

When you declare a generic method, the type parameter is placed after the modifiers (such as public and static) and before the return type

```java
public class Arrays {
  public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
```

#### Type bound

Sometimes, the type parameters of a generic class or method need to fulfill certain requirements. You can specify a type bound to require that the type extends certain classes or implements certain interfaces

Suppose, for example, you have an ArrayList of objects of a class that implements the AutoCloseable interface, and you want to close them all:

```java
public static <T extends AutoCloseable> void closeAll(ArrayList<T> elems) throws Exception {
  for (T elem : elems) elem.close();
}
```
The type bound extends AutoCloseable ensures that the element type is a subtype of AutoCloseable. Therefore, the call elem.close() is valid.

#### Subtype wildcard

The wildcard type ? extends `Employee` indicates some unknown subtype of `Employee`. You can call this method with an `ArrayList<Employee>` or an array list of a subtype, such as `ArrayList<Manager>`.

```java
public static void printNames(ArrayList<? extends Employee> staff) {
  for (int i = 0; i < staff.size(); i++) {
    Employee e = staff.get(i);
    System.out.println(e.getName());
  }
}
```

Whatever type ? denotes, it is a subtype of Employee, and the result of staff.get(i) can be assigned to the Employee variable e. 

#### Supertype wildcard

The wildcard type `? extends Employee` denotes an arbitrary subtype of `Employee`. The converse is the wildcard type `? super Employee` which denotes a supertype of Employee.

Now suppose you want to use a `Predicate<Object>` instead, for example

```java
Predicate<Object> evenLength = e -> e.toString().length() % 2 == 0; printAll(employees, evenLength);
```

This should not be a problem. After all, *every Employee is an Object with a toString method*. However, like all generic types, the Predicate interface is invariant, and there is no relationship between `Predicate<Employee>` and `Predicate<Object>`.
The remedy is to allow any `Predicate<? super Employee>`:

```java
public interface Predicate<T> {
  boolean test(T arg);
}
public static void printAll(Employee[] staff, Predicate<? super Employee> filter) {
  for (Employee e : staff)
    if (filter.test(e))
      System.out.println(e.getName());
}
```

This is a generic method that works for arrays of any type. The type param- eter is the type of the array that is being passed. However, it suffers from the limitation that you saw in the preceding section. The type parameter of Predicate must exactly match the type parameter of the method.

This method takes a filter for elements of type T or any supertype of T.

```java
public static <T> void printAll(T[] elements, Predicate<? super T> filter)
```

example of complex declaration

`public static <T extends Comparable<? super T>> void sort(List<T> list)`

Comparable interface is again generic:

```java
public interface Comparable<T> {
  int compareTo(T other);
}
```

Its type parameter specifies the argument type of the compareTo method. So, it would seem that Collections.sort could be declared as 

`public static <T extends Comparable<T>> void sort(List<T> list)`

But that is too restrictive. Suppose that the Employee class implements `Comparable<Employee>`, comparing employees by salary. And suppose that the Manager class extends Employee. Note that it implements `Comparable<Employee>`, and not `Comparable<Manager>`. Therefore, Manager is not a subtype of `Comparable<Manager>`, but it is a subtype of `Comparable<? super Manager>`.

#### Unbounded wildcard

It is possible to have unbounded wildcards for situations where you only do very generic operations. For example, here is a method to check whether an ArrayList has any null elements:

```java
public static boolean hasNulls(ArrayList<?> elements) {     for (Object e : elements) {
    if (e == null) return true;
  }
  return false;
}
```
