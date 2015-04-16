package strategy

/**
 * Created by kasonchan on 4/16/15.
 */
trait SPerson {

  case class SPerson(firstname: Option[String],
                     middlename: Option[String],
                     lastname: Option[String],
                     sex: Option[Char])

  def isSexValid(person: SPerson): Boolean = person.sex.isDefined

  def isFullnameValid(person: SPerson): Boolean = person match {
    case SPerson(f, m, l, s) => f.isDefined && m.isDefined && l.isDefined
  }

  def noMiddlename(person: SPerson): Boolean = person match {
    case SPerson(f, m, l, s) => !m.isDefined
  }

  def personCollector(isValid: (SPerson) => Boolean): (SPerson) => Vector[SPerson] = {
    var validPeople = Vector[SPerson]()
    (person: SPerson) => {
      if (isValid(person)) validPeople = validPeople :+ person
      validPeople
    }
  }
}
