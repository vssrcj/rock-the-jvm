package lectures.part1oop

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  val jim = new Person("Jim", 24)
  // 1. class params are fields
  println(jim.name)

  // 2. sensible toString
  println(jim)

  // 3. equals
  val jim2 = new Person("Jim", 24)
  println(jim == jim2)

  // 4. handy copy
  val jim3 = jim2.copy(age = 45)
  println(jim3)

  // 5. have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. Serializable
  // Akka

  // 7. Css have extractor patterns.

  case object UnitedKingdom {
    def name: String = "The UK of GB and ..."
  }
}
