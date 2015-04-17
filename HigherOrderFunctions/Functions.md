Higher-order functions
======================

Collections
-----------

- Create a list of integer

```scala
    (1 to n).toList
```

- Create a map collection. `Map` lets us associate a value with each element of 
the collection, and index with integers counting from `0`.

```scala
    Map(0 -> Identity..., 1 -> Identity..., ...)
```

- Create a set collection. `Set` ensures at most one of each object determined 
by `==` will be contained in the set at any one time.

```scala
    Set(...)
```

Higher-order functions
----------------------

- `map` takes a function and a sequence and applies that function to every element in the sequence, producing a new sequence

```scala
    sequence map (_ operation)
    sequence map (x => function(x))
    sequence map { case(key, value) => function(value)}
```

- `flatmap` takes a function returning a list of elements as its right operand, applies the function to each list element and returns the concatenation of all function results. 

```scala
    sequence flatmap (_ operation)
```

- `foreach` takes a procedure as right operand, applies to each list element.

```scala
    sequence foreach (procedure)
```

- `filter` takes a predicate and a sequence, and returns the sequence of elements that satisfy that predicate. Predicates returns `true` or `false`.

```scala
     sequence filter (predicate)
     sequence filter { case(key, value) => predicate(value) }
```

- `partition` takes a predicate and a sequence, and returns the sequence of elements that satisfy that predicate.

```scala
    sequence partition (predicate)
```

- `TakeWhile` takes a predicate as their right operand, takes the longest prefix of list that satisfy predicate.

```scala
    sequence takeWhile predicate
```

- `dropWhile` takes a predicate as their right operand, removes the longest prefix from list that satisfy predicate.

```scala
    sequence dropWhile predicate
```

- `span` combines takeWhile and dropWhile in one operation and it avoids traversing the list twice.

```scala
    sequence span predicate
```

- `find` takes a predicate and a sequence, and returns the first element satisfying a given predicate. Return Some(x) if predicate is true, otherwise return None.

```scala
    sequence find (predicate)
```

- `zip` takes two sequences and forms a list of pairs.

```scala
    sequence zip sequence
    sequence zip (Stream from 1)
    sequence zipWithIndex // Index start with 0
    (sequence, sequence) zipped map (function)
```

- `unzip` take any list of tuples and changes back to a tuple of lists.

```scala
    (tuple sequence) unzip
```

- `fold` takes a binary function, a starting value called accumulator and a sequence to fold up.

```scala
    sequence fold (accumulator) (function)
    sequence foldLeft (accumulator) (function)
    (accumulator /: sequence) (function)
    sequence foldRight (accumulator) (function)
    (sequence :\ accumulator) (function)
```
