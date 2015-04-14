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

  def makeCount(register: Register, count: Int): () => Unit = {
    () => {
      echo("This count: " + count)
      register.addCount(count)
    }
  }

  def executeCount(count: () => Unit): Unit = {
    counts = counts :+ count
    count()
  }
}




