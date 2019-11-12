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
