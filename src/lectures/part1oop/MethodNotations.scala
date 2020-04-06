package lectures.part1oop

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", this.favoriteMovie)
    def unary_! : String = s"Not $name"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation (example of syntactic sugar)

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)

  println(1.+(2)) // ALL OPERATORS ARE METHODS!

  // prefix notation
  val x = -1
  val y = 1.unary_-
  println(y)

  println(!mary)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary)

  println((mary + "the Rock star")())
}
