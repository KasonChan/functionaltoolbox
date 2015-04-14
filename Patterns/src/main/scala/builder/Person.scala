package builder

/**
 * Created by kasonchan on 4/14/15.
 */
class Person(val firstname: String,
             val middlename: String = "",
             val lastname: String,
             val sex: String)

case class PersonCaseClass(val firstname: String,
                           val middlename: String = "",
                           val lastname: String,
                           val sex: String)