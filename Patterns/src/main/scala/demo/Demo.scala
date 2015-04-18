package demo

import builder.{Person, PersonCaseClass}
import command.{LikeRegister, Register}
import decorator.Decorator
import iterator.{Address, NPerson, NonIterator}
import strategy.SPerson

/**
 * Created by kasonchan on 4/13/15.
 */
case class Credentials(username: String, password: String)

case class User(firstname: String, middlename: String, lastname: String, credentials: Credentials)

object Demo extends io.IO with Register with NonIterator with SPerson with Decorator {

  /**
   * Replace functional interface with named functions
   */
  /**
   * Complicated sort
   * @param u1 User 1
   * @param u2 User 2
   * @return Boolean true if user 1 is equal to user 2
   */
  def compSort(u1: User, u2: User): Boolean = {
    if (u1.firstname != u2.firstname)
      u1.firstname < u2.firstname
    else if (u1.lastname != u2.lastname)
      u1.lastname < u2.lastname
    else
      u1.credentials.username < u2.credentials.username
  }

  /**
   * Replace state-carrying function interface
   */
  /**
   * Compare firstname
   * @param u1 User 1
   * @param u2 User 2
   * @return Int 0 if they are the same
   */
  def firstnameComparison(u1: User, u2: User): Int =
    u1.firstname.compareTo(u2.firstname)

  /**
   * Compare lastname
   * @param u1 User 1
   * @param u2 User 2
   * @return Int 0 if they are the same
   */
  def lastnameComparison(u1: User, u2: User): Int =
    u1.lastname.compareTo(u2.lastname)

  /**
   * Compare username
   * @param u1 User 1
   * @param u2 User 2
   * @return Int 0 if they are the same
   */
  def usernameComparison(u1: User, u2: User): Int =
    u1.credentials.username.compareTo(u2.credentials.username)

  /**
   * Make comparison
   * @param comparisons 0 to many comparisons argument
   * @return Int 0 if the users are the same
   */
  def makeComparison(comparisons: ((User, User) => Int)*): (User, User) => Int = {
    (u1: User, u2: User) =>
      comparisons.map(cmp => cmp(u1, u2)).find(_ != 0).getOrElse(0)
  }

  /**
   * Replace iterator
   * Iterator is an object that allows us to iterate over all the objects in the sequence
   */
  val isVowel = Set('a', 'e', 'i', 'o', 'u')

  /**
   * Returns the vowels in word
   * @param word String
   * @return
   */
  def vowelInWord(word: String): Set[Char] = word.filter(isVowel).toSet

  /**
   * Returns the product of the sequence
   * @param sequence
   * @return
   */
  def multipleSequence(sequence: Seq[Int]): Int =
    if (sequence.isEmpty) 0 else sequence.reduce((acc, curr) => acc * curr)

  /**
   * Replace template method
   */
  /**
   * Make sum reporter
   * @param sequenceOperation Sequence operation function
   * @param print function to print
   * @return
   */
  def makeSumReporter(sequenceOperation: Seq[Double] => Double, print: Double => Unit) =
    (sums: Seq[Double]) => {
      print(sequenceOperation(sums))
    }

  /**
   * Sum the sequence
   * @param sequence
   * @return
   */
  def sumSequence(sequence: Seq[Double]): Double =
    if (sequence.isEmpty) 0 else sequence.reduce((acc, curr) => acc + curr)

  def echoDouble(d: Double): Unit = println(d)

  /**
   * Encrypt text
   * @param operation String operation function
   * @param print print function
   * @return
   */
  def makeProtection(operation: String => String, print: String => Unit) =
    (texts: String) => {
      print(operation(texts))
    }

  def protect(text: String): String =
    text.map(t => (t.toInt + 3)).toString()

  def echoString(s: String): Unit = println(s)

  /**
   * Replace null object
   */
  def some = Some("foo")

  def none = None

  def map = Map(1 -> "A", 2 -> "B")

  case class APerson(firstname: String = "Peter", lastname: String = "Parker")

  val nullPerson = APerson()

  def fetchAPerson(people: Map[Int, APerson], id: Int) =
    people.getOrElse(id, nullPerson)

