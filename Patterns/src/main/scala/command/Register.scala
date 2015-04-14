package command

/**
 * Created by kasonchan on 4/13/15.
 */
class LikeRegister(var totalCounts: Int) extends io.IO with Register {
  total = totalCounts
}

trait Register extends io.IO {
  var total: Int = 0
  var counts: Vector[() => Unit] = Vector()

  def addCount(toAdd: Int): Unit = {
    total += toAdd
  }

  def makeLike(register: Register, count: Int): () => Unit = {
    () => {
      echo("Total counts: " + count)
      register.addCount(count)
    }
  }

  def executeLike(count: () => Unit): Unit = {
    counts = counts :+ count
    count()
  }
}




