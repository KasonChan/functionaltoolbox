package visitor

/**
 * Created by kasonchan on 4/18/15.
 */
trait Visitor {

  // Add new methods to an existing type
  trait VPerson {
    def fullname: String

    def firstname: String

    def lastname: String

    def houseNum: Int

    def street: String
  }

  // Mix-in inheritance and trait
  class SimplePerson(val firstname: String, val lastname: String,
                     val houseNum: Int, val street: String) extends VPerson {
    def fullname = firstname + " " + lastname
  }

  // Implicit conversion
  implicit class ExtendedPerson(person: VPerson) {
    def fullAddress = person.houseNum + " " + person.street
  }

  //  Extend a data type with both a new operation and a new implementation
  class VAddress(val houseNum: Int, val street: String)

  class VName(val firstname: String, val lastname: String)

  class VComplexPerson(name: VName, address: VAddress) extends VPerson {
    def fullname = name.firstname + " " + name.lastname

    def firstname = name.firstname

    def lastname = name.lastname

    def houseNum = address.houseNum

    def street = address.street
  }

}
