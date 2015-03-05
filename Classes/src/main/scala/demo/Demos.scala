package demo

import classes.{Car, Motorcycle, Truck}
import io.IO

/**
 * Created by kasonchan on 3/4/15.
 */
object Demos extends IO {

  def main(args: Array[String]) {

    val car = new Car(2, "Toyota", "Camry")
    val t = new Truck(2, "Ford", "Aries")
    val mo = new Motorcycle(0, "Yamaha", "Beta", 3)

    echo(car)

    echo(t)

    echo(mo)
  }
}
