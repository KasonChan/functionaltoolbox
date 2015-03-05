package classes

/**
 * Created by kasonchan on 3/4/15.
 */
class Car(override val doors: Int,
          override val made: String,
          override val model: String) extends Vehicle {

  override def toString = "Car: " + doors + " " + made + " " + model + " " + wheels
}
