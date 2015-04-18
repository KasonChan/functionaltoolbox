## Replace object-oriented patterns

### Replace functional interface

Functional interface allows us to call an object as if it were a function. It is
also known as **functional object**, **functoid** and **functor**. This intent 
to encapsulate a bit of program logic so that it can be passed around, stored in 
data structures and generally treated like any other first-class construct. 

#### Anonymous function

```java
    collection.function(..., new Comparator<...>() {
      public int compare(...) {
        return ...compareTo...
    }
})
```

It can be replaced using anonymous function.


```scala
    (arg1: type1, arg2: type2) => ... // anonymous function syntax

    collection.function(anonymous function)
```

#### Named function

```java
    public class ComplicatedComparator implements Comparator... {
      public int compare (...) {
        ... // Complicated logic
      }
    }
```

It can be replaced using named function.

```scala
    def namedFunction(arguments): Boolean = ...

    collection.function(namedFunction)
```

Anonymous function and named function can reduces the syntatic overhead of many
common tasks. Higher-order function doesn't need a new type for each type of 
functional interface when just existing function types will do.

### Replace state-carrying functional interface

Functional interface implementations that need state using a construct called 
**closure**. **Closure** is a construct that wraps up a function along with the 
state available to it when it was created.

```java
    public class ComposedComparator<T> implements Comparator<T> {
      private Comparator<T>[] comparators;
      public ComposedComparator(Comparator<T>... comparators) {
        this.comparators = comparators;
      }
    }
```

It can be replaced by some higher-order function.

```scala
     def comparison1(arg1: type1, arg2: type2, ...): type = ...
     def comparison2(arg1: type1, arg2: type2, ...): type = ...
     ...

     def makeComparisons(comparisons: (type, type, ...) => ...) = ...
```

### Replace command

Command encapsulates an action along with the information needed to perform it.
It is also known as action. Command contains `Command` interface and its 
implementation, a client is responsible for creating the `Command`,
an invoker is responsible for running it, and a receiver on which the 
`Command` performs its action. The following code snippets are an example of 
logging command.

```scala
    /**
     * Command class
     * Use the replace state-carrying function interface pattern
     */
    class Register(var count: Int) {
      def addCount(toCount: Int) {
        total += toCount
      }
    }
```
    
```scala
    /**
     * Client
     */
    def makeCount(register: Register, count: Int) = {
      () => {
        ...
        register.addCount(count)
      }
    }
```

```scala
    /**
     * Invoker
     */
    var counts: Vector[() => Unit] = Vector()
    def executeCount(count: () => Unit) = {
      counts = counts :+ count // Insert the new count to the counts vector
      count() // execute count
    }
```

This pattern is a replacement for the full pattern in invoker/receiver/client.

### Replace builder for immutable object

This intent to create immutable object using a friendly syntax for setting 
attributes. It is a common way of carrying data around in `java` class with a 
bunch of getters and setters.

```java
    public class ImmutableClass {
      private final type attribute;
      ...
      
      public type getAttribute() {
        return attribute;
      }
      ...
      
      private ImmutableClass(Builder builder) {
        attribute = build.attribute;
        ...
      }

      public static class Builder {
        private type attribute;
        ...

        public Builder attribute(type attribute) {
          this.attribute = attribute;
          return this;
        }
        ...

        public ImmutableClass build() {
          return new ImmutableClass(this);
        }
      }

      public static Builder newBuilder() {
        return new Builder();
      }
    }
```

This builder pattern can be replaced by 3 different ways - **immutable classes**, 
**case classes** and **tuples** in `Scala`.

#### Immutable classes

```scala
    class Class(val attribute1: Int, attribute2: Char = '', attribute3: Char)

    // A new class can be created using the constructor parameters positionally
    val class1 = new Class(1, 'A', 'B')

    // A new class can be created as named parameters
    val class2 = new Class(attribute1 = 1, attribute3 = 'A')
```

#### Case classes

A new immutable object can also be created using `case class`, except we don't
need to use the `new` operator.

```scala
    case class Class(attribute1: Int, attribute2: Char = '', attribute3: Char)

    // Create a new class using named parameters without new operator
    val class = Class(attribute = 1, attribute3 = 'A')
```

#### Tuples

Enclose the values that it contains inside of parentheses to create a tuple. 
Reference them by position to get values back or use pattern matching.

```scala
    def class = (1, 'A')

    class._1 // 1
    class._2 // A

    class match {
      case (attribute1, attribute2) => {
        attribute1 ...
        attribute2 ... 
      }
    }
```

### Replace iterator

Iterator is an object that allows us to iterator over all the objects in a 
sequence. It is intent to iterate throught the elements of a sequence in order
without indexing them. It is also known as **cursor** and **enumerator**. The 
following code snippets show filtering, prepending and accumulating a sequence.

#### Filtering

