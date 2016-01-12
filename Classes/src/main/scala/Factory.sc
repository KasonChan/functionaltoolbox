sealed trait Animal {
  def speak: Unit = { println("Hi") }
}

case object Dog extends Animal {
  override def speak: Unit = println("woof")
}

case object Cat extends Animal {
  override def speak: Unit = println("meow")
}

object Animal extends Animal {

  def apply(s: String): Animal = {
    s match {
      case "dog" => Dog
      case "cat" => Cat
    }
  }

}

val dog = Animal("dog")
val cat = Animal("cat")

dog.speak
cat.speak
