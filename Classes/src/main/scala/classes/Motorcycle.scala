package classes

/**
 * Created by kasonchan on 3/5/15.
 */
class Motorcycle(override val doors: Int,
                 override val made: String,
                 override val model: String,
                 override val wheels: Int) extends Vehicle {

  override def toString = "This is a motorcycle. It is made by " + made +
    " and the model is  " + model + ". It has " + wheels + " wheels and " +
    doors + " doors"
}