```java
    /**
     * Java
     * Filter some type of element out of sequence
     */
    public static Set<T1> function(T2 argument) {
      Set<T1> filtered = new HashSet<T1>();
      for (T1 variable: argument...) {
        if (predicate(variable)) {
          ...
        }
      }
      return filtered;
    }
```

```scala
    // Scala
    val function = Set(...: T1)

    def function(argument: T2) = argument.filter(function).toSet
```

#### Prepending

```java
    /**
     * Java
     * Prepend a list of arguments with something
     */
    public static List<T> prepend(List<T> arguments) {
      List<T> prepended = new ArrayList<T>();
      for (T argument: arguments) {
        prepended.add(Something + argument);
      }
      return prepended;
    }
```

```scala
    // Scala
    def prepend(arguments: Seq[T]) = 
      arguments.map(argument => Something + argument)

    prepend(sequence)
```

#### Accumulating

```java
    /**
     * Java
     * Accumulate a sequence
     */
    public static T accumulate(List<T> sequence) {
      T accumulator = 0;
      for (T element: sequence) {
        accumulator += element;
      }
      return accumulator;
    }
```

```scala
     // Scala
     def accumulate(sequence: Seq[T]) = 
       if (sequence.isEmpty) T 
       else sequence.reduce((accumulator, element) => accumulator + element)

     accmulate(sequence)
```

### Replace template

Template method pattern consists of an abstract class that defines some 
operation,or a set of operations, in terms of abstract suboperation. It is 
intent to specifiy the outline of an algorithm, letting callers plug in some of 
the specifics.

```java
    public abstract class Template {
      public void Operation{
        subOperation();
        ...
      }

      protected abstract void subOperation();
      ...
    }
```

Use higher-order functions to implement suboperations instead of subclassing. 
Use function composition by passing the suboperations into function builder and 
have it return a new function that does the full operation.

```scala
def makeOperation(subOperation1: () => Unit, 
                  subOperation2: () => Unit) = 
                  () => {
                    subOperation1()
                    subOperation2()
                  },
                  ...)
```

Instead of using subtypes to implement specific suboperations, use **functional 
composition** and higher-order functions. In OOP, use 
**replace dependency injection** to inject suboperations into a class,
rather than using **template method** and subclassing. This helps prevent code 
duplication. **Composition** does better job of making an API explicit.

### Replace strategy

Strategy consists of an interface that represents some algorithm such as 
validation logic or sorting routine, implementations of interface, and the 
clients that use the objects. It is also known as **policy**. Its intent to 
define an algorithm in abstract terms so it can be implemented in several 
different ways, and to allow it to be injected into clients so it can be used 
across several different clients.

```scala
    // validation logic or sorting routine
    def isValid(t: T): Boolean = t.isDefined

    // Validator execute different validation logic or sorting routine
    def validator(isValid: T => Boolean) = {
      var valided = Vector[T]()
      (valid: T) => {
        if(isValid(t)) valided = valided :+ valid
      }
    }

    // Create a new validator
    val newValidator = validator(isValid)

    val toBeValidated: T = ... 

    // Execute validator
    newValidator(toBeValidated)
```

Strategy and template method server smiliar ends. **Strategy** uses 
**composition** and **template method** use **inheritance**, but we replace both
patterns with ones based on **functional composition**.

### Replace null object

This intent to avoid scattering null checks throughtout the code by 
encapsulating the action taken for null references into a surrogate null object.
2 main benefits are (1) avoid scattering null checks which keeps the code clean
and easier to read (2) centralize logic that deals with handling the absence of 
a value.

```java
    if (null == object) {
      // Default null handling behavior
    } else {
     object.method()
    }
```

Use container types `Option` or `Either`. `getOrElse` method is called with 
single argument, a default value. When called on an instance of `Some`, carried
value is returned; otherwise, default value is returned when called on `None`.

```scala
    def some = Some(...)
    def none = None

    some.getOrElse("Default value") // ...
    none.getOrElse("Default value") // Default value
    
    def map = Map(1 -> "A", 2 -> "B")
    
    map.getOrElse(3, "Default value") // Default value
```

If any of generators produces a `None`, then the value of the entire expression
is a `None`.

```scala
    val bSome = Some("b")
    val none = None

    for (b <- bSome; n <- none) yield (b, n) // None
```

### Replace decorator

Decorator is used when we need to add some behavior to an existing class and we
can not change the existing class. Its intent is to add bechvior to an 
individual object rather than to an entire class of objects. Decorator uses a
combination of inheritance and composition. It is known as **wrapper**.

Functional replacement is to create higher-order function that takes in the 
existing function and returns a new, wrapped function.

```scala
    def add(a: Int, b: Int) = a + b
    
    // Wrapper
    def makeLogger(operation: (Int, Int) => Int) = 
      (a: Int, b: Int) => {
        val result = operation(a, b)
        println("Result is " + result)
        result
    }
    
    val loggingAdd = makeLogger(add)
    
    loggingAdd(2, 3) // Result is 5
```
