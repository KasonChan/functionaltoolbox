package demos

import scala.collection.immutable.Iterable

/**
 * Created by kasonchan on 3/2/15.
 */
object Demos {

  def main(args: Array[String]) {

    //    Create a list of number.
    //    List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = (1 to 10).toList
    echo(numbers)

    //    Create a list of letters.
    //    List(a, b, c, d, e)
    val letters = List("a", "b", "c", "d", "e")
    echo(letters)

    //    Create a map of 4 identities.
    //    Map(0 -> Identity(Kason,Chan,0), 1 -> Identity(Osborn,Wu,1), 2 -> Identity(Bernice,Chan,2), 3 -> Identity(Bird,lei,3))
    val map1 = Map(0 -> Identity("Kason", "Chan", 0),
      1 -> Identity("Osborn", "Wu", 1),
      2 -> Identity("Bernice", "Chan", 2),
      3 -> Identity("Bird", "lei", 3)
    )
    echo(map1)

    //    Create a set of colors.
    //    Set(purple, blue, orange, green, yellow, red)
    val colors = Set("red", "orange", "yellow", "green", "blue", "purple")
    echo(colors)

    echo("")

    /**
     * Map
     * Takes a function and a sequence and applies that function to every
     * element in the sequence, producing a new sequence.
     */
    //    Add 3 to every element in the list.
    //    List(4, 5, 6, 7, 8)
    val listAdd3: List[Int] = numbers.map(_ + 3)
    echo(listAdd3)

    //    Add 3 to every element in the list.
    //    List(7, 14, 21, 28, 35)
    val listMultiple7: List[Int] = numbers.map(i => i * 7)
    echo(listMultiple7)

    //    Add the list to every element in the list.
    //    List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4), List(3, 4, 5), List(4, 5, 6))
    val listAddList: List[List[Int]] = numbers.map(addList(_))
    echo(listAddList)

    //    Add 3 to every id in the Identity.
    //    List(Identity(Kason,Chan,3), Identity(Osborn,Wu,4), Identity(Bernice,Chan,5), Identity(Bird,lei,6))
    val mapAdd3: Iterable[Identity] = map1.map {
      case (key, value) => add3(value)
    }
    echo(mapAdd3)

    //    Converts every element to list
    //    Set(List(b, l, u, e), List(g, r, e, e, n), List(o, r, a, n, g, e), List(r, e, d), List(p, u, r, p, l, e), List(y, e, l, l, o, w))
    echo(colors map (_.toList))

    echo("")

    /**
     * Flatmap
     * Takes a function returning a list of elements as its right operand,
     * applies the function to each list element and returns the concatenation
     * of all function results.
     */
    //    Flatten every element and concatenate to list
    //    Set(e, n, y, u, a, b, g, l, p, r, w, o, d)
    echo(colors flatMap ((_).toList))

    /**
     * Foreach
     * Takes a procedure as right operand, applies to each list element.
     */

    /**
     * Filter
     * Takes a predicate and a sequence, and returns the sequence of elements
     * that satisfy that predicate.
     */
    //    Filter the numbers to get all the odd numbers
    //    List(1, 3, 5)
    val oddList: List[Int] = numbers.filter(_ % 2 == 1)
    echo(oddList)

    //    Filter the numbers to get all the even numbers
    //    List(2, 4)
    val evenList: List[Int] = numbers.filter(_ % 2 == 0)
    echo(evenList)

    //    Filter the map1 to get all elements with the last name that is equal to Chan
    //    Map(0 -> Identity(Kason,Chan,0), 2 -> Identity(Bernice,Chan,2))
    val mapChan: Map[Int, Identity] = map1.filter {
      case (key, value) => lastChan(value)
    }
    echo(mapChan)

    echo("")

    /**
     * Partition
     * Takes a predicate and a sequence, and returns the sequence of elements
     * that satisfy that predicate.
     */
    //    Partition the list into multiple of 3
    //    (List(3, 6, 9),List(1, 2, 4, 5, 7, 8, 10))
    echo(numbers partition (_ % 3 == 0))

    //    Partition the red from the list
    //    (Set(red),Set(purple, blue, orange, green, yellow))
    echo(colors partition (_ == "red"))

    echo("")

    /**
     * Find
     * Takes a predicate and a sequence, and returns the first element
     * satisfying a given predicate. Return Some(x) if predicate is true,
     * otherwise return None.
     */
    //    Find multiple of two
    //    Some(2)
    echo(numbers find (_ % 2 == 0))

    //    Find number that is smaller than zero
    //    None
    echo(numbers find (_ < 0))

    echo("")

    /**
     * Zip
     * Takes two or more sequences and forms a list of pairs.
     *
     * Unzip
     * Take any list of tuples and changes back to a tuple of lists.
     */
    //    Zip letters with stream from 1
    //    List((a,1), (b,2), (c,3), (d,4), (e,5))
    val lettersWithStream = letters.zip(Stream from 1)
    echo(lettersWithStream)

    //    Zip letters with indices
    //    List((a,0), (b,1), (c,2), (d,3), (e,4))
    val lettersWithIndices = letters.zipWithIndex
    echo(lettersWithIndices)

    //    Zip letters with numbers
    //    List((a,1), (b,2), (c,3), (d,4), (e,5))
    val lettersZipNumbers: List[(String, Int)] = letters.zip(numbers)
    echo(lettersZipNumbers)

    //    Zip numbers with letters
    //    List((1,a), (2,b), (3,c), (4,d), (5,e))
    val numbersZipLetters: List[(Int, String)] = numbers.zip(letters)
    echo(numbersZipLetters)

    //    Zip colors with numbers
    //    Set((orange,3), (blue,2), (yellow,5), (green,4), (purple,1))
    val colorsZipNumbers: Set[(String, Int)] = colors.zip(numbers)
    echo(colorsZipNumbers)

    //    Zip numbers with map1
    //    List((1,(0,Identity(Kason,Chan,0))), (2,(1,Identity(Osborn,Wu,1))), (3,(2,Identity(Bernice,Chan,2))), (4,(3,Identity(Bird,lei,3))))
    val numbersZipMap: List[(Int, (Int, Identity))] = numbers.zip(map1)
    echo(numbersZipMap)

    //    Zip map1 with numbers
    //    Map((0,Identity(Kason,Chan,0)) -> 1, (1,Identity(Osborn,Wu,1)) -> 2, (2,Identity(Bernice,Chan,2)) -> 3, (3,Identity(Bird,lei,3)) -> 4)
    val map1ZipNumbers: Map[(Int, Identity), Int] = map1.zip(numbers)
    echo(map1ZipNumbers)

    //    zipWith(\a b -> (a * 30 + 3) / b [reversed numbers] [numbers]
    //    List(153, 61, 31, 15, 6)
    val numbersZipNumbers = (numbers.reverse.map(i => i * 30 + 3), numbers).zipped.map(_ / _)
    echo(numbersZipNumbers)

    //    Create tuple from numbers and letters
    //    List((1,a), (1,b), (1,c), (1,d), (1,e), (2,a), (2,b), (2,c), (2,d), (2,e), (3,a), (3,b), (3,c), (3,d), (3,e), (4,a), (4,b), (4,c), (4,d), (4,e), (5,a), (5,b), (5,c), (5,d), (5,e))
    val numbersWithLetters = for {
      n <- numbers
      l <- letters
    } yield (n, l)
    echo(numbersWithLetters)

    //    Unzip numbers and letters
    //    (List(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5),List(a, b, c, d, e, a, b, c, d, e, a, b, c, d, e, a, b, c, d, e, a, b, c, d, e))
    val unzippedNumbersLetters = numbersWithLetters.unzip
    echo(unzippedNumbersLetters)

    echo("")

    /**
     * Fold, fold left, fold right
     * A fold takes a binary function, a starting value called accumulator and
     * a sequence to fold up.
     */
    //    Fold the numbers starting with accumulator 0
    //    15
    val sum1: Int = numbers.fold(0)(_ + _)
    echo(sum1)

    //    Fold left the numbers with accumulator 0
    //    15
    val sum2: Int = numbers.foldLeft(0)(_ + _)
    echo(sum2)

    //    Accumulator on the left
    val sum3 = (0 /: numbers)(_ + _)
    echo(sum3)

    //    Fold right the numbers with accumulator 0
    //    15
    val sum4: Int = numbers.foldRight(0)(_ + _)
    echo(sum4)

    //    Accumulator on the right
    val sum5 = (numbers :\ 0)(_ + _)
    echo(sum5)

    echo("")

    /**
     * List operations
     */
    //    Print the length
    //    10
    echo(numbers.length)

    //    Print the rest of the list except the last
    //    List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    echo(numbers.init)

    //    Print the last element
    //    10
    echo(numbers.last)

    //    Print the first element of the list
    //    1
    echo(numbers.head)

    //    Print the rest of the list except the first element
    //    List(2, 3, 4, 5, 6, 7, 8, 9, 10)
    echo(numbers.tail)

    //    Print the reverse of the list
    //    List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    echo(numbers.reverse)

    //    Print the first two elements of the list
    //    List(1, 2)
    echo(numbers.take(2))

    //  Print all the elements of the list except the first 7 ones
    //    List(8, 9, 10)
    echo(numbers.drop(7))

    //    Print the pair of two lists splits at index 4
    //    (List(1, 2, 3, 4),List(5, 6, 7, 8, 9, 10))
    echo(numbers.splitAt(4))

    //    Print the numbers at index
    //    7
    echo(numbers apply 6)

    //    Print the indices of the elements
    //    Range(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    echo(numbers indices)

    //    Print the set of numbers
    //    List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    echo(List(numbers, numbers).flatten)
    //    List()
    echo(List(List(), List()).flatten)

    //    Print the string of numbers
    //    List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    echo(numbers.toString())
    //    {1-2-3-4-5-6-7-8-9-10}
    echo(numbers.mkString("{", "-", "}"))

    //    Access list elements via an iterator
    val it = numbers.iterator
    //    true
    echo(it.hasNext)
    //    1
    echo(it.next)

    //    Sort the numbers
    //    List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    echo(numbers.sortWith(_ > _))

    //    Create a range of numbers
    //    List.range(from, until)
    //    List(9, 7, 5, 3)
    echo(List.range(9, 1, -2))
    //    List(1, 3, 5, 7)
    echo(List.range(1, 9, 2))

    //    Creating uniform list
    //    List(hello, hello, hello)
    echo(List.fill(3)("hello"))
    //     List(List(a, a, a), List(a, a, a))
    echo(List.fill(2, 3)("a"))

    //    Creates a list whose elements are computed according to a supplied
    //    function
    //    List(1, 4, 9, 16, 25)
    echo((List.tabulate(6)(n => if (n != 0) n * n).tail))

    //    Concatenates lists together
    //    List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    echo(List.concat(numbers, numbers))

    /**
     * Composition
     */

    val fg = f _ compose g _
    val gf = f _ andThen g _

    val ma = square _ compose add _
    val am = square _ andThen add _

    val fg1 = fg("Hello")
    println(fg1) // f(g[Hello])

    val gf1 = gf("World")
    println(gf1) // g[f(World)]

    val ma1 = ma(3)
    println(ma1) // = 3 + 3 = 6 = 6 * 6 = 36

    val am1 = am(3)
    println(am1) // = 3 * 3 = 9 = 9 + 9 = 18
  }

  //  Add list to the parameter x such that List(x - 1, x, x + 1)
  def addList(x: Int): List[Int] = List(x - 1, x, x + 1)

  //  Add 3 to id of list of identity
  def add3(identity: Identity): Identity = {
    identity match {
      case Identity(first, last, id) => Identity(first, last, id + 3)
    }
  }

  //  Return true if identity last name is Chan
  def lastChan(identity: Identity): Boolean = {
    identity match {
      case Identity(first, "Chan", id) => true
      case _ => false
    }
  }

  def echo(args: Any*): Unit = {
    for (arg <- args)
      println(arg)
  }

  case class Identity(first: String, last: String, id: Int)

  def square(x: Int): Int = x * x

  def add(x: Int): Int = x + x

  def f(s: String): String = "f(" + s + ")"

  def g(s: String): String = "g[" + s + "]"
}
