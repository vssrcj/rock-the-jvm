package lectures.part1oop

object Generics extends App {
  class MyList[+A] {
    def add[B >: A](el: B): MyList[B] = ???
  }

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Does list of cat, extends list of animal?

  // 1. Yes
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add() ?? Hard question >> we return a list of animals.

  // 2. No - invariance.
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hello no - contravariance.
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // makes more sense
  class Trainer[-A]
  val trainer: ContravariantList[Cat] = new ContravariantList[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage[Dog](new Dog)


}
