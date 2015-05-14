package functionbuilder

import scala.annotation.tailrec

/**
 * Created by kasonchan on 5/14/15.
 */
trait FunctionBuilder {

  /**
   * Odd
   * Return true if the num is odd
   * @param num
   * @return
   */
  def odd(num: Int): Boolean = num % 2 != 0

  /**
   * Even
   * Return true if the num is even
   * @param num Int
   * @return Boolean
   */
  def even(num: Int): Boolean = !odd(num)

  /**
   * Discount
   * Take percent and check to ensure it's between 0 and 100
   * Creates a function that user the percent to calculate the discount
   * @param percent Double
   * @return (Double) => Double
   */
  def discount(percent: Double): (Double) => Double = {
    if (percent < 0.0 || percent > 100.0) {
      throw new IllegalArgumentException("Discounts must be between 0.0 and 100.0.")
    }
    (price: Double) => price - (price * percent * 0.01)
  }

  /**
   * Discount
   * Take percent and check to ensure it's between 0 and 100
   * Creates a function that user the percent to calculate the discount
   * @param percent Double
   * @return (Double) => Double
   */
  def discount2(percent: Double): (Double) => Option[Double] = {
    (price: Double) => {
      if (percent < 0.0 || percent > 100.0) None
      else Some(price - (price * percent * 0.01))
    }
  }

  /**
   * Foo
   * Take num return string if num is odd
   * Otherwise, return append 2 to the string
   * @param num
   * @return
   */
  def foo(num: Int): (String) => String = {
    (string: String) => {
      if (odd(num)) string
      else string + "2"
    }
  }

  /**
   * Selector
   * Take variable number of symbol arguments and return a function
   * The function return itself takes a map from symbol to any and return
   * option[any]
   * @param path Symbol*
   * @return (Map[Symbol, Any]) => Option[Any]
   */
  def selector(path: Symbol*): (Map[Symbol, Any]) => Option[Any] = {
    if (path.size <= 0) None

    @tailrec
    def selectorHelper(path: Seq[Symbol], ds: Map[Symbol, Any]): Option[Any] =
      if (path.size == 1) {
        ds.get(path(0))
      } else {
        val currentPiece = ds.get(path.head)

        currentPiece match {
          case Some(currentMap: Map[Symbol, Any]) =>
            selectorHelper(path.tail, currentMap)
          case None => None
          case _ => None
        }
      }

    (ds: Map[Symbol, Any]) => selectorHelper(path.toSeq, ds)
  }

  /**
   * ConcatX
   * Append x to the string
   * @return String => String
   */
  def concatX: String => String = (s: String) => s + "x"

  /**
   * ConcatY
   * Append Y to the string
   * @return String => String
   */
  def concatY: String => String = (s: String) => s + "y"

  /**
   * ConcatZ
   * Append z to the string
   * @return String => String
   */
  def concatZ: String => String = (s: String) => s + "z"

  /**
   * Sum1
   * Sum two parameters together
   * @param x Int
   * @param y Int
   * @return Int
   */
  def sum1(x: Int, y: Int): Int = x + y

  /**
   * Sum2 currying
   * Sum two parameters together curried
   * @param x Int
   * @param y Int
   * @return Int
   */
  def sum2(x: Int)(y: Int): Int = x + y

}
