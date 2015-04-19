package visitor

/**
 * Created by kasonchan on 4/18/15.
 */
trait Visitor {
  trait VPerson {
    def fullname: String
    def firstname: String
    def lastname: String
    def houseNum: Int
    def street: String
  }

  // Use mix-in inheritance and trait
  class SimplePerson(val firstname: String, val lastname: String,
                     val houseNum: Int, val street: String) extends VPerson {
    def fullname = firstname + " " + lastname
  }

  // Use implicit conversion
  implicit class ExtendedPerson(person: VPerson) {
    def fullAddress = person.houseNum + " " + person.street
  }
}
