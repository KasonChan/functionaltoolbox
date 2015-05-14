## Functional Patterns

### Filter-Map-Reduce

Intent to manipulate a sequence (`list`, `vector`...) using `filter`, `map` and 
`reduce` declaratively to produce a new sequence. Instead of of iteratively 
through elements in the sequence, `filter` at a higher level to select elements, 
`map` to transform each element and `reduce` or `fold` to combine the results.
Code clarity is one main advantage over iteration. `Anonnymous function` is 
prefered.

```scala
    Seq(...) filter (filter function) map
      (map function) reduce
        (reduce function)
```

--------------------------------------------------------------------------------

### Chain of operations

Intent to chain a sequence of computations together like `Filter-Map-Reduce`, 
this allows us to work with immutable data without storing lots of temporary 
results. Since we do not mutate a data structure, we send immutable one through 
a series of transformations. This pattern can lead to succinct code. This also 
can avoid creating noisy temporary values. The chain of operations can include 
`map`, `filter`, `reduce`, `mkString`, `sum`, and other higher-order functions.

```scala
    sequence filter (filter function) map (map function) sum
    sequence map (map function) map (map function) mkString
```

--------------------------------------------------------------------------------

### Function builder

Intent to create a function that itself creates functions, allowing us to 
synthesize behaviors on the fly. To user function builder, we write higer-order 
function that returns a function.

```scala
    def function(parameter1) = {
      ...
      (parameter2) => 
        ...
    }
```

#### Map key selector

Intent to create functions that help to pick out values from data structure 
consisting of maps nested inside each other. This is handy when working with 
structured data like `XML` or `JSON`.

#### Function composition

It is a way to chain function invocations together. `compose` and `andThen` 
operators allows us to compose functions together.

```scala
    ... function compose function ...
    ... function andThen function ...
```

#### Partially applied functions

Partially applying a function takes one function and a subset of the arguments 
that function takes and returns a new function.

```scala
    def function1(i: Any, j: Any) = ...
    val function2 = function1(i, _)
    function2(x)
```
