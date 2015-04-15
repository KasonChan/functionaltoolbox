package iterator

/**
 * Created by kasonchan on 4/15/15.
 */
case class NPerson(name: String, address: Address)

case class Address(zip: Int)

trait NonIterator {
  def generateGreetings(people: Seq[NPerson]): Seq[String] =
    for (NPerson(name, address) <- people if isCloseZip(address.zip))
      yield "Hello, %s, and welcome to Lambda Bar.".format(name)

  def isCloseZip(zipCode: Int) =
    zipCode == 79407 || zipCode == 79408
}
