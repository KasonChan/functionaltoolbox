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
    println(numbers)

    //    Create a list of letters.
    //    List(a, b, c, d, e)
    val letters = List("a", "b", "c", "d", "e")
    println(letters)

    //    Create a map of 4 identities.
    //    Map(0 -> Identity(Kason,Chan,0), 1 -> Identity(Osborn,Wu,1), 2 -> Identity(Bernice,Chan,2), 3 -> Identity(Bird,lei,3))
    val map1 = Map(0 -> Identity("Kason", "Chan", 0),
      1 -> Identity("Osborn", "Wu", 1),
      2 -> Identity("Bernice", "Chan", 2),
      3 -> Identity("Bird", "lei", 3)
    )
    println(map1)

    //    Create a set of colors.
    //    Set(purple, blue, orange, green, yellow, red)
    val colors = Set("red", "orange", "yellow", "green", "blue", "purple")
    println(colors)

    println()

    /**
     * Map
     * Takes a function and a sequence and applies that function to every 
     * element in the sequence, producing a new sequence.
     */
    //    Add 3 to every element in the list.
    //    List(4, 5, 6, 7, 8)
    val listAdd3: List[Int] = numbers.map(_ + 3)
    println(listAdd3)

    //    Add 3 to every element in the list.
    //    List(7, 14, 21, 28, 35)
    val listMultiple7: List[Int] = numbers.map(i => i * 7)
    println(listMultiple7)

    //    Add the list to every element in the list.
    //    List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4), List(3, 4, 5), List(4, 5, 6))
    val listAddList: List[List[Int]] = numbers.map(addList(_))
    println(listAddList)

    //    Add 3 to every id in the Identity.
    //    List(Identity(Kason,Chan,3), Identity(Osborn,Wu,4), Identity(Bernice,Chan,5), Identity(Bird,lei,6))
    val mapAdd3: Iterable[Identity] = map1.map {
      case (key, value) => add3(value)
    }
    println(mapAdd3)

    println()

    /**
     * Filter
     * Takes a predicate and a sequence, and returns the sequence of elements 
     * that satisfy that predicate.
     */
    //    Filter the numbers to get all the odd numbers
    //    List(1, 3, 5)
    val oddList: List[Int] = numbers.filter(_ % 2 == 1)
    println(oddList)

    //    Filter the numbers to get all the even numbers
    //    List(2, 4)
    val evenList: List[Int] = numbers.filter(_ % 2 == 0)
    println(evenList)

    //    Filter the map1 to get all elements with the last name that is equal to Chan
    //    Map(0 -> Identity(Kason,Chan,0), 2 -> Identity(Bernice,Chan,2))
    val mapChan: Map[Int, Identity] = map1.filter {
      case (key, value) => lastChan(value)
    }
    println(mapChan)

    println()

    /**
     * Zip
     * Takes two sequences and forms a list of pairs.
     *
     * Unzip
     * Take any list of tuples and changes back to a tuple of lists.
     */
    //    Zip letters with stream from 1
    //    List((a,1), (b,2), (c,3), (d,4), (e,5))
    val lettersWithStream = letters.zip(Stream from 1)
    println(lettersWithStream)

    //    Zip letters with indices
    //    List((a,0), (b,1), (c,2), (d,3), (e,4))
    val lettersWithIndices = letters.zipWithIndex
    println(lettersWithIndices)

    //    Zip letters with numbers
    //    List((a,1), (b,2), (c,3), (d,4), (e,5))
    val lettersZipNumbers: List[(String, Int)] = letters.zip(numbers)
    println(lettersZipNumbers)

    //    Zip numbers with letters
    //    List((1,a), (2,b), (3,c), (4,d), (5,e))
    val numbersZipLetters: List[(Int, String)] = numbers.zip(letters)
    println(numbersZipLetters)

    //    Zip colors with numbers
    //    Set((orange,3), (blue,2), (yellow,5), (green,4), (purple,1))
    val colorsZipNumbers: Set[(String, Int)] = colors.zip(numbers)
    println(colorsZipNumbers)

    //    Zip numbers with map1
    //    List((1,(0,Identity(Kason,Chan,0))), (2,(1,Identity(Osborn,Wu,1))), (3,(2,Identity(Bernice,Chan,2))), (4,(3,Identity(Bird,lei,3))))
    val numbersZipMap: List[(Int, (Int, Identity))] = numbers.zip(map1)
    println(numbersZipMap)

    //    Zip map1 with numbers
    //    Map((0,Identity(Kason,Chan,0)) -> 1, (1,Identity(Osborn,Wu,1)) -> 2, (2,Identity(Bernice,Chan,2)) -> 3, (3,Identity(Bird,lei,3)) -> 4)
    val map1ZipNumbers: Map[(Int, Identity), Int] = map1.zip(numbers)
    println(map1ZipNumbers)

    //    zipWith(\a b -> (a * 30 + 3) / b [reversed numbers] [numbers]
    //    List(153, 61, 31, 15, 6)
    val numbersZipNumbers = (numbers.reverse.map(i => i * 30 + 3), numbers).zipped.map(_ / _)
    println(numbersZipNumbers)

    //    Create tuple from numbers and letters
    //    List((1,a), (1,b), (1,c), (1,d), (1,e), (2,a), (2,b), (2,c), (2,d), (2,e), (3,a), (3,b), (3,c), (3,d), (3,e), (4,a), (4,b), (4,c), (4,d), (4,e), (5,a), (5,b), (5,c), (5,d), (5,e))
    val numbersWithLetters = for {
      n <- numbers
      l <- letters
    } yield (n, l)
    println(numbersWithLetters)

    //    Unzip numbers and letters
    //    (List(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5),List(a, b, c, d, e, a, b, c, d, e, a, b, c, d, e, a, b, c, d, e, a, b, c, d, e))
    val unzippedNumbersLetters = numbersWithLetters.unzip
    println(unzippedNumbersLetters)

    println()

    /**
     * Fold, fold left, fold right
     * A fold takes a binary function, a starting value called accumulator and 
     * a sequence to fold up. 
     */
    //    Fold the numbers starting with accumulator 0
    //    15
    val sum1: Int = numbers.fold(0)(_ + _)
    println(sum1)

    //    Fold left the numbers with accumulator 0
    //    15
    val sum2: Int = numbers.foldLeft(0)(_ + _)
    println(sum2)

    //    Accumulator on the left
    val sum3 = (0 /: numbers)(_ + _)
    println(sum3)

    //    Fold right the numbers with accumulator 0
    //    15
    val sum4: Int = numbers.foldRight(0)(_ + _)
    println(sum4)

    //    Accumulator on the right
    val sum5 = (numbers :\ 0)(_ + _)
    println(sum5)

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

  case class Identity(first: String, last: String, id: Int)

}
