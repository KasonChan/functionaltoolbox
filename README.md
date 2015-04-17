Functional toolbox
==================

The repository is dedicated for recording what I learnt in functional 
programming in Scala with 
[SBT](http://www.scala-sbt.org/download.html) and [Akka](http://akka.io/). 
It will be used as a toolbox (notes) for me when I need some references 
such as code snippets, basic project set up, templates, etc.

## Table of Contents ##

- [Classes](Classes/Classes.md)
  - `abstract` modifier
  - `sealed` modifier
  - `extends` keyword
  - `require` keyword
  - `override` modifier
  - `toString` function
- [Pattern matching](Classes/Classes.md)
  - `_*`
  - Variable binding
  - Pattern in `for` expression
  - User `getOrElse` for `Option` result
  - Deconstruct with patterns in variable definitions
  - `@unchecked` annotation 
- [Collections](HigherOrderFunctions/Functions.md)
  - Create a list of integer
  - Create a map collection
  - Create a set collection
- [Higher-order functions](HigherOrderFunctions/Functions.md)
  - `map` takes a function and a sequence and applies that function to every 
  element in the sequence, producing a new sequence
  - `flatmap` takes a function returning a list of elements as its right 
  operand, applies the function to each list element and returns the 
  concatenation of all function results. 
  - `foreach` takes a procedure as right operand, applies to each list element.
  - `filter` takes a predicate and a sequence, and returns the sequence of 
  elements that satisfy that predicate. Predicates returns `true` or `false`.
  - `partition` takes a predicate and a sequence, and returns the sequence of 
  elements that satisfy that predicate.
  - `TakeWhile` takes a predicate as their right operand, takes the longest 
  prefix of list that satisfy predicate.
  - `dropWhile` takes a predicate as their right operand, removes the longest 
  prefix from list that satisfy predicate.
  - `span` combines takeWhile and dropWhile in one operation and it avoids 
  traversing the list twice.
  - `find` takes a predicate and a sequence, and returns the first element 
  satisfying a given predicate. Return Some(x) if predicate is true, otherwise return None.
  - `zip` takes two sequences and forms a list of pairs.
  - `unzip` take any list of tuples and changes back to a tuple of lists.
  - `fold` takes a binary function, a starting value called accumulator and a 
  sequence to fold up.
- [Swing](Swing/Swing.md)

