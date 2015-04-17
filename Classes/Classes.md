Classes
=======

- `abstract` modifier signifies that the class may have abstract members that do 
not have an implementation.
- `sealed` trait/class can not have any new subclasses added.
- Hierarchy of classes intended to be pattern matched.

```scala
sealed abstract class Polygon
case class Sides(num: Int) extends Polygon
case class Square(name: String, Sides: Sides) extends Polygon
case class Circle(name: String, Sides: Sides) extends Polygon
```

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

Pattern matching
================

- `_*` specifies the last element in the sequence.

```
sequence match {
  case Seq(1, _*) => echo("Found it")
  ...
}
```

- Variable binding: variable name, an at sign `@` and then the pattern. 
`pattern` can be used as `a`.

```
selector match {
  case alternative(..., a @ pattern) => a
  ...
```

- Patterns in for expressions: for expression prints only the `Some`s

```
val sequence = Seq(Some(...), None, Some(...))
for(item <- sequence)
  print(item)
```

- Use `getOrElse` for `Option` result: If `optionResult` return `None`, 
`defaultValue` will be used.

```
optionResult.getOrElse(defaultValue)
```

- Deconstructs with pattern in variable definitons

```
val tuple = ("abc", 123)
val (string, integer) = tuple  
print(string)
print(integer)
```

- `@unchecked` annotation suppress exhaustive checking

```
... (selector: @unchecked) match {
  ...
}
```
