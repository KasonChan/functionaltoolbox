## Classes

### `abstract`, `sealed` and `extends`

- `abstract` modifier signifies that the class may have abstract members that do 
not have an implementation.
- `sealed` trait/class can not have any new subclasses added.
- Hierarchy of classes intended to be pattern matched.
- `extends` keyword has 2 effects: 
  - `Subclass` inherit all non-private members from `Superclass`.
  - `Subclass` becomes a subtype of `Superclass`.

```scala
    sealed abstract class Superclass
    case class Subclass(...) extends Superclass
```

### `require`, `override` and `toString`

- `require` signals a precondition constraint on values passed into method or 
constructor. It is a requirement which callers must fulfill.
- `override` modifier signifies that the previous definition is overriden.
- `classname` auto inherits the implementation of `toString` defined in 
`java.lang.Object`. The result of `toString` helps us to give info in debugging.  

```scala
    class classname(p: Any) {
      require(condition of p)
      ...
      override def toString = ...
    }
```
--------------------------------------------------------------------------------

## Pattern matching

### Specify last element in sequence

- `_*` specifies the last element in the sequence.

```scala
    sequence match {
      case Seq(1, _*) => echo("Found it")
      ...
    }
```

### Variable binding

- Variable binding: variable name, an at sign `@` and then the pattern. 
`pattern` can be used as `a`.

```scala
    selector match {
      case alternative(..., a @ pattern) => a
      ...
    }
```

### In `for` expression

- Pattern in `for` expression: `for` expression prints only the `Some`s.

```scala
    val sequence = Seq(Some(...), None, Some(...))
    for(item <- sequence)
      print(item)
```

### Use `getOrElse` for `Option`

- Use `getOrElse` for `Option` result: If `optionResult` return `None`, 
`defaultValue` will be used.

```scala
    optionResult.getOrElse(defaultValue)
```

### Deconstruct pattern

- Deconstruct with patterns in variable definitons.

```scala
    val tuple = ("abc", 123)
    val (string, integer) = tuple  
    print(string)
    print(integer)
```

### `@unchecked` annotation

- `@unchecked` annotation suppresses exhaustive checking.

```scala
    ... (selector: @unchecked) match {
      ...
    }
```
