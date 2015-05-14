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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

### Replace visitor

Its intent to encapsulate an action to be performed on a data structure in a way
that allows the additon of new operations to the data structure without having 
to modify it. It need to be done without recompiling the original source. It is
known as the *expression problem*.

The norm is that it is easy to add a new operation on some data type by writing
a new function that operators on it, but it is difficult to add new data types
to an existing operation. There are two methods of replacing visitor: 
1. Extend the operations in an existing library that use Scala's **implicit 
conversion** system. This allows us to add new operations to existing libraries.
2. Take advantage of Scala's **mix-in inheritance** and `trait`. This allows
us to add both new operations and new implementation to a data type.

The follow code snippet shows how to add new methods to an existing type.

```scala
    trait Person {
      def fullname: String
      def firstname: String
      def lastname: String
      def houseNum: Int
      def street: String
    }
    
    // Mix-in inheritance and trait
    class SimplePerson(val firstname: String, val lastname: String, 
    val houseNum: Int, val street: String) extends Person {
      def fullname = firstname + " " + lastname
    }
    
    val simplePerson = new SimplePerson("Kason", "Chan", 1234, "Real. St.")
    
    simplePerson.fullName // Kason Chan
    
    // Implicit conversion
    implicit class ExtendedPerson(person: Person) {
      def fullAddress = person.houseNum + " " + person.street
    }
    
    simplePerson.fullAddress // 1234 Real. St.
```

The following code snippet shows how to extend a data type with both new 
operations and new implementations.

```scala
    class VAddress(val houseNum: Int, val street: String)

    class VName(val firstname: String, val lastname: String)

    class VComplexPerson(name: VName, address: VAddress) extends VPerson {
      def fullname = name.firstname + " " + name.lastname
      def firstname = name.firstname
      def lastname = name.lastname
      
      def houseNum = address.houseNum
      def street = address.street
    }
    
    val vname = new VName("Kason", "Chan")
    val vaddress = new VAddress(1234, "Real. St.")
    val vcomplexPerson = new VComplexPerson(vname, vaddress)
    
    echo(vcomplexPerson.fullname) // Kason Chan
    echo(vcomplexPerson.fullAddress) // 1234 Real. St.
```

--------------------------------------------------------------------------------

### Replace dependency injection

Dependency Injection is to inject an object's dependencies through a constructor
or setter. Its intent is to compose object together using an external 
configuration or code, rather than having an object instantiate it own 
dependencies. This allows us to inject different dependency implementations
into an object and provide centralized place to understand what dependencies
a given object has.

Dependency injection is handy for swapping out a real dependency with a stub in
a unit test. Also, it makes it easier to declaratively specify the overall shape
of a system as each component has its dependencies injected into it in a 
configuration file or in a bit of configuration code with appropriate container
support.

Composing functions in functional programming is naturally involved. **Cake
pattern** use Scala `trait`s and self-type annotations to accomplish the same
functionality that we get with Dependency Injection without the need for a 
container.

```scala
    case class Book(bookId: String, title: String)
    
    case class Page(bookId: String)
    
    case class DecoratedBook(book: Book, page: Page)
    
    //  Define some traits as interfaces for dependencies
    trait BookDaoComponent {    
      trait BookDao {
        def getBook(id: String): Book
      }
    }

    trait FavouritesServiceComponent {
      trait FavouritesService {
        def getFavouritePages(id: String): Vector[Page]
      }
    }
  
    // Stub out to return static responses by implementing the interfaces
    trait BookDaoComponentImpl extends BookDaoComponent {
      class MovieDaoImpl extends BookDao {
        def getBook(id: String): Book = new Book("1", "A")
      }
    }
  
    // Stub out to return static responses by implementing the interfaces
    trait FavouritesServiceComponentImpl extends FavouritesServiceComponent {
      class FavouritesServiceImpl extends FavouritesService {
        def getFavouritePages(id: String): Vector[Page] = Vector(Page("1"))
        def getFavouriteBooks(id: String): Vector[Page] = Vector(new Page("1"))
      }
    }
  
    trait BookServiceComponentImpl {
      // Self-type annotation ensures whenver mixed into an object or class,
      // this reference of that object has the type of below
      this: BookDaoComponent with FavouritesServiceComponent =>
      val favouritesService: FavouritesService
      val bookDao: BookDao
 
      class BookServiceImpl {
        def getFavouriteDecoratedBooks(userId: String): Vector[DecoratedBook] =
          for (
            favouriteBook <- favouritesService.getFavouritePages(userId);
            val book = bookDao.getBook((favouriteBook.bookId))
          ) yield DecoratedBook(book, favouriteBook)
      }
    }
    
    object ComponentRegistry extends BookServiceComponentImpl
    with FavouritesServiceComponentImpl with BookDaoComponentImpl {
    
      val favouritesService = new FavouritesServiceImpl
      val bookDao = new BookDaoImpl
    
      val bookService = new BookServiceImpl
    }
```