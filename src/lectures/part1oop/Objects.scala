package lectures.part1oop

object Objects {
  // Scala does not have class-level functionality ("static")

  // scala object = singleton instance
  object Person {
    // "static" functionality
    val N_EYES = 2
    def canFly: Boolean = false
    // factory method
    // def from(mother: Person, father: Person): Person = new Person("Bobby")
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  class Person(val name: String) {
    // "instance" functionality
  }

  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)

    val mary = new Person("Mary")
    val john = new Person("John")
    val bobby = Person(mary, john)

    println(bobby.name)
  }
}
