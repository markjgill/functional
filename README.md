## Functional

A library of Functions to make functional coding in Java easy

### Import

...into a Maven project

```xml
<dependency>
    <groupId>com.markjgill</groupId>
    <artifactId>functional</artifactId>
    <version>0.1.0</version>
</dependency>
```

...into a Gradle project

```gradle
api 'com.markjgill:functional:0.1.0'
```

### Usage

- All functions are static, therefore we recommend statically importing the classes where possible.
- IT is possible to use anonymous functions, however you may need to explicitly add generics as required.
- Functions can be composed together, using Java's `Function.compose` or `Function.andThen`.
- Composed functions may start with a function of any arity, but subsequent functions must be unary.
- Collection-based functions accept vavr.io's `Traversable` interface.

### Examples

Compose functions against a list:

```java
Function<Integer, Integer> tripler = x -> x * 3;

Collection.<Integer>reverse()
        .compose(map(tripler))
        .apply(List.of(1, 2, 3, 4)); // [12, 9, 6, 3]
```

Compose functions against a string:

```java
between("a", "f")
        .compose(defaultTo("z"))
        .apply("c"); // true 
```

```java
between("a", "f")
        .compose(defaultTo("z"))
        .apply(null); // false
```