package classes

/**
 * Created by kasonchan on 3/4/15.
 */
trait Vehicle {

  def doors: Int = 4

  def made: String = "Toyota"

  def model: String = "Camry"

  def wheels: Int = 4

  override def toString = "This is a vehicle."
}
