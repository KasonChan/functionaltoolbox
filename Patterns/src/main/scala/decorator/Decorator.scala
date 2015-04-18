package decorator

import demo.Demo._

/**
 * Created by kasonchan on 4/18/15.
 */
trait Decorator {
  def add(a: Int, b: Int) = a + b

  def makeLogger(operation: (Int, Int) => Int) =
    (a: Int, b: Int) => {
      val result = operation(a, b)
      echo("Result is " + result)
      result
    }

  def loggingAdd = makeLogger(add)
}
