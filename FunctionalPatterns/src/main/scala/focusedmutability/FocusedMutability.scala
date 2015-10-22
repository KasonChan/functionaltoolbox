package focusedmutability

import scala.util.Random

/**
 * Created by kasonchan on 7/20/15.
 */
case class Numbers(num1: Int, num2: Int, num3: Int)

trait FocusedMutability {

  val r = new Random

  def makeNumbers = Numbers(r.nextInt(100), r.nextInt(100), r.nextInt(100))

  def infiniteNumbers: Stream[Numbers] = makeNumbers #:: infiniteNumbers

}
