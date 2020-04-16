package lectures.part3pm

import scala.util.Random

object PatternMatching extends App {
  val random = new Random()
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "the double"
    case 3 => "third time is the charm"
    case _ => "something else"
  }

  println(x)
  println(description)

  // Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    // with guard
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }

  println(greeting)

  /*
    1. cases are matched in order
    2. if no match => error
    3. match will unify type
    4. PM works really well with case classes
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of a $someBreed dog")
  }

  // match everything (DON't do THIS)
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  // WHY?
  val isEven2 = if (x % 2== 0) true else false
  val isEvenNormal = x % 2 == 0

  /*
    Exercises
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(el: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def maybeShowParentheses(e: Expr) = e match {
        case Prod(_, _) => show(e)
        case Number(_) => show(e)
        case _ => s"(${show(e)})"
      }
      s"${maybeShowParentheses(e1)} * ${maybeShowParentheses(e2)}"
    }
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
}
