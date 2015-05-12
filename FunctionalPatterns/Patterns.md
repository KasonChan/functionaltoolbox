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

### Chain of Operations

Intent to chain a sequence of computations together like `Filter-Map-Reduce`, 
this allows us to work with immutable data without storing lots of temporary 
results. Since we do not mutate a data structure, we send immutable one through 
a series of transformations. This pattern can lead to succinct code. This also 
can avoid creating noisy temporary values. The chain of operations can include 
`map`, `filter`, `reduce`, `mkstring`, and other higher-order functions.
