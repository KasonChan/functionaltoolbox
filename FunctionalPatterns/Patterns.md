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

