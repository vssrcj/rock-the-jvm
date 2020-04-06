package lectures.part1oop

object AbstractDataTypes extends App {
  abstract class Animal {
    val creatureType: String
    val eat: Unit = println("nomnom")
  }

  class Dog extends Animal {
    val creatureType: String = "canine"
    override val eat: Unit = println("crunch crunch")
  }

  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "meat"
  }
  trait ColdBlooded {

  }

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    override val eat: Unit = "nononon"

    override def eat(animal: Animal): Unit = println(s"I'm a $creatureType and I'm eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc eat dog

  // traits vs abstract classes
  // traits do not have constructors
  // classes may inherit multiple traits
  // traits = behaviour / abstract class = type
}
