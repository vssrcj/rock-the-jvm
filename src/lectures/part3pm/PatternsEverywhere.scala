package lectures.part3pm

object PatternsEverywhere extends App {
  try {

  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually matches
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  // generators are also based on PM
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  println(filterTuples)

  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  val (d, _, _) = tuple

  val head :: tail = list
  println(head)
  println(tail)

  // partial function literal
  val mappedList = list.map {
    case v if v % 2 == 0 => s"$v is even"
    case 1 => "the one"
    case _ => "something else"
  }
  val mappedList2 = list.map { x => x match {
    case v if v % 2 == 0 => s"$v is even"
    case 1 => "the one"
    case _ => "something else"
  }}
  println(mappedList)
}
