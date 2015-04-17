package demo

import classes.{Car, Motorcycle, Truck}
import io.IO

/**
 * Created by kasonchan on 3/4/15.
 */
/**
 * abstract modifier signifies that the class may have abstract members that
 * do not have an implementation.
 *
 * sealed trait/class can not have any new subclasses added.
 * Hierarchy of classes intended to be pattern matched.
 */
sealed abstract class Polygon

case class Sides(num: Int) extends Polygon

case class Square(name: String, Sides: Sides) extends Polygon

case class Circle(name: String, Sides: Sides) extends Polygon


object Demos extends IO {

  def main(args: Array[String]) {

    /**
     * New classes - Car, Truck, Motorcycle
     */
    val car = new Car(2, "Toyota", "Camry")
    val t = new Truck(2, "Ford", "Aries")
    val mo = new Motorcycle(0, "Yamaha", "Beta", 3)

    echo(car)

    echo(t)

    echo(mo)

    echo("")

    val l1 = (1 to 10).toList
    val l2 = (0 to 9).toList
    val a1 = Array(3, 5, 6)
    val colors = Set("red", "orange", "black")

    /**
     * Pattern matching
     */
    l1 match {
      //    _* specifies last element of the pattern
      case Seq(1, _*) => echo("Found it")
      case _ =>
    }

    a1 match {
      case Array(3, _*) => echo("Found it")
      case _ => echo("Not found")
    }

    val apple = Fruit("apple")
    val orange = Fruit("orange")

    val is1 = IntStringFruit(1, "a", apple)
    val is2 = IntStringFruit(2, "o", orange)

    //    Patterns in for expressions
    for (i <- foundIntStringFruit(is1))
      echo(i)

    //    Use getOrElse from Option result
    echo(foundIntStringFruit(is2).getOrElse(Fruit("pear")))

    echo("")

    //    Pattern in variable definitions
    //    Deconstructs with pattern
    val tuple = ("abc", 123)
    val (string, integer) = tuple

    echo(string)
    echo(integer)

    echo("")

    /**
     * Sealed classes
     */
    val s = Square("square", Sides(4))
    val r = Square("rectangle", Sides(4))

    echo(describe(s))
    echo(describe2(s))

  }

  /**
   * Variable binding function
   * @param is1
   * @return
   */
  def foundIntStringFruit(is1: IntStringFruit): Option[Fruit] = {
    is1 match {
      case IntStringFruit(1, "a", f@Fruit(_)) => Some(f)
      case _ => None
    }
  }

  /**
   * Not exhaustive pattern matching function
   * @param v
   * @return
   */
  def describe(v: Polygon): String = v match {
    case Square(n, Sides(s)) => n + " has " + s + " sides."
  }

  /**
   * @unchecked annotation suppress exhaustive checking
   * @param v
   * @return
   */
  def describe2(v: Polygon): String = (v: @unchecked) match {
    case Square(n, Sides(s)) => n + " has " + s + " sides."
  }

  case class Fruit(s: String)

  case class IntStringFruit(i: Int, s: String, f: Fruit)

  case class StringInt(s: String, i: Int)

}
