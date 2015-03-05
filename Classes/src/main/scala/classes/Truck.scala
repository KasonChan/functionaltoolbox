package classes

/**
 * Created by kasonchan on 3/5/15.
 */
class Truck(val doorNum: Int, val make: String, val modelMade: String) extends Vehicle {

  override val doors = doorNum
  override val made = make
  override val model = modelMade

  override def toString = "Truck: " + doors + " " + made + " " + model + " " + wheels
}
