package demos

import scala.collection.immutable.Iterable

/**
 * Created by kasonchan on 3/2/15.
 */
object Demos {

  def main(args: Array[String]) {
    val numbers = (1 to 5).toList
    println(numbers)

    val letters = List("a", "b", "c", "d", "e")
    println(letters)

    val map1 = Map(0 -> Identity("Kason", "Chan", 0),
      1 -> Identity("Osborn", "Wu", 1),
      2 -> Identity("Bernice", "Chan", 2),
      3 -> Identity("Bird", "lei", 3)
    )
    println(map1)

    val colors = Set("red", "orange", "yellow", "green", "blue", "purple")
    println(colors)

    println()

    /**
     * Map 
     */
    //    Add 3 to every element in the list
    val listAdd3: List[Int] = numbers.map(_ + 3)
    println(listAdd3)

    //    Add 3 to every element in the list
    val listMultiple7: List[Int] = numbers.map(i => i * 7)
    println(listMultiple7)

    //    Add the list to every element in the list
    val listAddList: List[List[Int]] = numbers.map(addList(_))
    println(listAddList)

    //    Add 3 to every id in the Identity
    val mapAdd3: Iterable[Identity] = map1.map {
      case (key, value) => add3(value)
    }
    println(mapAdd3)

    println()

    /**
     * Filter * 
     */
    //    Filter the numbers to get all the odd numbers
    val oddList: List[Int] = numbers.filter(_ % 2 == 1)
    println(oddList)

    //    Filter the numbers to get all the even numbers
    val evenList: List[Int] = numbers.filter(_ % 2 == 0)
    println(evenList)

    //    Filter the map1 to get all elements with the last name that is equal to Chan
    val mapChan: Map[Int, Identity] = map1.filter {
      case (key, value) => lastChan(value)
    }
    println(mapChan)

    println()

    /**
     * Zip * 
     */
    //    Zip letter with numbers
    val lettersZipNumbers: List[(String, Int)] = letters.zip(numbers)
    println(lettersZipNumbers)

    //    Zip numbers with letters
    val numbersZipLetters: List[(Int, String)] = numbers.zip(letters)
    println(numbersZipLetters)

    //    Zip colors with numbers
    val colorsZipNumbers: Set[(String, Int)] = colors.zip(numbers)
    println(colorsZipNumbers)

    //    Zip numbers with map
    val numbersZipMap: List[(Int, (Int, Identity))] = numbers.zip(map1)
    println(numbersZipMap)

    //    Zip map with numbers
    val map1ZipNumbers: Map[(Int, Identity), Int] = map1.zip(numbers)
    println(map1ZipNumbers)

    //    zipWith(\a b -> (a * 30 + 3) / b [reversed numbers] [numbers]
    val numbersZipNumbers = (numbers.reverse.map(i => i * 30 + 3), numbers).zipped.map(_ / _)
    println(numbersZipNumbers)

    //    Create tuple from numbers and letters
    val numbersWithLetters = for {
      n <- numbers
      l <- letters
    } yield (n, l)
    println(numbersWithLetters)

    //    Unzip numbers and letters
    val unzippedNumbersLetters = numbersWithLetters.unzip
    println(unzippedNumbersLetters)

    println()

    /**
     * Fold, fold left, fold right * 
     */
    //    Fold the numbers starting with accumulator 0
    val sum1: Int = numbers.fold(0)(_ + _)
    println(sum1)

    //    Fold left the numbers with accumulator 0
    val sum2: Int = numbers.foldLeft(0)(_ + _)
    println(sum2)

    val sum3 = (0 /: numbers)(_ + _)
    println(sum3)

    //    Fold right the numbers with accumulator 0
    val sum4: Int = numbers.foldRight(0)(_ + _)
    println(sum4)

    val sum5 = (numbers :\ 0)(_ + _)
    println(sum5)


  }

  def addList(x: Int): List[Int] = List(x - 1, x, x + 1)

  def add3(identity: Identity): Identity = {
    identity match {
      case Identity(first, last, id) => Identity(first, last, id + 3)
    }
  }

  def lastChan(identity: Identity): Boolean = {
    identity match {
      case Identity(first, "Chan", id) => true
      case _ => false
    }
  }

  case class Identity(first: String, last: String, id: Int)

}