  /**
   * Main
   * @param args String array
   */
  def main(args: Array[String]) {
    val u1 = User("Louis", "", "Chan", Credentials("louischan", "12345678"))
    val u2 = User("Bernice", "", "Chan", Credentials("bernicechan", "12345678"))
    val u3 = User("Osborn", "", "Wu", Credentials("osbornwu", "12345678"))
    val u4 = User("A", "A", "A", Credentials("a", "12345678"))
    val u5 = User("Louis", "Bird", "Chan", Credentials("lcc", "12345678"))

    val users = Vector(u1, u2, u3, u4, u5)

    /**
     * Replace functional interface with anonymous functions
     */
    val sortWithFirstname = users.sortWith((u1, u2) => u1.firstname < u2.firstname)
    val sortWithLastname = users.sortWith((u1, u2) => u1.lastname < u2.lastname)
    val sortWithUsername = users.sortWith((u1, u2) => u1.credentials.username < u2.credentials.username)

    //    Print anonymous function result with echo function
    echo(sortWithFirstname)
    echo(sortWithLastname)
    echo(sortWithUsername)

    echo("")

    /**
     * Replace functional interface with named functions
     */
    val sortWithCompSort = users.sortWith(compSort)

    //    Print named function result with echo function
    echo(sortWithCompSort)

    echo("")

    /**
     * Replace state-carrying function interface
     */
    val complicatedComparison = makeComparison(firstnameComparison, lastnameComparison, usernameComparison)

    val compareU1U2 = complicatedComparison(u1, u5)
    echo(compareU1U2)

    echo("")

    /**
     * Replace command
     */
    //    Create new count registers
    val likeRegister1 = new LikeRegister(0)
    val likeRegister2 = new LikeRegister(0)

    //    Create counts
    val firstLike = makeCount(likeRegister1, 1)
    val secondLike = makeCount(likeRegister2, 2)
    val thirdLike = makeCount(likeRegister1, 3)

    //    Execute counts
    executeCount(firstLike)
    executeCount(secondLike)
    executeCount(thirdLike)

    //    Print counts
    echo("likeRegister1: " + likeRegister1.total)
    echo("likeRegister2: " + likeRegister2.total)

    //    Reset total counts
    likeRegister1.total = 0
    likeRegister2.total = 0

    //    Print counts
    echo("likeRegister1: " + likeRegister1.total)
    echo("likeRegister2: " + likeRegister2.total)

    for (count <- counts) {
      count.apply()
    }

    //    Print counts
    echo("likeRegister1: " + likeRegister1.total)
    echo("likeRegister2: " + likeRegister2.total)

    echo("")

    /**
     * Replace builder for immutable object
     */
    val p1 = new Person(firstname = "Peter", lastname = "Wong", sex = "Male")
    val p2 = new Person(firstname = "Evie", middlename = "K.", lastname = "Wu", sex = "Female")
    val p3 = new Person("Michael", "", "Tso", "Male")

    echo(p1)
    echo(p2)
    echo(p3)

    val p4 = PersonCaseClass(firstname = "Kelvin", lastname = "Lei", sex = "Male")
    val p5 = PersonCaseClass("Kelvin", "S.", "Lei", "Male")
    val p6 = PersonCaseClass("Kelvin", "", "Lei", "Male")
    val p7 = PersonCaseClass("Doris", "I.", "Chan", "Female")
    val p8 = PersonCaseClass(firstname = "Kelvin", lastname = "Lei", sex = "Male")

    echo(p4)
    echo(p5)
    echo(p6)
    echo(p7)

    echo("p4 equals p5? " + p4.equals(p5))
    echo("p4 equals p6? " + p4.equals(p6))
    echo("p4 equals p8? " + p4.equals(p8))

    val p9 = p4.copy(middlename = "S.")

    echo(p9)

    echo("p9 equals p5? " + p9.equals(p5))

    //    User of tuples
    val p10 = ("Kason", "Chan")

    echo(p10._1)
    echo(p10._2)

    //    Pattern matching
    p10 match {
      case (firstname, lastname) => {
        echo("Firstname: " + firstname)
        echo("Lastname: " + lastname)
      }
    }

    echo("")

    /**
     * Replace iterator
     * Iterator is an object that allows us to iterate over all the objects in the sequence
     */
    echo(vowelInWord("Kason Chan"))

    // No iterating and no mutation using just higher order function
    echo(multipleSequence(Vector(1, 2, 3, 4, 5)))

    val p11 = NPerson("Kason Chan", Address(79407))
    val p12 = NPerson("Bart Wong", Address(79400))
    val p13 = NPerson("Cat Wu", Address(76201))
    val p14 = NPerson("Desmond Ng", Address(79408))

    val people = Vector(p11, p12, p13, p14)

    //    Monadic transformation
    val generatedGreetings = generateGreetings(people)

    for (g <- generatedGreetings)
      echo(g)

    echo("")

    /**
     * Replace template method
     */
    val doubles = Vector(35.0, 32.5, 76.2, 90.3)

    val r = makeSumReporter(sumSequence, echoDouble)

    r(doubles)

    val string = "This is a testing."

    val s = makeProtection(protect, echoString)

    s(string)

    echo("")

    /**
     * Replace strategy
     */
    val s1 = SPerson(Some("Kason"), None, Some("Chan"), Some('M'))
    val s2 = SPerson(Some("Bernice"), None, Some("Chan"), Some('F'))
    val s3 = SPerson(None, None, None, None)
    val s4 = SPerson(Some("Michael"), Some("W."), Some("Smith"), Some('M'))

    val ss = Vector(s1, s2, s3, s4)

    val noMiddleNameCollector = personCollector(noMiddlename)

    for (s <- ss)
      echo(noMiddleNameCollector(s))

    val fullnameValidCollector = personCollector(isFullnameValid)

    for (s <- ss)
      echo(fullnameValidCollector(s))

    echo("")

    /**
     * Replace null object
     */
    echo(some.getOrElse("default"))
    echo(none.getOrElse("default"))

    echo(map.getOrElse(3, "default"))

    val mary = APerson("Mary", "Jane")
    val jack = APerson("Jack", "Miller")
    val somePeople = Map(1 -> mary, 2 -> jack)

    echo(fetchAPerson(somePeople, 3))

    val aV = Vector("a")
    val bS = Some("b")
    val cS = Some("c")
    val nN = None

    val result = for (b <- bS; n <- nN) yield (b, n)
    echo(result)

    echo("")

    /**
     * Replace decorator
     */
    val loggedAdd = loggingAdd(2, 3)
    echo(loggedAdd)

    echo("")
  }
}
