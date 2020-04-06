package lectures.part1oop

object Inheritance extends App {
  // single class inheritance
  sealed class Animal {
    val creatureType: String = "wild"
    protected def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int)
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String = "domestic") extends Animal {
    override def eat: Unit = {
      super.eat
      println("Crunch, woof")
    }
  }

  // type substitution : polymorphism
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // prevent overrides
  // final on member
  // final on class
  // seal the class - extendable in this file, not in others (used if inherited members is exhaustive
}
